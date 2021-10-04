package com.tamerontheweb.jparsec.parsers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestManyParser {

  private final Parser<List<String>> parser = new ManyParser<>(new StringParser("me"));

  @Test
  public void testParser_SingleMatchingSource() {
    parser.parse("meow")
            .ifPresentOrElse(v -> {
              assertEquals(1, v.data.size());
              assertEquals("me", v.data.stream().reduce("", String::join));
              assertEquals("ow", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_ManyMatchingSource() {
    parser.parse("mememeow")
            .ifPresentOrElse(v -> {
              assertEquals(3, v.data.size());
              assertEquals("mememe", v.data.stream().reduce("", String::concat));
              assertEquals("ow", v.rest);
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
