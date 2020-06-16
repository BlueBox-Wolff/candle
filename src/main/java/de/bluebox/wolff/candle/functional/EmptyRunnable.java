package de.bluebox.wolff.candle.functional;

public final class EmptyRunnable implements Runnable {
  private static final EmptyRunnable INSTANCE = new EmptyRunnable();

  private EmptyRunnable() {}

  @Override
  public void run() {}

  public static EmptyRunnable create() {
    return INSTANCE;
  }
}
