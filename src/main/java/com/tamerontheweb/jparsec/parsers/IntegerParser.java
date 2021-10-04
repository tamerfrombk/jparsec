package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class IntegerParser implements Parser<Integer> {

  private final Parser<List<Integer>> parser = new ManyParser<>(new DigitParser());

  @Override
  public Optional<Value<Integer>> parse(String src) {
    return parser.parse(src)
            .map(listValue -> {
                      Integer value = listValue
                              .data.stream()
                              .reduce(0, this::toInteger);
                      return new Value<>(value, listValue.rest);
                    }
            );
  }

  private int toInteger(Integer a, Integer b) {
    return (a * 10) + b;
  }

}
