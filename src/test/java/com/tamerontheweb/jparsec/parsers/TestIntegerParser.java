package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestIntegerParser {

  @Test
  public void testParser_MatchingSource() {
    new IntegerParser().parse("12a")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 12, v.data);
              assertEquals("a", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_AllMatchingSource() {
    new IntegerParser().parse("12")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 12, v.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NonMatchingSource() {
    assertTrue(new IntegerParser().parse("foo").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(new IntegerParser().parse("").isEmpty());
  }

}
