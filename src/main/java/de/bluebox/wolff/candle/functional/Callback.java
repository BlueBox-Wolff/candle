package de.bluebox.wolff.candle.functional;

@FunctionalInterface
public interface Callback<T> {
  void done(T t);
}
