package com.learn.problems;


class MyTask implements Runnable {
    static final int THRESHOLD_LIMIT = 1000;
    int[] randomArray;
    MyTask(int[] randomArray) {
        this.randomArray = randomArray;
    }
    @Override
    public void run() {
        if(randomArray.length <= THRESHOLD_LIMIT) {
            insertionSort();
            return;
        }
        else {
            int n = randomArray.length;
            int mid = n/2;
            int[] X = new int[mid];
            int[] Y = new int[n-mid];

            for(int i = 0;i < mid;i++) {
                X[i] = randomArray[i];
            }
            for(int i = mid;i < n;i++) {
                Y[i-mid] = randomArray[i];
            }
            //divide the task
            MyTask left = new MyTask(X);
            MyTask right = new MyTask(Y);
            Thread leftThread = new Thread(left);
            Thread rightThread = new Thread(right);
            leftThread.start();
            rightThread.start();
            try {
                leftThread.join();
            } catch (InterruptedException e) {
            }
            try {
                rightThread.join();
            } catch (InterruptedException e) {
            }
            //merge sorted arrays
            merge(left.randomArray,right.randomArray);
        }
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
    public void merge(int[] a,int[] b) {
        int i = 0, j = 0, k = 0;
        while(i < a.length && j < b.length)
            randomArray[k++] = a[i] < b[j] ? a[i++] : b[j++];

        while(i < a.length)
            randomArray[k++] = a[i++];

        while(j < b.length)
            randomArray[k++] = b[j++];
    }
    public static boolean isSorted(int[] array) {
        for(int i = 1; i < array.length; i++) {
            if(array[i-1] > array[i]) return false;
        }
        return true;
    }
}
public class Question4 {
    int[] randomArray;
    public Question4(int[] randomArray) {
        this.randomArray = randomArray;
    }
    public void getTimeTaken() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        MyTask myTask = new MyTask(randomArray);
        Thread thread = new Thread(myTask);
        thread.start();
        thread.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken in sorting array using insertion sort by partition with two threads: " + (endTime - startTime) + " milliseconds");
    }

}
