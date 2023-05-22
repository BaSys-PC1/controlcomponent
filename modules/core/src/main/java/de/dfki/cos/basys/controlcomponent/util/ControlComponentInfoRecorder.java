package de.dfki.cos.basys.controlcomponent.util;

import com.google.common.eventbus.Subscribe;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ControlComponentInfoRecorder {
	private final Logger LOGGER = LoggerFactory.getLogger("Rec");


	private LinkedBlockingQueue<ControlComponentInfo> infos = new LinkedBlockingQueue<>();

	public ControlComponentInfoRecorder() {
		ComponentContext.getStaticContext().getEventBus().register(this);
	}
	
	public ControlComponentInfoRecorder(ComponentContext context) {
		context.getEventBus().register(this);
	}

	public void close() {
		ComponentContext.getStaticContext().getEventBus().unregister(this);
	}

	@Subscribe
	private void notifyComponentInfo(ControlComponentInfo info) {
		LOGGER.debug("++ " + info);
		infos.add(info);
	}

	public LinkedBlockingQueue<ControlComponentInfo> getInfos() {
		return infos;
	}

	public boolean isEmpty() {
		return infos.isEmpty();				
	}
	
	public void clear() {
		infos.clear();
		LOGGER.debug("CLEARED " + infos.size());
	}
	
	public ControlComponentInfo getLastInfo() {
		try {
			ControlComponentInfo info = infos.poll(20, TimeUnit.SECONDS);
			LOGGER.debug("-- " + info);
			return info;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ControlComponentInfo waitForExecutionState(ExecutionState state) {
		ControlComponentInfo info = null;		
		while (true) {
			try {
				info = infos.poll(20, TimeUnit.SECONDS);
				if (info.getExecutionState() == state) {					
					return info;
				}
			} catch (InterruptedException e) {
				return null;
			}			
		}
	}
	
}
