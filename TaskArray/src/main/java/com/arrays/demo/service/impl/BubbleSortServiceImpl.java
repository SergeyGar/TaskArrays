package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.factory.ArrayFactory;
import com.arrays.demo.factory.impl.IntegerArrayFactory;
import com.arrays.demo.service.SortService;

public final class BubbleSortServiceImpl implements SortService {
  private final ArrayFactory factory = new IntegerArrayFactory();

  @Override
  public IntegerArrayEntity sort(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    int[] values = array.getValues();
    for (int end = values.length - 1; end > 0; end--) {
      boolean changed = false;
      for (int index = 0; index < end; index++) {
        if (values[index] > values[index + 1]) {
          int previous = values[index];
          values[index] = values[index + 1];
          values[index + 1] = previous;
          changed = true;
        }
      }
      if (!changed) {
        break;
      }
    }
    return factory.create(values);
  }
}
