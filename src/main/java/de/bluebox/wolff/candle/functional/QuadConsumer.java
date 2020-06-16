package de.bluebox.wolff.candle.functional;

@FunctionalInterface
public interface QuadConsumer<V, T, U, X> {
  void accept(V v, T t, U u, X x);

  default QuadConsumer<V, T, U, X> andThen(
      QuadConsumer<? super V, ? super T, ? super U, ? super X> after) {
    return (v, t, u, x) -> {
      accept(v, t, u, x);

      after.accept(v, t, u, x);
    };
  }
}
