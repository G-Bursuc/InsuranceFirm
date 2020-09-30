/*
 * Student Name: George Bursuc
 * Student Number: C18399946
 * Course Code: DT354/TU914
 * Class Name: InsuranceFirm.java
 */

import java.util.ArrayList; // access to the arraylist
import java.util.Scanner; // access to the scanner for user inputs
import java.text.DecimalFormat; // access to decimal formatting

public class InsuranceFirm {
	// create global variables
	ArrayList<Car> carList; // store the cars into a list
	ArrayList<Driver> driverList; // store the drivers into a list
	ArrayList<Policy> policyList; // store the policies into a list
	Scanner scan; // create scanner variable
	int numberOfPolicies; // stores the number of the user wants to create

	public InsuranceFirm() {
		// setup all of the lists
		carList = new ArrayList<Car>();
		driverList = new ArrayList<Driver>();
		policyList = new ArrayList<Policy>();

		scan = new Scanner(System.in); // setup scanner

		addItemsToLists();
		//addHardCodedItemsToLists(); // method uses hard coded items instead of getting user input
		deleteAPolicy();
		// if user only input 1 policy, they are forced to delete it, don't continue on with program
		if (numberOfPolicies > 0) {
			increasePremiums();
			displayTotalClaims();
			displayTotalPremiums();
		}
		else
			System.out.println("\nNo more policies exist in the system.");
	}

	// allows user to input car, driver and policy details
	public void addItemsToLists() {
		// call methods to let user create cars, driver and policies
		inputAndAddCars();
		inputAndAddDrivers();
		inputAndAddPolicies();
	}

	// takes in user input for car details and adds them to the list, uses limit to create number of items user requested
	public void inputAndAddCars() {
		int limit; // stores how many cars the user wants to create

		// placeholder variables
		String regNo;
		boolean validReg; // determines if registration is unique
		int yearOfM;
		double engineSize;
		boolean passedNCT;
		String passedNCTAns; // stores users answer for passedNCT boolean

		// let user input how many cars to create
		System.out.println("Please enter the number of cars you would like to add:");
		limit = getNumberOfItems(); // method ensures value will be positive

		System.out.println("Please enter details for cars...");
		// iterates the number of times the user requested, takes in user input of all details
		for (int x = 0; x < limit; x++) {
			System.out.println("\nDetails for car #" + (x + 1) + ":"); // keeps track of which car is being added

			// ensure no duplicate registration numbers are input
			do {
				validReg = true; // assume its not a dupe

				// user input
				System.out.println("Enter the registration number:");
				regNo = scan.nextLine();

				// if this method returns false then it is a duplicate, let user try again
				if (!validateRegNo(regNo))
					validReg = false;
					
			} while(!validReg);
			
			System.out.println("Enter the year the car was manufactured:");
			yearOfM = scan.nextInt();
			scan.nextLine();

			System.out.println("Enter the engine size of the car (in litres):");
			engineSize = scan.nextDouble();
			scan.nextLine();

			// user needs to enter a valid answer: yes or no
			System.out.println("Has the car passed the NCT? (yes/no):");
			do {
				passedNCTAns = scan.nextLine();

				if (!passedNCTAns.equalsIgnoreCase("yes") && !passedNCTAns.equalsIgnoreCase("no"))
					System.out.println("\nERROR - Invalid input, please enter yes or no.");

			} while (!passedNCTAns.equalsIgnoreCase("yes") && !passedNCTAns.equalsIgnoreCase("no"));

			// assign the boolean variable based on verified user answer
			if (passedNCTAns.equalsIgnoreCase("yes"))
				passedNCT = true;
			else
				passedNCT = false;

			// add the car to the car list and display confirmation message
			carList.add(new Car(regNo, yearOfM, engineSize, passedNCT));
			System.out.println("\nCar was added successfully.");
		}
	}

	// ensures that the number input is a positive
	public int getNumberOfItems() {
		int num; // placeholder

		do { // keep user trapped until they enter a positive number
			num = scan.nextInt();
			scan.nextLine();

			if (num < 1)
				System.out.println("ERROR - Please enter a positive number.");
		} while (num < 1);

		return num;
	}

