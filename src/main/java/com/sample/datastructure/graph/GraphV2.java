package com.sample.datastructure.graph;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

public class GraphV2 {

    private Set<Vertex> vertices;

    public GraphV2() {
        vertices = new HashSet<>();
    }

    boolean addVertex(Vertex vertex){
        return vertices.add(vertex);
    }

    @Data
    public static class Vertex{
        private String label;
        private Set<Edge> edges; //collection of edges to neighbors

        public Vertex(String label) {
            this.label = label;
            edges = new HashSet<>();
        }

        boolean addEdge(Edge edge){
            return edges.add(edge);
        }
    }

    public static class Edge{
        private Vertex destination;
        private int weight;

        public Edge(Vertex destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public void printPath() {
        for (Vertex vertex : vertices) {
            for (Edge neighbor : vertex.edges) {
                System.out.print(vertex.label + " -> ");
                System.out.println(neighbor.destination.label + "(" + neighbor.weight +") ");
            }
        }
    }

    public static void main(String[] args) {

        GraphV2 graph = new GraphV2();

        //construct vertices
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");

        //to make the graph un directed use the same weight
        //both ways
        v1.addEdge(new Edge(v2, 1)); //connect v1 v2
        v2.addEdge(new Edge(v1, 1));

        v2.addEdge(new Edge(v3, 2)); //connect v2 v3
        v3.addEdge(new Edge(v2, 2));

        v2.addEdge(new Edge(v4, 3)); //connect v2 v4
        v4.addEdge(new Edge(v2, 3));

        v4.addEdge(new Edge(v5, 1)); //connect v4 v5
        v5.addEdge(new Edge(v4, 1));

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.printPath();
    }
}
