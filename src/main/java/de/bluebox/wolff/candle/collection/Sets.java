package de.bluebox.wolff.candle.collection;

import de.bluebox.wolff.candle.Assert;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Sets {
  public static <E> Set<E> fromArray(E[] array) {
    Set<E> list = new HashSet<>(array.length);

    Collections.addAll(list, array);
    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  public static <E> Set<E> fromArray(E[][] array) {
    Set<E> list =
        Arrays.stream(array)
            .flatMap(Arrays::stream)
            .collect(Collectors.toCollection(() -> new HashSet<>(array.length)));

    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  @SafeVarargs
  public static <E> Set<E> fromElements(E... elements) {
    Set<E> list = new HashSet<>(elements.length);

    Collections.addAll(list, elements);
    assert list.size() == elements.length;
    return list;
  }
}
