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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayIterator<E> implements Iterator<E> {
  protected final E[] elements;
  protected final int length;
  protected int index;

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