	// takes in a registration number and checks against other cars in the car list. Returns true if it is not a duplicate, returns false if a duplicate
	public boolean validateRegNo(String regNo) {
		for(Car car : carList) {
			if (car.getRegNo().equalsIgnoreCase(regNo)) {
				System.out.println("ERROR - This registration number already exists in the system.");
				return false;
			}
		}
		return true;
	}

	// takes in user input for driver details and adds them to the list, uses limit to create number of items user requested
	public void inputAndAddDrivers() {
		int limit; // stores how many drivers the user wants to create

		// placeholder variables
		int licenceNum;
		boolean validLicence; // determines if licence number is a dupe or not
		String name;
		int points;
		String contactNo;

		System.out.println("Please enter the number of drivers you would like to add:");
		limit = getNumberOfItems();

		System.out.println("Please enter details for drivers...");
		// iterates the number of times the user requested, takes in user input of all details
		for (int x = 0; x < limit; x++) {
			System.out.println("\nDetails for driver #" + (x + 1) + ":");

			// ensure no duplicate drivers are input
			do {
				validLicence = true; // assume it isn't a dupe

				// user input
				System.out.println("Enter the driver's licence number:");
				licenceNum = scan.nextInt();
				scan.nextLine();

				if (!validateLicenceNum(licenceNum)) // if method returns false then a duplicate number has been input, user tries again
					validLicence = false;

			} while (!validLicence);

			System.out.println("Enter the driver's name:");
			name = scan.nextLine();

			System.out.println("Enter the number of points on the driver's licence:");
			points = scan.nextInt();
			scan.nextLine();

			System.out.println("Enter the driver's contact number:");
			contactNo = scan.nextLine();

			// add the driver to the driver list and display confirmation message
			driverList.add(new Driver(licenceNum, name, points, contactNo));
			System.out.println("\nDriver was added successfully.");
		}
	}

	// takes in a licence number and and checks against other drivers in the driver list. Returns true if it is not a duplicate, returns false if a duplicate
	public boolean validateLicenceNum(int licenceNum) {
		for (Driver driver : driverList) {
			if (driver.getLiceNum() == licenceNum) {
				System.out.println("ERROR - This licence number already exists in the system.");
				return false;
			}
		}
		return true;
	}

	// takes in user input for policy details and adds them to the list, uses limit to create number of items user requested
	public void inputAndAddPolicies() {
		int limit; // stores how many policies the user wants to create

		// placeholder variables
		Car sampleCar;
		Driver sampleDriver;
		boolean validChoice; // stores if user made a valid choice
		String carReg;
		int driverLice;
		int policyID;
		double premium;
		int numOfClaims;

		// let user decide how many policies to input
		System.out.println("Please enter the number of policies you would like to add:");
		limit = getNumberOfItems();
		numberOfPolicies = limit; // assign global variable number of policies user will create
		
		System.out.println("Please enter details for policies...");
		for (int x = 0; x < limit; x++) {
			System.out.println("\nDetails for policy # " + (x + 1) + ":");

			// CAR INPUT
			listAllCarRegs(); // list the car regs to make it easier for user to pick a correct one
			do { // keep user trapped until they enter an existing car reg
				validChoice = false; // assume its invalid

				System.out.println("Please input the registration of the car you would like to add to this policy:");
				carReg = scan.nextLine();

				sampleCar = findValidCar(carReg); // check if the car exists (returns car object) or if it exists but isn't valid or doesn't exist (both return null)

				if (sampleCar != null) // if the car isn't null then it is valid
					validChoice = true;

			} while(!validChoice);

			// DRIVER INPUT
			listAllDrivers();
			do { // keep user trapped until they enter an existing licence number
				validChoice = false; // assume its invalid

				System.out.println("Please input the licence number of the driver you would like to add to this policy:");
				driverLice = scan.nextInt();
				scan.nextLine();

				sampleDriver = findValidDriver(driverLice); // check if the driver exists (returns driver object) or if it exists but isn't valid or doesn't exist (both return null)

				if (sampleDriver != null) // if the driver isn't null then it is valid
					validChoice = true;

			} while(!validChoice);
			
			// POLICY DETAILS
			// ensure no duplicate policy ID's are input
			do {
				validChoice = true; // assume it isn't a dupe

				System.out.println("Enter the policy ID:");
				policyID = scan.nextInt();
				scan.nextLine();

				if (!validateLicenceNum(policyID))
					validChoice = false;
					
			} while (!validChoice);

			System.out.println("Enter the premium amount:");
			premium = scan.nextDouble();
			scan.nextLine();

			System.out.println("Enter the number of claims for this policy:");
			numOfClaims = scan.nextInt();
			scan.nextLine();
			
			// add the driver to the driver list and display confirmation message
			policyList.add(new Policy(sampleCar, sampleDriver, policyID, premium, numOfClaims));
			System.out.println("\nPolicy was added successfully.");
		}
	}

