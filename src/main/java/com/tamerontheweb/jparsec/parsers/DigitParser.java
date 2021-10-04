package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;

import java.util.Optional;
import java.util.function.Predicate;

public class DigitParser implements Parser<Integer> {

  @Override
  public Optional<Value<Integer>> parse(String source) {
    Predicate<Integer> isDigitAt = i -> Character.isDigit(source.charAt(i));

    if (source.length() > 0 && isDigitAt.test(0)) {
      return Optional.of(new Value<>(source.charAt(0) - '0', source.substring(1)));
    }

    return Optional.empty();
  }
}
