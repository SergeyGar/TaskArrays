package com.arrays.demo.parser.impl;

import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.parser.LineParser;
import com.arrays.demo.validator.LineValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class IntegerLineParser implements LineParser {
  private static final String TOKEN_REGEX =
      "(?:^\\s*|\\s*[,;]\\s*|\\s*-\\s*|\\s+)(-?\\d+)";
  private static final Pattern TOKEN_PATTERN = Pattern.compile(TOKEN_REGEX);
  private final LineValidator validator;

  public IntegerLineParser(LineValidator validator) {
    if (validator == null) {
      throw new ArrayProcessingException("Line validator must not be null");
    }
    this.validator = validator;
  }

  @Override
  public int[] parse(String line) {
    if (!validator.isValid(line)) {
      throw new ArrayProcessingException("Invalid integer array definition: " + line);
    }
    List<Integer> values = new ArrayList<>();
    Matcher tokens = TOKEN_PATTERN.matcher(line);
    while (tokens.find()) {
      String token = tokens.group(1);
      try {
        int value = Integer.parseInt(token);
        values.add(value);
      } catch (NumberFormatException exception) {
        throw new ArrayProcessingException("Integer outside supported range: " + token, exception);
      }
    }
    int[] result = new int[values.size()];
    for (int index = 0; index < result.length; index++) {
      result[index] = values.get(index);
    }
    return result;
  }
}
