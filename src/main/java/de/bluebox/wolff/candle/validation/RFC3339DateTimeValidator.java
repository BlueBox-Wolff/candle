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

package de.bluebox.wolff.candle.validation;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RFC3339DateTimeValidator implements FormatValidator {
  private static final String ERROR_MESSAGE_TEMPLATE = "%s must be a valid date-time";
  private static final String WRONG_INPUT_MESSAGE = "wrong input";
  private static final Pattern DATE_TIME_OFFSET_PATTERN =
      Pattern.compile("^.*[Tt]\\d{2}:\\d{2}:\\d{2}.*([zZ]|([+-]\\d{2}:\\d{2}))$");

  @Override
  public Optional<String> validate(String dateTime) {
    try {
      OffsetDateTime.parse(dateTime, ISO_OFFSET_DATE);

      Matcher matcher = DATE_TIME_OFFSET_PATTERN.matcher(dateTime);
      return matcher.matches()
          ? Optional.empty()
          : Optional.of(String.format(ERROR_MESSAGE_TEMPLATE, WRONG_INPUT_MESSAGE));
    } catch (DateTimeParseException ex) {
      return Optional.of(String.format(ERROR_MESSAGE_TEMPLATE, ex.getMessage()));
    }
  }
}
