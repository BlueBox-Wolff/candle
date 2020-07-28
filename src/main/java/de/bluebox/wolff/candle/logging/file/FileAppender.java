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

package de.bluebox.wolff.candle.logging.file;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.io.file.Files;
import de.bluebox.wolff.candle.logging.LoggingAppender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public final class FileAppender implements LoggingAppender {
  private final File file;

  private FileAppender(File file) {
    this.file = file;
    try {
      Files.createFileOrDirectory(file);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void append(String logMessage) {
    this.writeAndFlush(logMessage);
  }

  private void writeAndFlush(String logMessage) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true))) {
      writer.write(logMessage);
      writer.write('\n');
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static FileAppender create(File file) {
    Preconditions.checkNotNull(file);
    return new FileAppender(file);
  }
}
