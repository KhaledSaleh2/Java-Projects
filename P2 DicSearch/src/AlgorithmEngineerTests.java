/*** JUnit imports ***/
// We will use the BeforeEach and Test annotation types to mark methods in
// our test class.
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// The Assertions class that we import from here includes assertion methods like assertEquals()
// which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

public class AlgorithmEngineerTests {

  /*
   * First test ensures that remove called on an empty tree throws an error and no changes are made
   * to the tree
   */
  @Test
  public void test1() {
    try {
      AlgorithmEngineer<Integer> tree = new AlgorithmEngineer<Integer>();
      try {
        tree.remove(3);
        // If this is reached, the tree did not throw a NoSuchElementException when it should
        fail("NoSuchElementException not thrown when removing from empty tree");
      } catch (NoSuchElementException e) {
        // DO NOTHING - this should be reached
      }
      assertEquals(tree.toLevelOrderString(), "[  ]");
    } catch (Exception e) {
      // The test threw an unexpected exception
      System.out.println(e.getStackTrace());
      fail("Unexpected exception thrown");

    }
  }

  /*
   * Tests that a single node in a tree can be removed when that node is the root
   */
  @Test
  public void test2() {
    try {
      AlgorithmEngineer<Integer> tree = new AlgorithmEngineer<Integer>();
      tree.insert(3);
      tree.remove(3);
      assertEquals(tree.toLevelOrderString(), "[  ]");
    } catch (Exception e) {
      // The test threw an unexpected exception
      System.out.println(e.getStackTrace());
      fail("Unexpected exception thrown");

    }
  }

  /*
   * Tests that a non-root node can be removed in a tree
   */
  @Test
  public void test3() {
    try {
      AlgorithmEngineer<Integer> tree = new AlgorithmEngineer<Integer>();
      tree.insert(3);
      tree.insert(1);
      tree.insert(5);
      tree.remove(5);
      assertEquals(tree.toLevelOrderString(), "[ 3, 1 ]");
    } catch (Exception e) {
      // The test threw an unexpected exception
      System.out.println(e.getStackTrace());
      fail("Unexpected exception thrown");
    }
  }

  /*
   * Tests a complex removal case for the red black tree, that results in moving of nodes and
   * recoloring
   */
  @Test
  public void test4() {
    try {
      AlgorithmEngineer<Integer> tree = new AlgorithmEngineer<Integer>();
      tree.insert(10);
      tree.insert(5);
      tree.insert(20);

      tree.insert(3);
      tree.insert(7);
      tree.insert(15);
      tree.insert(25);

      tree.insert(1);
      tree.insert(6);
      tree.insert(16);
      tree.insert(27);

      tree.remove(10);

      assertEquals(tree.toLevelOrderString(), "[ 15, 5, 20, 3, 7, 16, 25, 1, 6, 27 ]");
    } catch (Exception e) {
      // The test threw an unexpected exception
      System.out.println(e.getStackTrace());
      fail("Unexpected exception thrown");
    }
  }

  /*
   * Tests the get method of the red black tree
   */
  @Test
  public void test5() {
    int a = 1 + 2;
    assertEquals(a, 3);
    try {
      // Sets up the tree to prepare for removal
      AlgorithmEngineer<Integer> tree = new AlgorithmEngineer<Integer>();
      tree.insert(10);

      tree.insert(5);
      tree.insert(15);

      tree.insert(3);
      tree.insert(7);
      tree.insert(13);
      tree.insert(17);

      // Attempts to get the value 7
      assertEquals(tree.findNodeWithData(7).data, 7);
    } catch (Exception e) {
      // The test threw an unexpected exception
      System.out.println(e.getStackTrace());
      fail("Unexpected exception thrown");
    }
  }
}
