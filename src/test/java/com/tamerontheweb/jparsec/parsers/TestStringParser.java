package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestStringParser {

  private final Parser<String> parser = new StringParser("m");

  @Test
  public void testParser_MatchingSource() {
    new StringParser("me").parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals("me", v.data);
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_PartiallyMatchingSource() {
    List<String> sources = List.of(
            "foo"
            , ""
    );

    for (String source : sources) {
      assertTrue(parser.parse(source).isEmpty());
    }
  }
}
