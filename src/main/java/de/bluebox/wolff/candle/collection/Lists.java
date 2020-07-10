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

package de.bluebox.wolff.candle.collection;

import de.bluebox.wolff.candle.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Collection of utilities for list.
 * For example, to easily create a list from a multidimensional array.
 * </p>
 *
 * @author Jerome Wolff
 * @since 1.0.0
 */
public final class Lists {
  private Lists() {
  }

  /**
   * <p>
   * Creates a list with a certain type from an array.
   * </p>
   *
   * @param array array from which the list is to be created
   * @param <E>   Type of the array and the returned contents of the list
   * @return the created list
   * @since 1.0.0
   */
  public static <E> List<E> fromArray(E[] array) {
    List<E> list = new ArrayList<>(array.length);

    Collections.addAll(list, array);
    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  /**
   * <p>
   * Creates a list with a certain type from an multidimensional array.
   * </p>
   *
   * @param array multidimensional array from which the list is to be created
   * @param <E>   Type of the array and the returned contents of the list
   * @return the created list
   * @since 1.0.0
   */
  public static <E> List<E> fromArray(E[][] array) {
    List<E> list =
      Arrays.stream(array)
        .flatMap(Arrays::stream)
        .collect(Collectors.toCollection(() -> new ArrayList<>(array.length)));

    Assert.assertCondition(list.size() == array.length);
    return list;
  }

  /**
   * <p>
   * Creates a list with a certain type from multiple elements.
   * </p>
   *
   * @param elements elements from which the list is to be created
   * @param <E>      Type of the array and the returned contents of the list
   * @return the created list
   * @since 1.0.0
   */
  @SafeVarargs
  public static <E> List<E> fromElements(E... elements) {
    List<E> list = new ArrayList<>(elements.length);

    Collections.addAll(list, elements);
    Assert.assertCondition(list.size() == elements.length);
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
