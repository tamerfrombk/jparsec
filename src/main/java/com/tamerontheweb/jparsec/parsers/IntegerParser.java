package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class IntegerParser implements Parser<Integer> {

  private final String src;

  @Override
  public Optional<Value<Integer>> parse() {
    if (src.isEmpty()) {
      return Optional.empty();
    }

    if (!isDigitAt(0)) {
      return Optional.empty();
    }

    int n = 0;
    int digitEnd = 0;
    while (digitEnd < src.length() && isDigitAt(digitEnd)) {
      char c = src.charAt(digitEnd);
      n *= 10;
      n += c - '0';
      ++digitEnd;
    }

    return Optional.of(new Value<>(n, src.substring(digitEnd)));
  }

  private boolean isDigitAt(int i) {
    return Character.isDigit(src.charAt(i));
  }
}
