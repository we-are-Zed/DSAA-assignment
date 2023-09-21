import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String SID = input.next();
        int age = input.nextInt();
        float weight = input.nextFloat();
        char grade = input.next().charAt(0);
        int Class = Integer.parseInt(SID.charAt(4)+"") + Integer.parseInt((int)SID.charAt(5)+"");
        System.out.println(Class);
        System.out.printf("Welcome Student %s from class %d,aged %d,weighed %.2f, and with the grade of %c.",SID , Class, age , weight , grade);
    }
}