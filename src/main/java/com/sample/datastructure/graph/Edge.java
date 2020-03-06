package com.sample.datastructure.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode
public class Edge {

  private Vertex vertex1;
  private Vertex vertex2;

  public Edge(Vertex vertex1, Vertex vertex2) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
  }
}
