package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
public class OptionParser<T> implements Parser<T> {

  @Getter
  private final T otherwise;

  private final Parser<T> parser;

  @Override
  public Optional<Value<T>> parse(String source) {
    return parser.parse(source)
            .or(() -> Optional.of(new Value<>(otherwise, source)));
  }
}
