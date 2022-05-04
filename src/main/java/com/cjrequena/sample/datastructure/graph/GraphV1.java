package com.cjrequena.sample.datastructure.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphV1 {

  private Map<Vertex, Set<Vertex>> adjacencyVerticesMap;

  public GraphV1() {
    this.adjacencyVerticesMap = new HashMap<>();
  }

  @Data
  @EqualsAndHashCode
  public static class Vertex {
    private String label;
    public Vertex(String label) {
      this.label = label;
    }
  }

  @Data
  @EqualsAndHashCode
  public static class Edge {
    private Vertex source;
    private Vertex destination;

    public Edge(Vertex source, Vertex destination) {
      this.source = source;
      this.destination = destination;
    }
  }

  void addEdge(Edge edge) {
    if (!adjacencyVerticesMap.containsKey(edge.getSource())) {
      adjacencyVerticesMap.put(edge.getSource(), new HashSet<>());
    }
    if (!adjacencyVerticesMap.containsKey(edge.getDestination())) {
      adjacencyVerticesMap.put(edge.getDestination(), new HashSet<>());
    }
    adjacencyVerticesMap.get(edge.getSource()).add(edge.getDestination());
    adjacencyVerticesMap.get(edge.getDestination()).add(edge.getSource());
  }

  public void printPath() {
    for (Vertex vertex : adjacencyVerticesMap.keySet()) {
      for (Vertex neighbor : adjacencyVerticesMap.get(vertex)) {
        System.out.print(vertex.getLabel() + " -> ");
        System.out.println(neighbor.getLabel() + " ");
      }
    }
  }

  public static void main(String[] args) {

    GraphV1 graph = new GraphV1();

    //construct vertices
    Vertex a = new Vertex("a");
    Vertex b = new Vertex("b");
    Vertex c = new Vertex("c");
    Vertex d = new Vertex("d");
    Vertex e = new Vertex("e");

    graph.addEdge(new Edge(a,b));
    graph.addEdge(new Edge(b,c));
    graph.addEdge(new Edge(b,d));
    graph.addEdge(new Edge(c,e));
    graph.addEdge(new Edge(b,a));

    graph.printPath();
  }
}
