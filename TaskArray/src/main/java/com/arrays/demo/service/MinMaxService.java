package com.arrays.demo.service;

import com.arrays.demo.entity.IntegerArrayEntity;
import java.util.OptionalInt;

public interface MinMaxService {
  OptionalInt min(IntegerArrayEntity array);

  OptionalInt max(IntegerArrayEntity array);
}
