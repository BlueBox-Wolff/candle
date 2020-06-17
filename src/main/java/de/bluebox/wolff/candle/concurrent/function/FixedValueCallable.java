package de.bluebox.wolff.candle.concurrent.function;

import de.bluebox.wolff.candle.Preconditions;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class FixedValueCallable<V> implements Callable<V> {
  private final V value;

  private FixedValueCallable(V value) {
    this.value = value;
  }

  @Override
  public V call() {
    return this.value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    FixedValueCallable<?> other = (FixedValueCallable<?>) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FixedValueCallable.class.getSimpleName() + "[", "]")
        .add("value=" + this.value)
        .toString();
  }

  public static <V> FixedValueCallable<V> of(V value) {
    Preconditions.checkNotNull(value);
    return new FixedValueCallable<>(value);
  }
}
