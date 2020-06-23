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

package de.bluebox.wolff.candle.collection.entry;

import de.bluebox.wolff.candle.Preconditions;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringJoiner;

public class ReadOnlyEntry<K, V> implements Entry<K, V> {
  protected final Entry<K, V> entry;

  protected ReadOnlyEntry(Entry<K, V> entry) {
    this.entry = entry;
  }

  @Override
  public K getKey() {
    return this.entry.getKey();
  }

  @Override
  public V getValue() {
    return this.entry.getValue();
  }

  @Override
  public V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ReadOnlyEntry<?, ?> that = (ReadOnlyEntry<?, ?>) object;
    return Objects.equals(this.entry, that.entry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entry);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ReadOnlyEntry.class.getSimpleName() + "[", "]")
        .add("entry=" + this.entry)
        .toString();
  }

  public static <K, V> ReadOnlyEntry<K, V> of(Entry<K, V> entry) {
    Preconditions.checkNotNull(entry);
    return new ReadOnlyEntry<>(entry);
  }
}
