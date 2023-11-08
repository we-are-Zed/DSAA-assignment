import java.util.Scanner;

public class MedianUsingHeaps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int n = sc.nextInt();
            MaxHeap maxHeap = new MaxHeap(n);
            MinHeap minHeap = new MinHeap(n);

            for (int i = 1; i <= n; i++) {
                int num = sc.nextInt();
                addNumber(num, maxHeap, minHeap);
                balanceHeaps(maxHeap, minHeap);

                if (i % 2 != 0) {
                    System.out.print(maxHeap.peek() + " ");
                }
            }
            System.out.println();
        }
    }

    private static void addNumber(int num, MaxHeap maxHeap, MinHeap minHeap) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
    }

    private static void balanceHeaps(MaxHeap maxHeap, MinHeap minHeap) {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    static class MaxHeap {
        int[] arr;
        int size;

        MaxHeap(int capacity) {
            arr = new int[capacity + 1];
            size = 0;
        }

        void add(int value) {
            arr[++size] = value;
            swim(size);
        }

        int poll() {
            int max = arr[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        int peek() {
            return arr[1];
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        private void swim(int k) {
            while (k > 1 && arr[k] > arr[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && arr[j] < arr[j + 1]) j++;
                if (arr[k] >= arr[j]) break;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    static class MinHeap {
        int[] arr;
        int size;

        MinHeap(int capacity) {
            arr = new int[capacity + 1];
            size = 0;
        }

        void add(int value) {
            arr[++size] = value;
            swim(size);
        }

        int poll() {
            int min = arr[1];
            swap(1, size--);
            sink(1);
            return min;
        }

        int peek() {
            return arr[1];
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return size;
        }

        private void swim(int k) {
            while (k > 1 && arr[k] < arr[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && arr[j] > arr[j + 1]) j++;
                if (arr[k] <= arr[j]) break;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
