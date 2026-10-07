package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.service.SumService;
import java.util.OptionalLong;

public final class SumServiceImpl implements SumService {
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
}
