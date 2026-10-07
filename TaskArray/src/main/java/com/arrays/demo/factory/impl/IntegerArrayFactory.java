package com.arrays.demo.factory.impl;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.entity.builder.IntegerArrayBuilder;
import com.arrays.demo.factory.ArrayFactory;

public final class IntegerArrayFactory implements ArrayFactory {
  @Override
  public IntegerArrayEntity create(int[] values) {
    IntegerArrayBuilder builder = new IntegerArrayBuilder();
    builder.withValues(values);
    return builder.build();
  }
}
