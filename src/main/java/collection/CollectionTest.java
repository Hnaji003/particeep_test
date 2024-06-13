package collection;

import java.util.List;
import java.util.stream.Collectors;

/**
 * You should complete the function in this class
 * <p>
 * Feel free to define any method and / or class you want
 */
class CollectionTest {


  /**
   * operation : x -> ((x * 2) + 3) ^ 5
   */
  public static List<Double> compute1(List<Integer> input) {
    return input.stream().map(x -> Math.pow((x * 2) + 3, 5)).collect(Collectors.toList());
  }

  /**
   * operation : abc -> AbcAbc
   */
  public static List<String> compute2(List<String> input) {
    return input.stream().map(str -> {
      if (str == null || str.isEmpty()) {
        return "";
      }
      String transformed = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
      return transformed + transformed;
    }).collect(Collectors.toList());
  }

}
