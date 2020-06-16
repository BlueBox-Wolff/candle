package de.bluebox.wolff.candle.functional;

@FunctionalInterface
public interface FailureCallback<T extends Throwable> {
  void failure(T throwable);
}
