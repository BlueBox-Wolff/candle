package de.bluebox.wolff.candle.collection.tuple;

import de.bluebox.wolff.candle.Preconditions;
import java.util.Objects;
import java.util.StringJoiner;

public final class Triple<F, S, T> {
  private final F first;
  private final S second;
  private final T third;

  private Triple(F first, S second, T third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  public F first() {
    return first;
  }

  public S second() {
    return second;
  }

  public T third() {
    return third;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Triple<?, ?, ?> triple = (Triple<?, ?, ?>) object;
    return Objects.equals(this.first, triple.first)
        && Objects.equals(this.second, triple.second)
        && Objects.equals(this.third, triple.third);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Triple.class.getSimpleName() + "[", "]")
        .add("first=" + this.first)
        .add("second=" + this.second)
        .add("third=" + this.third)
        .toString();
  }

  public static <F, S, T> Triple<F, S, T> of(F first, S second, T third) {
    Preconditions.checkNotNull(first);
    Preconditions.checkNotNull(second);
    Preconditions.checkNotNull(third);

    return new Triple<>(first, second, third);
  }
}
