package com.arrays.demo.service;

import com.arrays.demo.entity.IntegerArrayEntity;
import java.util.OptionalDouble;

public interface AverageService {
  OptionalDouble average(IntegerArrayEntity array);
}
