package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CountParser<T> implements Parser<List<T>> {

  private int count;
  private final Parser<T> p;

  @Override
  public Optional<Value<List<T>>> parse(String source) {
    List<T> ts = new ArrayList<>();

    String rest = source;
    for (int i = 0; i < count; ++i) {
      var result = p.parse(rest);
      if (result.isPresent()) {
        ts.add(result.get().data);
        rest = result.get().rest;
      } else {
        break;
      }
    }

    if (ts.size() == count) {
      return Optional.of(new Value<>(ts, rest));
    }

    return Optional.empty();
  }
}
