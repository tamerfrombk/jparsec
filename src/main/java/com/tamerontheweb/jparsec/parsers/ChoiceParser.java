package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ChoiceParser<T> implements Parser<T> {

  private final List<Parser<T>> parsers;

  @SafeVarargs
  public ChoiceParser(Parser<T>... parsers) {
    this(List.of(parsers));
  }

  @Override
  public Optional<Value<T>> parse(String src) {
    for (Parser<T> p : parsers) {
      var v = p.parse(src);
      if (v.isPresent()) {
        return v;
      }
    }

    return Optional.empty();
  }
}
