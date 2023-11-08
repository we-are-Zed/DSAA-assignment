import java.util.Scanner;
public class Main {
    public static class Node{
        int value;
        Node next;
        public Node(int value) {
            this.value=value;
            this.next=null;
        }
    }
    public Node front;
    public Node rear;
    public int size;
    public Main(){
        this.front=null;
        this.rear=null;
        this.size=0;
    }

    public boolean isEmpty(Main queue){
        return queue.front == null;
    }
    public int size(Main queue){
        return queue.size;
    }
    public int peek(Main queue){
        if(queue.front!=null)
        {
            return queue.front.value;
        }
         return 0;
    }
    public void enqueue(int value,Main queue){
        Node newNode=new Node(value);
        if(isEmpty(queue)){
            front=newNode;
            rear=newNode;
        }else{
            rear.next=newNode;
            rear=newNode;
        }
        size++;
    }
    public int dequeue(Main queue) {
        if (isEmpty(queue)) {
            throw new RuntimeException("Queue is empty, cannot dequeue");
        }
        int value = queue.front.value;
        queue.front = queue.front.next;
        if (queue.front == null) {
            // 如果队列为空了，确保rear指针也指向null
            queue.rear = null;
        }
        queue.size--;
        return value;
    }


    public int[] deleteQueue(Main queue1,Main queue2,int n,Node[] nodes1,Node[] nodes2)
    {
        int[] result=new int[n];
        boolean[] deleted = new boolean[n];
        int i=1;
        while (queue1.size!=0||queue2.size!=0)
        {
            while (queue1.size!=0&&deleted[queue1.front.value-1]!=false)
            {
                queue1.dequeue(queue1);
            }
            while (queue2.size!=0&&deleted[queue2.front.value-1]!=false)
            {
                queue2.dequeue(queue2);
            }
            if (queue1.size==0&&queue2.front!=null)
            {
                while (deleted[queue2.front.value-1]!=false)
                {
                    queue2.dequeue(queue2);
                }
                result[queue2.front.value-1]=i;
                deleted[queue2.front.value-1]=true;
                queue2.dequeue(queue2);
                i++;
                continue;
            }
            if(queue2.size==0&&queue1.front!=null)
            {
                while (deleted[queue1.front.value-1]!=false)
                {
                    queue1.dequeue(queue1);
                }
                result[queue1.front.value-1]=i;
                deleted[queue1.front.value-1]=true;
                queue1.dequeue(queue1);
                i++;
                continue;
            }
            if(queue1.front!=null&&queue2.front!=null&&queue1.front.value==queue2.front.value)
            {
                while (deleted[queue1.front.value-1]!=false)
                {
                    queue1.dequeue(queue1);
                }
                result[queue1.front.value-1]=i;
                queue1.dequeue(queue1);
                queue2.dequeue(queue2);
            }else if(queue1.front!=null&&queue2.front!=null&&queue1.front.value!=queue2.front.value)
            {
                result[queue1.front.value-1]=i;
                result[queue2.front.value-1]=i;
                deleted[queue1.front.value-1]=true;
                deleted[queue2.front.value-1]=true;
                queue1.dequeue(queue1);
                queue2.dequeue(queue2);
            }
            i++;
        }
        return result;
    }

    public int find(int target,Node[] nodes)
    {
        int index=-1;
        for(int i=0;i<nodes.length;i++)
        {
            if(nodes[i].value==target)
            {
                index=i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int p=in.nextInt();
        int q=in.nextInt();
        int[] answer=new int[n];
        Main queue1=new Main();
        Main queue2=new Main();
        Node[] nodes1=new  Node[p];
        Node[] nodes2=new  Node[q];
        for(int i=0;i<p;i++)
        {
            int temp = in.nextInt();
            Node newNode=new Node(temp);
            queue1.enqueue(temp,queue1);
            nodes1[i]=newNode;
        }
        for(int i=0;i<q;i++)
        {
            int temp = in.nextInt();
            Node newNode=new Node(temp);
            queue2.enqueue(temp,queue2);
            nodes2[i]=newNode;
        }
        answer= queue1.deleteQueue(queue1,queue2,n,nodes1,nodes2);
        for(int i=0;i<n;i++)
        {
            System.out.print(answer[i]+" ");
        }
    }

}
