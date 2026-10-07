package com.arrays.demo.reader;

import java.nio.file.Path;
import java.util.List;

public interface FileDataReader {
  List<String> read(Path path);
}
