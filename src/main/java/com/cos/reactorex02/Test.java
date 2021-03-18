package com.cos.reactorex02;

public class Test {
	
	public static void main(String[] args) {
		
		String response = "username=ssar&password=1234";
		String[] splitData = response.split("&|=");
		
		for(int i=0; i<splitData.length; i++) { 
			if(i%2==1) {
		    	System.out.println("User: " + splitData[i]);
		    }
		}
	}
}
