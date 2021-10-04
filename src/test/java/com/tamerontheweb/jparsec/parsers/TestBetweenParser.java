package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestBetweenParser {

  @Test
  public void testParser_MatchingSource() {
    createParser().parse("(1)")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 1, v.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_PartiallyMatchingSource() {
    assertTrue(createParser().parse("(1").isEmpty());
  }

  @Test
  public void testParser_NonMatchingSource() {
    assertTrue(createParser().parse("foo").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(createParser().parse("").isEmpty());
  }

  private BetweenParser<String, String, Integer> createParser() {
    Parser<String> begin = new StringParser("(");
    Parser<String> end = new StringParser(")");
    Parser<Integer> p2 = new IntegerParser();

    return new BetweenParser<>(begin, end, p2);
  }
}
