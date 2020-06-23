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

package de.bluebox.wolff.candle.concurrent.future;

import de.bluebox.wolff.candle.annotation.Nullable;
import de.bluebox.wolff.candle.functional.Completable;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class AbstractSettableFuture<V> implements RunnableFuture<V>, Completable {
  private V value;
  private Throwable throwable;
  private volatile boolean done;

  protected AbstractSettableFuture() {}

  @Override
  public boolean isDone() {
    return this.done;
  }

  @Override
  public void complete() {
    this.done = true;
    this.notifyAll();
  }

  public synchronized final void setValue(V value) {
    this.value = value;
    this.complete();
  }

  public synchronized final void setException(Throwable throwable) {
    this.throwable = throwable;
    this.complete();
  }

  protected synchronized final void waitForCompletion() throws InterruptedException {
    while (!this.isDone()) {
      this.wait();
    }
  }

  protected synchronized final void waitForCompletion(long timeout)
      throws InterruptedException, TimeoutException {
    long waitUntil = System.currentTimeMillis() + timeout;

    while (!this.isDone()) {
      this.wait(timeout);
      timeout = waitUntil - System.currentTimeMillis();

      if (timeout < 0) {
        throw new TimeoutException();
      }
    }
  }

  @Nullable
  protected final V value() {
    return value;
  }

  @Nullable
  protected final Throwable throwable() {
    return throwable;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    AbstractSettableFuture<?> that = (AbstractSettableFuture<?>) object;
    return this.done == that.done
        && Objects.equals(this.value, that.value)
        && Objects.equals(this.throwable, that.throwable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.value, this.throwable, this.done);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AbstractSettableFuture.class.getSimpleName() + "[", "]")
        .add("value=" + this.value)
        .add("throwable=" + this.throwable)
        .add("done=" + this.done)
        .toString();
  }
}
