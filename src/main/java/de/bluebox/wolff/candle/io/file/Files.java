package de.bluebox.wolff.candle.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
    if (file.isDirectory()) {
      return file.mkdir();
    } else {
      return file.createNewFile();
    }
  }

  public static boolean deleteDirectories(File[] directory) {
    return Arrays.stream(directory)
        .filter(File::isDirectory)
        .map(File::delete)
        .reduce(true, (a, b) -> a && b);
  }

  public static boolean deleteDirectory(File directory) {
    return directory.isDirectory() && directory.delete();
  }
}
