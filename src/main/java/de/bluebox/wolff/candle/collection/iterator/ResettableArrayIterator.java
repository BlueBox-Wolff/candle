package de.bluebox.wolff.candle.collection.iterator;

public class ResettableArrayIterator<E> extends ArrayIterator<E> implements ResettableIterator<E> {
  protected ResettableArrayIterator(E[] elements) {
    super(elements);
  }

  @Override
  public void reset() {
    this.index = 0;
  }
}
