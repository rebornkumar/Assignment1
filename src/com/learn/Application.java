package com.learn;

import com.learn.problems.Question1;
import com.learn.problems.Question2;
import com.learn.problems.Question3;
import com.learn.util.RandomArrayGenerator;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        RandomArrayGenerator randomArrayGenerator = new RandomArrayGenerator(n);
        Question1 question1 = new Question1(randomArrayGenerator.getRandomArray());
        question1.getTimeTaken();
        int[] B = randomArrayGenerator.getRandomArray();
        Question2 question2 = new Question2(B);
        question2.getTimeTaken();
        int[] C = randomArrayGenerator.getRandomArray();
        Question3 question3 = new Question3(C);
        question3.getTimeTaken();
    }
}
