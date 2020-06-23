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
