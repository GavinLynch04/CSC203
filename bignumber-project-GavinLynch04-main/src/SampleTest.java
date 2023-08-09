import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This file contains JUnit tests for some sample arithmetic
 * expressions. This file tests your program "as a whole", by
 * checking its printed output.
 *
 * YOU SHOULD NOT WRITE YOUR OWN TESTS THIS WAY, OR IN THIS FILE.
 * You should test the individual UNITS in your program, i.e., call
 * your add, multiply, and exponentiate methods and test their return
 * values.
 */
public class SampleTest {
    /**
     * The actual standard output stream.
     */
    private PrintStream old;

    /**
     * The streams we're using to capture printed output.
     */
    private ByteArrayOutputStream baos;

    /**
     * Gets called before each test method. Need to do this so that we can
     * capture the printed output from each method.
     */
    @BeforeEach
    public void setUp() {
        this.old = System.out; // Save a reference to the original stdout stream.
        this.baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void testSampleFile() {
        BigNumArithmetic.main(new String[] { "SampleInput.txt" });
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals("1 + 2 = 3\n" +
                "3 * 5 = 15\n" +
                "0 * 1 = 0", output);
    }

    @Test
    public void test2() {
        FileProcessor.processFile("Input2.txt");
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals("5 + 50 = 55\n" +
                "5 * 50 = 250\n" +
                "33 + 67 = 100\n" +
                "1 + 2 = 3\n" +
                "0 + 1 = 1\n" +
                "0 + 0 = 0\n" +
                "1 + 1 = 2\n" +
                "9 + 1 = 10\n" +
                "999999 + 1 = 1000000\n" +
                "64319 + 60137 = 124456\n" +
                "1234567890 + 987654321 = 2222222211\n" +
                "111111111 * 122333444455555 = 13592604925913506171605\n" +
                "5055 * 3900 = 19714500\n" +
                "2587 * 54 = 139698\n" +
                "0 * 3461000 = 0", output);
    }

    @Test
    public void testErrors() {
        FileProcessor.processFile("Inup.txt");
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals(output, "File not found: Inup.txt");
    }

    @Test
    public void testLines() {
        LinkList link = new LinkList();
        link.addDigitFront(9);
        assertEquals(link.getValueAt(0), 9);
    }

    @Test
    public void testArithmetic() {
        assertThrows(ArithmeticException.class, () -> {
            LinkList num = new LinkList();
            num.addDigitFront(9);
            BigNumArithmetic.prepareOperands(num, 5, '2');
        });
    }

    @Test
    public void testFiles() {
        assertThrows(IllegalArgumentException.class, () -> {
            BigNumArithmetic.main(new String[]{"SampleInput.txt", "hi"});
        });
    }

    /**
     * Gets called after each test method. Need to do this so that we are
     * no longer capturing all printed output and printed stuff appears
     * like normal.
     */
    @AfterEach
    public void tearDown() {
        System.out.flush();
        System.setOut(this.old);
    }
}
