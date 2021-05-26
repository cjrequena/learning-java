package com.sample.reactive.reactor;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Data
public class Reactor {

  public static Mono<String> mono() {
    return Mono.just("John");
  }

  public static Flux<String> flux() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate");
  }
}
