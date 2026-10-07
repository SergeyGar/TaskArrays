package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.service.AverageService;
import com.arrays.demo.service.SumService;
import java.util.OptionalDouble;
import java.util.OptionalLong;

public final class AverageServiceImpl implements AverageService {
  private final SumService sumService = new SumServiceImpl();

  @Override
  public OptionalDouble average(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    OptionalLong sum = sumService.sum(array);
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
