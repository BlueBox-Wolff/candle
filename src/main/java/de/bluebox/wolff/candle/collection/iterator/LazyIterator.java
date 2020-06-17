package de.bluebox.wolff.candle.collection.iterator;

import de.bluebox.wolff.candle.Preconditions;
import de.bluebox.wolff.candle.annotation.Nullable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LazyIterator<E> implements Iterator<E> {
  @Nullable private E next;
  protected State state;

  protected LazyIterator() {}

  public abstract E computeNext();

  @Override
  public boolean hasNext() {
    Preconditions.checkState(this.state != State.FAILED);

    switch (this.state) {
      case DONE:
        return false;
      case READY:
        return true;
    }
    return this.tryComputeNext();
  }

  public boolean tryComputeNext() {
    this.state = State.FAILED;
    this.next = this.computeNext();

    if (this.state != State.DONE) {
      this.state = State.READY;
      return true;
    }
    return false;
  }

  @Override
  public E next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    this.state = State.NOT_READY;
    return this.next;
  }

  @Nullable
  protected final E endOfData() {
    this.state = State.DONE;
    return null;
  }

  protected enum State {
    READY,
    NOT_READY,
    DONE,
    FAILED
  }
}
