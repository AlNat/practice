package dev.alnat.practice.sort;


/**
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public class QuickSort extends AbstractSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        // Если сортируется один элемент - выходим
        if (from >= to) return array;

        // Ищем опорный элемент и осуществляем разбиение
        int index = partition(array, from, to);
        sort(array, from, index);
        sort(array, index + 1, to);

        return array;
    }

    /**
     * Этап нахождение опорного элемента и помещение его в середину,
     * сортируя таким образом оставшийся массив на 2 части - больше опорного или меньше
     */
    private static int partition(int[] array, int from, int to) {
        // Случайно выбираем опорный элемент
        int mid = array[getRandomInt(from, to)];

        int i = from;
        int j = to;

        // И проходим с помощью двух указателей с обоих концов массива
        // Тем самым перемещая их относительно опорного элемента
        while (i <= j) {
            // Пропускаем те элементы что точно меньше опорного, расположенные слева от него
            while (array[i] < mid) i++;

            // Аналогично те что точно больше него справо от него
            while (array[j] > mid) j--;

            if (i >= j) break;

            // Теперь на оставшемся подмассиве меняем местами все элементы
            swap(array, i++, j--);
        }

        return j;
    }

}