	// tries to return a car object that matches the car reg, returns null if not found
	public Car findValidCar(String carReg) {
		// look for matching car registration
		for (Car car : carList) {
			if (car.getRegNo().equalsIgnoreCase(carReg)) {
				if (car.hasPassedNCT()) // if the car has been found, ensure it has passed the NCT
					return car; // return the car object if car is valid for a policy
				else { // else the car is not valid for a policy
					System.out.println("ERROR - Car has been found however it does not meet NCT standards and therefore cannot be added to our policies.");
					return null;
				}
			}
		}
		System.out.println("ERROR - Car was not found. Please enter a car that exists in the system.");
		return null;
	}

	// displays all of the car registrations in the car list
	public void listAllCarRegs() {
		System.out.println("\nThe list of all the car registrations in the system:");
		for (Car car : carList) {
			if (!car.isInAPolicy()) // doesn't display a car if it is already in a policy
				System.out.println(car.getRegNo());
		}
	}

	// displays all of the driver numbers in the car list
	public void listAllDrivers() {
		System.out.println("\nThe list of all the driver licence numbers in the system:");
		for (Driver driver : driverList) {
			if (!driver.isInAPolicy()) // doesn't display a driver if it is already in a policy
				System.out.println(driver.getLiceNum());
		}
	}

	// tries to return a driver object that matches the licence number, returns null if not found
	public Driver findValidDriver(int liceNum) {
		// look for matching licence number
		for (Driver driver : driverList) {
			if (driver.getLiceNum() == liceNum) {
				if (driver.getPoints() < 12) // if the driver has been found, ensure they have less than 12 points
					return driver; // return the driver object if car is valid for a policy
				else { // else the driver is not valid for a policy
					System.out.print("ERROR - Driver has been found however they exceed the 12 point limit on their licence and therefore cannot be added to our policy. Please choose another driver to add to a policy.");
					return null;
				}
			}
		}
		System.out.println("ERROR - Driver was not found. Please enter a driver that exists in the system.");
		return null;
	}

	// takes in a policy ID and and checks against other policies in the list. Returns true if it is not a duplicate, returns false if a duplicate
	public boolean validatePolicyID(int iD) {
		for(Policy policy : policyList) {
			if (policy.getPolicyID() == iD) {
				System.out.println("ERROR - This policy ID already exists in the system.");
				return false;
			}
		}
		return true;
	}
	
	// takes in a hard coded in policy and adds it to the policy list if the policy meets the requirements
	public void verifyAndAddHardCodedValidPolicy(Policy policy) {
		if (policy.getPoints() < 12)
			if (policy.hasPassedNCT())
				policyList.add(policy);
			else // policy is not added because driver did not meet requirements
				System.out.println("WARNING - Policy with ID" + policy.getPolicyID() + " was not added to the system.\nReason: Driver exceeds limit of 12 points on their licence.");
		else // policy is not added because car did not meet requirements
			System.out.println("WARNING - Policy with ID" + policy.getPolicyID() + " was not added to the system.\nReason: Car did not pass its NCT.");
	}

