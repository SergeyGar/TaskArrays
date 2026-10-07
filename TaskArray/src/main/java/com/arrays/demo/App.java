package com.arrays.demo;

import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.launcher.ApplicationLauncher;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class App {
  private static final Logger LOGGER = LogManager.getLogger(App.class);

  public static void main(String[] args) {
    String input = args.length > 0 ? args[0] : "data/arrays.txt";
    try {
      Path path = Path.of(input);
      ApplicationLauncher launcher = new ApplicationLauncher();
      launcher.run(path);
    } catch (ArrayProcessingException | InvalidPathException exception) {
      LOGGER.error("Array processing failed", exception);
      System.exit(1);
    }
  }
}
