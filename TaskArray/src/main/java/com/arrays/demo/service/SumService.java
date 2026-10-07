package com.arrays.demo.service;

import com.arrays.demo.entity.IntegerArrayEntity;
import java.util.OptionalLong;

public interface SumService {
  OptionalLong sum(IntegerArrayEntity array);
}
