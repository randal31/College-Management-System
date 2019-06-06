package model;

import utils.FacultyFactory;

public class ChargeBag {
	private FMSS [] arr;
	private int nElems;
	
	public ChargeBag(int maxSize) {
		arr = new FMSS[maxSize];
		nElems = 0;
	}
	
	public void insert(FMSS bill) {
		arr[nElems++] = bill;
	}
	
	public double ChargeTotalAmount() {
		double billTotal = 0.0;
		
		for (int i = 0; i < nElems; i++) {
			if (arr[i] instanceof Student || arr[i] instanceof Faculty) {
				if (arr[i] instanceof Student) {
					billTotal = arr[i].chargeTotal(billTotal, arr[i].chargeActivity());
					if (((Student) arr[i]).hasMajor()) {
						billTotal = arr[i].chargeTotal(billTotal, arr[i].chargeTech());
					}
				}
				else {
					billTotal = arr[i].chargeTotal(billTotal, arr[i].chargeOffice(FacultyFactory.getSalary()));
				}
				billTotal = arr[i].chargeTotal(billTotal, arr[i].chargeFood());
				billTotal = arr[i].chargeTotal(billTotal, arr[i].chargeParking());
			}
		}
		return billTotal;
	}
}
