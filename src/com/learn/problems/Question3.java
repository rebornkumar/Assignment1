package com.learn.problems;

public class Question3 {
    int[] randomArray;
    public Question3(int[] array) {
        this.randomArray = array;
    }
    private void insertionSort() {
        int n = randomArray.length;
        int index = 1;
        while(index < n) {
            int element = randomArray[index];
            for(int i = 0;i < index;i++) {
                if(element < randomArray[i]) {
                    int tmp = randomArray[i];
                    randomArray[i] = element;
                    element = tmp;
                }
            }
            randomArray[index] = element;
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
        for(int i = mid;i < n;i++) {
            B[i-mid] = randomArray[i];
        }
        Question3 left = new Question3(A);
        Question3 right = new Question3(B);
        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                left.insertionSort();
            }
        });
        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                right.insertionSort();
            }
        });
        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        int i = 0,j = 0, k = 0;
        while(i < left.randomArray.length && j < right.randomArray.length) {
            if(left.randomArray[i] < right.randomArray[j]) {
                randomArray[k++] = left.randomArray[i++];
            }
            else {
                randomArray[k++] = right.randomArray[j++];
            }
        }
        while(i < left.randomArray.length) {
            randomArray[k++] = left.randomArray[i++];
        }
        while(j < right.randomArray.length) {
            randomArray[k++] = right.randomArray[j++];
        }
    }
    public void getTimeTaken() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        merge();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort by partition with two threads: " + (endTime - startTime) + " milliseconds");
    }
}
