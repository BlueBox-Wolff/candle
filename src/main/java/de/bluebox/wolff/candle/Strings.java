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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Strings {
  public static final String EMPTY_STRING = "";
  public static final String SPACE = " ";
  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final char[] HEX_CHARS = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  private Strings() {}

  private static final int DEFAULT_OFFSET = 0;

  /**
   * Tests if the specified string starts with the provided prefix. This Method is case insensitive.
   *
   * <pre>
   * StringUtils.startWithIgnoreCase("", "")                      = true
   * StringUtils.startWithIgnoreCase("lower_case", "LOWER")       = true
   * StringUtils.startWithIgnoreCase("lower_case", "lower")       = true
   * StringUtils.startWithIgnoreCase("lower_case", "CASE")        = false
   * StringUtils.startWithIgnoreCase("lower_case", "case")        = false
   * StringUtils.startWithIgnoreCase("lower_case", "_")           = false
   * </pre>
   *
   * @param string the string
   * @param prefix the prefix the string seems to start with
   * @return {@code true} if string starts with prefix. {@code false} otherwise
   */
  public static boolean startWithIgnoreCase(String string, String prefix) {
    return string.regionMatches(true, DEFAULT_OFFSET, prefix, DEFAULT_OFFSET, prefix.length());
  }

  @Nullable
  public static String readStringFromClassPath(ClassLoader classLoader, String classPath) {
    Preconditions.checkNotNull(classLoader);
    Preconditions.checkNotNull(classPath);

    try (InputStream inputStream = classLoader.getResourceAsStream(classPath)) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[128];
      int length;

      Assert.assertNonNull(inputStream);
      while ((length = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, length);
      }
      return outputStream.toString(DEFAULT_CHARSET);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static String bytesToHexString(byte[] bytes) {
    Preconditions.checkNotNull(bytes);
    return bytesToHexString(bytes, DEFAULT_OFFSET, bytes.length);
  }

  public static String bytesToHexString(byte[] bytes, int offset, int length) {
    Preconditions.checkArgument(length >= 0);
    Preconditions.checkArgument(offset >= 0);

    char[] chars = new char[length * 2];

    for (int index = 0; index < length; index++) {
      int unsignedInt = bytes[index + offset] & 0xFF;

      chars[2 * index] = HEX_CHARS[unsignedInt >>> 4];
      chars[2 * index + 1] = HEX_CHARS[unsignedInt & 0x0F];
    }
    return new String(chars);
  }
}
