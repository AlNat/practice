package dev.alnat.practice.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Вершина
 *
 * Created by @author AlNat on 02.02.2020.
 * Licensed by Apache License, Version 2.0
 */
public class Vertex {

    /**
     * Набор ребер, исходящий из этой вершины
     */
    private List<Edge> edgeList;

    private String name;


    public Vertex() {
        edgeList = new ArrayList<>();
    }

    public Vertex(String name) {
        this.name = name;
        edgeList = new ArrayList<>();
    }

    
    public List<Vertex> getAllChildren() {
        return edgeList.stream()
                .map(Edge::getTo)
                .collect(Collectors.toList());
//        List<Vertex> subling = new LinkedList<Vertex>();
//        for (Edge e : edgeList) {
//            subling.add(e.getTo());
//        }
//        return subling;
    }


    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
