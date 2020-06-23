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
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LazyIterator<E> implements Iterator<E> {
  @Nullable private E next;
  protected State state;

  protected LazyIterator() {}

  public abstract E computeNext();

  @Override
  public boolean hasNext() {
    Preconditions.checkState(this.state != State.FAILED);

    switch (this.state) {
      case DONE:
        return false;
      case READY:
        return true;
    }
    return this.tryComputeNext();
  }

  public boolean tryComputeNext() {
    this.state = State.FAILED;
    this.next = this.computeNext();

    if (this.state != State.DONE) {
      this.state = State.READY;
      return true;
    }
    return false;
  }

  @Override
  public E next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    this.state = State.NOT_READY;
    return this.next;
  }

  protected final void endOfData() {
    this.state = State.DONE;
  }

  protected enum State {
    READY,
    NOT_READY,
    DONE,
    FAILED
  }
}
