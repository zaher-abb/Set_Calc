import java.util.Scanner;
import java.util.regex.*;
import java.util.*;

public class Taschenrechner1 {
	 
	 	//our first Regular Expressions for first Set and operation and seconde Set 
	     private final static String regex =
	 "^(?<str1>(\\[\\])|(\\[\\d+\\])|(\\[(\\d+,)+\\d+\\]))(?<op>\\+|\\*|\\-)(?<str2>(\\[\\])|(\\[[0-9]+\\])|(\\[(\\d+,)+\\d+\\]))$";
	     private final static Pattern pattern = Pattern.compile(regex);
	     
	     //our seconde Regular Expressions 
	     private final static String regex2 = "\\D(?<number>\\d+)";
	     
	     private final static Pattern pattern2 = Pattern.compile(regex2);
	     
	 	public static void main(String[] args) {
	 	
	 			Scanner sc = new Scanner(System.in);
	 		 	System.out.println("please write test for testing the Programm or anythings else to do operation");
	 		 	String test=sc.nextLine(); 		 	
	 		 // testing the programm with 5 different Sets 
	 		if(test.equals("test"))
	 		{	
	 			String[] arr = {"","","","",""};
	 	
				arr[0] = "[15,78, 3] + [49,7,222, 4,19 ]";
				arr[1] = "[1,2,22,7]-[4,6,1,2  ]";
				arr[2] = "[ 10 , 2 , 288 , 7 ] * [8 , 10 , 288]";
				arr[3] = "[]*[]";
				arr[4] = "[577, 60, 32] +[59, 183]";
				 
				for(int i= 0; i< arr.length;i++) {		
					arr[i]=arr[i].replace(" ", "");
					
					Matcher matcher = pattern.matcher(arr[i]);
					
					while(matcher.find()) {
					
		 				String str1 = matcher.group("str1"); // first Set 
		 				char op = matcher.group("op").charAt(0); //the operation 
		 				String str2 = matcher.group("str2"); // seconde Set 
		 				
		 				 
		 		 		//call the methode that giving us the result of the operation
		 				Set<Integer> result = in_operations(getMenge(str1),getMenge(str2),op);
			 			//printing the result	 				 
		 				System.out.println(result);
				
						}
							}	 		 		
	 		}
	 		
	 		else {
	 		
	 		String str1 = "";
	 		String str2 = "";
	 		char op = ' ';
	 		
	 		System.out.println("Enter the Operation:");
	 		//Scanner to read the Operation(input) from the user 
	 		
	 		String input = sc.nextLine();

	 		// the program still running until pressing enter 
	 		while(!input.trim().equals("")) {
	 		//get the whitespace out
	 		input = input.replace(" ", "");
	 		
	 		//matching the input with our Regular Expressions
	 		Matcher matcher = pattern.matcher(input);
	 	   
	 		// checking if the input Format wrong or right 
	 		if(matcher.find()==false) {
	 		 	
		 		System.out.println("Wrong input Format");
		 		
	 		}
	 	   
	 		else {
	 			
	 		matcher = pattern.matcher(input);
	 		
	 		while(matcher.find()) { 
	 				 str1 = matcher.group("str1"); // first Set 
	 				 op = matcher.group("op").charAt(0); //the operation 
	 				 str2 = matcher.group("str2"); // seconde Set 
	 				 
	 		//call the methode that giving us the result of the operation 
	 		Set<Integer> result = in_operations(getMenge(str1),getMenge(str2),op);
	 			//printing the result	 				 
	 		System.out.println(result);
	 		
	 		}
	 		}
	 		// input another operation  
	 		System.out.println("Please enter the Operation :");		
	 		input = sc.nextLine();
	 		
	 	}
	 	
	 		// turning the calculator off
	 		System.out.println("End");
	 		sc.close();
	 		}	
	 	}
	 	
	 
	 		
	 	// methode that doing the operation 
	 	public static Set<Integer> in_operations(Set<Integer> str1, Set<Integer> str2, char op){
	 	
	 		Set<Integer> str_set = new HashSet<Integer>();
	 		
	 		
	 		// switch case for choose the operation that we need 
	 		
	 		//Unions
	 		if(op == '+') {	 		
	 			str_set.addAll(str1);
	 			str_set.addAll(str2);
	 				
	 		}//Intersections
	 		 if(op=='*') {
	 			str_set.addAll(str1);
	 			str_set.retainAll(str2);
	 			
	 		}//Complements	
	 		 if (op== '-') {
	 			str_set.addAll(str1);
	 			str_set.removeAll(str2);
	 	    	
	 		}
	 		return str_set;
	 	}
	 	
	 	//pick out the nummbers from the string of our Set 
	 	public static Set<Integer> getMenge(String menge) {
	 		
	 		Matcher matcher = pattern2.matcher(menge);
	 		Set<Integer> just_number = new HashSet<Integer>();
	 		
	 		while(matcher.find()) {
	 			just_number.add(Integer.parseInt(matcher.group("number")));
	 			}
	 		
	 		return just_number;
	 	}
	 	

	 }
