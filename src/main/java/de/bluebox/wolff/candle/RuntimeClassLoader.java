package de.bluebox.wolff.candle;

import java.net.URL;
import java.net.URLClassLoader;

public class RuntimeClassLoader extends URLClassLoader {
  static {
    ClassLoader.registerAsParallelCapable();
  }

  protected RuntimeClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
  }

  public static RuntimeClassLoader create(URL[] urls, ClassLoader parent) {
    Preconditions.checkNotNull(urls);
    Preconditions.checkNotNull(parent);

    return new RuntimeClassLoader(urls, parent);
  }
}
