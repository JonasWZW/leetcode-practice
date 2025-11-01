package algo.codetop;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: jonas
 * @CreateTime: 2025-11-01  22:19
 * @Description: TODO
 * @Version: 1.0
 */
public class MinHeap {
    int capacity;
    int size;
    int[] heap;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        this.capacity = this.capacity * 2;
        heap = Arrays.copyOf(heap, this.capacity);
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftIndex(int index) {
        return index * 2 + 1;
    }

    private int rightIndex(int index) {
        return index * 2 + 2;
    }

    public void insert(int val) {
        if (size == capacity) {
            resize();
        }
        heap[size] = val;
        heapUp(size);
        size++;
    }

    private void heapUp(int index) {
        int parentIndex = parentIndex(index);
        while (index > 0 && heap[parentIndex] > heap[index]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentIndex(parentIndex);
        }
    }

    private void swap(int index, int parentIndex) {
        int k = heap[index];
        heap[index] = heap[parentIndex];
        heap[parentIndex] = k;
    }

    public int pop() {
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapDown(0);
        return min;
    }

    private void heapDown(int i) {
        int leftIndex = leftIndex(i);
        int rightIndex = rightIndex(i);
        int min = i;
        if (leftIndex < size && heap[leftIndex] < heap[min]) {
            min = leftIndex;
        }
        if (rightIndex < size && heap[rightIndex] < heap[min]) {
            min = rightIndex;
        }

        if (min != i) {
            swap(i, min);
            heapDown(min);
        }

    }
}
