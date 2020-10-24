package com.learn.problems;

public class Question2 {
    int[] randomArray;
    public Question2(int[] array) {
        this.randomArray = array;
    }

    private void insertionSort() {
        int n = randomArray.length;
        int index = 1;
        while(index < n) {
            int element = randomArray[index];
            for(int i = 0;i <= index;i++) {
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
    private void merge() {

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
        //divide into two parts
        Question2 left = new Question2(A);
        Question2 right = new Question2(B);

        left.insertionSort();
        right.insertionSort();
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
    public void getTimeTaken() {
        long startTime = System.currentTimeMillis();
        merge();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort by partition: " + (endTime - startTime) + " milliseconds");
    }
}
