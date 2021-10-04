package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCharacterParser {

  @Test
  public void testParser_MatchingSource() {
    new CharacterParser("meow", 'm').parse()
            .ifPresentOrElse(v -> {
              assertEquals((Character) 'm', v.data);
              assertEquals("eow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NonMatchingSource() {
    assertTrue(new CharacterParser("foo", 'm').parse().isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(new CharacterParser("", 'm').parse().isEmpty());
  }
}