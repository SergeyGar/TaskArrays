package com.arrays.demo.entity.builder;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.exception.ArrayProcessingException;

public final class IntegerArrayBuilder {
  private int[] values = new int[0];

  public IntegerArrayBuilder withValues(int[] values) {
    if (values == null) {
      throw new ArrayProcessingException("Array values must not be null");
    }
    this.values = values.clone();
    return this;
  }

  public IntegerArrayEntity build() {
    return new IntegerArrayEntity(values);
  }
}
