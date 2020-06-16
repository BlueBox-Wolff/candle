package de.bluebox.wolff.candle;

import de.bluebox.wolff.candle.annotation.NonNull;
import de.bluebox.wolff.candle.annotation.Nullable;

public final class Assert {
  private Assert() {}

  @NonNull
  public static <T> T assertNonNull(T value) {
    assert value != null;
    return value;
  }

  @NonNull
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
