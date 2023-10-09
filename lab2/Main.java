import java.util.Scanner;
public class Main {
    static class Plant {
        long height;
        long strength;
        long growth;
        public Plant(long height, long strength) {
            this.height = height;
            this.strength = strength;
            this.growth = height - strength;
        }
    }
    public static long maxStrength(Plant[] plants, int p, int q) {
        MergeSort(plants, 0, plants.length - 1);
        long[] total=new long[plants.length];
        if (q == 0) {
            long totalStrength = 0;
            for (Plant plant : plants) {
                totalStrength += plant.strength;
            }
            return totalStrength;
        }
        int index = -1;
        for (int i = 0; i < plants.length && q > 1; i++) {
            if (plants[i].growth>0) {
                plants[i].strength = plants[i].height;
                q--;
                index = i;
            }
        }
        long Total=0;
        for(int j=0;j<plants.length;j++)
        {
            Total+=plants[j].strength;
        }
        for(int i=0;i<plants.length;i++)
        {
            total[i]=Total;
        }
        long maxGlobalGain = Long.MIN_VALUE;
        for (int i = 0; i < plants.length; i++) {
            long originalStrength = plants[i].strength;
                plants[i].height =plants[i].height* (long) Math.pow(2, p);

            if(plants[i].height>plants[i].strength)
            {
                total[i]+=(plants[i].height-plants[i].strength);
            }
            if (i <= index &&( plants[index+1].growth>0)&&q>0) {
                total[i] += plants[index+1].height - plants[index+1].strength;
            }
            if(total[i]>maxGlobalGain)
            {
                maxGlobalGain=total[i];
            }
            plants[i].strength = originalStrength;
            plants[i].height = plants[i].height / (long) Math.pow(2, p);
        }
        return maxGlobalGain;
    }

    public static void MergeSort(Plant[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
            Merge(arr, l, m, r);
        }
    }

    public static void Merge(Plant[] arr, int l, int m, int r) {
        Plant[] left = new Plant[m - l + 1];
        Plant[] right = new Plant[r - m];
        for (int i = 0; i < m - l + 1; i++) {
            left[i] = arr[l + i];
        }
        for (int i = 0; i < r - m; i++) {
            right[i] = arr[m + 1 + i];
        }
        int i = 0, j = 0, k = l;
        while (i < m - l + 1 && j < r - m) {
            if (left[i].growth >= right[j].growth) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < m - l + 1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < r - m) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        Plant[] plants = new Plant[n];
        for (int i = 0; i < n; i++) {
            plants[i] = new Plant(scanner.nextLong(), scanner.nextLong());
        }
        System.out.println(maxStrength(plants, p, q));
    }
}
