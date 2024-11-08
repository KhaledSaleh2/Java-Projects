import java.util.NoSuchElementException;

// tester class for HashtableMap.java class
public class HashtableMapTests {

  /**
   * tester method for constructors tests both the constructor with and without a parameter
   * 
   * @return true if the constructors are working properly, false otherwise
   */
  @SuppressWarnings("rawtypes")
  public static boolean testConstructors() {
    try {
      // tests constructor with no parameters
      HashtableMap test1 = new HashtableMap();
      // makes sure capacity was set correctly
      if (test1.getCapacity() != 8) {
        return false; // if capacity not set properly
      }
      if (test1.getSize() != 0) {
        return false; // if size not set properly
      }
      // tests constructor with no parameters
      HashtableMap test2 = new HashtableMap(11);
      // makes sure capacity was set correctly
      if (test2.getCapacity() != 11) {
        return false; // if capacity not set properly
      }
      if (test2.getSize() != 0) {
        return false; // if size not set properly
      }
      return true; // if all tests pass
    } catch (Exception e) {
      return false; // if unexpected exception is thrown through testers
    }
  }


  @SuppressWarnings({"rawtypes", "unchecked"})
  /**
   * tests that put() and containsKey() methods are working properly
   * 
   * @return true if put() and containsKey() are working properly, false otherwise
   */
  public static boolean testPutContains() {
    try {
      HashtableMap test = new HashtableMap(10);
      Hash h1 = new Hash("10", "Khaled");
      Hash h2 = new Hash("20", "Ben");
      Hash h3 = new Hash("30", "Ryan");

      test.put(h1.getKey(), h1.getVal());
      test.put(h2.getKey(), h2.getVal());
      test.put(h3.getKey(), h3.getVal());

      // makes sure all keys were put in correctly and that containsKey() is working properly
      if (!test.containsKey("10")) {
        return false; // if not working properly
      }
      if (!test.containsKey("20")) {
        return false; // if not working properly
      }
      if (!test.containsKey("30")) {
        return false; // if not working properly
      }
      if (test.containsKey("40")) {
        return false; // if not working properly
      }
      // makes sure size is updated properly
      if (test.getSize() != 3) {
        return false;
      }
      return true; // if all tests pass
    } catch (Exception e) {
      return false; // if unexpected exception is thrown through testers
    }

  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  /**
   * tester for get() method tests when key does exist and when key doesn't exist
   * 
   * @return true if get() working properly, false otherwise
   */
  public static boolean testGet() {
    try {
      // creates HashtableMap with capacity 10 and adds three hashs to it
      HashtableMap test = new HashtableMap(10);

      Hash h1 = new Hash("10", "Khaled");
      Hash h2 = new Hash("20", "Ben");
      Hash h3 = new Hash("30", "Ryan");

      test.put(h1.getKey(), h1.getVal());
      test.put(h2.getKey(), h2.getVal());
      test.put(h3.getKey(), h3.getVal());

      // tests get() method on keys that actually exist
      if (!test.get("10").equals("Khaled")) {
        return false; // if get not working properly
      }
      if (!test.get("20").equals("Ben")) {
        return false; // if get not working properly
      }
      if (!test.get("30").equals("Ryan")) {
        return false; // if get not working properly
      }
      // tests get() method with a key that doesn't exist
      try {
        test.get("40"); // should throw exception
        return false; // if exception not thrown
      } catch (NoSuchElementException e) {

      }
      return true; // if all tests pass
    } catch (Exception e) {
      return false; // if unexpected exception is thrown throughout tests
    }

  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  /**
   * tester method for doubleSize() method tests scenario where a HashtableMap is filled past 70%,
   * makes sure all attributes adjusted properly
   * 
   * @return true if doubleSize() working properly, false otherwise
   */
  public static boolean testDoubleSize() {
    try {
      // creates HashtableMap with capacity 4 and adds three Hashes to it
      HashtableMap test = new HashtableMap(4);

      Hash h1 = new Hash("10", "Khaled");
      Hash h2 = new Hash("20", "Ben");
      Hash h3 = new Hash("30", "Ryan");

      test.put(h1.getKey(), h1.getVal());
      test.put(h2.getKey(), h2.getVal());
      test.put(h3.getKey(), h3.getVal());

      // third Hash inserted should've caused capacity to double, as 3/4 is greater than 0.7

      // makes sure capacity is doubled properly
      if (test.getCapacity() != 8) {
        return false;
      }
      // makes sure proper size is maintained
      if (test.getSize() != 3) {
        return false;
      }
      // makes sure the same Hashes were kept in Hashtable
      if (!test.containsKey("10")) {
        return false; // if Hashes not kept properly
      }
      if (!test.containsKey("20")) {
        return false; // if Hashes not kept properly
      }
      if (!test.containsKey("30")) {
        return false; // if Hashes not kept properly
      }
      return true; // if all tests pass
    } catch (Exception e) {
      return false; // if unexpected exception is thrown
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  /**
   * tester method for remove() method tests remove on an existing key and a non-existing key, makes
   * sure everything updated properly
   * 
   * @return true if remoev() working properly, false otherwise
   */
  public static boolean testRemove() {
    try {
      // creates HashtableMap with capacity 10 and adds three Hashes to it
      HashtableMap test = new HashtableMap(10);

      Hash h1 = new Hash("10", "Khaled");
      Hash h2 = new Hash("20", "Ben");
      Hash h3 = new Hash("30", "Ryan");

      test.put(h1.getKey(), h1.getVal());
      test.put(h2.getKey(), h2.getVal());
      test.put(h3.getKey(), h3.getVal());

      // tests remove() on a key that exists
      if (!test.remove("20").equals("Ben")) {
        return false; // if remove not returning the correct value
      }
      // makes sure size was updated properly
      if (test.getSize() != 2) {
        return false; // if size not updated properly
      }
      // tests containsKey() method on a key thats been removed
      if (test.containsKey("20")) {
        return false; // if Tombstone status of key not updated properly
      }
      // tests remove on a key that doesn't exist
      try {
        test.remove("40"); // should throw an exception
        return false;
      } catch (NoSuchElementException e) {

      }
      // makes sure size didn't change
      if (test.getSize() != 2) {
        return false; // if size changed
      }
      return true; // if all tests pass
    } catch (Exception e) {
      return false; // if unexpected exception is thrown throughout tests
    }
  }

  // runs all tester methods
  public static void main(String[] args) {

    System.out.println("testConstructors(): " + testConstructors());
    System.out.println("testPutContains(): " + testPutContains());
    System.out.println("testGet(): " + testGet());
    System.out.println("testDoubleSize(): " + testDoubleSize());
    System.out.println("testRemove(): " + testRemove());
  }

}
