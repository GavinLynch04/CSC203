public class LinkList {
    private Node front = null;
    private Node back = null;

    public void addDigitFront(int data) {
        Node newest = new Node(data);
        if (front == null && back == null) {
            front = newest;
            back = newest;
        } else {
            newest.next = front; //Switch to opposite of addDigit
            front = newest;
        }
    }

    public void addDigit(int data) {
        Node newest = new Node(data);
        if (front == null && back == null) {
            front = newest;
            back = newest;
        } else {
            back.next = newest;
            back = newest;
        }
    }

    public void clear() {
        this.front = null;
        this.back = null;
    }

    public String toString() {
        String finalNum = "";
        Node node = front;
        while (node != null) {
            finalNum = node.digit + finalNum;
            node = node.next;
        }
        return finalNum;
    }

    public int length() {
        int length = 0;
        Node node = front;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public int getValueAt(int index) {
        Node node = front;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.digit;
    }

    public LinkList removeZeros() {
        String number = this.toString();
        int i;
        for (i = 0; i < number.length() - 1; i++) {
            if (number.charAt(i) != '0') {
                break;
            }
        }
        if (i > 0) {
            this.clear();
            for (int j = 0; j < number.substring(i).length(); j++) {
                this.addDigitFront(Character.getNumericValue(number.substring(i).charAt(j)));
            }
        }
        return this;
    }
    private static class Node{
        private final int digit;
        private Node next;

        private Node(int digit) {
            this.digit = digit;
            this.next = null;
        }
    }
}

