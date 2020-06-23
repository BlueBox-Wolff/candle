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

package de.bluebox.wolff.candle.io.file;

import de.bluebox.wolff.candle.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public final class FileAttributes {
  private FileAttributes() {}

  private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  public static void setAttribute(Path path, String attribute, String value) throws IOException {
    UserDefinedFileAttributeView view = lookupUserDefinedFileAttributeView(path);

    if (view == null) {
      return;
    }

    byte[] bytes = value.getBytes(DEFAULT_CHARSET);
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);

    buffer.put(bytes);
    buffer.flip();
    view.write(attribute, buffer);
  }

  @Nullable
  public static String getAttribute(Path path, String attribute) throws IOException {
    UserDefinedFileAttributeView view = lookupUserDefinedFileAttributeView(path);

    if (view == null) {
      return null;
    }

    ByteBuffer buffer = ByteBuffer.allocate(view.size(attribute));

    view.read(attribute, buffer);
    buffer.flip();
    return new String(buffer.array(), DEFAULT_CHARSET);
  }

  @Nullable
  private static UserDefinedFileAttributeView lookupUserDefinedFileAttributeView(Path path)
      throws IOException {
    if (!(path.toFile().exists()
        || Files.getFileStore(path)
            .supportsFileAttributeView(UserDefinedFileAttributeView.class))) {
      return null;
    }
    return Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
  }
}
