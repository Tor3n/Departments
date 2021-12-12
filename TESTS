import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DepartTEST {

    //each test is ok separately
    //but together they influence each other.
    //I don't know at this point...

    @Test
    void exceptionTesting() {
        IOException exception = assertThrows(
                IOException.class,
                () -> {             Depart2 depart2 = new Depart2();
                    depart2.setInput(null); }
        );

        assertEquals("input string is null", exception.getMessage());
    }

    @Test
    void outputTest() throws IOException {
        Depart2 depart2 = new Depart2();
        depart2.main(new String[0]);
        depart2.setInput(new String[] {"K1\\SK1","K1\\SK2","K1\\SK1\\SSK1","K1\\SK1\\SSK2","K2","K2\\SK1\\SSK1","K2\\SK1\\SSK2"});
        ArrayList<String> output2 = new ArrayList<>();
        output2.add("K2");
        output2.add("K2\\SK1");
        output2.add("K2\\SK1\\SSK2");
        output2.add("K2\\SK1\\SSK1");
        output2.add("K1");
        output2.add("K1\\SK2");
        output2.add("K1\\SK1");
        output2.add("K1\\SK1\\SSK2");
        output2.add("K1\\SK1\\SSK1");
        for (int i = 0; i < output2.size(); i++) {
            assertEquals(output2.get(i), depart2.getResult().get(i));
        }
    }

    @Test
    void outputTest3() throws IOException {
        Depart2 depart3 = new Depart2();
        depart3.setInput(new String[0]);
        depart3.setInput(new String[] {"1\\1","1\\2","1\\1\\1","1\\1\\2","2","2\\1\\1","2\\1\\2"});
        depart3.main(new String[0]);
        ArrayList<String> output3 = new ArrayList<>();
        output3.add("2");
        output3.add("2\\1");
        output3.add("2\\1\\2");
        output3.add("2\\1\\1");
        output3.add("1");
        output3.add("1\\2");
        output3.add("1\\1");
        output3.add("1\\1\\2");
        output3.add("1\\1\\1");
        for (int i = 0; i < output3.size(); i++) {
            assertEquals(output3.get(i), depart3.getResult().get(i));
        }
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void enrichmentTest() throws IOException {
        System.setOut(new PrintStream(outContent));
        Depart2 depart2 = new Depart2();
        depart2.setInput(new String[] {"K1\\KS1\\KSS1\\KSSS1\\KSSSS1"});
        depart2.main(new String[0]);
        String output ="K1"+System.lineSeparator() +
                "K1\\KS1" +System.lineSeparator()+
                "K1\\KS1\\KSS1" +System.lineSeparator()+
                "K1\\KS1\\KSS1\\KSSS1" +System.lineSeparator()+
                "K1\\KS1\\KSS1\\KSSS1\\KSSSS1" +System.lineSeparator();

        assertEquals(output, outContent.toString());
        System.setOut(originalOut);
    }



}

