package de.bluebox.wolff.candle.concurrent.thread.factory;

import de.bluebox.wolff.candle.annotation.NonNull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NamedDaemonThreadFactory extends NamedThreadFactory {
  protected NamedDaemonThreadFactory(String baseName) {
    super(baseName);
  }

  @NonNull
  @Override
  public synchronized Thread newThread(@NonNull Runnable task) {
    Thread thread = super.newThread(task);

    thread.setDaemon(true);
    return thread;
  }
}
