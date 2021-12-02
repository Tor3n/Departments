import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Consumer;

public class Depart {

    /*public static void updateDepart(String depart){
        //here we enrich the set
        Set<String> buffer = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int value = s1.compareTo(s2);

                System.out.println(s1.split("\\\\").length);
                System.out.println(s2.split("\\\\").length);
                System.out.println("s1: "+s1);
                System.out.println("s2: "+s2);

                if ((s1.split("\\\\").length==s2.split("\\\\").length)&&s1.split("\\\\").length>1){
                    //  value = s2.compareTo(s1);
                }
                return value;
            }
        });

        for (int i = 0; i <prelimenaryDep.size() ; i++) {
            if((input[i].split("\\\\")[0]).equals(depart)){
                buffer.add(prelimenaryDep.get(i));
            }
        }

        System.out.println(buffer);

        //result.add();
    }*/

    private static class Support  {
        public static Integer getHashKey(String toGetCode, String[] initial){

            return 1;
        }

    }

    public static void main(String[] args) {
        //String[] result = {"K1","K1\\SK2","K2","K2\\SK1","K2\\SK1\\SSK2","K2\\SK1\\SSK1","K1\\SK1","SSK23","SSK2","SSK24","K1\\SK3" };
        //String[] result = {"K2\\SK1\\SSK2","K2\\SK1\\SSK1","K2","K1"};
        String[] input = {"K2","K2\\SK1","K2\\SK1\\SSK2","K1","K1\\SK2"};
        //String[] input = {"K1\\SK1", "K1\\SK2","K1"};

        SortedSet<Map.Entry<Integer,String>> sortedset = new TreeSet<Map.Entry<Integer,String>>(
                new Comparator<Map.Entry<Integer,String>>() {
                    public int compare(Map.Entry<Integer,String> e1,
                                       Map.Entry<Integer,String> e2) {
                        return e1.getKey().compareTo(e2.getKey());
                    }});

        SortedMap<Integer,String> myMap = new TreeMap<Integer,String>();

        for (int i = 0; i < input.length; i++) {
            myMap.put(Support.getHashKey(input[i],input),input[i]);
        }

        /*myMap.put( 1,"K2");
        myMap.put( 10,"K2\\SK1");
        myMap.put( 100,"K2\\SK1\\SSK2");
        myMap.put( 1000,"K1");
        myMap.put( 1010,"K1\\SK2");*/

        //some method to understand if map is consistent if not, add new instances via getHashKey(String input)


        sortedset.addAll(myMap.entrySet());
        Iterator<Map.Entry<Integer,String>> iterator = myMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,String> pair = iterator.next();
            System.out.println(pair.getValue());
        }


    }

}


