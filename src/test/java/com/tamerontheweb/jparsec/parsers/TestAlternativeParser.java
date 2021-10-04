package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAlternativeParser {

  @Test
  public void testParser_FirstMatchingSource() {
    createParser("meow", "me").parse()
            .ifPresentOrElse(v -> {
              assertEquals("me", v.data.first.data);
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_SecondMatchingSource() {
    createParser("123", "q").parse()
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 123, v.data.second.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NeitherMatchingSource() {
    assertTrue(new StringParser("foo", "m").parse().isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(new StringParser("", "m").parse().isEmpty());
  }

  private AlternativeParser<String, Integer> createParser(String source, String needle) {
    Parser<String> p1 = new StringParser(source, needle);
    Parser<Integer> p2 = new IntegerParser(source);

    return new AlternativeParser<>(p1, p2);
  }

}
