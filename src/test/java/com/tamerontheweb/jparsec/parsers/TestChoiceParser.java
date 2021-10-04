package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestChoiceParser {

  @Test
  public void testParser_FirstMatchingSource() {
    createParser("me", "you").parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals("me", v.data);
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_SecondMatchingSource() {
    createParser("foo", "bar").parse("bar exam")
            .ifPresentOrElse(v -> {
              assertEquals("bar", v.data);
              assertEquals(" exam", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NeitherMatchingSource() {
    assertTrue(createParser("foo", "bar").parse("baz").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(createParser("m", "n").parse("").isEmpty());
  }

  private ChoiceParser<String> createParser(String needle1, String needle2) {
    return new ChoiceParser<>(new StringParser(needle1), new StringParser(needle2));
  }

}
