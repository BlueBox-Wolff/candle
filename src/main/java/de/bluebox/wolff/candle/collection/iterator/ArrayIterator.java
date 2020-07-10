/*
 * MIT License
 *
 * Copyright (c) 2020 BlueBox-Wolff
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.bluebox.wolff.candle.collection.iterator;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.annotation.Nullable;

import java.util.*;

/**
 * <p>
 * {@link Iterator} with specialization on arrays
 * </p>
 *
 * @param <E> type of the array
 * @author Jerome Wolff
 * @since 1.0.0
 */
public class ArrayIterator<E> implements Iterator<E> {
  protected final E[] array;
  protected final int length;
  protected int index;

  /**
   * <p>
   * Creates an {@link Iterator} of an specific array
   * </p>
   *
   * @param array array over which to iterate
   * @since 1.0.0
   */
  protected ArrayIterator(E[] array) {
    this.array = array;
    this.length = array.length;
    this.index = 0;
  }

  /**
   * Returns {@code true} if the iteration has more elements.
   * (In other words, returns {@code true} if {@link #next} would
   * return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   * @see Iterator
   * @since 1.0.0
   */
  @Override
  public boolean hasNext() {
    return this.length > this.index;
  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration or {@code null}
   * @throws NoSuchElementException if the iteration has no more elements
   * @see Iterator
   * @since 1.0.0
   */
  @Nullable
  @Override
  public E next() {
    E element = null;

    if (this.hasNext()) {
      element = this.array[this.index];
      this.index++;
    }
    return element;
  }

  /**
   * Removes from the underlying collection the last element returned
   * by this iterator (optional operation).  This method can be called
   * only once per call to {@link #next}.
   * <p>
   * The behavior of an iterator is unspecified if the underlying collection
   * is modified while the iteration is in progress in any way other than by
   * calling this method, unless an overriding class has specified a
   * concurrent modification policy.
   * <p>
   * The behavior of an iterator is unspecified if this method is called
   * after a call to the {@link #forEachRemaining forEachRemaining} method.
   *
   * @throws UnsupportedOperationException if the {@code remove}
   *                                       operation is not supported by this iterator
   * @throws IllegalStateException         if the {@code next} method has not
   *                                       yet been called, or the {@code remove} method has already
   *                                       been called after the last call to the {@code next}
   *                                       method
   * @implSpec The default implementation throws an instance of
   * {@link UnsupportedOperationException} and performs no other action.
   * @see Iterator
   * @since 1.0.0
   */
  @Deprecated
  @Override
  public void remove() {
    this.array[this.index] = null;
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
      && Arrays.equals(this.array, that.array);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(this.length, this.index);
    result = 31 * result + Arrays.hashCode(this.array);
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ArrayIterator.class.getSimpleName() + "[", "]")
      .add("elements=" + Arrays.toString(this.array))
      .add("length=" + this.length)
      .add("index=" + this.index)
      .toString();
  }

  /**
   * <p>
   * Invokes constructor {@code ArrayIterator(E[])}
   * </p>
   *
   * @param array array over which to iterate
   * @param <E>   type of the array
   * @return an iterator of specific array
   * @since 1.0.0
   */
  public static <E> ArrayIterator<E> of(E[] array) {
    Preconditions.checkNotNull(array);
    return new ArrayIterator<>(array);
  }
}
