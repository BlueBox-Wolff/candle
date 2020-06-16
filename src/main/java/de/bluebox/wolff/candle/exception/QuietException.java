package de.bluebox.wolff.candle.exception;

public final class QuietException extends RuntimeException {
  public QuietException(String message) {
    super(message);
  }

  @Override
  public synchronized Throwable initCause(Throwable cause) {
    return this;
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return this;
  }
}
