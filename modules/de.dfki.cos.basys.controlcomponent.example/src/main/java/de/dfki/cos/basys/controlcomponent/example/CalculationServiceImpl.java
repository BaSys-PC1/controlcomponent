package de.dfki.cos.basys.controlcomponent.example;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ServiceProvider;

public class CalculationServiceImpl implements CalculationService, ServiceProvider<CalculationService> {

	@Override
	public boolean connect(ComponentContext context, String connectionString) {
		return true;
	}

	@Override
	public void disconnect() {

	}

	@Override
	public boolean isConnected() {		
		return true;
	}

	@Override
	public double calculateHypothenuseLength(double a, double b) {		
		return Math.sqrt(a*a + b*b);
	}

	@Override
	public long calculateFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1l;
		} else {
			return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
		}

	}

	@Override
	public CalculationService getService() {
		return this;
	}

}
