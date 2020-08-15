package dev.alnat.practice.sort;

/**
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public class MergeSort extends AbstractSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length);
    }

    private static int[] sort(int[] array, int from, int to) {
        // Если вызов одного элемента из массива - он уже отсортирован
        if (from == to - 1) return array;

        // Иначе рекурсивно делим массив пополам
        int mid = from + (to - from) / 2;

        // Рекурсивно вызываем у подмассивов
        sort(array, from, mid);
        sort(array, mid, to);

        // И сливаем отсортированные массивы
        merge(array, from, mid, to);

        return array;
    }

    /**
     * Метод слияния 2 заранее отсортированных массивов
     * Неявно передаем 2 массива: [from, mid) и [mid, to)
     */
    private static void merge(int[] array, int from, int mid, int to) {
        int[] buffer = new int[to - from];

        int i = from;
        int j = mid;

        // Подряд проверяем оба массива и смотрим - какой из 2 элементов больше
        for (int k = 0; k < to - from; k++) {
            if (i < mid && j < to) {
                if (array[i] < array[j]) {
                    buffer[k] = array[i++];
                } else {
                    buffer[k] = array[j++];
                }
            } else if (i < mid) { // Если дошли до конца второго - перемещаем первый в конце
                buffer[k] = array[i++];
            } else { // Аналогично, если закончился первый - перемещаем второй
                buffer[k] = array[j++];
            }
        }

        // Копируем из буфера в основной массив
        System.arraycopy(buffer, 0, array, from, to - from);
    }

}
