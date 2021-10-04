package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAlternativeParser {

  @Test
  public void testParser_FirstMatchingSource() {
    createParser("me").parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals("me", v.data.first.data);
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_SecondMatchingSource() {
    createParser("q").parse("123")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 123, v.data.second.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NeitherMatchingSource() {
    assertTrue(createParser("m").parse("foo").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(createParser("m").parse("").isEmpty());
  }

  private AlternativeParser<String, Integer> createParser(String needle) {
    Parser<String> p1 = new StringParser(needle);
    Parser<Integer> p2 = new IntegerParser();

    return new AlternativeParser<>(p1, p2);
  }

}
