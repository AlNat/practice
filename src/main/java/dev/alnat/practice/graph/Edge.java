package dev.alnat.practice.graph;

/**
 * Ребро
 *
 * Created by @author AlNat on 02.02.2020.
 * Licensed by Apache License, Version 2.0
 */
public class Edge {

    private Vertex from;
    private Vertex to;

    private int cost;
    private String desc;

    public Edge() {
    }

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public Vertex getTo() {
        return to;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
