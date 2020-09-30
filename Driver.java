/*
 * Student Name: George Bursuc
 * Student Number: C18399946
 * Course Code: DT354/TU914
 * Class Name: Driver.java
 */

public class Driver {
	private int licenceNumber;
	private String name;
	private int points;
	private String contactNo;
	private boolean inAPolicy;
	
	public Driver(int licNum, String name, int p, String contNo) {
		this.licenceNumber = licNum;
		this.name = name;
		this.points = p;
		this.contactNo = contNo;
		this.inAPolicy = false;
	}
	
	public int getLiceNum() {
		return this.licenceNumber;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void togglePolicy() {
		this.inAPolicy = !this.inAPolicy;
	}
	
	public boolean isInAPolicy() {
		return this.inAPolicy;
	}
}
