package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class BetweenParser<B, E, T> implements Parser<T> {

  private final Parser<B> begin;
  private final Parser<E> end;
  private final Parser<T> p;

  @Override
  public Optional<Value<T>> parse(String source) {
    return begin.parse(source)
            .flatMap(value -> p.parse(value.rest))
            .flatMap(value -> end.parse(value.rest)
                    .map(e -> new Value<>(value.data, e.rest))
            );
  }
}
