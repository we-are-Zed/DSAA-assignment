import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
class Node{
    int coefficient;
    int exponent;
    Node next;
    Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}
class Polynomial {
    Node head = null;
    Node tail = null;

    void addNode(int coe, int exp) {
        Node newNode = new Node(coe, exp);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    void poll() {
        if (head != null) {
            head = head.next;
        }
        if (head == null) {
            tail = null;
        }
    }

    int length() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}


public class PolynomialSummation {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw=new PrintWriter(System.out);
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        Polynomial p1=new Polynomial();
        Polynomial p2=new Polynomial();
        for(int i=0;i<n;i++)
        {
            st = new StringTokenizer(br.readLine());
            int coe=Integer.parseInt(st.nextToken());
            int exp=Integer.parseInt(st.nextToken());
            p1.addNode(coe,exp);
        }
        for(int i=0;i<m;i++)
        {
            st = new StringTokenizer(br.readLine());
            int coe=Integer.parseInt(st.nextToken());
            int exp=Integer.parseInt(st.nextToken());
            p2.addNode(coe,exp);
        }
        Polynomial result=add(p1,p2);
        pw.println(result.length());
        Node current=result.head;
        while (current!=null)
        {
            pw.println(current.coefficient+" "+current.exponent);
            current=current.next;
        }
        pw.flush();
    }
    public static Polynomial add(Polynomial p1,Polynomial p2)
    {
        Polynomial result=new Polynomial();

        while (p1.head!=null||p2.head!=null)
        {
            if (p1.head == null) {
                while(p2.head != null) {
                    result.addNode(p2.head.coefficient, p2.head.exponent);
                    p2.poll();
                }
                break;
            }
            if (p2.head == null) {
                while(p1.head != null) {
                    result.addNode(p1.head.coefficient, p1.head.exponent);
                    p1.poll();
                }
                break;
            }
            Node term1=p1.head;
            Node term2=p2.head;
            if(term1.exponent==term2.exponent)
            {
                int newcoe=term1.coefficient+term2.coefficient;
                if(newcoe!=0)
                {
                    result.addNode(newcoe,term1.exponent);
                }
                p1.poll();
                p2.poll();
            }else if(term1.exponent>term2.exponent)
            {
                result.addNode(term1.coefficient,term1.exponent);
                p1.poll();
            }
            else {
                result.addNode(term2.coefficient,term2.exponent);
                p2.poll();
            }
        }
        return result;
    }
}
