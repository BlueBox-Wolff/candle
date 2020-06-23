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

import de.bluebox.wolff.candle.annotation.NonNull;
import de.bluebox.wolff.candle.annotation.Nullable;

public final class Preconditions {
  private Preconditions() {}

  @NonNull
  public static <T> T checkNotNull(T value) {
    if (value == null) {
      throw new NullPointerException();
    }
    return value;
  }

  @NonNull
  public static <T> T checkNotNull(T value, String errorMessage) {
    if (value == null) {
      throw new NullPointerException(errorMessage);
    }
    return value;
  }

  public static void checkArgument(boolean condition) {
    if (!condition) {
      throw new IllegalArgumentException();
    }
  }

  public static void checkArgument(boolean condition, String errorMessage) {
    if (!condition) {
      throw new IllegalArgumentException(errorMessage);
    }
  }

  public static void checkArgument(
      boolean condition, String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
    if (!condition) {
      throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
    }
  }

  public static void checkState(boolean condition) {
    if (!condition) {
      throw new IllegalStateException();
    }
  }

  public static void checkState(boolean condition, String errorMessage) {
    if (!condition) {
      throw new IllegalStateException(errorMessage);
    }
  }

  public static void checkState(
      boolean condition, String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
    if (!condition) {
      throw new IllegalStateException(String.format(errorMessageTemplate, errorMessageArgs));
    }
  }
}
