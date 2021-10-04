package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;

import java.util.Optional;

public class CharacterParser implements Parser<Character> {

  private final String src;
  private final char needle;

  public CharacterParser(String src, char needle) {
    this.src = src;
    this.needle = needle;
  }

  @Override
  public Optional<Value<Character>> parse() {
    if (src.length() > 0 && src.charAt(0) == needle) {
      return Optional.of(new Value<>(needle, src.substring(1)));
    }

    return Optional.empty();
  }
}
