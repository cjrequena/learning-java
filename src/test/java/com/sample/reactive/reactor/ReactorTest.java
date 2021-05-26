package com.sample.reactive.reactor;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@Log4j2
class ReactorTest {

  @BeforeAll
  static void initAll() {
  }

  @Test
  void mono() {
    //Reactor.mono().subscribe( x -> log.info(x));
    final Mono<String> mono = Reactor.mono();
    StepVerifier.create(mono)
      .expectNext("John")
      .expectComplete()
      .verifyThenAssertThat()
      .tookLessThan(Duration.ofMillis(1050));
  }

  @Test
  void flux() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<String> flux = Reactor.flux();
    StepVerifier
      .create(flux)
      .expectNext("John")
      .expectNextMatches(name -> name.startsWith("Mo"))
      .expectNext("Mark", "Cloe", "Frank", "Casper", "Olivia","Emily")
      .expectNext("Cate")
      .expectComplete()
      .verify();
  }
}
