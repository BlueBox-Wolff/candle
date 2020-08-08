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

package de.bluebox.wolff.candle.io;

import de.bluebox.wolff.candle.Preconditions;

import java.io.Closeable;
import java.io.InputStream;

public final class Streams {
  private Streams() {}

  /**
   * Closes the {@link Closeable} object only if it is not {@code null} This method ignores all
   * errors
   *
   * @param closeable object which should be closed
   */
  public static void closeQuietly(Closeable closeable) {
    if (closeable == null) {
      return;
    }

    try {
      closeable.close();
    } catch (Throwable ignored) {
    }
  }

  public static InputStream getClassPathResourceAsStream(String path) {
    String pathToUse = path.replace("\\", "/");

    if (path.startsWith("/")) {
      pathToUse = pathToUse.substring(1);
    }
    return Preconditions.checkNotNull(getClassPathResourceAsStream0(pathToUse));
  }

  private static InputStream getClassPathResourceAsStream0(String path) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    return classLoader == null
        ? ClassLoader.getSystemResourceAsStream(path)
        : classLoader.getResourceAsStream(path);
  }
}
