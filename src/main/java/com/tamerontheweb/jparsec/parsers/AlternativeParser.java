package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import com.tamerontheweb.jparsec.tuples.Pair;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class AlternativeParser<T, U> implements Parser<Pair<Value<T>, Value<U>>> {

  private final Parser<T> p1;
  private final Parser<U> p2;

  @Override
  public Optional<Value<Pair<Value<T>, Value<U>>>> parse(String src) {
    return p1.parse(src)
            .map(this::left)
            .or(() -> p2.parse(src).map(this::right));
  }

  private Value<Pair<Value<T>, Value<U>>> left(Value<T> v) {
    return new Value<>(new Pair<>(v, null), v.rest);
  }

  private Value<Pair<Value<T>, Value<U>>> right(Value<U> v) {
    return new Value<>(new Pair<>(null, v), v.rest);
  }
}
