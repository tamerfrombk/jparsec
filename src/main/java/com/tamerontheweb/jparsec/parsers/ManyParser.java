package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ManyParser<T> implements Parser<List<T>> {

  private final Parser<T> p;

  @Override
  public Optional<Value<List<T>>> parse(String src) {
    List<T> ts = new ArrayList<>();

    String rest = "";
    String source = src;
    while (true) {
      var result = p.parse(source);
      if (result.isPresent()) {
        Value<T> v = result.get();
        ts.add(v.data);
        rest = v.rest;
        source = v.rest;
      } else {
        break;
      }
    }

    if (ts.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(new Value<>(ts, rest));
  }

}
