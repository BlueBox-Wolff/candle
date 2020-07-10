/*
 * MIT License
 *
 * Copyright (c) 2020 BlueBox-Wolff
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.bluebox.wolff.candle.exception;

import de.bluebox.wolff.candle.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class StringsStartsWithTest {
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
