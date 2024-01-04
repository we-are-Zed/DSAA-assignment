import java.util.Scanner;

public class Main {
    private int[] heap;
    private int size;

    public Main(int capacity) {
        heap = new int[capacity];
    }

    public int insert(int value) {
        heap[size] = value;
        return bubbleUp(size++);
    }

    private int bubbleUp(int index) {
        int swapCount = 0;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index] > heap[parentIndex]) {
                swap(heap, index, parentIndex);
                swapCount++;
                index = parentIndex;
            }  else {
                break;
            }
        }
        return swapCount;
    }
    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Main maxHeap = new Main(n);
        int[] swapCounts = new int[n];

        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            swapCounts[i] = maxHeap.insert(num);
        }

        for (int count : swapCounts) {
            System.out.print(count + " ");
        }
    }
    void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            array[i] = temp[k];
        }
    }
    void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
}

