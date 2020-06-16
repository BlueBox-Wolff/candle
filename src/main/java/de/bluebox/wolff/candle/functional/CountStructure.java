package de.bluebox.wolff.candle.functional;

public interface CountStructure<T extends Number> {
  T get();

  T increment();

  T increment(T count);

  T decrement();

  T decrement(T count);

  void delete();

  void expires(int timeInSeconds);
}
