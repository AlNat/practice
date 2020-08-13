package dev.alnat.practice.sort;

/**
 * Сортировка пузырьком
 *
 * Сортирует по возрастанию
 *
 * Created by @author AlNat on 03.02.2020.
 * Licensed by Apache License, Version 2.0
 */
public class BubbleSort extends AbstractSort {

    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    swap(array, i, j);
                }
            }
        }

        return array;
    }

}
