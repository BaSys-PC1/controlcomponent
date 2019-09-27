package de.dfki.cos.basys.controlcomponent.server.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ExecutionState;

public class ControlComponentInfoRecorder {

	private LinkedBlockingQueue<ControlComponentInfo> infos = new LinkedBlockingQueue<>();

	public ControlComponentInfoRecorder() {
		ComponentContext.getStaticContext().getEventBus().register(this);
	}
	
	public ControlComponentInfoRecorder(ComponentContext context) {
		context.getEventBus().register(this);
	}
	
	@Subscribe
	private void notifyComponentInfo(ControlComponentInfo info) {
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
	}
	
	public ControlComponentInfo getLastInfo() {
		try {
			return infos.poll(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ControlComponentInfo waitForExecutionState(ExecutionState state, int millis) {
		ControlComponentInfo info = null;		
		while (true) {
			try {
				info = infos.poll(millis, TimeUnit.MILLISECONDS);
				if (info.getExecutionState() == state) {					
					return info;
				}
			} catch (InterruptedException e) {
				return null;
			}			
		}
	}
	
}
