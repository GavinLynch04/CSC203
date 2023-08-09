import javax.swing.*;

public class BigNumArithmetic {

    /**
     * The entry point of the program.
     * //@param //args Command line arguments. Should have exactly one argument: a file name.
     */

    public static LinkList add(LinkList num1, LinkList num2) {
        LinkList finalNum = new LinkList();
        if (num2.length() > num1.length()) {
            LinkList temp = num1;
            num1 = num2;
            num2 = temp;
        }
        num2 = prepareOperands(num2, num1.length()-num2.length(), '+');
        int carry = 0;
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int temp = num1.getValueAt(i) + num2.getValueAt(i);
            if (temp + carry > 9) {
                temp += carry;
                carry = temp/10;
                temp = temp%10;
                finalNum.addDigit(temp);
                if (i+1 == Math.max(num1.length(), num2.length())) {
                    finalNum.addDigit(carry);
                }
            } else {
                finalNum.addDigit(temp + carry);
                carry = 0;
            }
        }
        num1.removeZeros();
        num2.removeZeros();
        return finalNum;
    }

    public static LinkList multiply(LinkList num1, LinkList num2) {
        LinkList temp1 = new LinkList();
        LinkList temp2 = new LinkList();
        temp2.addDigit(0);
        int carry = 0;
        if (num2.length() > num1.length()) {
            LinkList temp = num1;
            num1 = num2;
            num2 = temp;
        }
        for (int i = 0; i < num2.length(); i++) {
            for (int j = 0; j < num1.length(); j++) {
                int temp = num1.getValueAt(j) * num2.getValueAt(i);
                if (temp + carry > 9) {
                    temp += carry;
                    carry = temp / 10;
                    temp = temp % 10;
                    temp1.addDigit(temp);
                    if (j + 1 == num1.length()) {
                        temp1.addDigit(carry);
                        carry = 0;
                    }
                } else {
                    temp1.addDigit(temp + carry);
                    carry = 0;
                }
            }
            if (num2.length() == 1) {
                return temp1;
            } else {
                for (int x = i; x > 0; x--) {
                    temp1.addDigitFront(0);
                }
                temp2 = add(temp2, temp1);
                temp1.clear();
            }

        }
        return add(temp2, temp1);
    }

    public static LinkList prepareOperands(LinkList num, int difference, char op) {
        if (op == '+') {
            while (difference > 0) {
                num.addDigit(0);
                difference--;
            }
            return num;
        } else {
            throw new ArithmeticException("Invalid Operator");
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Expected exactly 1 argument: a file name.");
        }
        String filePath = args[0];
        FileProcessor.processFile(filePath);
    }
}

