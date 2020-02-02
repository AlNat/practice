package dev.alnat.practice.graph;

import java.util.*;

/**
 * Основной класс для создания графов
 *
 * Created by @author AlNat on 02.02.2020.
 * Licensed by Apache License, Version 2.0
 */
public class Graph {

    /**
     * Корневая вершина
     */
    private Vertex rootVertex;

    /**
     * Набор вершин в данном графе
     */
    private List<Vertex> vertexList;

    public Graph(Vertex rootVertex, List<Vertex> vertexList) {
        this.rootVertex = rootVertex;
        this.vertexList = vertexList;
    }


    public Vertex bfs(String searchValue) {
        return bfs(rootVertex, searchValue);
    }

    /**
     * Итеративный вариант поиска в ширину
     *
     * Принимаем начальную вершину поиска и добавляем ее в список на просмотр
     * Пока список не пуст - идем по нему
     *  Если вершина - нужная = вернули ее
     *  Иначе добавили все дочерние вершины в список
     *
     * @param root корневая вершина
     * @param searchValue искомое значение
     * @return найеная вершина или null если ее нет
     */
    public Vertex bfs(Vertex root, String searchValue) {
        Queue<Vertex> vertexToVisit = new LinkedList<>();
        vertexToVisit.add(root);

        while (!vertexToVisit.isEmpty()) {
            // Вытащили верхний элемент очереди
            Vertex currentVertex = vertexToVisit.poll();

            System.out.println("Check " + currentVertex.getName() + " vertex");

            // Если это нужная вершина - возвращаем ее
            if (searchValue.equals(currentVertex.getName())) {
                return currentVertex;
            }

            // Иначе начинаем искать дальше
            // Добавляем все дочерние вершины в очередь на поиск и проверяем первую вершину сверху
            vertexToVisit.addAll(currentVertex.getAllChildren());
        }

        return null;
    }



    public Vertex dfs(String searchValue) {
        return dfs(rootVertex, searchValue);
    }

    /**
     * Поиск в глубину рекурсивный вариант
     *
     * Проверяем вершину, если она - возвращаем ее
     * Иначе помечаем как пройденую и ищем в ее потоках рекурсивно
     *
     * @param root начало поисков
     * @param searchValue искомое значени
     * @return вершину или null, если такой не найдено
     */
    public Vertex dfs(Vertex root, String searchValue) {
        Set<Vertex> visited = new HashSet<>();

        System.out.println("Check " + root.getName() + " vertex");
        if (root.getName().equals(searchValue)) {
            return root;
        }
        visited.add(root);

        for (Vertex sub : root.getAllChildren()) {
            if (!visited.contains(sub)) {
                Vertex v = dfs(sub, searchValue);
                if (v != null) {
                    return v;
                }
            }
        }

        return null;
    }

    // TODO Алгоритм Дийкстры https://e-maxx.ru/algo/dijkstra

    // TODO Общий предок https://e-maxx.ru/algo/lca

}
