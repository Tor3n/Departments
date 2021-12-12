import java.io.*;
import java.util.*;

public class Depart3 {

	private static Comparator<String> comp = new Comparator<String>() {
		public int compare(String s1, String s2) {
			int s1Length = s1.split("\\\\").length;
			int s2Length =s2.split("\\\\").length;
			int value = s1Length < s2Length ? -1 : s1Length > s2Length ? 1 : s2.compareTo(s1);
			return value;
		}
	};

	private static String[] input = {"K1\\D\\L3\\T4","K1\\C\\L3\\T4","K1\\B\\K3\\K4","K1\\A\\K6\\K4","K2\\C\\A\\K4","K2\\C\\B\\K4","K3\\C\\B\\A","K3\\C\\B\\B"};
	//private static String[] input = {"K1\\SK1","K1\\SK2","K1\\SK1\\SSK1","K1\\SK1\\SSK2","K2","K2\\SK1\\SSK1","K2\\SK1\\SSK2"};
	private static TreeMap<String,TreeSet<String>> result = new TreeMap<String,TreeSet<String>>(comp);
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

    //method that adds departmens if they are not present
	private static void makeDepartmentsFull(String inp){
		int lengthCounter =0;
		int slashCounter=0;
		for (int j = 0; j < inp.split("\\\\").length ; j++) {
			lengthCounter = lengthCounter+inp.split("\\\\")[j].length()+slashCounter;
			String whatToAdd= inp.substring(0,lengthCounter);
			if (whatToAdd.split("\\\\").length>1) {
				result.get(inp.split("\\\\")[0]).add(whatToAdd);
			}
			slashCounter=1;
		}
	}

	public static void main(String[] args) {
		
		for(int i=0;i<input.length;i++) {
			//if we have the rood dep. present
			if(result.containsKey(input[i].split("\\\\")[0])){
				makeDepartmentsFull(input[i]);
			} else {
			//if we don't have it present, create a new treeset and make it full in case we have many departs
			result.put(input[i].split("\\\\")[0],new TreeSet<String>(comp));
			makeDepartmentsFull(input[i]);
			}
		}

		//get the resuly by modifying the existing toString() method
		//remove unneeded symbols
		String first = result.toString().replaceAll("[\\{\\}\\[\\]\\,]","");
		String second = first.replaceAll("\\="," ");
		String[] splitted = second.split(" ");

		//transfer everything to resulting ArrayList
		for (int i = 0; i < splitted.length ; i++) {
			resultArr.add(splitted[i]);
		}

		for (int i = 0; i < resultArr.size() ; i++) {
			System.out.println(resultArr.get(i));
		}

	}

}
