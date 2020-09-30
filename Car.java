/*
 * Student Name: George Bursuc
 * Student Number: C18399946
 * Course Code: DT354/TU914
 * Class Name: Car.java
 */

public class Car {
	private String regNo;
	private int yearOfManufacture;
	private double engineSize;
	private boolean passedNCT;
	private boolean inAPolicy;
	
	public Car(String regNo, int mY, double eS, boolean nct) {
		this.regNo = regNo;
		this.yearOfManufacture = mY;
		this.engineSize = eS;
		this.passedNCT = nct;
		this.inAPolicy = false;
	}
	
	public String getRegNo() {
		return this.regNo;
	}
	
	public boolean hasPassedNCT() {
		return this.passedNCT;
	}
	
	public void togglePolicy() {
		this.inAPolicy = !this.inAPolicy;
	}
	
	public boolean isInAPolicy() {
		return this.inAPolicy;
	}
}
