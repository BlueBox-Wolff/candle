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

/**
 * Resettable implementation of {@link ArrayIterator}
 *
 * @param <E> type of the array
 * @author Jerome Wolff
 * @since 1.0.0
 */
public class ResettableArrayIterator<E> extends ArrayIterator<E> implements ResettableIterator<E> {
  /**
   * Creates an {@link ResettableIterator} of an specific array
   *
   * @param array array over which to iterate
   * @since 1.0.0
   */
  protected ResettableArrayIterator(E[] array) {
    super(array);
  }

  /**
   * Resets index of {@link ArrayIterator} This means that the iteration starts all over again.
   *
   * @since 1.0.0
   */
  @Override
  public void reset() {
    this.index = 0;
  }
}
