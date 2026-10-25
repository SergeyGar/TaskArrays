package com.arrays.demo.service;

import com.arrays.demo.entity.IntegerArrayEntity;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface CalculationService {
  OptionalInt min(IntegerArrayEntity array);

  OptionalInt max(IntegerArrayEntity array);

  OptionalLong sum(IntegerArrayEntity array);

  OptionalDouble average(IntegerArrayEntity array);
}
