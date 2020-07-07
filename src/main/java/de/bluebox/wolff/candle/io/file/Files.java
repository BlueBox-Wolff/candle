package de.bluebox.wolff.candle.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

public final class Files {
  private Files() {}

  public static File createTemporaryFile(String path, String fileName) throws IOException {
    return createTemporaryFile(Paths.get(path), fileName);
  }

  public static File createTemporaryFile(Path path, String fileName) throws IOException {
    return createTemporaryFile(path.toFile(), fileName);
  }

  public static File createTemporaryFile(File path, String fileName) throws IOException {
    File file = new File(path, fileName);

    if (file.exists()) {
      System.out.printf("File %s already exists%n", file.getName());
      return file;
    }

    boolean createdSuccessfully = createFileOrDirectory(file);

    if (createdSuccessfully) {
      System.out.printf("Temporary file %s successfully created%n", file.getName());
      file.deleteOnExit();
    } else {
      System.out.printf("An error occurred while creating file %s%n", file.getName());
    }
    return file;
  }

  public static boolean createFileOrDirectory(File file) throws IOException {
    return file.isDirectory() ? file.mkdir() : file.createNewFile();
  }

  public static boolean deleteDirectories(File[] directory) {
    return deleteDirectories(Arrays.asList(directory));
  }

  public static boolean deleteDirectories(Collection<File> directories) {
    return directories.stream().map(Files::deleteDirectory).reduce(true, (a, b) -> a && b);
  }

  public static boolean deleteDirectory(File directory) {
    return directory.isDirectory() && directory.delete();
  }
}
