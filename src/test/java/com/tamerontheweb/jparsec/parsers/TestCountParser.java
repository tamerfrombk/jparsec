package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCountParser {

  private final Parser<List<Integer>> parser = new CountParser<>(3, new DigitParser());

  @Test
  public void testParser_MatchingSource() {
    parser.parse("1234")
            .ifPresentOrElse(v -> {
              assertEquals(3, v.data.size());
              assertEquals("4", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_PartiallyMatchingSource() {
    assertTrue(parser.parse("12").isEmpty());
  }

  @Test
  public void testParser_UnmatchedSource() {
    List<String> sources = List.of(
            "foo"
            , ""
    );

    for (String source : sources) {
      assertTrue(parser.parse(source).isEmpty());
    }
  }

}
