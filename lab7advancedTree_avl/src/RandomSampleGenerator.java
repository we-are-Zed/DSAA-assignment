import java.io.PrintWriter;
import java.util.Random;

public class RandomSampleGenerator {
    public static void main(String[] args) {
        Random rand = new Random();
        try (PrintWriter writer = new PrintWriter("samples.txt", "UTF-8")) {
            for (int i = 0; i < 100000; i++) {
                // 生成 n 和 k
                int n = rand.nextInt(191) + 10;  // 10 到 200
                int k = rand.nextInt(n - 9) + 10; // 10 到 n

                // 写入第一行
                writer.println(n + " " + k);

                // 写入第二行
                for (int j = 0; j < n; j++) {
                    int num = rand.nextInt(100) - 50; // -10000 到 10000
                    writer.print(num + " ");
                }
                writer.println();

                // 写入第三行
                for (int j = 0; j < n - k + 1; j++) {
                    int num = rand.nextInt(k) + 1; // 1 到 k
                    writer.print(num + " ");
                }
                writer.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
