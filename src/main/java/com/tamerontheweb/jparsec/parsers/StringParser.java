package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class StringParser implements Parser<String> {

  private final String needle;

  @Override
  public Optional<Value<String>> parse(String src) {
    if (src.startsWith(needle)) {
      return Optional.of(new Value<>(needle, src.substring(needle.length())));
    }

    return Optional.empty();
  }
}
