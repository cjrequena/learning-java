package com.cjrequena.sample.collections;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class ScrambleWords {

  public static void main(String args[]) {

    String filePath = "src/main/resources/word-list.txt";
    List<String> words = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      words = stream.collect(Collectors.toList());
    } catch (IOException ex) {
      log.error(ex);
    }

    // Print the word list.
    log.info("::WORD_LIST::");
    words.forEach(System.out::println);
    log.info("::END_WORD_LIST::");

    // Print the word list in natural order or alphabetically
    log.info("::NATURAL_ORDER::");
    words.sort(Comparator.naturalOrder());
    words.forEach(System.out::println);
    log.info("::END_NATURAL_ORDER::");

    // Print the word list in  reverse order or non-alphabetically
    log.info("::REVERSE_ORDER::");
    words.sort(Comparator.reverseOrder());
    words.forEach(System.out::println);
    log.info("::END_REVERSE_ORDER::");

    // Print the word list in  shuffle or random way
    log.info("::SHUFFLE::");
    Collections.shuffle(words);
    words.forEach(System.out::println);
    log.info("::END_SHUFFLE::");

  }
}
