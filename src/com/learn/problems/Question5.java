package com.learn.problems;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Question5 {
    int[] A;
    public Question5(int[] A) {
        this.A = A;
    }
    public void getTimeTaken() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<int[]>sortArrayTask = new MergeTask(A);
        int[] result = sortArrayTask.invoke();
        forkJoinPool.shutdown();
        long endTime = System.currentTimeMillis();
        if(Question5.isSorted(result)) {
            System.out.println("given array sorted");
        }
        else {
            System.out.println("something went wrong while sorting array");
        }
        System.out.println("Time taken in sorting array using insertion sort by partition with two threads: " + (endTime - startTime) + " milliseconds");
    }
    public static class MergeTask extends RecursiveTask<int[]> {
        private static final int THRESHOLD = 100;
        private final int[] array;

        public MergeTask(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            if (array.length < THRESHOLD) {
                return insertionSort(array);
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
            return merge(left.join(), right.join());
        }
        private int[] insertionSort(int[] A) {
            int n = A.length;
            int index = 1;
            while(index < n) {
                int element = A[index];
                for(int i = 0;i <= index;i++) {
                    if(A[i] > element) {
                        int tmp = A[i];
                        A[i] = element;
                        element = tmp;
                    }
                }
                index++;
            }
            return A;
        }
    }
    public static int[] merge(int[] a, int[] b) {

        int[] answer = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while(i < a.length && j < b.length)
            answer[k++] = a[i] < b[j] ? a[i++] : b[j++];

        while(i < a.length)
            answer[k++] = a[i++];

        while(j < b.length)
            answer[k++] = b[j++];

        return answer;
    }

    static boolean isSorted(int[] array) {
        for(int i = 1; i < array.length; i++) {
            if(array[i-1] > array[i]) return false;
        }
        return true;
    }
}

