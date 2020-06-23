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
