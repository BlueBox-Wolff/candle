package de.bluebox.wolff.candle.functional;

public interface ThrowingConsumer<T, E extends Throwable> {
  void accept(T t) throws E;

  default ThrowingConsumer<T, E> andThen(ThrowingConsumer<T, E> after) {
    return (t) -> {
      accept(t);

      after.accept(t);
    };
  }
}
