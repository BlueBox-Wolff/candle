package de.bluebox.wolff.candle.functional;

@FunctionalInterface
public interface TriConsumer<V, T, U> {
  void accept(V v, T t, U u);

  default TriConsumer<V, T, U> andThen(TriConsumer<? super V, ? super T, ? super U> after) {
    return (v, l, r) -> {
      accept(v, l, r);

      after.accept(v, l, r);
    };
  }
}
