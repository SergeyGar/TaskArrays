package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.service.CalculationService;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public final class CalculationServiceImpl implements CalculationService {
  @Override
  public OptionalInt min(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalInt.empty();
    }
    int minimum = values[0];
    for (int value : values) {
      minimum = Math.min(minimum, value);
    }
    return OptionalInt.of(minimum);
  }

  @Override
  public OptionalInt max(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalInt.empty();
    }
    int maximum = values[0];
    for (int value : values) {
      maximum = Math.max(maximum, value);
    }
    return OptionalInt.of(maximum);
  }

  @Override
  public OptionalLong sum(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    int[] values = array.getValues();
    if (values.length == 0) {
      return OptionalLong.empty();
    }
    long total = 0;
    for (int value : values) {
      total += value;
    }
    return OptionalLong.of(total);
  }

  @Override
  public OptionalDouble average(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    OptionalLong sum = sum(array);
    if (sum.isPresent()) {
      long total = sum.getAsLong();
      int size = array.size();
      double average = (double) total / size;
      return OptionalDouble.of(average);
    } else {
      return OptionalDouble.empty();
    }
  }
}
