package de.bluebox.wolff.candle.exception;

import de.bluebox.wolff.candle.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertSame;

public class StringsHexTest {
  @ParameterizedTest
  @CsvSource({
    "Byte,        42797465",
    "To,              546F",
    "Hex,            486578",
    "String,   537472696E67",
    "Test,         54657374"
  })
  public static void testHexToByteString(String string, String expectedHexString) {
    assertSame(Strings.bytesToHexString(string.getBytes()), expectedHexString);
  }
}
