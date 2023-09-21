import java.util.Scanner;
import java.util.Arrays;
public class SolutionF {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long X1 = input.nextLong();
        long Y1 = input.nextLong();
        long X2 = input.nextLong();
        long Y2 = input.nextLong();
        long k= input.nextLong();
        String moves= input.next();
        long result=Time(X1,Y1,X2,Y2,moves.length(),moves);
        System.out.println(result);
    }
    public static long Time(long x1, long y1, long x2, long y2, int t, String s)
    {
        long left=0 ,right=Long.MAX_VALUE;
        while (left<=right)
        {
            long mid=left+(right-left)/2;
            if (check(x1,y1,x2,y2,mid,t,s))
            {
                right=mid-1;
            }
            else
            {
                left=mid+1;
            }
        }
        return left == Long.MAX_VALUE ? -1 : left;

    }
    public static boolean check(long x1, long y1, long x2, long y2, long mid, int t, String s) {
        long dx = 0, dy = 0;

        // 计算完整周期数
        long zhouqi = mid / t;

        // 计算Neko在一个完整周期中的总移动距离
        for (int i = 0; i < t; i++) {
            char move = s.charAt(i);
            if (move == 'U') dy++;
            if (move == 'D') dy--;
            if (move == 'L') dx--;
            if (move == 'R') dx++;
        }
        // 将Neko在一个完整周期中的移动距离乘以完整周期数，得到Neko在所有完整周期中的总移动距离
        dx *= zhouqi;
        dy *= zhouqi;

        // 计算在最后一个不完整周期中Neko的移动距离
        for (int i = 0; i < mid % t; i++) {
            char move = s.charAt(i);
            if (move == 'U') dy++;
            if (move == 'D') dy--;
            if (move == 'L') dx--;
            if (move == 'R') dx++;
        }
        x2 += dx;
        y2 += dy;
        long distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
        return distance <= mid;
    }

}