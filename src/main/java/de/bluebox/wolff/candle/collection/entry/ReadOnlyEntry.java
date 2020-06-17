package de.bluebox.wolff.candle.collection.entry;

import de.bluebox.wolff.candle.Preconditions;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringJoiner;

public class ReadOnlyEntry<K, V> implements Entry<K, V> {
  protected final Entry<K, V> entry;

  protected ReadOnlyEntry(Entry<K, V> entry) {
    this.entry = entry;
  }

  @Override
  public K getKey() {
    return this.entry.getKey();
  }

  @Override
  public V getValue() {
    return this.entry.getValue();
  }

  @Override
  public V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ReadOnlyEntry<?, ?> that = (ReadOnlyEntry<?, ?>) object;
    return Objects.equals(this.entry, that.entry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.entry);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ReadOnlyEntry.class.getSimpleName() + "[", "]")
        .add("entry=" + this.entry)
        .toString();
  }

  public static <K, V> ReadOnlyEntry<K, V> of(Entry<K, V> entry) {
    Preconditions.checkNotNull(entry);
    return new ReadOnlyEntry<>(entry);
  }
}
