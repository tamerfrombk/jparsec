package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestStringParser {

  @Test
  public void testParser_MatchingSource() {
    new StringParser("me").parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals("me", v.data);
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NonMatchingSource() {
    assertTrue(new StringParser("m").parse("foo").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(new StringParser("m").parse("").isEmpty());
  }

}
