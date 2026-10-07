import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @version (20220501) Initial test code
 * @version (20261007) int型へのチェックテストを追加
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

    /** 
     * int型への変換チェック
     * 数値以外の引数で NumberFormatException が発生するか検証
     */
    @Test
    public void testIntTypeConversion() {
        assertThrows(NumberFormatException.class, () -> {
            Prog42.main(new String[]{"a", "1", "2"});
        }, "実行時引数を int 型（Integer.parseInt）に変換していません。課題の指示に従ってください。");
    }
    
    /**
     * 多数の要素の出力検証（改行の有無は問わない）
     * 繰返し回数を固定値にしていないか、動的にカンマ区切り出力できているかを検証
     */
    @Test
    public void testManyNumbers()
    {
        // action
        Prog42.main(new String[]{"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"});

        String actual = bos.toString().trim();
        assertEquals("1,2,3,1,2,3,1,2,3,1,2,3,1,2,3" , actual,
                "出力が期待通りになっていません!（コード内で繰り返し回数を固定の数値にしていませんか？）"
        );
    }

    /**
     * 要素数が1個の場合の検証（改行の有無は問わない）
     * args[1] や numbers[1] など存在しないインデックスを参照していないかを検証
     */
    @Test
    public void testSingleNumber()
    {
        try {
            Prog42.main(new String[]{"1"});
        } catch (ArrayIndexOutOfBoundsException expt) {
            after();
            throw new AssertionError("実行時引数が１つの場合に、存在しないargs[1]の値を参照しようとしています!", expt);
        }

        String actual = bos.toString().trim();
        assertEquals("1", actual,
            "実行時引数が「1」の場合の出力が期待通りになっていません!"
        );
    }


    /**
     * 末尾の改行チェック
     */
    @Test
    public void testOutputEndingWithNewline() {
        Prog42.main(new String[]{"3", "2", "1"});
        
        String output = bos.toString();
        assertTrue(output.endsWith(System.lineSeparator()),
            "出力の末尾に改行がありません。System.out.println を使用してください。"
        );
    }
}
