package com.arrays.demo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.entity.builder.IntegerArrayBuilder;
import com.arrays.demo.exception.ArrayProcessingException;
import com.arrays.demo.factory.ArrayFactory;
import com.arrays.demo.factory.impl.IntegerArrayFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


class EntityCreationTest {
  private final ArrayFactory factory = new IntegerArrayFactory();
  private final IntegerArrayBuilder builder = new IntegerArrayBuilder();

  @Test
  void factoryCreatesEntity() {
    // given
    int[] values = {3, 1, 2};
    // when
    IntegerArrayEntity result = factory.create(values);
    // then
    assertArrayEquals(values, result.getValues());
  }

  @Test
  void builderCopiesInput() {
    // given
    int[] values = {3, 1};
    builder.withValues(values);
    values[0] = 99;
    // when
    IntegerArrayEntity result = builder.build();
    // then
    assertArrayEquals(new int[] {3, 1}, result.getValues());
  }

  @Test
  void constructorCopiesInput() {
    // given
    int[] values = {3, 1};
    IntegerArrayEntity entity = new IntegerArrayEntity(values);
    // when
    values[0] = 99;
    // then
    assertArrayEquals(new int[] {3, 1}, entity.getValues());
  }

  @Test
  void getterCopiesOutput() {
    // given
    IntegerArrayEntity entity = new IntegerArrayEntity(new int[] {3, 1});
    int[] values = entity.getValues();
    // when
    values[0] = 99;
    // then
    assertArrayEquals(new int[] {3, 1}, entity.getValues());
  }

  @Test
  void constructorRejectsNull() {
    // given
    int[] values = null;
    Executable action = () -> new IntegerArrayEntity(values);
    // when
    ArrayProcessingException exception = assertThrows(ArrayProcessingException.class, action);
    // then
    assertEquals("Array values must not be null", exception.getMessage());
  }
}
