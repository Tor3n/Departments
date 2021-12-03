import java.io.IOException;
import java.util.*;

public class Depart2 {

    private static String[] input = {"K1\\SK1","K1\\SK2","K1\\SK1\\SSK1","K1\\SK1\\SSK2","K2","K2\\SK1\\SSK1","K2\\SK1\\SSK2"};

    private static ArrayList<String> result = new ArrayList<String>();

    public void setInput (String[] input) throws IOException {
        if (input!=null){
            this.input=input;
        } else {
            throw new IOException("input string is null");
        }

    }

    public ArrayList<String> getResult() throws IOException{
        if (result.isEmpty()){
            throw new IOException("error, result is empty");
        } else{
            return result;
        }
    }

    private static class Support {
        private static HashSet<String> buffer = new HashSet<String>();
        private static TreeSet<String> roots;

        public static void updateDepart(){
            //Пройдёмся по каждому и будем в лоб вставлять недостающие элементы.
            //Примерно то же самое, что и проверка, а потом вставка, но без проверки, HashSet не даст вставить лишнее
            for (int i = 0; i < input.length ; i++) {
                String[] nextBatch = input[i].split("\\\\");
                int lengthCounter =0;
                int slashCounter=0;
                 for (int j = 0; j < nextBatch.length ; j++) {
                     lengthCounter = lengthCounter+nextBatch[j].length()+slashCounter;
                     String whatToAdd= input[i].substring(0,lengthCounter);
                     buffer.add(whatToAdd);
                     slashCounter=1;
                 }
            }
            roots =getRoot();
            remUpdDepart();
        }

        public static TreeSet<String> getRoot(){
            //Будем вычленять из буффера по департаменту верхнего уровня, сортировать и вставлять в результат
            TreeSet<String> insideRoots = new TreeSet<>();
            for (int i = 0; i < input.length; i++) {
                insideRoots.add(input[i].split("\\\\")[0]);
            }
            return (TreeSet<String>) insideRoots.descendingSet();
        }

        public static void remUpdDepart(){
            //уже тут отсортируем и вставим в результат попутно удаляя из буффера
            ArrayList<String> subTotal = null;
            ArrayList<String> toRemove = null;
            Iterator<String> rootsIterator =roots.iterator();
            //пока буфер не опустеет
            while (!buffer.isEmpty()){
                subTotal = new ArrayList<>();
                toRemove = new ArrayList<>();
            if (rootsIterator.hasNext()){
                String root = rootsIterator.next();
                Iterator<String> bufferRator =buffer.iterator();
                    while (bufferRator.hasNext()){
                        String nexDep =bufferRator.next();
                        String getDep = nexDep.split("\\\\")[0];
                        if(getDep.equals(root)){
                            subTotal.add(nexDep);
                            toRemove.add(nexDep);
                        }
                    }

                //удаляем из буфера тут
                for (int i = 0; i < toRemove.size(); i++) {
                    buffer.remove(toRemove.get(i));
                }

                //сортируем предварительный результат сначала по иерархии
                for (int i = subTotal.size()-1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        if (subTotal.get(j).split("\\\\").length>subTotal.get(j+1).split("\\\\").length) {
                            String a=subTotal.get(j);
                            subTotal.set(j,subTotal.get(j+1));
                            subTotal.set(j+1,a);
                        }
                    }
                }

                //сортируем предварительный результат (по1 департаменту) внутри кусков одинаковой иерархии
                //если нашли первую разницу, то сортируем по ней, а не идем дальше. Т.к. департаменты уже отличаются
                //например K\\A\\B и K\\B\\A отличия во втором знаке следовательно на 3 не смотрим
                //т.е. вывод будет
                //K\\B\\A
                //K\\A\\B
                //т.к. из задания не понятно какой из поддепартаментов "выше", с более низким подвторым или подтретим департаментом
                //можно например сделать чтобы смотрело и по последнему
                for (int i = subTotal.size()-1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        if (subTotal.get(j).split("\\\\").length==subTotal.get(j+1).split("\\\\").length) {
                            int whereDiffer=0;
                            //полезем внутрь чтобы найти в каком департаменте отличие
                            for (int k = 0; k < subTotal.get(j).split("\\\\").length; k++) {
                                if (subTotal.get(j).split("\\\\")[k].compareTo(subTotal.get(j+1).split("\\\\")[k])!=0){
                                    whereDiffer=k;
                                    break;
                                }
                            }
                            if (subTotal.get(j).split("\\\\")[whereDiffer].compareTo(subTotal.get(j+1).split("\\\\")[whereDiffer])<0){
                                String a=subTotal.get(j);
                                subTotal.set(j,subTotal.get(j+1));
                                subTotal.set(j+1,a);
                                //k=subTotal.get(j).split("\\\\").length+1;
                                //break;
                            }

                        }

                    }
                }

                //add to result
                for (int i = 0; i <subTotal.size(); i++) {
                    result.add(subTotal.get(i));
                }
            }

            }

        }
    }

    public static void main(String[] args) {
        Support.updateDepart();

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

        //System.out.println("memory used: "+(Runtime.getRuntime().totalMemory())/1048576);
    }
}
