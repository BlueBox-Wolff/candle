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

package de.bluebox.wolff.candle.concurrent.thread.factory;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.annotation.NonNull;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NamedThreadFactory implements ThreadFactory {
  private final String baseName;
  private final AtomicInteger threadNumber;

  protected NamedThreadFactory(String baseName) {
    this.baseName = baseName;
    this.threadNumber = new AtomicInteger(0);
  }

  @NonNull
  @Override
  public synchronized Thread newThread(@NonNull Runnable task) {
    Thread thread = Executors.defaultThreadFactory().newThread(task);

    thread.setName(String.format("%s-%d", this.baseName, this.threadNumber.incrementAndGet()));
    return thread;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    NamedThreadFactory that = (NamedThreadFactory) object;
    return Objects.equals(this.baseName, that.baseName)
        && Objects.equals(this.threadNumber, that.threadNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.baseName, this.threadNumber);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", NamedThreadFactory.class.getSimpleName() + "[", "]")
        .add("baseName='" + this.baseName + "'")
        .add("threadNumber=" + this.threadNumber)
        .toString();
  }

  public static NamedThreadFactory create(String baseName) {
    Preconditions.checkNotNull(baseName);
    return new NamedThreadFactory(baseName);
  }
}
