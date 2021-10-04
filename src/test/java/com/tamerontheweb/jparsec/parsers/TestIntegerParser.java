package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestIntegerParser {

  private final Parser<Integer> parser = new IntegerParser();

  @Test
  public void testParser_MatchingSource() {
    parser.parse("12a")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 12, v.data);
              assertEquals("a", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_AllMatchingSource() {
    parser.parse("12")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 12, v.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_UnmatchedSource() {
    List<String> sources = List.of(
            "baz"
            , ""
    );

    for (String source : sources) {
      assertTrue(parser.parse(source).isEmpty());
    }
  }
}
