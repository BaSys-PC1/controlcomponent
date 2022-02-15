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
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

@Configuration
public class ControlComponentConfig {

    @Value("${basys.controlcomponent.id}")
    private String id;

    @Value("${basys.controlcomponent.name}")
    private String name;

    @Value("${basys.controlcomponent.category:CONTROL_COMPONENT}")
    private String category;

    @Value("${basys.controlcomponent.register:false}")
    private boolean register;

    @Value("${basys.controlcomponent.executionMode:SIMULATE}")
    private String executionMode;

    @Value("${basys.controlcomponent.disableExecutionModeChange:false}")
    private boolean disableExecutionModeChange;

    @Value("${basys.controlcomponent.disableOccupationCheck:false}")
    private boolean disableOccupationCheck;

    @Value("${basys.controlcomponent.disableServiceMock:false}")
    private boolean disableServiceMock;

    @Value("${basys.controlcomponent.serviceImplementationJavaClass}")
    private String serviceImplementationJavaClass;

    @Value("${basys.controlcomponent.serviceConnectionString}")
    private String serviceConnectionString;

    @Value("${basys.controlcomponent.implementationJavaClass:de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent}")
    private String implementationJavaClass;

    @Value("${basys.controlcomponent.operationModeJavaPackage:de.dfki.cos.basys}")
    private String operationModeJavaPackage;


//    @Bean
//    @Profile({"AUTO"})
//    public ServiceProvider getServiceAUTO() {
//        return new DroneServiceImplMqtt();
//    }
//
//    @Bean
//    @Profile({"SIMULATE"})
//    public ServiceProvider getServiceSIMULATE() {
//        return null;
//    }

    @Autowired
    private ComponentContext context;

    @Bean
    public ServiceProvider ccService() {
        Properties config = new Properties();
        config.setProperty("serviceImplementationJavaClass", serviceImplementationJavaClass);

        ServiceManager serviceManager = new ServiceManagerImpl(config);
        return serviceManager.getServiceProvider();
    }

    @Bean
    public ControlComponent controlComponent(ServiceProvider serviceProvider) throws ComponentException, ClassNotFoundException {
        Properties config = new Properties();
        config.setProperty("id", id);
        config.setProperty("name", name);
        config.setProperty("category", category);
        config.setProperty("register", register+"");
        config.setProperty("executionMode", executionMode);
        config.setProperty("disableExecutionModeChange", disableExecutionModeChange+"");
        config.setProperty("disableOccupationCheck", disableOccupationCheck+"");
        config.setProperty("disableServiceMock", disableServiceMock+"");
        config.setProperty("serviceConnectionString", serviceConnectionString);

        BaseControlComponent cc = new BaseControlComponent(config, serviceProvider);

        try {
            Class<?> ccClass = Class.forName(implementationJavaClass);
            Constructor<BaseControlComponent> ccConstructor = null;

            ccConstructor = (Constructor<BaseControlComponent>) ccClass.getConstructor(Properties.class, ServiceProvider.class);
            cc = ccConstructor.newInstance(config,ccService());

        } catch (NoSuchMethodException | ClassNotFoundException| InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
            cc = new BaseControlComponent(config, ccService());
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

}
