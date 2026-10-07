package com.arrays.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.service.impl.AverageServiceImpl;
import com.arrays.demo.service.impl.MinMaxServiceImpl;
import com.arrays.demo.service.impl.SumServiceImpl;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.junit.jupiter.api.Test;


class StatisticsTest {
  private static final IntegerArrayEntity INPUT =
      new IntegerArrayEntity(new int[] {-3, 2, 8, 2});
  private static final IntegerArrayEntity EMPTY = new IntegerArrayEntity(new int[0]);
  private static final IntegerArrayEntity LARGE =
      new IntegerArrayEntity(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE});
  private final MinMaxServiceImpl minMax = new MinMaxServiceImpl();
  private final SumServiceImpl sum = new SumServiceImpl();
  private final AverageServiceImpl average = new AverageServiceImpl();

  @Test
  void findsMinimum() {
    // given
    IntegerArrayEntity array = INPUT;
    // when
    OptionalInt result = minMax.min(array);
    // then
    assertEquals(OptionalInt.of(-3), result);
  }

  @Test
  void findsMaximum() {
    // given
    IntegerArrayEntity array = INPUT;
    // when
    OptionalInt result = minMax.max(array);
    // then
    assertEquals(OptionalInt.of(8), result);
  }

  @Test
  void computesSum() {
    // given
    IntegerArrayEntity array = INPUT;
    // when
    OptionalLong result = sum.sum(array);
    // then
    assertEquals(OptionalLong.of(9), result);
  }

  @Test
  void sumAvoidsIntegerOverflow() {
    // given
    IntegerArrayEntity array = LARGE;
    // when
    OptionalLong result = sum.sum(array);
    // then
    assertEquals(OptionalLong.of(4294967294L), result);
  }

  @Test
  void computesAverage() {
    // given
    IntegerArrayEntity array = INPUT;
    // when
    OptionalDouble result = average.average(array);
    // then
    assertEquals(OptionalDouble.of(2.25), result);
  }

  @Test
  void minIsAbsentForEmptyArray() {
    // given
    IntegerArrayEntity array = EMPTY;
    // when
    OptionalInt result = minMax.min(array);
    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void maxIsAbsentForEmptyArray() {
    // given
    IntegerArrayEntity array = EMPTY;
    // when
    OptionalInt result = minMax.max(array);
    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void sumIsAbsentForEmptyArray() {
    // given
    IntegerArrayEntity array = EMPTY;
    // when
    OptionalLong result = sum.sum(array);
    // then
    assertTrue(result.isEmpty());
  }

  @Test
  void averageIsAbsentForEmptyArray() {
    // given
    IntegerArrayEntity array = EMPTY;
    // when
    OptionalDouble result = average.average(array);
    // then
    assertTrue(result.isEmpty());
  }
}
