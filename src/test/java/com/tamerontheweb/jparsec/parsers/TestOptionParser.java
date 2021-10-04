package com.tamerontheweb.jparsec.parsers;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestOptionParser {

  private Parser<String> parser;

  @Before
  public void setUp() {
    parser = new OptionParser<>("kitty", new StringParser("meow"));
  }

  @Test
  public void testParser_MatchingSource() {
    parser.parse("meows")
            .ifPresentOrElse(v -> {
              assertEquals("meow", v.data);
              assertEquals("s", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_PartiallyMatchingSource() {
    List<String> sources = List.of(
            "meo"
            , "foo"
            , ""
    );

    for (String source : sources) {
      parser.parse(source)
              .ifPresentOrElse(
                      v -> assertEquals("kitty", v.data)
                      , () -> fail("otherwise value should always be returned")
              );
    }
  }
}
