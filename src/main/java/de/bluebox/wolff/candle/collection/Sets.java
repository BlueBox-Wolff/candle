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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collection of utilities for sets. For example, to easily create a set from a multidimensional
 * array.
 *
 * @author Jerome Wolff
 * @since 1.0.0
 */
public class Sets {
  /**
   * Creates a set with a certain type from an array.
   *
   * @param array array from which the set is to be created
   * @param <E> Type of the array and the returned contents of the set
   * @return the created set
   * @since 1.0.0
   */
  public static <E> Set<E> fromArray(E[] array) {
    Set<E> set = new HashSet<>(array.length);

    Collections.addAll(set, array);
    Assert.assertCondition(set.size() == array.length);
    return set;
  }

  /**
   * Creates a set with a certain type from an multidimensional array.
   *
   * @param array multidimensional array from which the set is to be created
   * @param <E> Type of the array and the returned contents of the set
   * @return the created set
   * @since 1.0.0
   */
  public static <E> Set<E> fromArray(E[][] array) {
    Set<E> set =
        Arrays.stream(array)
            .flatMap(Arrays::stream)
            .collect(Collectors.toCollection(() -> new HashSet<>(array.length)));

    Assert.assertCondition(set.size() == array.length);
    return set;
  }

  /**
   * Creates a set with a certain type from multiple elements.
   *
   * @param elements elements from which the set is to be created
   * @param <E> Type of the array and the returned contents of the set
   * @return the created set
   * @since 1.0.0
   */
  @SafeVarargs
  public static <E> Set<E> fromElements(E... elements) {
    Set<E> set = new HashSet<>(elements.length);

    Collections.addAll(set, elements);
    assert set.size() == elements.length;
    return set;
  }
}
