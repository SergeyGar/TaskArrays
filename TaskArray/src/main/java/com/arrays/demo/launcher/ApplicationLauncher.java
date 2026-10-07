package com.arrays.demo.launcher;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.factory.ArrayFactory;
import com.arrays.demo.factory.impl.IntegerArrayFactory;
import com.arrays.demo.parser.LineParser;
import com.arrays.demo.parser.impl.IntegerLineParser;
import com.arrays.demo.reader.FileDataReader;
import com.arrays.demo.reader.impl.NioFileDataReader;
import com.arrays.demo.service.AverageService;
import com.arrays.demo.service.MinMaxService;
import com.arrays.demo.service.SortService;
import com.arrays.demo.service.SumService;
import com.arrays.demo.service.impl.AverageServiceImpl;
import com.arrays.demo.service.impl.BubbleSortServiceImpl;
import com.arrays.demo.service.impl.InsertionSortServiceImpl;
import com.arrays.demo.service.impl.MinMaxServiceImpl;
import com.arrays.demo.service.impl.SumServiceImpl;
import com.arrays.demo.validator.LineValidator;
import com.arrays.demo.validator.impl.IntegerLineValidator;
import java.nio.file.Path;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ApplicationLauncher {
  private static final Logger LOGGER = LogManager.getLogger(ApplicationLauncher.class);
  private final FileDataReader reader = new NioFileDataReader();
  private final LineValidator validator = new IntegerLineValidator();
  private final LineParser parser = new IntegerLineParser(validator);
  private final ArrayFactory factory = new IntegerArrayFactory();
  private final MinMaxService minMax = new MinMaxServiceImpl();
  private final SumService sum = new SumServiceImpl();
  private final AverageService average = new AverageServiceImpl();
  private final SortService bubbleSort = new BubbleSortServiceImpl();
  private final SortService insertionSort = new InsertionSortServiceImpl();

  public void run(Path path) {
    LOGGER.info("Reading array definitions from {}", path);
    List<String> lines = reader.read(path);
    int processed = 0;
    for (int index = 0; index < lines.size(); index++) {
      String line = lines.get(index);
      if (line.isBlank()) {
        continue;
      }
      if (validator.isValid(line)) {
        try {
          int[] values = parser.parse(line);
          IntegerArrayEntity array = factory.create(values);
          report(array);
          processed++;
        } catch (ArrayProcessingException exception) {
          LOGGER.error("Cannot process line {}", index + 1, exception);
        }
      } else {
        LOGGER.warn("Skipping invalid line {}: {}", index + 1, line);
      }
    }
    LOGGER.info("Processed {} arrays", processed);
  }

  private void report(IntegerArrayEntity array) {
    OptionalInt minimum = minMax.min(array);
    OptionalInt maximum = minMax.max(array);
    OptionalLong total = sum.sum(array);
    OptionalDouble mean = average.average(array);
    LOGGER.info("Array: {}; min: {}; max: {}; sum: {}; average: {}",
        array, minimum, maximum, total, mean);
    IntegerArrayEntity bubbleResult = bubbleSort.sort(array);
    IntegerArrayEntity insertionResult = insertionSort.sort(array);
    LOGGER.info("Bubble sort: {}; insertion sort: {}",
        bubbleResult, insertionResult);
  }
}
