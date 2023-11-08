import java.util.Scanner;

class MyArrayList {
    private int[] data;
    private int size;

    public MyArrayList(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        int[] temp = new int[newCapacity];
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }
}

class MyDeque {
    private int[] data;
    private int size, head, tail;

    public MyDeque(int capacity) {
        data = new int[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int value) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
    }

    public void pollFirst() {
        head = (head + 1) % data.length;
        size--;
    }

    public void pollLast() {
        tail = (tail - 1 + data.length) % data.length;
        size--;
    }

    public int peekFirst() {
        return data[head];
    }

    public int peekLast() {
        return data[(tail - 1 + data.length) % data.length];
    }

    private void resize(int newCapacity) {
        int[] temp = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(head + i) % data.length];
        }
        data = temp;
        head = 0;
        tail = size;
    }
}

public class magicSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        MyArrayList list = new MyArrayList(50); // 初始化容量为50，它会根据需要扩容

        while (true) {
            int value = scanner.nextInt();
            if (value == -1) {
                break;
            }
            list.add(value);
        }
        scanner.close();

        int result = 0;
        MyDeque deque = new MyDeque(list.size());

        for (int i = 0; i < list.size(); i++) {
            while (!deque.isEmpty() && list.get(deque.peekLast()) <= list.get(i)) {
                deque.pollLast();
            }
            deque.addLast(i);

            while (!deque.isEmpty() && deque.peekFirst() <= i - m) {
                deque.pollFirst();
            }

            if (i >= m - 1) {
                result ^= list.get(deque.peekFirst());
            }
        }

        System.out.println(result);
    }
}
