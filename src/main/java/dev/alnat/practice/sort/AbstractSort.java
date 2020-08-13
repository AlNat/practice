package dev.alnat.practice.sort;

import java.util.Random;

/**
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public abstract class AbstractSort {

    protected synchronized static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    protected static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    protected static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + ", ");
        }
        System.out.println("\n");
    }

}
