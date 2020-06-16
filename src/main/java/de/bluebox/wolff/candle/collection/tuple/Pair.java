package de.bluebox.wolff.candle.collection.tuple;

import de.bluebox.wolff.candle.Preconditions;
import java.util.Objects;
import java.util.StringJoiner;

public final class Pair<F, S> {
  private final F first;
  private final S second;

  private Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  public F first() {
    return first;
  }

  public S second() {
    return second;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Pair<?, ?> pair = (Pair<?, ?>) object;
    return Objects.equals(this.first, pair.first) && Objects.equals(this.second, pair.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.first, this.second);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
        .add("first=" + this.first)
        .add("second=" + this.second)
        .toString();
  }

  public static <F, S> Pair<F, S> of(F first, S second) {
    Preconditions.checkNotNull(first);
    Preconditions.checkNotNull(second);

    return new Pair<>(first, second);
  }
}
