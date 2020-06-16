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
