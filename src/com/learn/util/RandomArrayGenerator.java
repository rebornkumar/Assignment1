package com.learn.util;

import java.util.Random;

public class RandomArrayGenerator {
    int[] randomArray;
    int n;
    public RandomArrayGenerator(int n) {
        this.n = n;
        randomArray = new int[n];
    }
    public int[] getRandomArray() {
        Random random = new Random();
        for(int i = 0;i < n;i++) {
            randomArray[i] = random.nextInt();
        }
        return randomArray;
    }
}
