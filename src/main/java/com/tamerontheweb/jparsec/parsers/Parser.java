package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;

import java.util.Optional;

@FunctionalInterface
public interface Parser<T> {

  Optional<Value<T>> parse();

}
