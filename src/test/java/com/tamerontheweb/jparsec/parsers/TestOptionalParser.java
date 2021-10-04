package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestOptionalParser {

  private final Parser<String> parser = new OptionalParser<>(new StringParser("kitty"));

  @Test
  public void testParser_MatchingSource() {
    parser.parse("kitty")
            .ifPresentOrElse(v -> {
              assertNull(v.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NonMatchingSource() {
    List<String> sources = List.of(
            "meo"
            , "foo"
            , ""
    );

    for (String source : sources) {
      parser.parse(source)
              .ifPresentOrElse(v -> {
                        assertNull(v.data);
                        assertEquals(source, v.rest);
                      }
                      , () -> fail("optional parser should never fail")
              );
    }
  }
}
