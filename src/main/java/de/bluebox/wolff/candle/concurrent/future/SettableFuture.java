package de.bluebox.wolff.candle.concurrent.future;

import de.bluebox.wolff.candle.annotation.NonNull;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SettableFuture<V> implements RunnableFuture<V> {
  private V value;
  private Throwable throwable;
  private volatile boolean done;

  protected SettableFuture() {}

  public synchronized void set(V value) {
    this.value = value;
    this.done = true;
    this.notifyAll();
  }

  public synchronized void setException(Throwable throwable) {
    this.throwable = throwable;
    this.done = true;
    this.notifyAll();
  }

  protected synchronized void waitForCompletion() throws InterruptedException {
    while (!this.isDone()) {
      this.wait();
    }
  }

  protected synchronized void waitForCompletion(long timeout)
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

  @Override
  public void run() {
    this.set(null);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return false;
  }

  @Override
  public boolean isCancelled() {
    return false;
  }

  @Override
  public boolean isDone() {
    return this.done;
  }

  @Override
  public V get() throws InterruptedException, ExecutionException {
    this.waitForCompletion();

    if (this.throwable != null) {
      throw new ExecutionException(this.throwable);
    }
    return this.value;
  }

  @Override
  public V get(long timeout, @NonNull TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    this.waitForCompletion(timeout);

    if (this.throwable != null) {
      throw new ExecutionException(this.throwable);
    }
    return this.value;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    SettableFuture<?> that = (SettableFuture<?>) object;
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
    return new StringJoiner(", ", SettableFuture.class.getSimpleName() + "[", "]")
        .add("value=" + this.value)
        .add("throwable=" + this.throwable)
        .add("done=" + this.done)
        .toString();
  }

  public static <V> SettableFuture<V> create() {
    return new SettableFuture<>();
  }
}
