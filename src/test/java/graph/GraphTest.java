package graph;

import dev.alnat.practice.graph.Edge;
import dev.alnat.practice.graph.Graph;
import dev.alnat.practice.graph.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by @author AlNat on 02.02.2020.
 * Licensed by Apache License, Version 2.0
 */
public class GraphTest {

    /**
     * Генерируем тестовый граф
     *          1
     *        /  \
     *       2    3
     *     /  \    \
     *    4    5    6
     */
    public Graph generateTestGraph() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex fourth = new Vertex("4");
        Vertex fifth = new Vertex("5");
        Vertex sixth = new Vertex("6");


        Edge firstEdge = new Edge(first, second);
        Edge secondEdge = new Edge(first, third);
        first.setEdgeList(Arrays.asList(firstEdge, secondEdge));

        Edge thirdEdge = new Edge(second, fourth);
        Edge fourthEdge = new Edge(second, fifth);
        second.setEdgeList(Arrays.asList(thirdEdge, fourthEdge));

        Edge fifthEdge = new Edge(third, sixth);
        third.setEdgeList(Collections.singletonList(fifthEdge));

        return new Graph(first, Arrays.asList(first, second, third, fourth, fifth, sixth));
    }

    @Test
    public void BFSTest() {
        Graph testGraph = generateTestGraph();

        System.out.println("Search 3 in BFS");
        Assert.assertEquals("3", testGraph.bfs("3").getName());

        System.out.println("\nSearch 6 in BFS");
        Assert.assertEquals("6", testGraph.bfs("6").getName());

        System.out.println("\nSearch 12 in BFS");
        Assert.assertNull(testGraph.bfs("12"));
    }

    @Test
    public void DFSTest() {
        Graph testGraph = generateTestGraph();

        System.out.println("Search 3 in DFS");
        Assert.assertEquals("3", testGraph.dfs("3").getName());

        System.out.println("\nSearch 6 in DFS");
        Assert.assertEquals("6", testGraph.dfs("6").getName());

        System.out.println("\nSearch 12 in BFS");
        Assert.assertNull(testGraph.dfs("12"));
    }


}
