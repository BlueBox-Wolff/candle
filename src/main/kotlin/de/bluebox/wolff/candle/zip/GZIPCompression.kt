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
package de.bluebox.wolff.candle.zip

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class GZIPCompression internal constructor() : ZIPCompression {
  override fun compress(input: ByteArray): ByteArray {
    try {
      ByteArrayOutputStream(input.size).use { byteArrayOutputStream ->
        GZIPOutputStream(byteArrayOutputStream).use { gzipOutputStream ->
          gzipOutputStream.write(input)
          return byteArrayOutputStream.toByteArray()
        }
      }
    } catch (ex: IOException) {
      ex.printStackTrace()
      return input
    }
  }

  override fun decompress(input: ByteArray): ByteArray {
    try {
      ByteArrayInputStream(input).use { byteArrayInputStream ->
        GZIPInputStream(byteArrayInputStream).use { gzipInputStream ->
          ByteArrayOutputStream().use { byteArrayOutputStream ->
            val buffer = ByteArray(BUFFER_SIZE)
            var length: Int

            while (gzipInputStream.read(buffer).also { length = it } > 0) {
              byteArrayOutputStream.write(buffer, OFFSET, length)
            }
            return byteArrayOutputStream.toByteArray()
          }
        }
      }
    } catch (ex: IOException) {
      ex.printStackTrace()
      return input
    }
  }

  companion object {
    private const val BUFFER_SIZE = 1024
    private const val OFFSET = 0
  }
}