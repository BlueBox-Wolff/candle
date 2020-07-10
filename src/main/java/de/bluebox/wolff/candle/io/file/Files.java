package de.bluebox.wolff.candle.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

/**
 * <p>
 * Collections of file utilities
 * </p>
 *
 * @author Jerome Wolff
 * @since 1.0.0
 */
public final class Files {
  private Files() {
  }

  /**
   * <p>
   * Creates a file or folder that is deleted after the program ends.
   * </p>
   *
   * <pre>
   *   Files.createTemporaryFile("/home/", "test_file")
   * </pre>
   *
   * @param path     Path of the file
   * @param fileName Name of the file
   * @return the file that was created. If this file already exists, it will be returned.
   * @throws IOException If the file or directory could not created
   * @since 1.0.0
   */
  public static File createTemporaryFile(String path, String fileName) throws IOException {
    return createTemporaryFile(Paths.get(path), fileName);
  }

  /**
   * <p>
   * Creates a file or folder that is deleted after the program ends.
   * </p>
   *
   * <pre>
   *   Files.createTemporaryFile(java.nio.file.Paths.get("/home/"), "test_file")
   * </pre>
   *
   * @param path     Path of the file
   * @param fileName Name of the file
   * @return the file that was created. If this file already exists, it will be returned.
   * @throws IOException If the file or directory could not created
   * @since 1.0.0
   */
  public static File createTemporaryFile(Path path, String fileName) throws IOException {
    return createTemporaryFile(path.toFile(), fileName);
  }

  /**
   * <p>
   * Creates a file or folder that is deleted after the program ends.
   * </p>
   *
   * <pre>
   *   Files.createTemporaryFile(new java.io.File("/home/"), "test_file")
   * </pre>
   *
   * @param path     Path of the file
   * @param fileName Name of the file
   * @return the file that was created. If this file already exists, it will be returned.
   * @throws IOException If the file or directory could not created
   * @since 1.0.0
   */
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

  /**
   * <p>
   * Creates a new file or folder.
   * This method is only used to avoid code duplications.
   * </p>
   *
   * <pre>
   *   Files.createFileOrDirectory(new java.io.File("/home/test_file.xml"))
   * </pre>
   *
   * @param file File or directory which should be created
   * @return {@code true} if the file/folder could be created. Otherwise {@code false}
   * @throws IOException If the file or directory could not created
   * @since 1.0.0
   */
  public static boolean createFileOrDirectory(File file) throws IOException {
    return file.isDirectory() ? file.mkdir() : file.createNewFile();
  }

  /**
   * <p>
   * Delete multiple directories at the same time
   * </p>
   *
   * <pre>
   *   Files.deleteDirectories(new File[] {new java.io.File("/home/"), new java.io.File("/home2/")})
   * </pre>
   *
   * @param directories the folders to be deleted as array
   * @return {@code true} if every folder could be deleted. Otherwise {@code false}
   */
  public static boolean deleteDirectories(File[] directories) {
    return deleteDirectories(Arrays.asList(directories));
  }

  /**
   * <p>
   * Delete multiple directories at the same time
   * </p>
   *
   * <pre>
   *   Files.deleteDirectories(java.util.Arrays.asList(new File[] {new java.io.File("/home/"), new java.io.File("/home2/")}))
   * </pre>
   *
   * @param directories the folders to be deleted as collection
   * @return {@code true} if every folder could be deleted. Otherwise {@code false}
   * @since 1.0.0
   */
  public static boolean deleteDirectories(Collection<File> directories) {
    return directories.stream().map(Files::deleteDirectory).reduce(true, (a, b) -> a && b);
  }

  /**
   * <p>
   * Deletes the specified file only if it is a folder.
   * </p>
   *
   * <pre>
   *   Files.deleteDirectory(new java.io.File("/home/"))
   * </pre>
   *
   * @param directory the folder to be deleted
   * @return {@code true} if every folder could be deleted. Otherwise {@code false}
   * @since 1.0.0
   */
  public static boolean deleteDirectory(File directory) {
    return directory.isDirectory() && directory.delete();
  }
}
