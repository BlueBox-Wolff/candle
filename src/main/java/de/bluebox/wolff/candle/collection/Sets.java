package de.bluebox.wolff.candle.collection;

import de.bluebox.wolff.candle.Assert;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Sets {
  public static <E> Set<E> fromArray(E[] array) {
    Set<E> set = new HashSet<>(array.length);

    Collections.addAll(set, array);
    Assert.assertCondition(set.size() == array.length);
    return set;
  }

  public static <E> Set<E> fromArray(E[][] array) {
    Set<E> set =
        Arrays.stream(array)
            .flatMap(Arrays::stream)
            .collect(Collectors.toCollection(() -> new HashSet<>(array.length)));

    Assert.assertCondition(set.size() == array.length);
    return set;
  }

  @SafeVarargs
  public static <E> Set<E> fromElements(E... elements) {
    Set<E> set = new HashSet<>(elements.length);

    Collections.addAll(set, elements);
    assert set.size() == elements.length;
    return set;
  }
}
