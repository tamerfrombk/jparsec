package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestManyParser {

  @Test
  public void testParser_SingleMatchingSource() {
    createParser("me").parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals(1, v.data.size());
              assertEquals("me", v.data.stream().reduce("", String::join));
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_ManyMatchingSource() {
    createParser("me").parse("mememeow")
            .ifPresentOrElse(v -> {
              assertEquals(3, v.data.size());
              assertEquals("mememe", v.data.stream().reduce("", String::concat));
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_NoMatchingSource() {
    assertTrue(createParser("q").parse("123").isEmpty());
  }

  @Test
  public void testParser_EmptySource() {
    assertTrue(createParser("m").parse("").isEmpty());
  }

  private ManyParser<String> createParser(String needle) {
    return new ManyParser<>(new StringParser(needle));
  }

}
