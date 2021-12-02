import java.sql.SQLOutput;
import java.util.*;

public class Depart2 {
    //static String[] input = {"K2\\SK1\\SSK2","K2\\SK1","K1","K1\\SK2","K3\\SK1"};
   // private static String[] input = {"K1\\SK1","K1\\SK2","K1\\SK1\\SSK1","K1\\SK1\\SSK2","K2","K2\\SK1\\SSK1","K2\\SK1\\SSK2"};
    private static String[] input = {"K2\\SK2","K2\\SK1\\SSK1","K2\\SK1\\SSK2","K2\\SK1"};
    private static ArrayList<String> prelimenaryDep = new ArrayList<String>();
    private static LinkedList<String> result = new LinkedList<>();

    public void setInput (String[] input){
        this.input=input;
    }
    public LinkedList<String> getResult(){
        return result;
    }

    public static class Support {
        public static TreeSet<String> getRoot(String[] input){
            TreeSet<String> output = new TreeSet<String>();
            for (int i = 0; i < input.length; i++) {
                output.add(input[i].split("\\\\")[0]);
            }
            return (TreeSet<String>) output.descendingSet();
        }

        public static void updateDepart(String depart){
            //here we enrich the set
            LinkedList<String> buffer = new LinkedList<String>();

            for (int i = 0; i <prelimenaryDep.size() ; i++) {
                if((input[i].split("\\\\")[0]).equals(depart)){
                    //understand where to put it
                    buffer.add(prelimenaryDep.get(i));
                }
            }

            System.out.println(buffer);

            //result.add();
        }

        public static void removeDepart(String depart){

        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < input.length ; i++) {
            prelimenaryDep.add(input[i]);
        }

        TreeSet<String> roots = Support.getRoot(input);
        Iterator rootsIterator = roots.iterator();

        Support.updateDepart((String) rootsIterator.next());
        //System.out.println("K2\\SK1\\SSK5".compareTo("K2\\SK1\\SSK2"));
        //System.out.println(prelimenaryDep);

       /* while (!prelimenaryDep.isEmpty()){
            if (rootsIterator.hasNext()){
                Support.updateDepart((String) rootsIterator.next());
                Support.removeDepart((String) rootsIterator.next());
            } else {
                break;
            }
        }*/



        //System.out.println(prelimenaryDep);
        //System.out.println((Runtime.getRuntime().totalMemory())/1048576);
        //System.out.println(Runtime.getRuntime().maxMemory());
    }
}
