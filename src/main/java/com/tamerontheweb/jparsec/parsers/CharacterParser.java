package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CharacterParser implements Parser<Character> {

  private final String src;
  private final char needle;

  @Override
  public Optional<Value<Character>> parse() {
    if (src.length() > 0 && src.charAt(0) == needle) {
      return Optional.of(new Value<>(needle, src.substring(1)));
    }

    return Optional.empty();
  }
}
