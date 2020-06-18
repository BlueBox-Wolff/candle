package de.bluebox.wolff.candle.concurrent.future;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CombinedFuture<V> implements Future<List<V>>, Serializable {
  private final Future<V>[] futures;

  private CombinedFuture(Future<V>[] futures) {
    this.futures = futures;
  }

  @SuppressWarnings("unchecked")
  private CombinedFuture(Collection<Future<V>> futures) {
    this.futures = futures.toArray(new Future[0]);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return Arrays.stream(this.futures)
        .map(future -> future.cancel(mayInterruptIfRunning))
        .reduce(true, (a, b) -> a && b);
  }

  @Override
  public boolean isCancelled() {
    return Arrays.stream(this.futures).map(Future::isCancelled).reduce(true, (a, b) -> a && b);
  }

  @Override
  public boolean isDone() {
    return Arrays.stream(this.futures).map(Future::isDone).reduce(true, (a, b) -> a && b);
  }

  @Override
  public List<V> get() throws InterruptedException, ExecutionException {
    List<V> result = new ArrayList<>(this.futures.length);

    for (Future<V> future : this.futures) {
      result.add(future.get());
    }
    return result;
  }

  @Override
  public List<V> get(long timeout, @NonNull TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    List<V> result = new ArrayList<>(this.futures.length);

    for (Future<V> future : this.futures) {
      result.add(future.get(timeout, unit));
    }
    return result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    CombinedFuture<?> that = (CombinedFuture<?>) object;
    return Arrays.equals(this.futures, that.futures);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(this.futures);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CombinedFuture.class.getSimpleName() + "[", "]")
        .add("futures=" + Arrays.toString(this.futures))
        .toString();
  }

  public static <V> CombinedFuture<V> fromArray(Future<V>[] futures) {
    Preconditions.checkNotNull(futures);
    return new CombinedFuture<>(futures);
  }

  @SafeVarargs
  public static <V> CombinedFuture<V> fromElements(Future<V>... elements) {
    Preconditions.checkNotNull(elements);
    return new CombinedFuture<>(elements);
  }

  public static <V> CombinedFuture<V> fromCollection(Collection<Future<V>> futures) {
    Preconditions.checkNotNull(futures);
    return new CombinedFuture<>(futures);
  }
}
