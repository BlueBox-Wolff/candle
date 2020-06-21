package de.bluebox.wolff.candle.concurrent.future;

import de.bluebox.wolff.candle.annotation.NonNull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SettableFuture<V> extends AbstractSettableFuture<V> {
  protected SettableFuture() {}

  @Override
  public V get() throws InterruptedException, ExecutionException {
    this.waitForCompletion();

    if (this.throwable() != null) {
      throw new ExecutionException(this.throwable());
    }
    return this.value();
  }

  @Override
  public V get(long timeout, @NonNull TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    this.waitForCompletion(timeout);

    if (this.throwable() != null) {
      throw new ExecutionException(this.throwable());
    }
    return this.value();
  }

  public static <V> SettableFuture<V> create() {
    return new SettableFuture<>();
  }
}
