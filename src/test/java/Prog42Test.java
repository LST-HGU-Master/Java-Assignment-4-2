import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @version (20220501)
 **/
public class Prog42Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }

    @Test
    public void testManyNumbers()
    {
        // action
        Prog42.main(new String[]{"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"});

        // assertion
        try {
            assertEquals("1,2,3,1,2,3,1,2,3,1,2,3,1,2,3" + System.lineSeparator(), bos.toString(),
                        "print出力が期待通りになっていません!（コード内で繰り返し回数を直接数値指定しているかも？）"
            );
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }

    @Test
    public void testSingleNumber()
    {
        // action
        try {
            Prog42.main(new String[]{"1"});
        } catch (ArrayIndexOutOfBoundsException expt) {
            after();
            AssertionError asErr = new AssertionError("実行時引数が１つの場合に、存在しないargs[1]の値を参照しようとしています!");
            throw asErr;
        }
        // assertion
        try {
            assertEquals("1" + System.lineSeparator(), bos.toString(), 
                         "実行時引数が「１」の場合に、print出力が期待通りになっていません!"
            );
        } catch (AssertionError asErr) {
            after();
            throw asErr; 
        }
    }

    @Test
    public void testNoInput()
    {
        // assertion
        try {
            // action
            Prog42.main(null);
            String str = bos.toString();
            fail();
        } catch (NullPointerException e) {
            // pass
        }

    }

}
