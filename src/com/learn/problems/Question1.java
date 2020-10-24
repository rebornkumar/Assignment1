package com.learn.problems;

public class Question1 {
    int[] randomArray;
    public Question1(int[] array) {
        randomArray = array;
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
    public void getTimeTaken() {
        long startTime = System.currentTimeMillis();
        insertionSort();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort : " + (endTime - startTime) + " milliseconds");
//        for(int i = 0; i < randomArray.length;i++) {
//            System.out.print(randomArray[i] + " ,");
//        }
    }
}
