import java.util.Scanner;
import java.util.Arrays;

public class SolutionE {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int L = input.nextInt();
            int n = input.nextInt();
            int m = input.nextInt();
            int[] Points = new int[n+1];
            for (int i = 0; i < n; i++) {
                Points[i] = input.nextInt();
            }
            Points[n]= L;
            Arrays.sort(Points);
            int maxLength = MaxLength(L, m, Points);
            System.out.println(maxLength);
        }
    }
    public static int requiredRunners(int L, int maxLength, int[] Points,int m) {
        int count = 1;
        int lastPosition = 0;
        if (Points[0] > maxLength) {
            return m + 1;
        }
        for(int i=0; i<Points.length;) {
            if (Points[i] - lastPosition > maxLength) {
                count++;
                if(count > m) {
                    return m + 1;
                }
                lastPosition = Points[i - 1];
            }else {
                i++;
            }
        }
        return count;
    }

    public static int MaxLength(int L, int m, int[] Points) {

        int left = L/m;
        int right = L;
        int result = L/m;

        while (left <= right) { // 注意这里改为 <=
            int mid = left + (right - left) / 2;  // 避免整数溢出
            int runners = requiredRunners(L, mid, Points, m);
            if (runners <= m) {
                result = mid;
                right = mid-1 ;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
