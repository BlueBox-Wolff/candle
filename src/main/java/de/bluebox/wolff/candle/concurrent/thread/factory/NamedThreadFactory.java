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
