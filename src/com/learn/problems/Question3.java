package com.learn.problems;

public class Question3 {
    int[] randomArray;
    public Question3(int[] array) {
        randomArray = array;
    }
    private void insertionSort(int[] A) {
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
    }
    private void merge() throws InterruptedException {

        int n = randomArray.length;
        int mid = n/2;
        int[] A = new int[mid];
        int[] B = new int[n-mid];

        for(int i = 0;i < mid;i++) {
            A[i] = randomArray[i];
        }
        for(int i = mid;i + mid < n;i++) {
            B[i-mid] = randomArray[i];
        }
        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                insertionSort(A);
            }
        });
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                insertionSort(B);
            }
        });
        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        int i = 0,j = 0, k = 0;
        while(i < A.length && j < B.length) {
            if(A[i] < B[j]) {
                randomArray[k++] = A[i++];
            }
            else {
                randomArray[k++] = B[j++];
            }
        }
        while(i < A.length) {
            randomArray[k++] = A[i++];
        }
        while(j < B.length) {
            randomArray[k++] = B[j++];
        }
    }
    public void getTimeTaken() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        merge();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort by partition with two threads: " + (endTime - startTime) + " milliseconds");
    }
}
