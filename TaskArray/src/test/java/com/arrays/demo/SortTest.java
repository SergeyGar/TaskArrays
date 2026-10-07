package com.arrays.demo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.arrays.demo.entity.IntegerArrayEntity;
import com.arrays.demo.service.SortService;
import com.arrays.demo.service.impl.BubbleSortServiceImpl;
import com.arrays.demo.service.impl.InsertionSortServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class SortTest {
  private static final IntegerArrayEntity INPUT =
      new IntegerArrayEntity(new int[] {8, -3, 2, 2, 0});
  private static final IntegerArrayEntity EMPTY = new IntegerArrayEntity(new int[0]);
  private static final IntegerArrayEntity SINGLE = new IntegerArrayEntity(new int[] {1});
  private static final IntegerArrayEntity SORTED = new IntegerArrayEntity(new int[] {-1, 0, 3});
  private final SortService bubble = new BubbleSortServiceImpl();
  private final SortService insertion = new InsertionSortServiceImpl();

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void sortsAndPreservesInput(boolean useBubble) {
    // given
    SortService service = useBubble ? bubble : insertion;
    // when
    IntegerArrayEntity result = service.sort(INPUT);
    // then
    assertArrayEquals(new int[] {-3, 0, 2, 2, 8}, result.getValues());
    assertArrayEquals(new int[] {8, -3, 2, 2, 0}, INPUT.getValues());
    assertNotSame(INPUT, result);
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void handlesEmpty(boolean useBubble) {
    // given
    SortService service = useBubble ? bubble : insertion;
    // when
    IntegerArrayEntity result = service.sort(EMPTY);
    // then
    assertEquals(EMPTY, result);
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void handlesSingle(boolean useBubble) {
    // given
    SortService service = useBubble ? bubble : insertion;
    // when
    IntegerArrayEntity result = service.sort(SINGLE);
    // then
    assertEquals(SINGLE, result);
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void handlesSorted(boolean useBubble) {
    // given
    SortService service = useBubble ? bubble : insertion;
    // when
    IntegerArrayEntity result = service.sort(SORTED);
    // then
    assertEquals(SORTED, result);
  }
}
