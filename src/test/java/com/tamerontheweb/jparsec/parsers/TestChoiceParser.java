package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestChoiceParser {

  private Parser<String> parser = new ChoiceParser<>(new StringParser("foo"), new StringParser("bar"));

  @Test
  public void testParser_FirstMatchingSource() {
    parser.parse("foomaster")
            .ifPresentOrElse(v -> {
              assertEquals("foo", v.data);
              assertEquals("master", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_SecondMatchingSource() {
    parser.parse("barmeister")
            .ifPresentOrElse(v -> {
              assertEquals("bar", v.data);
              assertEquals("meister", v.rest);
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
