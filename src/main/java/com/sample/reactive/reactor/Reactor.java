package com.sample.reactive.reactor;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 *
 * @author cjrequena
 */
@Data
@Log4j2
public class Reactor {

  public static Mono<String> monoJust() {
    return Mono.just("John");
  }

  public static Flux<String> fluxJust() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate");
  }

  public static Mono<String> monoEmpty() {
    return Mono.empty();
  }

  public static Flux<String> fluxEmpty() {
    return Flux.empty();
  }

  public static void subscribe() {
    final Flux<String> flux = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate");
    final Mono<String> mono = Mono.just("John");
    flux.subscribe(f -> log.debug(f));
    mono.subscribe(m -> log.debug(m));
  }

  public static Flux<Integer> fluxRange() {
    return Flux.range(0, 3);
  }

  public static Flux<String> fluxRepeat() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate").repeat(3);
  }

  public static Flux<FooDTO> fromIterable() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList);
  }

  public static Mono<List<FooDTO>> collectList() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList).collectList();
  }

  /*
   * map is for synchronous, non-blocking, 1-to-1 transformations
   * map takes a Function<T, U> and returns a Flux<U>
   */
  public static Flux<FooDTO> map() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    Flux<FooDTO> flux = Flux.fromIterable(fooDTOList);
    return flux.map(p -> {
      p.setName(p.getName() + " Modified");
      return p;
    });
  }

  /*
   * flatMap is for asynchronous (non-blocking) 1-to-N transformations
   * flatMap takes a Function<T, Publisher<V>> and returns a Flux<V>
   */
  public static Flux<FooDTO> flatMap() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    Flux<FooDTO> flux = Flux.fromIterable(fooDTOList);
    return flux.flatMap(p -> {
      p.setName(p.getName() + " Modified");
      return Mono.just(p);
    });
  }

  public static Flux<List<FooDTO>> groupBy() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(1, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .groupBy(FooDTO::getId)
      .flatMap(id -> id.collectList())
      .doOnNext(log::info);
  }

  public static Flux<FooDTO> filter() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .filter(foo -> foo.getId() > 1);
  }

  public static Flux<FooDTO> distinct() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .distinct();
  }

  public static Flux<FooDTO> take() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .take(2);
  }

  public static Flux<FooDTO> takeLast() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .takeLast(2);
  }

  public static Flux<FooDTO> skip() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .skip(2);
  }

  public static Flux<FooDTO> skipLast() {
    List<FooDTO> fooDTOList = new ArrayList<>();
    fooDTOList.add(new FooDTO(1, "Foo1"));
    fooDTOList.add(new FooDTO(2, "Foo2"));
    fooDTOList.add(new FooDTO(3, "Foo3"));
    return Flux.fromIterable(fooDTOList)
      .skipLast(2);
  }

  public static Flux<FooDTO> merge() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    List<FooDTO> fooDTOList2 = new ArrayList<>();
    fooDTOList2.add(new FooDTO(4, "Foo4"));
    fooDTOList2.add(new FooDTO(5, "Foo5"));
    fooDTOList2.add(new FooDTO(6, "Foo6"));

    Flux<FooDTO> flux1 = Flux.fromIterable(fooDTOList1);
    Flux<FooDTO> flux2 = Flux.fromIterable(fooDTOList2);
    return Flux.merge(flux1, flux2).doOnNext(log::info);
  }

  public static Flux<Integer> merge2() {
    Flux<Integer> evenNumbers = Flux
      .range(1, 5)
      .filter(x -> x % 2 == 0); // i.e. 2, 4

    Flux<Integer> oddNumbers = Flux
      .range(1, 5)
      .filter(x -> x % 2 > 0);  // ie. 1, 3, 5

    return Flux.merge(
      evenNumbers,
      oddNumbers)
      .doOnNext(log::info);
  }

  public static Flux<FooDTO> mergeWith() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    List<FooDTO> fooDTOList2 = new ArrayList<>();
    fooDTOList2.add(new FooDTO(4, "Foo4"));
    fooDTOList2.add(new FooDTO(5, "Foo5"));
    fooDTOList2.add(new FooDTO(6, "Foo6"));

    Flux<FooDTO> flux1 = Flux.fromIterable(fooDTOList1);
    Flux<FooDTO> flux2 = Flux.fromIterable(fooDTOList2);
    return flux1.mergeWith(flux2).doOnNext(log::info);
  }

  /*
   *  The static method zip agglutinates multiple sources together, i.e., waits for all the sources to emit one element and combines these elements into
   *  an output value (constructed by the provided combinator function).
   */
  public static Flux<List<FooDTO>> zip() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    List<FooDTO> fooDTOList2 = new ArrayList<>();
    fooDTOList2.add(new FooDTO(4, "Foo4"));
    fooDTOList2.add(new FooDTO(5, "Foo5"));
    fooDTOList2.add(new FooDTO(6, "Foo6"));

    Flux<FooDTO> flux1 = Flux.fromIterable(fooDTOList1);
    Flux<FooDTO> flux2 = Flux.fromIterable(fooDTOList2);
    return Flux.zip(flux1, flux2, (BiFunction<FooDTO, FooDTO, List<FooDTO>>) Arrays::asList);
  }

  /*
   * The zipWith executes the same method that zip does, but only with two Publishers:
   */
  public static Flux<List<FooDTO>> zipWith() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    List<FooDTO> fooDTOList2 = new ArrayList<>();
    fooDTOList2.add(new FooDTO(4, "Foo4"));
    fooDTOList2.add(new FooDTO(5, "Foo5"));
    fooDTOList2.add(new FooDTO(6, "Foo6"));

    Flux<FooDTO> flux1 = Flux.fromIterable(fooDTOList1);
    Flux<FooDTO> flux2 = Flux.fromIterable(fooDTOList2);
    return flux1.zipWith(flux2, (f1, f2) -> Arrays.asList(f1, f2));
  }

  public static Flux<FooDTO> retry() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    Flux<FooDTO> flux1 = Flux.fromIterable(fooDTOList1);
    return flux1
      .concatWith(Flux.error(new RuntimeException("An Error")))
      .retry(1)
      .doOnNext(log::info);
  }

  public static Flux<FooDTO> onErrorReturn() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    return Flux.fromIterable(fooDTOList1)
      .concatWith(Flux.error(new RuntimeException("An Error")))
      .onErrorReturn(new FooDTO(0, "FooError"))
      .doOnNext(log::info);
  }

  public static Flux<FooDTO> onErrorResume() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    return Flux.fromIterable(fooDTOList1)
      .concatWith(Flux.error(new RuntimeException("An Error")))
      .onErrorResume(e -> Mono.just(new FooDTO(0, "FooError")))
      .doOnNext(log::info);
  }

  public static Flux<FooDTO> onErrorMap() {
    List<FooDTO> fooDTOList1 = new ArrayList<>();
    fooDTOList1.add(new FooDTO(1, "Foo1"));
    fooDTOList1.add(new FooDTO(2, "Foo2"));
    fooDTOList1.add(new FooDTO(3, "Foo3"));

    return Flux.fromIterable(fooDTOList1)
      .concatWith(Flux.error(new RuntimeException("An Error")))
      .onErrorMap(e -> new InterruptedException(e.getMessage()))
      .doOnNext(log::info);
  }

  public static Mono defaultIfEmpty() {
    return Mono.empty().defaultIfEmpty("Default Value");
  }

  public static Flux takeUntil() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .takeUntil(p -> p.equals("Monica"));
  }

  public static Flux timeout() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .delayElements(Duration.ofSeconds(3))
      .timeout(Duration.ofSeconds(2));
  }

  public static Mono average() {
    return Flux.just(0, 1, 1, 2, 3, 5, 8, 13, 21)
      .collect(Collectors.averagingInt(Integer::intValue));
  }

  public static Mono count() {
    return Flux.just(0, 1, 1, 2, 3, 5, 8, 13, 21).count();
  }

  public static Mono min() {
    Flux flux = Flux.just(0, 1, 1, 2, 3, 5, 8, 13, 21);
    return flux.collect(Collectors.minBy(Comparator.comparing(Integer::intValue)));
  }

  public static Mono sum() {
    Flux flux = Flux.just(0, 1, 1, 2, 3, 5, 8, 13, 21);
    return flux.collect(Collectors.summingInt(Integer::intValue));
  }

  public static Mono summarizing() {
    Flux flux = Flux.just(0, 1, 1, 2, 3, 5, 8, 13, 21);
    return flux.collect(Collectors.summarizingInt(Integer::intValue));
  }

  public static Mono<String> monoDoOnNext() {
    return Mono.just("John").doOnNext(log::info);
  }

  public static Flux<String> fluxDoOnNext() {
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .doOnNext(log::info);
  }
}
