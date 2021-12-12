import java.io.*;
import java.util.*;

public class Depart3 {
	
    private static String[] input = {"K1\\SK1","K1\\SK2","K1\\SK1\\SSK1","K1\\SK1\\SSK2","K2","K2\\SK1\\SSK1","K2\\SK1\\SSK2"};
    private static ArrayList<String> resultArr = new ArrayList<String>();
	
    public void setInput (String[] input) throws IOException {
        if (input!=null){
            this.input=input;
        } else {
            throw new IOException("input string is null");
        }
    }
    
    public ArrayList<String> getResult() throws IOException{
        if (resultArr.isEmpty()){
            throw new IOException("error, result is empty");
        } else{
            return new ArrayList<String>();
        }
    }
	
	public static void main(String[] args) {
		
        Comparator<String> comp = new Comparator<String>() {
        	public int compare(String s1, String s2) {
        		
				int value = s1.split("\\\\").length < s2.split("\\\\").length ? -1 : s1.split("\\\\").length > s2.split("\\\\").length ? 1 : s2.compareTo(s1);
				return value;
			}

        };		
		
		TreeMap<String,TreeSet<String>> result = new TreeMap<String,TreeSet<String>>(comp);
		
		for(int i=0;i<input.length;i++) {
			if(result.containsKey(input[i].split("\\\\")[0])){
				int lengthCounter =0;
		        int slashCounter=0;
		        for (int j = 0; j < input[i].split("\\\\").length ; j++) {
		        	lengthCounter = lengthCounter+input[i].split("\\\\")[j].length()+slashCounter;
		            String whatToAdd= input[i].substring(0,lengthCounter);
		            if (whatToAdd.split("\\\\").length>1) {
		            	result.get(input[i].split("\\\\")[0]).add(whatToAdd);
		            }
		            slashCounter=1;		                     	
		        } 
			} else {
			result.put(input[i].split("\\\\")[0],new TreeSet<String>(comp));				
			}
		}
		
		System.out.println(result);
	}

}
