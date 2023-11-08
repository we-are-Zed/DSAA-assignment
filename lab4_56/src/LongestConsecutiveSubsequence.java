import java.util.Scanner;

class Deque {
    private int[] arr;
    private int size, capacity, head, tail;

    public Deque(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int value) {
        arr[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
    }

    public int peekFirst() {
        return arr[head];
    }

    public int peekLast() {
        return arr[(tail - 1 + capacity) % capacity];
    }

    public void pollFirst() {
        head = (head + 1) % capacity;
        size--;
    }

    public void pollLast() {
        tail = (tail - 1 + capacity) % capacity;
        size--;
    }
}

public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        Deque minQueue = new Deque(n);
        Deque maxQueue = new Deque(n);
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < n; right++) {
            while (!minQueue.isEmpty() && minQueue.peekLast() > arr[right]) {
                minQueue.pollLast();
            }
            minQueue.addLast(arr[right]);

            while (!maxQueue.isEmpty() && maxQueue.peekLast() < arr[right]) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(arr[right]);

            while (!minQueue.isEmpty() && !maxQueue.isEmpty() && (maxQueue.peekFirst() - minQueue.peekFirst()) > k) {
                if (arr[left] == minQueue.peekFirst()) {
                    minQueue.pollFirst();
                }
                if (arr[left] == maxQueue.peekFirst()) {
                    maxQueue.pollFirst();
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
