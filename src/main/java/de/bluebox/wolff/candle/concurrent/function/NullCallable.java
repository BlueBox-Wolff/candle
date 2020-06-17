package de.bluebox.wolff.candle.concurrent.function;

import de.bluebox.wolff.candle.annotation.Nullable;
import java.util.concurrent.Callable;

public final class NullCallable<V> implements Callable<V> {
  @Nullable
  @Override
  public V call() throws Exception {
    return null;
  }

  public static <V> NullCallable<V> create() {
    return new NullCallable<>();
  }
}
