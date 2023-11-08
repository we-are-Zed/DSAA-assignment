import java.util.Scanner;
public class problemA {
    public static class Node{
        char value;
        Node next;
        public Node(char value) {
            this.value=value;
            this.next=null;
        }
    }
    public Node top;
    public int size;
    public problemA(){
        this.top=null;
        this.size=0;
    }
    public void push(char value){
        Node newNode=new Node(value);
        newNode.next=top;
        top=newNode;
        size++;
    }
    public char pop(){
        char value=top.value;
        top=top.next;
        size--;
        return value;
    }
    public char peek(){
        return top.value;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public boolean isMatched(String s) {
        if(s.length()%2!=0)
        {
            return false;
        }
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                push(ch);
            } else {
                if (isEmpty()) {
                    return false;
                }
                char leftBracket = pop();
                if (ch == ')' && leftBracket != '(') {
                    return false;
                } else if (ch == '}' && leftBracket != '{') {
                    return false;
                } else if (ch == ']' && leftBracket != '[') {
                    return false;
                }
            }
        }
        return isEmpty();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for(int i=0;i<T;i++)
        {
            int n=input.nextInt();
            problemA stack=new problemA();
            String s=input.next();
            if(stack.isMatched(s))
            {
                System.out.println("YES");}
            else{
                System.out.println("NO");
            }
        }
    }
}
