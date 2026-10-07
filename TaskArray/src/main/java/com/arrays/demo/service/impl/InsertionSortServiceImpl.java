package com.arrays.demo.service.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.factory.ArrayFactory;
import com.arrays.demo.factory.impl.IntegerArrayFactory;
import com.arrays.demo.service.SortService;

public final class InsertionSortServiceImpl implements SortService {
  private final ArrayFactory factory = new IntegerArrayFactory();

  @Override
  public IntegerArrayEntity sort(IntegerArrayEntity array) {
    if (array == null) {
      throw new ArrayProcessingException("Array entity must not be null");
    }
    int[] values = array.getValues();
    for (int index = 1; index < values.length; index++) {
      int value = values[index];
      int position = index - 1;
      while (position >= 0 && values[position] > value) {
        values[position + 1] = values[position];
        position--;
      }
      values[position + 1] = value;
    }
    return factory.create(values);
  }
}
