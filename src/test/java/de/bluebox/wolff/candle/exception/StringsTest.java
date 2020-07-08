package de.bluebox.wolff.candle.exception;

import de.bluebox.wolff.candle.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class StringsTest {
  private static final String STARTS_WITH_IGNORE_CASE_TEST_STRING = "lower_case";

  @Test
  public void testStartsWithIgnoreCase() {
    this.trueAssertions();
    this.falseAssertions();
  }

  void trueAssertions() {
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "LOWER"));
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "lower"));
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "Lower"));
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "LoWeR"));
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "loWer"));
    assertTrue(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "LOwER"));
  }

  void falseAssertions() {
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "_"));
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "CASE"));
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "case"));
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "Case"));
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "cASe"));
    assertFalse(Strings.startWithIgnoreCase(STARTS_WITH_IGNORE_CASE_TEST_STRING, "CasE"));
  }
}
