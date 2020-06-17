package de.bluebox.wolff.candle.concurrent.function;

import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class ConcurrentCountTask implements Runnable {
  private final AtomicLong count;
  private final int maxCount;

  private ConcurrentCountTask(long initialValue, int maxCount) {
    this.count = new AtomicLong(initialValue);
    this.maxCount = maxCount;
  }

  private static final long DEFAULT_INITIAL_VALUE = 0L;

  private ConcurrentCountTask(int maxCount) {
    this(DEFAULT_INITIAL_VALUE, maxCount);
  }

  private static final int DEFAULT_MAX_COUNT = 100;

  private ConcurrentCountTask(long initialValue) {
    this(initialValue, DEFAULT_MAX_COUNT);
  }

  private ConcurrentCountTask() {
    this(DEFAULT_INITIAL_VALUE, DEFAULT_MAX_COUNT);
  }

  @Override
  public void run() {
    if (this.maxCount <= this.count.get()) {
      this.count.incrementAndGet();
    }
  }
}
