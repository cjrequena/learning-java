package com.sample.java8.lambda;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Functions accept one argument and produce a result. Default methods can be used to chain multiple functions together (compose, andThen).
 */
public class Functions {

  /**
   *
   */
  public  static Function<String, Integer> toInteger = Integer::valueOf;

  /**
   *
   */
  public  static Function<String, String> backToString = toInteger.andThen(String::valueOf);

  /**
   *
   */
  static Function<String, String> convertToCamelCase = search -> {
    Pattern bound = Pattern.compile("_[a-z]");
    StringBuffer searchStringBuffer = new StringBuffer(search.length());
    Matcher matcher = bound.matcher(search);
    while (matcher.find()) {
      matcher.appendReplacement(searchStringBuffer, matcher.group().toUpperCase().replace("_",""));
    }
    return matcher.appendTail(searchStringBuffer).toString();
  };
}
