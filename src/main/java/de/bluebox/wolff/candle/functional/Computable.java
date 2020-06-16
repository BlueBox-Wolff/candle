package de.bluebox.wolff.candle.functional;

@FunctionalInterface
public interface Computable<K, V> {
  K compute(K key, Callback<V> callback);
}