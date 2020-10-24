package com.learn.problems;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;



class MergeTask extends RecursiveAction {
    private static final int THRESHOLD = 100;
    int[] array;

    public MergeTask(int[] array) {
        this.array = array;
    }
    @Override
    protected void compute() {
        if (array.length < THRESHOLD) {
            insertionSort();
            return;
        }
        int n = array.length;
        int mid = n/2;
        int[] A = new int[mid];
        int[] B = new int[n-mid];

        for(int i = 0;i < mid;i++) {
            A[i] = array[i];
        }
        for(int i = mid;i + mid < n;i++) {
            B[i-mid] = array[i];
        }

        MergeTask left = new MergeTask(A);
        MergeTask right = new MergeTask(B);
        invokeAll(left, right);
        left.join();
        right.join();
        merge(left.array, right.array);
    }
    private void insertionSort() {
        int n = array.length;
        int index = 1;
        while(index < n) {
            int element = array[index];
            for(int i = 0;i < index;i++) {
                if(element < array[i]) {
                    int tmp = array[i];
                    array[i] = element;
                    element = tmp;
                }
            }
            array[index] = element;
            index++;
        }
    }
    public  void merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;

        while(i < a.length && j < b.length)
            array[k++] = a[i] < b[j] ? a[i++] : b[j++];

        while(i < a.length)
            array[k++] = a[i++];

        while(j < b.length)
            array[k++] = b[j++];
    }
}
public class Question5 {
    int[] A;
    public Question5(int[] A) {
        this.A = A;
    }
    public void getTimeTaken() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> forkJoinTask = new ForkJoinTask<Void>() {
            @Override
            public Void getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Void aVoid) {
            }

            @Override
            protected boolean exec() {
                return true;
            }
        };
        forkJoinPool.invoke(forkJoinTask);
        forkJoinPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort by partition with two threads: " + (endTime - startTime) + " milliseconds");
    }

    static boolean isSorted(int[] array) {
        for(int i = 1; i < array.length; i++) {
            if(array[i-1] > array[i]) return false;
        }
        return true;
    }
}

