package com.vaccine.tracker.util;

import java.util.ArrayList;

public class CentreUtil {

	static ArrayList<String> pincodeOne45Plus = new ArrayList<>();
	static ArrayList<String> pincodeOne18Plus = new ArrayList<>();
	static ArrayList<String> pincodeTwo45Plus = new ArrayList<>();
	static ArrayList<String> pincodeTwo18Plus = new ArrayList<>();
	
	public static ArrayList<String> getPincodeOne45Plus() {
		return pincodeOne45Plus;
	}
	
	public static ArrayList<String> getPincodeOne18Plus() {
		return pincodeOne18Plus;
	}
	
	public static ArrayList<String> getPincodeTwo45Plus() {
		return pincodeTwo45Plus;
	}
	
	public static ArrayList<String> getPincodeTwo18Plus() {
		return pincodeTwo18Plus;
	}
	
	public static boolean updatePinCodeOne45Plus(String center) {
		if(pincodeOne45Plus.contains(center)) {
			return false;
		}
		return pincodeOne45Plus.add(center);
	}
	
	public static boolean updatePinCodeOne18Plus(String center) {
		if(pincodeOne18Plus.contains(center)) {
			return false;
		}
		return pincodeOne18Plus.add(center);
	}
	
	public static boolean updatePinCodeTwo45Plus(String center) {
		if(pincodeTwo45Plus.contains(center)) {
			return false;
		}
		return pincodeTwo45Plus.add(center);
	}
	
	public static boolean updatePinCodeTwo18Plus(String center) {
		if(pincodeTwo18Plus.contains(center)) {
			return false;
		}
		return pincodeTwo18Plus.add(center);
	}
	
	
	
	
}
