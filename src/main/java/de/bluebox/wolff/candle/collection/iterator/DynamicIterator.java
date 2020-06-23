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
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.concurrent.Semaphore;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class DynamicIterator<E> extends LazyIterator<E> {
  private final Semaphore available;

  @GuardedBy("queue")
  private final Queue<E> queue;

  @GuardedBy("queue")
  private boolean finished;

  private static final int DEFAULT_PERMITS = 0;

  private DynamicIterator(int initialSize) {
    this.queue = new ArrayDeque<>(initialSize);
    this.available = new Semaphore(DEFAULT_PERMITS);
  }

  private static final int DEFAULT_INITIAL_SIZE = 16;

  private DynamicIterator() {
    this(DEFAULT_INITIAL_SIZE);
  }

  @Nullable
  @Override
  public E computeNext() {
    try {
      this.available.acquire();
      return this.retrieveElement();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(ex);
    }
  }

  @Nullable
  private E retrieveElement() {
    synchronized (this.queue) {
      E element = this.queue.poll();

      if (element == null) {
        Preconditions.checkState(this.finished);
        this.endOfData();
      }
      return element;
    }
  }

  public void finish() {
    synchronized (this.queue) {
      this.finished = true;
    }
    this.available.release();
  }

  public boolean add(E element) {
    Preconditions.checkNotNull(element);

    boolean added;
    synchronized (this.queue) {
      Preconditions.checkState(!this.finished);
      added = this.queue.add(element);
    }
    if (added) {
      this.available.release();
    }
    return added;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    DynamicIterator<?> that = (DynamicIterator<?>) object;
    return super.equals(object)
        && this.finished == that.finished
        && Objects.equals(this.available, that.available)
        && Objects.equals(this.queue, that.queue);
  }

  @Override
  public int hashCode() {
    return super.hashCode() + Objects.hash(this.available, this.queue, this.finished);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DynamicIterator.class.getSimpleName() + "[", "]")
        .add("available=" + this.available)
        .add("queue=" + this.queue)
        .add("finished=" + this.finished)
        .add("state=" + this.state)
        .toString();
  }

  public static <E> DynamicIterator<E> create(int initialSize) {
    return new DynamicIterator<>(initialSize);
  }

  public static <E> DynamicIterator<E> create() {
    return new DynamicIterator<>();
  }
}
