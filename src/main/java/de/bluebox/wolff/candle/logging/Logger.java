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

package de.bluebox.wolff.candle.logging;

import de.bluebox.wolff.candle.io.AsyncPrintStream;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class Logger {
  private Logger() {}

  private static SimpleDateFormat dateFormat = null;
  private static boolean debugMode = false;
  private static PrintStream printStream;
  private static List<LoggingAppender> loggingAppenders;

  static {
    loggingAppenders = new ArrayList<>();
  }

  public static void initialize() {
    try {
      printStream = new AsyncPrintStream(System.out);
      System.setOut(printStream);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public static void log(LogLevel level, String message) {
    switch (level) {
      case INFO:
      case WARNING:
      case ERROR:
      case FATAL:
        log0(level, message);
        break;
      case DEBUG:
        if (debugMode) {
          log0(level, message);
        }
        break;
    }
  }

  private static void log0(LogLevel level, String message) {
    message = appendLogLevel(level, message);
    message = tryAppendDate(message);
    final String finalMessage = message;

    printStream.println(finalMessage);
    loggingAppenders.stream()
        .filter(Objects::nonNull)
        .forEach(appender -> appender.append(finalMessage));
  }

  private static String appendLogLevel(LogLevel level, String message) {
    return String.format("[%s] %s", level.name(), message);
  }

  public static void addLoggingAppender(LoggingAppender appender) {
    loggingAppenders.add(appender);
  }

  private static String tryAppendDate(String message) {
    if (dateFormat != null) {
      String date = dateFormat.format(new Date());
      return String.format("%s%s", date, message);
    }
    return message;
  }

  public static void enableDebugMode() {
    debugMode = true;
  }

  private static void setDateTimeFormat(String format) {
    dateFormat = new SimpleDateFormat(format);
  }
}
