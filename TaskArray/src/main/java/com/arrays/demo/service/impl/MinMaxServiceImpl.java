package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.service.MinMaxService;
import java.util.OptionalInt;

public final class MinMaxServiceImpl implements MinMaxService {
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
}
