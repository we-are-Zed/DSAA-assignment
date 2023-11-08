import java.util.Scanner;

public class VinuxEditor {

    static class Node {
        char value;
        Node prev, next;

        public Node(char value) {
            this.value = value;
        }
    }

    static class DoublyLinkedList {
        Node head, tail, current,middle;

        public DoublyLinkedList() {
            head = new Node('E');
            tail = new Node('O');

            head.next = tail;
            tail.prev = head;

            current = tail;
        }

        public void insert(char c) {
            Node node = new Node(c);
            node.next = current;
            node.prev = current.prev;
            current.prev.next = node;
            current.prev = node;
            moveLeft();  // 插入后，当前指针移到新插入的字符上
        }


        public void delete() {
            if (current != head && current != middle && current != tail) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                Node toDelete = current;
                moveRight();
                toDelete.next = toDelete.prev = null;
            }
        }


        public void moveLeft() {
            if (current.prev != tail) {
                current = current.prev;
            }
        }

        public void moveRight() {
            if (current != head) {
                current = current.next;
            }
        }

        public void moveToStart() {
            current = head.next;
        }

        public String buildString() {
            StringBuilder sb = new StringBuilder();
            Node node = head.next;
            while (node != tail) {
                sb.append(node.value);
                node = node.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();
            String input = sc.next();
            System.out.println(processVinuxInput(input));
        }
    }

    public static String processVinuxInput(String input) {
        DoublyLinkedList dll = new DoublyLinkedList();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case 'r':
                    i++;  // skip next character for replace
                    if (i < input.length()) {
                        dll.delete();
                        dll.insert(input.charAt(i));
                    }
                    break;

                case 'I':
                    dll.moveToStart();
                    break;
                case 'H':
                    dll.moveLeft();
                    break;
                case 'L':
                    dll.moveRight();
                    break;
                case 'x':
                    dll.delete();
                    break;
                default:
                    dll.insert(c);
                    break;
            }
        }

        return dll.buildString();
    }
}
