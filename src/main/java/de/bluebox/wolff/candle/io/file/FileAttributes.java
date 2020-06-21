package de.bluebox.wolff.candle.io.file;

import de.bluebox.wolff.candle.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public final class FileAttributes {
  private FileAttributes() {}

  private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

  public static void setAttribute(Path path, String attribute, String value) throws IOException {
    UserDefinedFileAttributeView view = lookupUserDefinedFileAttributeView(path);

    if (view == null) {
      return;
    }

    byte[] bytes = value.getBytes(DEFAULT_CHARSET);
    ByteBuffer buffer = ByteBuffer.allocate(bytes.length);

    buffer.put(bytes);
    buffer.flip();
    view.write(attribute, buffer);
  }

  @Nullable
  public static String getAttribute(Path path, String attribute) throws IOException {
    UserDefinedFileAttributeView view = lookupUserDefinedFileAttributeView(path);

    if (view == null) {
      return null;
    }

    ByteBuffer buffer = ByteBuffer.allocate(view.size(attribute));

    view.read(attribute, buffer);
    buffer.flip();
    return new String(buffer.array(), DEFAULT_CHARSET);
  }

  @Nullable
  private static UserDefinedFileAttributeView lookupUserDefinedFileAttributeView(Path path)
      throws IOException {
    if (!(path.toFile().exists()
        || Files.getFileStore(path)
            .supportsFileAttributeView(UserDefinedFileAttributeView.class))) {
      return null;
    }
    return Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
  }
}
