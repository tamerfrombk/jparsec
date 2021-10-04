package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class OptionalParser<T> implements Parser<T> {

  private final Parser<T> parser;

  @Override
  public Optional<Value<T>> parse(String source) {
    return parser.parse(source)
            .map(value -> new Value<T>(null, value.rest))
            .or(() -> Optional.of(new Value<>(null, source)));
  }
}
