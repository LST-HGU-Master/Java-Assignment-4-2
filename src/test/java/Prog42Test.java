import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class Prog42Test {

    @Test
    public void testManyNumbers()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog42.main(new String[]{"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"});

        // assertion
        assertEquals("1,2,3,1,2,3,1,2,3,1,2,3,1,2,3\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testSingleNumber()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Prog42.main(new String[]{"1"});

        // assertion
        assertEquals("1\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testNoInput()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // assertion
        try {
            // action
            Prog42.main(null);
            String str = bos.toString();
            fail();
        } catch (NullPointerException e) {
            // pass
        }

        // undo the binding in System
        System.setOut(originalOut);
    }

}
