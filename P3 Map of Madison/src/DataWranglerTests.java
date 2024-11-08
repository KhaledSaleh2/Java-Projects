// --== CS400 File Header Information ==--
// Name: Khaled Saleh
// Email: khsaleh@wisc.edu
// Group and Team: DK_blue
// Group TA: Yuye Jiang
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class DataWranglerTests {

  @Test
  /**
   * tests that correct amount of nodes are added
   */
  public void testNodesSize() {
    // constructs class and loads in DOT file
    LocationReader test = new LocationReader(new Scanner(System.in));
    List<String> testList = null;
    try {
      test.readGraphFromFile("Madison.gv");
      testList = test.getNodes();
    } catch (FileNotFoundException e) {

    }
    assertEquals(testList.size(), 12); // 12 nodes should be stored from file
  }

  @Test
  /**
   * tests that nodes are stored properly as predecessors in edges ArrayList
   */
  public void testPredNode() {
    // constructs class and loads in DOT file
    LocationReader test = new LocationReader(new Scanner(System.in));
    List<List<String>> testList = null;
    try {
      test.readGraphFromFile("Madison.gv");
      testList = test.getEdges();
    } catch (FileNotFoundException e) {

    }
    assertEquals(testList.get(5).get(0), "Memorial Union");
  }

  @Test
  /**
   * tests that nodes are stored properly as successors in edges ArrayList
   */
  public void testSuccNode() {
    // constructs class and loads in DOT file
    LocationReader test = new LocationReader(new Scanner(System.in));
    List<List<String>> testList = null;
    try {
      test.readGraphFromFile("Madison.gv");
      testList = test.getEdges();
    } catch (FileNotFoundException e) {

    }
    assertEquals(testList.get(4).get(1), "Bascom Hall");
  }

  @Test
  /**
   * tests that correct nodes are returned in correct order
   */
  public void testNodesFromFile() {
    // constructs class and loads in DOT file
    LocationReader test = new LocationReader(new Scanner(System.in));
    List<String> testList = new ArrayList<String>();
    try {
      test.readGraphFromFile("Madison.gv");
      testList = test.getNodes();
    } catch (FileNotFoundException e) {

    }
    String node1 = "The Capitol";
    String node2 = "Memorial Library";
    String node3 = "Gordon's Market";
    String node4 = "Memorial Union";
    String node5 = "Grainger";
    String node6 = "Bascom Hall";
    String node7 = "Waters";
    String node8 = "Chemistry Building";
    String node9 = "Engineering Hall";
    String node10 = "Camp Randall";
    String node11 = "Steenbock Library";
    String node12 = "Dejope";

    List<String> nodes = new ArrayList<String>();

    nodes.add(node1);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);
    nodes.add(node5);
    nodes.add(node6);
    nodes.add(node7);
    nodes.add(node8);
    nodes.add(node9);
    nodes.add(node10);
    nodes.add(node11);
    nodes.add(node12);

    assertEquals(testList, nodes);
  }

  @Test
  /**
   * makes sure that exception is thrown when file does not exist
   */
  public void testFileNotFound() {
    // constructs class and loads in DOT file
    LocationReader test = new LocationReader(new Scanner(System.in));
    assertThrows(FileNotFoundException.class, () -> {
      test.readGraphFromFile("Random String");
    });

  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  /**
   * AE code review #1 Tests that AE properly counts amount of nodes in graph
   */
  public void AETest1() {
    Scanner scan = new Scanner(System.in);
    Backend BD = new Backend(new LocationReader(scan), new AlgorithmEngineer());

    BD.loadData("Madison.gv");
    assertEquals(12, BD.graph.getNodeCount());
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  /**
   * AE code review #1 Tests that AE properly counts amount of edges in graph
   */
  public void AETest2() {
    Scanner scan = new Scanner(System.in);
    Backend BD = new Backend(new LocationReader(scan), new AlgorithmEngineer());

    BD.loadData("Madison.gv");
    assertEquals(32, BD.graph.getEdgeCount());
  }

  @SuppressWarnings("rawtypes")
  @Test
  /*
   * Integration test #1 Tests that AE, BD, and DW code all works together properly when loading in
   * DOT file
   */
  public void IntegrationTest1() {
    Scanner scan = new Scanner(System.in);
    @SuppressWarnings("unchecked")
    Backend BD = new Backend(new LocationReader(scan), new AlgorithmEngineer());

    BD.loadData("Madison.gv");
    String stats = BD.getStatistics();
    assertEquals(stats, "Edges: 32\nNodes: 12");
  }

  @SuppressWarnings({"rawtypes"})
  @Test
  /*
   * Integration test #2 Tests that AE, BD, and DW code all work properly when loading in DOT file
   * and finding the shortest path between two Locations
   */
  public void IntegrationTest2() {
    Scanner scan = new Scanner(System.in);
    @SuppressWarnings("unchecked")
    Backend BD = new Backend(new LocationReader(scan), new AlgorithmEngineer());

    BD.loadData("Madison.gv");
    assertEquals(BD.getDistance("Grainger", "Chemistry Building"), 2.0, 0);
  }
}
