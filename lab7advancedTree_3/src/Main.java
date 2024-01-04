import java.util.*;
public class Main {
    static class maxHeap{
       int size;
       long[] heap;
       maxHeap(int capacity){
           heap = new long[capacity];
       }
       public void insert(long value){
              heap[size] = value;
              bubbleUp(size++);
       }
       public void bubbleUp(int index){
           while(index > 0){
               int parentIndex = (index - 1) / 2;
               if(heap[index] > heap[parentIndex]){
                   swap(heap, index, parentIndex);
                   index = parentIndex;
               }else{
                   break;
               }
           }
       }
        public void bubbleDown(int index) {
            while (index < size) {
                int leftChildIndex = (index * 2) + 1;
                int rightChildIndex = (index * 2) + 2;
                if (leftChildIndex >= size) {
                    break;
                }
                int maxIndex = leftChildIndex;
                if (rightChildIndex < size && heap[rightChildIndex] > heap[leftChildIndex]) {
                    maxIndex = rightChildIndex;
                }
                if (heap[index] < heap[maxIndex]) {
                    swap(heap, index, maxIndex);
                    index = maxIndex;
                } else {
                    break;
                }
            }
        }

        public void swap(long[] arr, int index1, int index2){
              long temp = arr[index1];
              arr[index1] = arr[index2];
              arr[index2] = temp;
         }
         public void print(){
           mergeSort(heap, 0, size-1);
                for(int i=0;i<size;i++){
                    System.out.print(heap[i]+" ");
                }
         }
    }

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int m = input.nextInt();
    int k = input.nextInt();
    long[] N = new long[n];
    long[] M= new long[m];
    for(int i=0;i<n;i++){
        N[i] = input.nextInt();
    }
       for(int i=0;i<m;i++){
            M[i] = input.nextInt();
       }
       mergeSort(N, 0, n-1);
         mergeSort(M, 0, m-1);
        maxHeap heap = new maxHeap(k);
       for(int i=0;i<n;i++){
           int index=0;
           for(int j=0;j<m;j++){
               index=j;
               long mul= N[i] * M[j];
                if(heap.size < k){
                     heap.insert(mul);
                }else{
                    if(mul<heap.heap[0]){
                        heap.heap[0] = mul;
                        heap.bubbleDown(0);
                    }else if(mul>heap.heap[0]){
                        break;
                    }else {
                        break;
                    }
                }
           }
           if(index==0){
               break;
           }
       }
         heap.print();
    }
    static void merge(long[] array, int left, int mid, int right) {
        long[] temp = new long[right - left + 1];
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
    static void mergeSort(long[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
}