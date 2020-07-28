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

package de.bluebox.wolff.candle.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class AsyncPrintStream extends PrintStream {
  private static final boolean AUTO_FLUSH = true;
  private static final String DEFAULT_CHARSET = StandardCharsets.UTF_8.name();

  private final BlockingQueue<Runnable> queue;
  private final Thread workerThread;

  public AsyncPrintStream(OutputStream outputStream) throws UnsupportedEncodingException {
    super(outputStream, AUTO_FLUSH, DEFAULT_CHARSET);
    this.queue = new LinkedBlockingQueue<>();
    this.workerThread = new WorkerThread(this.queue);
  }

  @Override
  public void print(char c) {
    this.print0(() -> super.print(c));
  }

  @Override
  public void print(boolean b) {
    this.print0(() -> super.print(b));
  }

  @Override
  public void print(int i) {
    this.print0(() -> super.print(i));
  }

  @Override
  public void print(long l) {
    this.print0(() -> super.print(l));
  }

  @Override
  public void print(float f) {
    this.print0(() -> super.print(f));
  }

  @Override
  public void print(double d) {
    this.print0(() -> super.print(d));
  }

  @Override
  public void print(@Nullable String s) {
    this.print0(() -> super.print(s));
  }

  @Override
  public void print(@Nullable Object obj) {
    this.print0(() -> super.print(obj));
  }

  @Override
  public void print(@NotNull char[] s) {
    this.print0(() -> super.print(s));
  }

  @Override
  public void println(char x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(boolean x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(int x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(long x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(float x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(double x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(@Nullable String x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(@Nullable Object x) {
    this.print0(() -> super.println(x));
  }

  @Override
  public void println(@NotNull char[] x) {
    this.print0(() -> super.println(x));
  }

  private void print0(Runnable runnable) {
    if (Thread.currentThread() != this.workerThread) {
      this.queue.offer(runnable);
    } else {
      runnable.run();
    }
  }

  private static final class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    private WorkerThread(BlockingQueue<Runnable> queue) {
      this.queue = queue;
      this.initialize();
    }

    private void initialize() {
      this.setPriority(MIN_PRIORITY);
      this.setDaemon(true);
      this.start();
    }

    @Override
    public void run() {
      while (!this.isInterrupted()) {
        try {
          this.queue.take().run();
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
