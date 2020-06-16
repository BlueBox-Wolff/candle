package de.bluebox.wolff.candle.collection.iterator;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.annotation.Nullable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayIterator<E> implements Iterator<E> {
  private final E[] elements;
  private final int length;
  private int index;

  protected ArrayIterator(E[] elements) {
    this.elements = elements;
    this.length = elements.length;
    this.index = 0;
  }

  @Override
  public boolean hasNext() {
    return this.length > this.index;
  }

  @Nullable
  @Override
  public E next() {
    E element = null;

    if (this.hasNext()) {
      element = this.elements[this.index];
      this.index++;
    }
    return element;
  }

  @Deprecated
  @Override
  public void remove() {
    this.elements[this.index] = null;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ArrayIterator<?> that = (ArrayIterator<?>) object;
    return this.length == that.length
        && this.index == that.index
        && Arrays.equals(this.elements, that.elements);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(this.length, this.index);
    result = 31 * result + Arrays.hashCode(this.elements);
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ArrayIterator.class.getSimpleName() + "[", "]")
        .add("elements=" + Arrays.toString(this.elements))
        .add("length=" + this.length)
        .add("index=" + this.index)
        .toString();
  }

  public static <E> ArrayIterator<E> of(E[] elements) {
    Preconditions.checkNotNull(elements);
    return new ArrayIterator<>(elements);
  }
}
