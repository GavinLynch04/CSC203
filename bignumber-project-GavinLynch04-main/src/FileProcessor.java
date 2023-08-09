import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {

    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */

    public static void processFile(String filePath) {
        char currentOperator;
        File infile = new File(filePath);
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                currentOperator = '0';
                String line = scan.nextLine();
                LinkList num1 = new LinkList();
                LinkList num2 = new LinkList();
                for (int i = line.length()-1; i >= 0; i--) {
                    if (Character.isDigit(line.charAt(i))) {
                        if (currentOperator == '0') {
                            num1 = createLinkedNumber(line, i);
                            i -= num1.length()-1;
                            num1.removeZeros();
                        } else {
                            num2 = createLinkedNumber(line, i);
                            i -= num2.length()-1;
                            num2.removeZeros();
                        }
                    } else if (line.charAt(i) == '+' || line.charAt(i) == '*') {
                        currentOperator = line.charAt(i);
                    }
                }
                if (currentOperator != '0') {
                    writeToOutput(num2, num1, currentOperator);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());
        }
    }
    
    public static LinkList createLinkedNumber(String line, int startIndex) {
        LinkList num = new LinkList();
        while (startIndex >= 0 && Character.isDigit(line.charAt(startIndex))) {
            num.addDigit(Character.getNumericValue(line.charAt(startIndex)));
            startIndex--;
        }
        return num;
    }

    public static void writeToOutput(LinkList num1, LinkList num2, char op) {
        if (op == '+') {
            System.out.println(num1 + " " + op + " " + num2 + " " + "=" + " " + BigNumArithmetic.add(num1, num2));
        } else if (op == '*') {
            System.out.println(num1 + " " + op + " " + num2 + " " + "=" + " " + BigNumArithmetic.multiply(num1, num2).removeZeros());
        }
    }
}
