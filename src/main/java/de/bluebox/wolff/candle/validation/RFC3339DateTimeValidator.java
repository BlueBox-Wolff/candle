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
