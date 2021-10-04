package com.tamerontheweb.jparsec.parsers;

import com.tamerontheweb.jparsec.Value;
import com.tamerontheweb.jparsec.tuples.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestAlternativeParser {

  private Parser<Pair<Value<String>, Value<Integer>>> parser;

  @Before
  public void setUp() {
    Parser<String> p1 = new StringParser("needle");
    Parser<Integer> p2 = new IntegerParser();

    parser = new AlternativeParser<>(p1, p2);
  }

  @Test
  public void testParser_FirstMatchingSource() {
    parser.parse("needleman")
            .ifPresentOrElse(v -> {
              assertEquals("needle", v.data.first.data);
              assertEquals("man", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_SecondMatchingSource() {
    parser.parse("123")
            .ifPresentOrElse(v -> {
              assertEquals((Integer) 123, v.data.second.data);
              assertEquals("", v.rest);
            }, () -> fail("successful parse expected"));
  }

  @Test
  public void testParser_UnmatchedSource() {
    List<String> sources = List.of(
            "meo"
            , "foo"
            , ""
    );

    for (String source : sources) {
      assertTrue(parser.parse(source).isEmpty());
    }
  }
}
