package de.bluebox.wolff.candle.collection;

import de.bluebox.wolff.candle.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Lists {
  private Lists() {}

  public static <E> List<E> fromArray(E[] array) {
    List<E> list = new ArrayList<>(array.length);

    Collections.addAll(list, array);
    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  public static <E> List<E> fromArray(E[][] array) {
    List<E> list =
        Arrays.stream(array)
            .flatMap(Arrays::stream)
            .collect(Collectors.toCollection(() -> new ArrayList<>(array.length)));

    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  @SafeVarargs
  public static <E> List<E> fromElements(E... elements) {
    List<E> list = new ArrayList<>(elements.length);

    Collections.addAll(list, elements);
    assert list.size() == elements.length;
    return list;
  }

  public static <E> List<E> retainAll(Collection<E> collection, Collection<?> retain) {
    return collection.stream()
        .filter(retain::contains)
        .collect(
            Collectors.toCollection(
                () -> new ArrayList<>(Math.min(collection.size(), retain.size()))));
  }
}
