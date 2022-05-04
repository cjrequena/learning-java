package com.cjrequena.sample.collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrambleWords {

  public static void main(String args[]) {

    String filePath = "src/main/resources/word-list.txt";
    List<String> words = new ArrayList<>();

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      words = stream.collect(Collectors.toList());
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // Print the word list.
    System.out.println("::WORD_LIST::");
    words.forEach(System.out::println);
    System.out.println("::END_WORD_LIST::");

    System.out.println("::NATURAL_ORDER::");
    // Print the word list in natural order or alphabetically
    Collections.sort(words, Comparator.naturalOrder());
    words.forEach(System.out::println);
    System.out.println("::END_NATURAL_ORDER::");

    System.out.println("::REVERSE_ORDER::");
    // Print the word list in  reverse order or non-alphabetically
    Collections.sort(words, Comparator.reverseOrder());
    words.forEach(System.out::println);
    System.out.println("::END_REVERSE_ORDER::");

    // Print the word list in  shuffle or random way
    System.out.println("::SHUFFLE::");
    Collections.shuffle(words);
    words.forEach(System.out::println);
    System.out.println("::END_SHUFFLE::");

  }
}
