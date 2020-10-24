package com.learn;

import com.learn.problems.*;
import com.learn.util.RandomArrayGenerator;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        RandomArrayGenerator randomArrayGenerator = new RandomArrayGenerator(n);
        int[] A = randomArrayGenerator.getRandomArray();
        Question1 question1 = new Question1(A);
        question1.getTimeTaken();
        int[] B = randomArrayGenerator.getRandomArray();
        Question2 question2 = new Question2(B);
        question2.getTimeTaken();
        int[] C = randomArrayGenerator.getRandomArray();
        Question3 question3 = new Question3(C);
        question3.getTimeTaken();
        int[] D = randomArrayGenerator.getRandomArray();
        Question4 question4 = new Question4(D);
        question4.getTimeTaken();
        int[] E = randomArrayGenerator.getRandomArray();
        Question5 question5 = new Question5(E);
        question5.getTimeTaken();
    }
    public static void printArray(int[] A) {
        for(int i = 0;i < A.length;i++) {
            System.out.print(A[i] + " ");
            if(i > 0 && i % 10 == 0) {
                System.out.println(" ");
            }
        }
    }
}
