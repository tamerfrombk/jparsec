package com.tamerontheweb.jparsec;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Value<T> {
  public T data;
  public String rest;
}
