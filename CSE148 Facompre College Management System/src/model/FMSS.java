package model;

public interface FMSS {
	
	double chargeFood();
	
	default double chargeParking() {
		return 20.00;
	}
	
	default double chargeTech() {
		return 5.00;
	}
	
	default double chargeActivity() {
		return 50.00;
	}
	
	default double chargeOffice(double salary) {
		final double CHARGE_PERCENT = 1;
		final double SALARY_CHANGE = salary / 100.0 * CHARGE_PERCENT;
		return SALARY_CHANGE;
	}
	
	double chargeTotal(double value, double add);
}