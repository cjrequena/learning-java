package com.sample.datastructure.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Vertex {
  private String name;

  public Vertex(String name) {
    this.name = name;
  }
}
