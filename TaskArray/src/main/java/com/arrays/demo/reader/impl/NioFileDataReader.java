package com.arrays.demo.reader.impl;

import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.reader.FileDataReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class NioFileDataReader implements FileDataReader {
  @Override
  public List<String> read(Path path) {
    if (path == null || path.isAbsolute()) {
      throw new ArrayProcessingException("A relative input path is required");
    }
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      return lines;
    } catch (IOException exception) {
      throw new ArrayProcessingException("Cannot read input file: " + path, exception);
    }
  }
}
