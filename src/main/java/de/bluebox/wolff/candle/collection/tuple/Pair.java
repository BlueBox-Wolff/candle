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
