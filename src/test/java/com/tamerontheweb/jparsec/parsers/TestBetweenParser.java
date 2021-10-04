package com.tamerontheweb.jparsec.parsers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestBetweenParser {

  private Parser<Integer> parser;

  @Before
  public void setUp() {
    Parser<String> begin = new StringParser("(");
    Parser<String> end = new StringParser(")");
    Parser<Integer> p2 = new IntegerParser();

    parser = new BetweenParser<>(begin, end, p2);
  }

  @Test
  public void testParser_MatchingSource() {
    parser.parse("(1)")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 1, v.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_UnmatchedSource() {
    List<String> sources = List.of(
            "(1"
            , "foo"
            , ""
    );

    for (String source : sources) {
      assertTrue(parser.parse(source).isEmpty());
    }
  }

}
