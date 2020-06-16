package de.bluebox.wolff.candle.exception;

import org.junit.Test;

public final class QuietExceptionTestCase {
  @Test
  public void testPrintStackTrace() {
    QuietException exception =
        new QuietException("Testing printStackTrace() method for QuietException");
    exception.printStackTrace();
  }
}
