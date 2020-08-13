package dev.alnat.practice.tasks.order;

import java.util.*;

/**
 * Тут 2 похода - хранить или сразу в отсортированом виде, или сортировать перед печатью
 * Проще второй, да и по памяти меньше (и сильно - почти в 2 раза), но первый выигрывает по времени
 *
 * Реализуем второй через компоратор при печати
 *
 * А при первом варианте - стоит хранить LinkedList и добавлять вручную находя место,
 * куда вставить в существующий List новый элемент
 *
 * В теории можно раскладывать в Map вообще при toString, а хранить просто в виде строки.
 * А если не нужна именно структура данных, а просто преобразователь - можно даже попробовать в один stream pipeline упихнуть
 * @see dev.alnat.practice.tasks.order.Processor#process(String)
 *
 * Created by @author AlNat on 13.08.2020.
 * Licensed by Apache License, Version 2.0
 */
public class Data {

    /**
     * Внутреннее хранилище данных
     *
     * Ключ - символ в нижнем регистре
     * Значение - список слов
     */
    private final Map<Character, List<String>> data;


    public Data() {
        data = new HashMap<>();

        // Для каждого символа кириллицы в нижнем регистре добавляем пустой список слов
        for (int i = 'a'; i < 'я'; i++) {
            data.put((char) i, new ArrayList<>());
        }
    }

    public Data(String words) {
        this();

        // Бьем на части и добавляем по слову
        Arrays.stream(words.split(" ")).forEach(this::append);
    }

    /**
     * Метод добавления слов в текущий объект
     * @param newWord новое слово
     */
    public void append(String newWord) {
        // Получили первый символ и нашли его список
        char firstSymbol = Character.toLowerCase(newWord.charAt(0));
        List<String> words = data.get(firstSymbol);

        // После чего добавляем его в этот список
        words.add(newWord);
    }

    /**
     * Переопределяем метод
     *
     * Пишем в строу в виде  c = [list]
     * Для этого оборачиваем внешний в StringJoiner
     * И внутренний тоже, но сам символ и знак = через буфер
     *
     * @return готовая строка
     */
    @Override
    public String toString() {
        StringJoiner g = new StringJoiner(", ", "[", "]");
        // Идем по всем символам
        for (int i = 'a'; i < 'я'; i++) {
            // Получили список слов, начинающиеся с этого символа
            // Если их меньше 2 - не берем
            List<String> words = data.get((char) i);
            if (words.size() < 2) continue;

            // Иначе сортируем и добавляем их в нужном формате
            StringBuilder buffer = new StringBuilder(String.valueOf((char) i));
            buffer.append('=');
            StringJoiner j = new StringJoiner(", ", "[", "]");
            words.stream().sorted(lengthDescComparator).forEach(j::add);
            g.add(buffer.append(j));
        }
        return g.toString();
    }

    /**
     * Определили нужный компоратор
     * Сортируем строки по их длинне, но если длинна совпадает - сортируем лексикографически
     */
    private final Comparator<String> lengthDescComparator = (o1, o2) -> {
        if (o1.length() == o2.length()) return o1.compareTo(o2);
        return o1.length() <= o2.length() ? 1 : -1;
    };

}
