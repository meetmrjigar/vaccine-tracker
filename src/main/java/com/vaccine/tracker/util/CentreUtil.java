package com.vaccine.tracker.util;


import java.util.HashMap; 
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import com.vaccine.tracker.dto.ResponseContent;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentreUtil {

	static Set<String> centers = new HashSet<>();
	static Set<String> centers86 = new HashSet<>();
	static Set<String> centers77 = new HashSet<>();
	
	//static Map<String, ResponseContent> forStatusCheck = new HashMap<>();
	
//	static Set<String> centersFortyFivePlus = new HashSet<>();
//	static Set<String> centersBelowFortyFive = new HashSet<>();
//	static Set<String> centersFortyFivePlus2 = new HashSet<>();
//	static Set<String> centersBelowFortyFive2 = new HashSet<>();
//	static Set<String> centersFortyFivePlus3 = new HashSet<>();
//	static Set<String> centersBelowFortyFive3 = new HashSet<>();
//	static Set<String> centersFortyFivePlus4 = new HashSet<>();
//	static Set<String> centersBelowFortyFive4 = new HashSet<>();
//	static Set<String> centersFortyFivePlus5 = new HashSet<>();
//	static Set<String> centersBelowFortyFive5 = new HashSet<>();
//	static Set<String> centersFortyFivePlus6 = new HashSet<>();
//	static Set<String> centersBelowFortyFive6 = new HashSet<>();
//	
//	public synchronized static boolean updateFortyFivePlus(String key, int thread) {
//		switch(thread){		
//		case 1:
//			return centersFortyFivePlus.add(key);					
//		case 2:
//			return centersFortyFivePlus2.add(key);					
//		case 3:
//			return centersFortyFivePlus3.add(key);					
//		case 4:
//			return centersFortyFivePlus4.add(key);					
//		case 5:
//			return centersFortyFivePlus5.add(key);					
//		case 6:
//			return centersFortyFivePlus6.add(key);					
//		default:
//			return false;		
//		}		
//	}
//	
//	public synchronized static boolean updateBelowFortyFive(String key, int thread) {
//		switch(thread) {
//		case 1:
//			return centersBelowFortyFive.add(key);					
//		case 2:
//			return centersBelowFortyFive2.add(key);					
//		case 3:
//			return centersBelowFortyFive3.add(key);				
//		case 4:
//			return centersBelowFortyFive4.add(key);					
//		case 5:
//			return centersBelowFortyFive5.add(key);		
//		case 6:
//			return centersBelowFortyFive6.add(key);					
//		default:
//			return false;					
//		}		
//	}	
//
//	public synchronized static void clearAll(int thread){
//		switch(thread){
//		case 1:
//			centersBelowFortyFive.clear();
//			centersFortyFivePlus.clear();
//			break;			
//			
//		case 2:
//			centersBelowFortyFive2.clear();
//			centersFortyFivePlus2.clear();
//			break;
//		
//		case 3:
//			centersBelowFortyFive3.clear();
//			centersFortyFivePlus3.clear();
//			break;			
//		
//		case 4:
//			centersBelowFortyFive4.clear();
//			centersFortyFivePlus4.clear();
//			break;			
//		
//		case 5:
//			centersBelowFortyFive5.clear();
//			centersFortyFivePlus5.clear();
//			break;			
//		
//		case 6:
//			centersBelowFortyFive6.clear();
//			centersFortyFivePlus6.clear();
//			break;			
//		
//		default:
//			break;
//		}		
//	}		
//	
//	public synchronized static void removeBelowFortyFive(String key, int thread) {
//		switch(thread){
//		case 1:
//			centersBelowFortyFive.remove(key);
//			break;			
//			
//		case 2:
//			centersBelowFortyFive2.remove(key);
//			break;
//		
//		case 3:
//			centersBelowFortyFive3.remove(key);
//			break;			
//		
//		case 4:
//			centersBelowFortyFive4.remove(key);
//			break;			
//		
//		case 5:
//			centersBelowFortyFive5.remove(key);
//			break;			
//		
//		case 6:
//			centersBelowFortyFive6.remove(key);
//			break;			
//		
//		default:
//			break;
//		}		
//	}
//	
//	public synchronized static void removeFortyFivePlus(String key, int thread) {
//		switch(thread){
//		case 1:
//			centersFortyFivePlus.remove(key);
//			break;			
//			
//		case 2:
//			centersFortyFivePlus2.remove(key);
//			break;
//		
//		case 3:
//			centersFortyFivePlus3.remove(key);
//			break;			
//		
//		case 4:
//			centersFortyFivePlus4.remove(key);
//			break;			
//		
//		case 5:
//			centersFortyFivePlus5.remove(key);
//			break;			
//		
//		case 6:
//			centersFortyFivePlus6.remove(key);
//			break;			
//		
//		default:
//			break;
//		}		
//	}
	
	public static Set<String> getCenters() {
		return centers;
	}

//	public static Map<String, ResponseContent> getForStatusCheck() {
//		return forStatusCheck;
//	}

	public static Set<String> getCenters86() {
		return centers86;
	}

	public static Set<String> getCenters77() {
		return centers77;
	}

	public static boolean updateCenters(String key) {
		return centers.add(key);		
	}
	
	public static void removeCenter(String key) {
		centers.remove(key);
	}
	
	public static boolean updateCenters86(String key) {
		return centers86.add(key);		
	}
	
	public static void removeCenter86(String key) {
		centers86.remove(key);
	}
	
	public static boolean updateCenters77(String key) {
		return centers77.add(key);		
	}
	
	public static void removeCenter77(String key) {
		centers77.remove(key);
	}

	public static void setCenters(Set<String> centers) {
		CentreUtil.centers=centers;
		
	}

	public static void setCenters86(Set<String> centers86) {
		CentreUtil.centers86=centers86;		
	}

	public static void setCenters77(Set<String> centers77) {
		CentreUtil.centers77=centers77;
		
	}
	
//	public synchronized static void updateStatusCheck(String key, ResponseContent response) {
//		forStatusCheck.put(key, response);
//	}
	
}
