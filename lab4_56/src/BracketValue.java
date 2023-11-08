import java.util.Scanner;
public class BracketValue {

    private static final int MOD = 514329;

    public static void main(String[] args) {
       Scanner input= new Scanner(System.in);
       String s=input.nextLine();
         System.out.println(calculateValue(s)%MOD);
    }

    public static int calculateValue(String s) {
        Stack<Integer> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(-1); // Using -1 to represent '(' for simplicity
            } else if (c == ')') {
                if (stack.peek() != -1) {
                    int innerValue = stack.pop();
                    stack.pop();  // remove the '(' (-1)
                    stack.push((innerValue * 2) % MOD);
                } else {
                    stack.pop();  // remove the '(' (-1)
                    stack.push(1);
                }
            }

            // Combine continuous numbers in the stack
            while (stack.size() >= 2 &&
                    stack.peek() != -1 &&
                    stack.get(stack.size() - 2) != -1) {
                int right = stack.pop();
                int left = stack.pop();
                stack.push((left + right) % MOD);
            }
        }

        if (!stack.isEmpty() && stack.peek() != -1) {
            return stack.pop();
        }
        return 0;
    }

    static class Stack<E> {
        private Object[] items = new Object[10];
        private int size = 0;

        public void push(E item) {
            if (size == items.length) {
                doubleCapacity();
            }
            items[size++] = item;
        }

        public E pop() {
            if (!isEmpty()) {
                E item = (E) items[--size];
                items[size] = null;
                return item;
            }
            return null;
        }

        public E peek() {
            return (isEmpty()) ? null : (E) items[size - 1];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public E get(int index) {
            return (E) items[index];
        }

        private void doubleCapacity() {
            int newSize = items.length * 2;
            Object[] newArray = new Object[newSize];
            System.arraycopy(items, 0, newArray, 0, size);
            items = newArray;
        }
    }
}
