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
