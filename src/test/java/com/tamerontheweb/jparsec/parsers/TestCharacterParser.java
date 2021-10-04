package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCharacterParser {

  private final Parser<Character> parser = new CharacterParser('m');

  @Test
  public void testParser_MatchingSource() {
    parser.parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals((Character) 'm', v.data);
              assertEquals("eow", v.rest);
            }, () -> fail("successful parse expected"));
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
