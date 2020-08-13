package sort;

import dev.alnat.practice.sort.BubbleSort;
import dev.alnat.practice.sort.MergeSort;
import dev.alnat.practice.sort.QuickSort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public class SortTest {

    private int[] generateTestData() {
        int l = getRandomInt(10, 100);
        return generateTestData(l);
    }

    private int[] generateTestData(int l) {
        int[] res = new int[l];

        for (int i = 0; i < l; i++) {
            res[i] = getRandomInt(-100, 100);
        }

        return res;
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void print(int[] array) {
        System.out.println("Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }


    @Test
    public void bubbleTest() {
        int[] testData = generateTestData(10);

        System.out.println("Before:");
        print(testData);

        System.out.println("\n\n\nAfter:");
        int[] res = BubbleSort.sort(Arrays.copyOf(testData, testData.length));
        print(res);

        Arrays.sort(testData);

        Assert.assertArrayEquals(testData, res);
    }

    @Test
    public void quickTest() {
        int[] testData = generateTestData(10);

        System.out.println("Before:");
        print(testData);

        System.out.println("\n\n\nAfter:");
        int[] res = QuickSort.sort(Arrays.copyOf(testData, testData.length));
        print(res);

        Arrays.sort(testData);

        Assert.assertArrayEquals(testData, res);
    }


}