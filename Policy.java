/*
 * Student Name: George Bursuc
 * Student Number: C18399946
 * Course Code: DT354/TU914
 * Class Name: Policy.java
 */

public class Policy {
	private Car car;
	private Driver driver;
	private int policyID;
	private double premium;
	private int numOfClaims;
	
	public Policy(Car car, Driver dr, int pID, double prem, int claims) {
		this.car = car;
		this.driver = dr;
		this.policyID = pID;
		this.premium = prem;
		this.numOfClaims = claims;
		
		// toggle the inAPolicy booleans to indicate this car and driver are in a policy
		car.togglePolicy();
		driver.togglePolicy();
	}

	public int getPoints() {
		return this.driver.getPoints();
	}
	
	public boolean hasPassedNCT() {
		return this.car.hasPassedNCT();
	}
	
	public int getPolicyID() {
		return this.policyID;
	}
	
	public void increasePremiumBy5() {
		this.premium *= 1.05;
	}

	public int getNumOfClaims() {
		return this.numOfClaims;
	}
	
	public double getPremium() {
		return this.premium;
	}
}