	// adds sample cars, drivers and policies to their respective lists
	public void addHardCodedItemsToLists() {
		// create and add 3 cars to the car list
		carList.add(new Car("12-D-48134", 2012, 1.4, true));
		carList.add(new Car("04-WW-7108", 2004, 1, false));
		carList.add(new Car("20-D-1", 2020, 2.4, true));
		carList.add(new Car("16-G-819301", 2016, 1.6, true));
		carList.add(new Car("15-C-19325", 2015, 1.1, true));
		carList.add(new Car("13-G-29831", 2013, 1.2, true));

		// create and add 3 drivers to the driver list
		driverList.add(new Driver(3818340, "Joe Bloggs", 4, "0898619347"));
		driverList.add(new Driver(4018371, "Mark Phelan", 2, "0873928190"));
		driverList.add(new Driver(4810376, "Laura Kennedy", 14, "0897209137"));
		driverList.add(new Driver(9148305, "Jason Murphy", 8, "0861094372"));
		driverList.add(new Driver(193277, "Niamh Jones", 2, "0853498711"));
		driverList.add(new Driver(371905, "Chloe O'Conner", 0, "089302629"));

		// create a policy, verify it and add to policy list if valid
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(0), driverList.get(0), 1, 1250.63, 1));
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(1), driverList.get(1), 2, 2637.5, 2));
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(2), driverList.get(2), 3, 1893.89, 4));
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(3), driverList.get(3), 4, 1537.12, 3));
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(4), driverList.get(4), 5, 1008.45, 2));
		verifyAndAddHardCodedValidPolicy(new Policy(carList.get(5), driverList.get(5), 6, 820.56, 1));
	}

	// deletes a policy by letting user input a policy ID to delete
	public void deleteAPolicy() {
		boolean found;
		int policyID;

		displayAllPolicyIDs();
		
		System.out.println("Please enter a policy ID to delete:");

		// keeps user trapped in loop until they enter a valid policy to delete
		do {
			found = false; // assume policy is not found

			policyID = scan.nextInt(); // get user input
			scan.nextLine();

			if (findAndDeletePolicy(policyID)) // if findAndDeletePolicy() returns true then policy was found and deleted
				found = true;
			else // display error message, list valid policy ID's in the list and loop back to get user input again
				System.out.println("ERROR - Policy was not found, please enter a valid policy ID.");
		} while(!found);
	}

	// takes in user input policy ID and checks for matching policies in the policy list and deletes policy if found
	public boolean findAndDeletePolicy(int policyID) {
		for(Policy policy : policyList) { // check each policy in policy list
			if (policy.getPolicyID() == policyID) {
				policyList.remove(policy);
				System.out.println("Policy was found and has been deleted from the system.");
				numberOfPolicies--; // reduce global count of policies by one
				return true; // policy was found and was deleted
			}
		}
		return false; // policy was not found, nothing was deleted
	}

	// displays the policy ID of each policy in the list
	public void displayAllPolicyIDs() {
		System.out.println("\nList of valid policy ID's in the system:");

		// go through each policy in the list and display its policy ID
		for(Policy p : policyList) {
			System.out.println(p.getPolicyID());
		}
	}

	// increases premium amount in all policies by 5%
	public void increasePremiums() {
		// go through each policy in the list and call method to increase premium
		for(Policy p : policyList) {
			p.increasePremiumBy5();
		}

		System.out.println("\nAll policy premiums have been increased by 5%.");
	}

	// displays the total number of claims across all policies
	public void displayTotalClaims() {
		int claims = 0; // store number of claims into an int variable

		// add claims from each policy into int variable
		for(Policy p : policyList) {
			claims += p.getNumOfClaims();
		}

		// display number of claims
		System.out.println("\nThe total number of claims across all policies in the system is: " + claims);
	}

	// displays the total premium amount across all policies
	public void displayTotalPremiums() {
		// setup for decimal formatting on double variable
		DecimalFormat df = new DecimalFormat("#.##");

		double premium = 0; // store number of premiums into a double variable

		// add all premiums into double variable
		for(Policy p : policyList) {
			premium += p.getPremium();
		}

		// converts double to two decimal places
		premium = Double.valueOf(df.format(premium));

		// display total premium amount
		System.out.println("\nThe total premium across all policies in the system is: " + premium);
	}

	// constructor
	public static void main(String args[]) {
		//System.out.print("\f");
		new InsuranceFirm();
	}

}
