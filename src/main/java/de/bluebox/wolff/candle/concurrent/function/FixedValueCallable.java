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
