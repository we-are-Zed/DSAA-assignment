import java.util.*;
public class Main {
    static int size = 0;
    static outLinklist[] needToDelete = new outLinklist[size];
    static int deleteSize = 0;
    static int deleteSizeCopy = 0;
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
        outLinklist next;
        outLinklist prev;
        inLinklist Linklist;
        public outLinklist(){
            next = null;
            prev = null;
            Linklist = new inLinklist();
        }
    }
    public static class inLinklist{
        Node head;
        Node tail;
        Node next;
        Node pre;
        int size = 0;
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
            size++;
        }
    }
    public static void delete(outLinklist delete, outLinklist head) {
        if (delete.prev != head)
        {
            delete.prev.next = delete.next;
            delete.next.prev = delete.prev;
            if (delete.prev.Linklist.tail.pre.value > delete.next.Linklist.head.next.value && delete.next.Linklist.size == 1)
            {
                delete.prev.Linklist.insert(delete.next.Linklist.head.next.value);
                if (delete.next.next != null)
                {
                    delete.prev.next = delete.next.next;
                    delete.next.next.prev = delete.prev;
                }else
                {
                    delete.prev.next = null;
                }
                if (delete.prev.Linklist.size > 2 && deleteSizeCopy != 0)
                {
                    needToDelete[deleteSizeCopy - 1] = delete.prev;
                } else if (delete.prev.Linklist.size == 2)
                {
                    needToDelete[deleteSizeCopy++] = delete.prev;
                }
            }
        }else {
            delete.next.prev = head;
            head.next = delete.next;
        }
    }

    public static void printOuter(outLinklist head, outLinklist tail) {
        outLinklist currentOuter = head.next;
        while (currentOuter != tail) {
            System.out.print(currentOuter.Linklist.head.next.value+" ");
            currentOuter = currentOuter.next;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=0;i<T;i++)
        {
            int n=sc.nextInt();
            size = n;
            needToDelete = new outLinklist[size];
            outLinklist curretOuter=new outLinklist();
            outLinklist head = new outLinklist();
            head.next = curretOuter;
            curretOuter.prev = head;
            outLinklist tail = new outLinklist();
            tail.prev = curretOuter;
            curretOuter.next = tail;

            for(int j=0;j<n;j++)
            {
                int value=sc.nextInt();
                if (j == 0)
                {
                    curretOuter.Linklist.insert(value);
                }else
                {
                    if (value >= curretOuter.Linklist.tail.pre.value)
                    {
                        if (curretOuter.Linklist.size > 1)
                        {
                            needToDelete[deleteSize++] = curretOuter;
                        }
                        outLinklist newOuter = new outLinklist();
                        newOuter.Linklist.insert(value);
                        curretOuter.next = newOuter;
                        newOuter.prev = curretOuter;
                        tail.prev = newOuter;
                        newOuter.next = tail;
                        curretOuter = newOuter;
                    }else
                    {
                        curretOuter.Linklist.insert(value);
                    }
                }
                if (j == n - 1){
                    if (curretOuter.Linklist.size > 1)
                    {
                        needToDelete[deleteSize++] = curretOuter;
                    }
                }

            }
            while (true)
            {
                deleteSizeCopy = 0;
                for (int j = 0; j < deleteSize; j++)
                {
                    if (needToDelete[j] != null)
                    {
                        delete(needToDelete[j], head);
                    }
                }
                deleteSize = deleteSizeCopy;
                if (deleteSize == 0)
                {
                    break;
                }
            }
            printOuter(head, tail);
            System.out.println();
        }
    }
}
