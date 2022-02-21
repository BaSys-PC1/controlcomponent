package de.dfki.cos.basys.controlcomponent.spring.configuration;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ServiceManager;
import de.dfki.cos.basys.common.component.ServiceProvider;
import de.dfki.cos.basys.common.component.impl.ServiceManagerImpl;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.StreamSupport;

@Configuration
public class ControlComponentConfig {

    @Value("${basys.controlcomponent.id}")
    private String id;

    @Value("${basys.controlcomponent.name}")
    private String name;

    @Value("${basys.controlcomponent.executionMode:SIMULATE}")
    private String executionMode;

    @Value("${basys.controlcomponent.implementationJavaClass:de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent}")
    private String implementationJavaClass;

    @Value("${basys.controlcomponent.operationModeJavaPackage:de.dfki.cos.basys}")
    private String operationModeJavaPackage;

    @Autowired
    private ComponentContext context;

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "deactivate")
    public ControlComponent controlComponent() throws ComponentException, ClassNotFoundException {
        Properties config = new Properties();
        config.setProperty("id", id);
        config.setProperty("name", name);
        config.setProperty("category", "CONTROL_COMPONENT"); //legacy
        config.setProperty("register", "false");             //legacy
        config.setProperty("executionMode", executionMode.toUpperCase());

        config.setProperty("disableExecutionModeChange", env.getProperty("basys.controlcomponent." + executionMode.toLowerCase() + ".disableExecutionModeChange","true"));
        config.setProperty("disableOccupationCheck", env.getProperty("basys.controlcomponent." + executionMode.toLowerCase() + ".disableOccupationCheck","false"));

        String connectionString = env.getProperty("basys.controlcomponent." + executionMode.toLowerCase() + ".service.connectionString");
        if (connectionString != null) {
            config.setProperty("serviceConnectionString", connectionString);
        }

        if ("simulate".equalsIgnoreCase(executionMode)) {
            String serviceImplementationJavaClass = env.getProperty("basys.controlcomponent.simulate.service.implementationJavaClass");
            if (serviceImplementationJavaClass == null) {
                config.setProperty("disableServiceMock", false + "");
            } else {
                config.setProperty("disableServiceMock", true + "");
                config.setProperty("serviceImplementationJavaClass", serviceImplementationJavaClass);
            }
        } else {
            config.setProperty("serviceImplementationJavaClass", env.getProperty("basys.controlcomponent." + executionMode.toLowerCase() + ".service.implementationJavaClass"));
        }


        Properties serviceConfig = collectAndTrimProperties("basys.controlcomponent." + executionMode.toLowerCase() + ".service."); //last . is important for cutting away the prefix
        serviceConfig.remove("implementationJavaClass");
        serviceConfig.remove("connectionString");
        config.putAll(serviceConfig);

        //ServiceManager serviceManager = new ServiceManagerImpl(config);
        BaseControlComponent cc = null; //new BaseControlComponent(config, serviceManager.getServiceProvider());

        try {
            Class<?> ccClass = Class.forName(implementationJavaClass);
            Constructor<BaseControlComponent> ccConstructor =
                    (Constructor<BaseControlComponent>) ccClass.getConstructor(Properties.class);
            cc = ccConstructor.newInstance(config);

        } catch (NoSuchMethodException | ClassNotFoundException| InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
            cc = new BaseControlComponent(config);
        }

        ClassPathScanningCandidateComponentProvider scanner = createComponentScanner();
        for (BeanDefinition bd : scanner.findCandidateComponents(operationModeJavaPackage)) {
//            printMetadata(bd);
            Class<?> c = Class.forName(bd.getBeanClassName());
            de.dfki.cos.basys.controlcomponent.OperationMode operationMode = null;
            Constructor<OperationMode> constructor = null;

            try {
                try {
                    constructor = (Constructor<OperationMode>) c.getConstructor(BaseControlComponent.class);
                    operationMode = constructor.newInstance(cc);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (operationMode != null) {
                cc.registerOperationMode(operationMode);
            }
        }

        cc.activate(context);
        return cc;
    }

//    public void findAnnotatedClasses(String scanPackage) {
//        ClassPathScanningCandidateComponentProvider provider = createComponentScanner();
//        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) {
//            printMetadata(beanDef);
//        }
//    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner() {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(de.dfki.cos.basys.controlcomponent.annotation.OperationMode.class));
        return provider;
    }

//    private void printMetadata(BeanDefinition beanDef) {
//        try {
//            Class<?> cl = Class.forName(beanDef.getBeanClassName());
//            de.dfki.cos.basys.controlcomponent.annotation.OperationMode operationModeAnnotation = cl.getAnnotation(de.dfki.cos.basys.controlcomponent.annotation.OperationMode.class);
//            System.out.printf("Found class: %s, with meta name: %s%n",
//                    cl.getSimpleName(), operationModeAnnotation.name());
//        } catch (Exception e) {
//            System.err.println("Got exception: " + e.getMessage());
//        }
//    }
    public Properties collectAndTrimProperties(String prefix) {
        Properties result = new Properties();

        MutablePropertySources propSrcs = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::<String>stream)
                .filter(propName -> propName.startsWith(prefix))
                .forEach(propName -> result.setProperty(propName.substring(prefix.length()), env.getProperty(propName)));

        return result;
    }
}
