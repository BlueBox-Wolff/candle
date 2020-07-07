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

package de.bluebox.wolff.candle;

import de.bluebox.wolff.candle.annotation.Nullable;

public final class Assert {
  private Assert() {}

  public static <T> T assertNonNull(T value) {
    assert value != null;
    return value;
  }

  public static <T> T assertNonNull(T value, String errorMessage) {
    assert value != null : errorMessage;
    return value;
  }

  public static void assertCondition(boolean condition) {
    assert condition;
  }

  public static void assertCondition(boolean condition, String errorMessage) {
    assert condition : errorMessage;
  }

  public static void assertCondition(
      boolean condition, String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
    assertCondition(condition, String.format(errorMessageTemplate, errorMessageArgs));
  }
}
