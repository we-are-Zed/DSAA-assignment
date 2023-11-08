import java.util.*;
public class Main_3 {
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
    public static class outLinklist{
        inLinklist head;
        inLinklist tail;
        public outLinklist(){
            head=new inLinklist();
            tail=new inLinklist();
            head.next=tail;
            tail.pre=head;
        }
        public void insert(inLinklist inner){
            inner.next=tail;
            inner.pre=tail.pre;
            tail.pre.next=inner;
            tail.pre=inner;
        }
    }
    public static class inLinklist{
        Node head;
        Node tail;
        inLinklist next;
        inLinklist pre;
        public inLinklist(){
            head=new Node(-1);
            tail=new Node(Integer.MAX_VALUE);
            head.next=tail;
            tail.pre=head;
        }
        public void insert(int value){
            Node newNode=new Node(value);
            newNode.next=tail;
            newNode.pre=tail.pre;
            tail.pre.next=newNode;
            tail.pre=newNode;
        }
    }
    public static outLinklist DecrConnection(outLinklist outer) {
        inLinklist current = outer.head.next;
        while (current != outer.tail) {
            inLinklist nextInner = current.next;
            boolean didMergeCurrent = false;  // 判断当前的inLinklist是否进行了合并

            while (nextInner != null && current.tail.pre.value > nextInner.head.next.value) {
                // 把上面的if改成了while, 这样会一直检查并合并连续的降序序列，直到找不到降序为止
                if (nextInner == outer.tail) {
                    current.insert(nextInner.head.next.value);
                    current.next = outer.tail;
                    outer.tail.pre = current;
                    nextInner = null;
                } else {
                    current.insert(nextInner.head.next.value);
                    current.next = nextInner.next;
                    nextInner.next.pre = current;
                    nextInner = nextInner.next;
                }
                didMergeCurrent = true;  // 表示此次循环进行了合并
            }
            // 如果进行了合并，并且current的长度大于1，那么进行删除
            if (didMergeCurrent ) {
                inLinklist prev = current.pre;
                delete(outer, current);
                current = prev;  // 从被删除位置的前一个位置开始检查
            } else {
                current = current.next;  // 如果没有进行合并，正常移动到下一个位置
            }
        }
        return outer;
    }

    public static outLinklist delete(outLinklist outer, inLinklist Current) {
        Current.pre.next = Current.next;
        Current.next.pre = Current.pre;
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
            System.out.print(currentInner.head.next.value+" ");
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
            DecrConnection(outer);
            while (true)
            {
                int initialSize=CountSize(outer);
                DecrConnection(outer);
                int finalSize=CountSize(outer);
                if (initialSize==finalSize)
                {
                    break;
                }
            }
            printOuter(outer);
            System.out.println();
        }
    }
}
