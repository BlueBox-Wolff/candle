package de.bluebox.wolff.candle.collection.iterator;

import de.bluebox.wolff.candle.functional.Resettable;
import java.util.Iterator;

public interface ResettableIterator<E> extends Iterator<E>, Resettable {}
