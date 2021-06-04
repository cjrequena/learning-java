package com.sample.reactive.reactor;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author cjrequena
 */
@Log4j2
class ReactorTest {

  @BeforeAll
  static void initAll() {
  }

  @Test
  void monoJust() {
    //Reactor.mono().subscribe( x -> log.info(x));
    final Mono<String> mono = Reactor.monoJust();
    StepVerifier.create(mono)
      .expectNext("John")
      .expectComplete()
      .verifyThenAssertThat()
      .tookLessThan(Duration.ofMillis(1050));
  }

  @Test
  void fluxJust() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<String> flux = Reactor.fluxJust();
    StepVerifier
      .create(flux)
      .expectNext("John")
      .expectNextMatches(name -> name.startsWith("Mo"))
      .expectNext("Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily")
      .expectNext("Cate")
      .expectComplete()
      .verify();
  }

  @Test
  void monoEmpty() {
    final Mono<String> mono = Reactor.monoEmpty();
    StepVerifier.create(mono)
      .expectComplete()
      .verifyThenAssertThat()
      .tookLessThan(Duration.ofMillis(1050));
  }

  @Test
  void fluxEmpty() {
    final Flux<String> flux = Reactor.fluxEmpty();
    StepVerifier
      .create(flux)
      .expectComplete()
      .verify();
  }

  @Test
  void fluxRepeat() {
    final Flux<String> flux = Reactor.fluxRepeat();
    StepVerifier
      .create(flux)
      .expectNext("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .expectNext("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .expectNext("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .expectNext("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .expectComplete()
      .verify();
  }

  @Test
  void fluxRange() {
    final Flux<Integer> flux = Reactor.fluxRange();
    StepVerifier
      .create(flux)
      .expectNext(0, 1, 2)
      .expectComplete()
      .verify();
  }

  @Test
  void fromIterable() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.fromIterable();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"))
      .expectNext(new FooDTO(2, "Foo2"))
      .expectNext(new FooDTO(3, "Foo3"))
      .expectComplete()
      .verify();
  }

  @Test
  void collectList() {
    final Mono<List<FooDTO>> mono = Reactor.collectList();
    StepVerifier.create(mono)
      .recordWith(ArrayList::new)
      .thenConsumeWhile(v -> v.size() == 3)
      .verifyComplete();
  }

  @Test
  void map() {
    final Flux<FooDTO> flux = Reactor.map();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1 Modified"))
      .expectNext(new FooDTO(2, "Foo2 Modified"))
      .expectNext(new FooDTO(3, "Foo3 Modified"))
      .expectComplete()
      .verify();
  }

  @Test
  void flatMap() {
    final Flux<FooDTO> flux = Reactor.flatMap();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1 Modified"))
      .expectNext(new FooDTO(2, "Foo2 Modified"))
      .expectNext(new FooDTO(3, "Foo3 Modified"))
      .expectComplete()
      .verify();
  }

  @Test
  void groupBy() {
    final Flux<List<FooDTO>> flux = Reactor.groupBy();
    StepVerifier
      .create(flux)
      .expectNext(Arrays.asList(new FooDTO(1, "Foo1"), new FooDTO(1, "Foo2")))
      .expectNext(Arrays.asList(new FooDTO(3, "Foo3")))
      .expectComplete()
      .verify();
  }

  @Test
  void filter() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.filter();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(2, "Foo2"))
      .expectNext(new FooDTO(3, "Foo3"))
      .expectComplete()
      .verify();
  }

  @Test
  void distinct() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.distinct();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"))
      .expectNext(new FooDTO(3, "Foo3"))
      .expectComplete()
      .verify();
  }

  @Test
  void take() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.take();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"))
      .expectNext(new FooDTO(2, "Foo2"))
      .expectComplete()
      .verify();
  }

  @Test
  void takeLast() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.takeLast();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(2, "Foo2"))
      .expectNext(new FooDTO(3, "Foo3"))
      .expectComplete()
      .verify();
  }

  @Test
  void skip() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.skip();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(3, "Foo3"))
      .expectComplete()
      .verify();
  }

  @Test
  void skipLast() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.skipLast();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"))
      .expectComplete()
      .verify();
  }

  @Test
  void merge() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.merge();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectNext(new FooDTO(4, "Foo4"), new FooDTO(5, "Foo5"), new FooDTO(6, "Foo6"))
      .expectComplete()
      .verify();
  }

  @Test
  void merge2() {
    final Flux<Integer> flux = Reactor.merge2();
    StepVerifier.create(flux)
      .expectNext(2)
      .expectNext(4)
      .expectNext(1)
      .expectNext(3)
      .expectNext(5)
      .expectComplete()
      .verify();
  }

  @Test
  void mergeWith() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.mergeWith();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectNext(new FooDTO(4, "Foo4"), new FooDTO(5, "Foo5"), new FooDTO(6, "Foo6"))
      .expectComplete()
      .verify();
  }

  @Test
  void zip() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<List<FooDTO>> flux = Reactor.zip();
    StepVerifier
      .create(flux)
      .expectNext(Arrays.asList(new FooDTO(1, "Foo1"), new FooDTO(4, "Foo4")))
      .expectNext(Arrays.asList(new FooDTO(2, "Foo2"), new FooDTO(5, "Foo5")))
      .expectNext(Arrays.asList(new FooDTO(3, "Foo3"), new FooDTO(6, "Foo6")))
      .expectComplete()
      .verify();
  }

  @Test
  void zipWith() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<List<FooDTO>> flux = Reactor.zipWith();
    StepVerifier
      .create(flux)
      .expectNext(Arrays.asList(new FooDTO(1, "Foo1"), new FooDTO(4, "Foo4")))
      .expectNext(Arrays.asList(new FooDTO(2, "Foo2"), new FooDTO(5, "Foo5")))
      .expectNext(Arrays.asList(new FooDTO(3, "Foo3"), new FooDTO(6, "Foo6")))
      .expectComplete()
      .verify();
  }

  @Test
  void retry() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.retry();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectError()
      .verify();
  }

  @Test
  void onErrorReturn() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.onErrorReturn();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectNext(new FooDTO(0, "FooError"))
      .expectComplete()
      .verify();
  }

  @Test
  void onErrorMap() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.onErrorMap();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectError()
      .verify();
  }

  @Test
  void onErrorResume() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<FooDTO> flux = Reactor.onErrorResume();
    StepVerifier
      .create(flux)
      .expectNext(new FooDTO(1, "Foo1"), new FooDTO(2, "Foo2"), new FooDTO(3, "Foo3"))
      .expectNext(new FooDTO(0, "FooError"))
      .expectComplete()
      .verify();
  }

  @Test
  void defaultIfEmpty() {
    final Mono<String> mono = Reactor.defaultIfEmpty();
    StepVerifier.create(mono)
      .expectNext("Default Value")
      .expectComplete()
      .verifyThenAssertThat()
      .tookLessThan(Duration.ofMillis(1050));
  }

  @Test
  void takeUntil() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux<String> flux = Reactor.takeUntil();
    StepVerifier
      .create(flux)
      .expectNext("John")
      .expectNext("Monica")
      .expectComplete()
      .verify();
  }

  @Test
  void timeout() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Flux flux = Reactor.timeout();
    StepVerifier
      .create(flux)
      .expectError()
      .verify();
  }

  @Test
  void average() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Mono<Double> mono = Reactor.average();
    StepVerifier
      .create(mono)
      .expectNext(6.0)
      .expectComplete()
      .verify();
  }

  @Test
  void count() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Mono<Long> mono = Reactor.count();
    StepVerifier
      .create(mono)
      .expectNext(Long.valueOf(9))
      .expectComplete()
      .verify();
  }

  @Test
  void min() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Mono mono = Reactor.min();
    StepVerifier
      .create(mono)
      .expectNext(Optional.of(0))
      .expectComplete()
      .verify();
  }

  @Test
  void sum() {
    //Reactor.flux().subscribe( x -> log.info(x));
    final Mono mono = Reactor.sum();
    StepVerifier
      .create(mono)
      .expectNext(54)
      .expectComplete()
      .verify();
  }

  //  @Test
  //  void summarizing() {
  //    //Reactor.flux().subscribe( x -> log.info(x));
  //    final Mono mono = Reactor.summarizing();
  //    StepVerifier
  //      .create(mono)
  //      .expectNext(new IntSummaryStatistics(9, 0, 21, 54 ))
  //      .expectComplete()
  //      .verify();
  //  }

  @Test
  void monoDoOnNext() {
    final Mono<String> mono = Reactor.monoDoOnNext();
    StepVerifier.create(mono)
      .expectNext("John")
      .expectComplete()
      .verifyThenAssertThat()
      .tookLessThan(Duration.ofMillis(1050));
  }

  @Test
  void fluxDoOnNext() {
    final Flux<String> flux = Reactor.fluxDoOnNext();
    StepVerifier
      .create(flux)
      .expectNext("John")
      .expectNextMatches(name -> name.startsWith("Mo"))
      .expectNext("Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily")
      .expectNext("Cate")
      .expectComplete()
      .verify();
  }
}
