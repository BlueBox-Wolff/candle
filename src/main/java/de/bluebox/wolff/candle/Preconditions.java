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
