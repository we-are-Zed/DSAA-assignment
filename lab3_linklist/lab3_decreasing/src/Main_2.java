import java.util.*;
public class Main_2 {
    public static class Node{
        int value;
        Node next;
        Node pre;
        public Node(int value) {
            this.value=value;
            this.next=null;
            this.pre=null;
        }
    }
    public static class outLinklist {
        inLinklist head;
        inLinklist tail;
        int size;  // 添加size属性

        public outLinklist() {
            head = new inLinklist();
            tail = new inLinklist();
            head.next = tail;
            tail.pre = head;
            size = 0;  // 初始化size为0
        }

        public void insert(inLinklist inner) {
            inner.next = tail;
            inner.pre = tail.pre;
            tail.pre.next = inner;
            tail.pre = inner;
            size++;  // 增加size的值
        }
    }

    public static class inLinklist {
        Node head;
        Node tail;
        inLinklist next;
        inLinklist pre;
        int size;  // 添加size属性

        public inLinklist() {
            head = new Node(-1);
            tail = new Node(Integer.MAX_VALUE);
            head.next = tail;
            tail.pre = head;
            size = 0;  // 初始化size为0
        }

        public void insert(int value) {
            Node newNode = new Node(value);
            newNode.next = tail;
            newNode.pre = tail.pre;
            tail.pre.next = newNode;
            tail.pre = newNode;
            size++;  // 增加size的值
        }
    }
    public static outLinklist DecrConnection(outLinklist outer) {
        inLinklist current = outer.head.next;
        while (current != outer.tail) {
            inLinklist nextInner = current.next;
            boolean merged = false; // 标志来检查是否合并了

            while (nextInner != null && current.tail.pre.value > nextInner.head.next.value) {
                if (nextInner == outer.tail) {
                    current.insert(nextInner.head.next.value);
                    current.next = outer.tail;
                    outer.tail.pre = current;
                    nextInner = null;
                    merged = true;
                } else {
                    current.insert(nextInner.head.next.value);
                    current.next = nextInner.next;
                    nextInner.next.pre = current;
                    nextInner = nextInner.next;
                    merged = true;
                }
            }
            // 只有当没有合并时，我们才移动current
            if (!merged) {
                current = current.next;
            }
        }
        return outer;
    }
    public static outLinklist delete(outLinklist outer) {
        inLinklist current = outer.head;

        while (current.next != outer.tail) {
            inLinklist next = current.next;

            if (next.size > 1) {
                current.next = next.next;
                next.next.pre = current;
                outer.size--;  // 减少outer的size值
            } else {
                current = next;
            }
        }

        return outer;
    }

    public static int countSize(inLinklist innerList) {
        int count = 0;
        Node current = innerList.head.next;

        while (current != innerList.tail) {
            count++;
            current = current.next;
        }
        return count;
    }
    public static int CountSize(outLinklist outerList) {
        int count = 0;
        inLinklist current = outerList.head.next;

        while (current != outerList.tail) {
            count++;
            current = current.next;
        }
        return count;
    }
    public static void printOuter(outLinklist outer) {
        inLinklist currentInner = outer.head.next;
        while (currentInner !=outer.tail) {
            if (countSize(currentInner) == 1) {
                Node newNode = currentInner.head.next;
                System.out.print(newNode.value+" ");
            }
            currentInner = currentInner.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=0;i<T;i++)
        {
            int n=sc.nextInt();
            outLinklist outer=new outLinklist();
            for(int j=0;j<n;j++)
            {
                int value=sc.nextInt();
                Node newNode=new Node(value);
                inLinklist inner=new inLinklist();
                inner.insert(value);
                outer.insert(inner);
            }
            while (true)
            {
                int initialSize=CountSize(outer);
                DecrConnection(outer);
                int newSize=CountSize(outer);
                if(initialSize==newSize)
                {
                    break;
                }else {
                    delete(outer);
                }
            }
            printOuter(outer);
            System.out.println();
        }
    }
}
