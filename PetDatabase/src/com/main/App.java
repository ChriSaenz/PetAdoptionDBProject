package com.main;

import java.util.HashMap;
import java.util.Map;

public class App {
	
	//	To make matching functions to strings as well as text entries in code easier.
	//	[0] is the text to show in menus, [1] is the name of the corresponding function to call.
	static private String[] accountOptions = {"Account Options",""},
							adoptionRequests = {"Adoption Requests",""},
							animalProfiles = {"Manage Animal Profiles",""},
							manageAccounts = {"Manage User Accounts",""},
							manageAdoptions =  {"Manage Adoption Requests",""},
							salesStats = {"View Sales Stats"},
							viewAnimals = {"View Available Animals","viewAnimals(Type t)"};

	//	Display menu options based on user type.
	private static void mainMenu(Type t) {
		System.out.println("----- Main Menu -----");
		String[][] optionLists = new String[][]{
			//	Customer Options
			{accountOptions[0],viewAnimals[0],adoptionRequests[0]},
			//	Employee Options
			{accountOptions[0],animalProfiles[0],viewAnimals[0],manageAdoptions[0]},
			//	Admin Options
			{accountOptions[0],manageAccounts[0],animalProfiles[0],viewAnimals[0],manageAdoptions[0],salesStats[0]},
		};
		for(int option = 0; option < optionLists[t.ordinal()].length; option++) {
			System.out.println((option + 1) + ". " + optionLists[t.ordinal()][option]);
		}
		System.out.println("0. Log Out and Exit");
	}
	
	public static void main(String[] args) {
		
		//	Get user type from login/registration information
//		Type userType = PetUtils.startUp();
		Type userType = Type.ADMIN;
		
		//	Show main menu
		mainMenu(userType);
	}
}
