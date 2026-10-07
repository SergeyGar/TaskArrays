package com.arrays.demo.entity;

import com.arrays.demo.exception.ArrayProcessingException;
import java.util.Arrays;

public final class IntegerArrayEntity {
  private final int[] values;

  public IntegerArrayEntity(int[] values) {
    if (values == null) {
      throw new ArrayProcessingException("Array values must not be null");
    }
    this.values = values.clone();
  }

  public int[] getValues() {
    return values.clone();
  }

  public int size() {
    return values.length;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof IntegerArrayEntity entity) {
      return Arrays.equals(values, entity.values);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }

  @Override
  public String toString() {
    return Arrays.toString(values);
  }
}
