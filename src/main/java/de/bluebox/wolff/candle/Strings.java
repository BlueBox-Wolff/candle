package de.bluebox.wolff.candle;

public final class Strings {
  private static final String EMPTY_STRING = "";
  private static final String SPACE = " ";
  private static final char[] HEX_CHARS = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  private Strings() {}

  private static final int DEFAULT_OFFSET = 0;

  public static String bytesToHexString(byte[] bytes) {
    Preconditions.checkNotNull(bytes);
    return bytesToHexString(bytes, DEFAULT_OFFSET, bytes.length);
  }

  public static String bytesToHexString(byte[] bytes, int offset, int length) {
    Preconditions.checkArgument(length >= 0);
    Preconditions.checkArgument(offset >= 0);

    char[] chars = new char[length * 2];

    for (int index = 0; index < length; index++) {
      int unsignedInt = bytes[index + offset] & 0xFF;

      chars[2 * index] = HEX_CHARS[unsignedInt >>> 4];
      chars[2 * index + 1] = HEX_CHARS[unsignedInt & 0x0F];
    }
    return new String(chars);
  }

  public static String emptyString() {
    return EMPTY_STRING;
  }

  public static String space() {
    return SPACE;
  }
}
