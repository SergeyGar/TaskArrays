package com.arrays.demo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.parser.LineParser;
import com.arrays.demo.parser.impl.IntegerLineParser;
import com.arrays.demo.reader.FileDataReader;
import com.arrays.demo.reader.impl.NioFileDataReader;
import com.arrays.demo.validator.LineValidator;
import com.arrays.demo.validator.impl.IntegerLineValidator;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


class InputTest {
  private final LineValidator validator = new IntegerLineValidator();
  private final LineParser parser = new IntegerLineParser(validator);
  private final FileDataReader reader = new NioFileDataReader();

  @ParameterizedTest
  @ValueSource(strings = {"1, 2, 3", "1 - 2 - 3", "1;2;3", "3 4 7", "",
      "   ", "-5, 0, -2", "-2147483648 2147483647", "1-2-3", "1,2;3"})
  void acceptsValidLines(String line) {
    // given
    String input = line;
    // when
    boolean valid = validator.isValid(input);
    // then
    assertTrue(valid);
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(strings = {"1y1 21 32", "1, 2, x3, 6..5, 77", "11- 2 - 42-",
      "1,", ";1", "1,,2", "1; ;2", "1.5", "2147483648", "-2147483649", "1-2147483648"})
  void rejectsInvalidLines(String line) {
    // given
    String input = line;
    // when
    boolean valid = validator.isValid(input);
    // then
    assertFalse(valid);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1, 2, 3", "1 - 2 - 3", "1;2;3", "1 2 3", "1-2-3"})
  void parsesDelimiters(String line) {
    // given
    String input = line;
    // when
    int[] result = parser.parse(input);
    // then
    assertArrayEquals(new int[] {1, 2, 3}, result);
  }

  @Test
  void parsesSignedValues() {
    // given
    String input = "-5, 0, -2";
    // when
    int[] result = parser.parse(input);
    // then
    assertArrayEquals(new int[] {-5, 0, -2}, result);
  }

  @Test
  void parsesEmptyArray() {
    // given
    String input = " ";
    // when
    int[] result = parser.parse(input);
    // then
    assertArrayEquals(new int[0], result);
  }

  @Test
  void parserThrowsCustomException() {
    // given
    String input = "1, x";
    Executable action = () -> parser.parse(input);
    // when
    ArrayProcessingException exception = assertThrows(ArrayProcessingException.class, action);
    // then
    assertEquals("Invalid integer array definition: 1, x", exception.getMessage());
  }

  @Test
  void readsResource() {
    // given
    Path path = Path.of("test-data/arrays.txt");
    // when
    List<String> result = reader.read(path);
    // then
    assertEquals(List.of("1; 2; 3", "1, 2, x3, 6..5, 77", "", "11- 2 - 42-"), result);
  }

  @Test
  void parsesResourceLine() {
    // given
    List<String> lines = reader.read(Path.of("test-data/arrays.txt"));
    String line = lines.get(0);
    // when
    int[] result = parser.parse(line);
    // then
    assertArrayEquals(new int[] {1, 2, 3}, result);
  }

  @Test
  void readerThrowsCustomException() {
    // given
    Path path = Path.of("test-data/missing.txt");
    Executable action = () -> reader.read(path);
    // when
    ArrayProcessingException exception = assertThrows(ArrayProcessingException.class, action);
    // then
    assertEquals("Cannot read input file: test-data" + java.io.File.separator + "missing.txt",
        exception.getMessage());
  }
}
