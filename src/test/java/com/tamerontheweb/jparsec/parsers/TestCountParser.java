package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestCountParser {

  private Parser<List<Integer>> parser;

  @Before
  public void setUp() {
    parser = new CountParser<>(3, src -> {
      if (src.length() > 0 && Character.isDigit(src.charAt(0))) {
        return Optional.of(new Value<>(src.charAt(0) - '0', src.substring(1)));
      }

      return Optional.empty();
    });
  }

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
  public void testParser_NonMatchingSource() {
    assertTrue(parser.parse("foo").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(parser.parse("").isEmpty());
  }

}