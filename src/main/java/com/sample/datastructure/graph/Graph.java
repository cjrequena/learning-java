package com.sample.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

  private Map<Vertex, List<Vertex>> adjacencyVerticesMap;

  public Graph() {
    this.adjacencyVerticesMap = new HashMap<Vertex, List<Vertex>>();
  }

  public List<Vertex> getAdjacencyVertices(String name) {
    return adjacencyVerticesMap.get(new Vertex(name));
  }

  void addVertex(String name) {
    adjacencyVerticesMap.putIfAbsent(new Vertex(name), new ArrayList<>());
  }

  void removeVertex(String name) {
    Vertex v = new Vertex(name);
    adjacencyVerticesMap.values().stream().forEach(e -> e.remove(v));
    adjacencyVerticesMap.remove(new Vertex(name));
  }

  void removeEdge(Edge edge) {

    List<Vertex> eV1 = adjacencyVerticesMap.get(edge.getVertex1());
    List<Vertex> eV2 = adjacencyVerticesMap.get(edge.getVertex2());
    if (eV1 != null)
      eV1.remove(edge.getVertex2());
    if (eV2 != null)
      eV2.remove(edge.getVertex1());
  }

  void addEdge(Edge edge) {
    adjacencyVerticesMap.get(edge.getVertex1()).add(edge.getVertex2());
    adjacencyVerticesMap.get(edge.getVertex2()).add(edge.getVertex1());
  }

  public String printGraph() {
    StringBuffer sb = new StringBuffer();
    for (Vertex v : adjacencyVerticesMap.keySet()) {
      sb.append(v);
      sb.append(adjacencyVerticesMap.get(v));
    }
    return sb.toString();
  }
}
