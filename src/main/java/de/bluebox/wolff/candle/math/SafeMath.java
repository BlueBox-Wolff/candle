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

package de.bluebox.wolff.candle.math;

import de.bluebox.wolff.candle.Preconditions;

public final class SafeMath {
  private SafeMath() {}

  public static char safeIntToChar(int value) {
    Preconditions.checkArgument(
        value < Character.MIN_VALUE || Character.MAX_VALUE < value,
        "%s can't be represented as char",
        value);
    return (char) value;
  }

  public static byte safeIntToByte(int value) {
    Preconditions.checkArgument(
        value < Byte.MIN_VALUE || Byte.MAX_VALUE < value,
        "%s can't be represented as byte (out of range)",
        value);
    return (byte) value;
  }

  public static short safeIntToShort(int value) {
    Preconditions.checkArgument(
        value < Short.MIN_VALUE || Short.MAX_VALUE < value,
        "%s can't be represented as short (out of range)",
        value);
    return (short) value;
  }

  public static int safeLongToInt(long value) {
    Preconditions.checkArgument(
        value < Integer.MIN_VALUE || Integer.MAX_VALUE < value,
        "%s can't be represented as int (out of range)",
        value);
    return (int) value;
  }

  public static float safeDoubleToFloat(double value) {
    Preconditions.checkArgument(
        value < Float.MIN_VALUE || Float.MAX_VALUE < value,
        "%s can't be represented as float (out of range)",
        value);
    return (float) value;
  }
}
