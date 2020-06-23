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

package de.bluebox.wolff.candle;

import de.bluebox.wolff.candle.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DecoratingClassLoader extends ClassLoader {
  static {
    ClassLoader.registerAsParallelCapable();
  }

  private static final int INITIAL_MAP_CAPACITY = 8;

  private final Set<String> excludedPackages =
      Collections.newSetFromMap(new ConcurrentHashMap<>(INITIAL_MAP_CAPACITY));
  private final Set<String> excludedClasses =
      Collections.newSetFromMap(new ConcurrentHashMap<>(INITIAL_MAP_CAPACITY));

  public DecoratingClassLoader() {}

  public DecoratingClassLoader(@Nullable ClassLoader parent) {
    super(parent);
  }

  public void excludePackage(String packageName) {
    Preconditions.checkNotNull(packageName);
    this.excludedPackages.add(packageName);
  }

  public void excludeClass(String className) {
    Preconditions.checkNotNull(className);
    this.excludedClasses.add(className);
  }

  public boolean isPackageExcluded(String packageName) {
    return this.excludedPackages.contains(packageName);
  }

  public boolean isClassExcluded(String className) {
    if (this.excludedClasses.contains(className)) {
      return true;
    }
    return this.excludedPackages.stream().anyMatch(className::startsWith);
  }
}
