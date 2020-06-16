package de.bluebox.wolff.candle.builder;

@FunctionalInterface
public interface Builder<T> {
  T build();
}
