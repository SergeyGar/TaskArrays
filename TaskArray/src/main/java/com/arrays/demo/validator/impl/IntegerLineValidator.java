package com.arrays.demo.validator.impl;

import com.arrays.demo.validator.LineValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IntegerLineValidator implements LineValidator {
  private static final String LINE_REGEX =
      "\\s*-?\\d+(?:(?:\\s*[,;]\\s*|\\s*-\\s*|\\s+)-?\\d+)*\\s*";
  private static final Pattern LINE_PATTERN = Pattern.compile(LINE_REGEX);
  private static final String TOKEN_REGEX =
      "(?:^\\s*|\\s*[,;]\\s*|\\s*-\\s*|\\s+)(-?\\d+)";
  private static final Pattern NUMBER_PATTERN = Pattern.compile(TOKEN_REGEX);

  @Override
  public boolean isValid(String line) {
    if (line == null) {
      return false;
    }
    if (line.isBlank()) {
      return true;
    }
    Matcher structure = LINE_PATTERN.matcher(line);
    if (!structure.matches()) {
      return false;
    }
    Matcher numbers = NUMBER_PATTERN.matcher(line);
    while (numbers.find()) {
      try {
        String token = numbers.group(1);
        Integer.parseInt(token);
      } catch (NumberFormatException exception) {
        return false;
      }
    }
    return true;
  }
}
