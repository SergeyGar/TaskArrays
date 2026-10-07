package com.arrays.demo.factory;

import com.arrays.demo.entity.IntegerArrayEntity;

public interface ArrayFactory {
  IntegerArrayEntity create(int[] values);
}
