package de.bluebox.wolff.candle.io.file;

import de.bluebox.wolff.candle.annotation.NonNull;
import de.bluebox.wolff.candle.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Files {
  private Files() {}

  @NonNull
  public static File createTemporaryFile(@NonNull String path, @NonNull String fileName)
      throws IOException {
    return createTemporaryFile(Paths.get(path), fileName);
  }

  @NonNull
  public static File createTemporaryFile(@NonNull Path path, @NonNull String fileName)
      throws IOException {
    return createTemporaryFile(path.toFile(), fileName);
  }

  @NonNull
  public static File createTemporaryFile(@NonNull File path, @NonNull String fileName)
      throws IOException {
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
}
