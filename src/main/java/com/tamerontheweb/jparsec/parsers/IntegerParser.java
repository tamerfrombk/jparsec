package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@AllArgsConstructor
public class IntegerParser implements Parser<Integer> {

  @Override
  public Optional<Value<Integer>> parse(String src) {
    if (src.isEmpty()) {
      return Optional.empty();
    }

    Predicate<Integer> isDigitAt = i -> Character.isDigit(src.charAt(i));

    if (!isDigitAt.test(0)) {
      return Optional.empty();
    }

    int n = 0;
    int digitEnd = 0;
    while (digitEnd < src.length() && isDigitAt.test(digitEnd)) {
      char c = src.charAt(digitEnd);
      n *= 10;
      n += c - '0';
      ++digitEnd;
    }

    return Optional.of(new Value<>(n, src.substring(digitEnd)));
  }

}
