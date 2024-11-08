// --== CS400 File Header Information ==--
// Name: Khaled Saleh
// Email: khsaleh@wisc.edu
// Group and Team: DK Blue
// Group TA: Yuye Jiang
// Lecturer: Florian

import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class extends the BaseGraph data structure with additional methods for computing the total
 * cost and list of node data along the shortest path connecting a provided starting to ending
 * nodes. This class makes use of Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
    implements GraphADT<NodeType, EdgeType> {

  /**
   * While searching for the shortest path between two nodes, a SearchNode contains data about one
   * specific path between the start node and another node in the graph. The final node in this path
   * is stored in it's node field. The total cost of this path is stored in its cost field. And the
   * predecessor SearchNode within this path is referened by the predecessor field (this field is
   * null within the SearchNode containing the starting node in it's node field).
   *
   * SearchNodes are Comparable and are sorted by cost so that the lowest cost SearchNode has the
   * highest priority within a java.util.PriorityQueue.
   */
  protected class SearchNode implements Comparable<SearchNode> {
    public Node node;
    public double cost;
    public SearchNode predecessor;

    public SearchNode(Node node, double cost, SearchNode predecessor) {
      this.node = node;
      this.cost = cost;
      this.predecessor = predecessor;
    }

    public int compareTo(SearchNode other) {
      if (cost > other.cost)
        return +1;
      if (cost < other.cost)
        return -1;
      return 0;
    }
  }

  /**
   * This helper method creates a network of SearchNodes while computing the shortest path between
   * the provided start and end locations. The SearchNode that is returned by this method is
   * represents the end of the shortest path that is found: it's cost is the cost of that shortest
   * path, and the nodes linked together through predecessor references represent all of the nodes
   * along that shortest path (ordered from end to start).
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return SearchNode for the final end node within the shortest path
   * @throws NoSuchElementException when no path from start to end is found or when either start or
   *                                end data do not correspond to a graph node
   */
  protected SearchNode computeShortestPath(NodeType start, NodeType end) {

    // if start/end node either don't exist or are null throw NoSuchElementException
    if (start == null || end == null) {
      throw new NoSuchElementException();
    }
    if (!containsNode(start) || !containsNode(end)) {
      throw new NoSuchElementException();
    }
    // Priority queue to be iterated through for Djikstra's algorithm
    PriorityQueue<SearchNode> q = new PriorityQueue<SearchNode>();
    // adds start node to priority queue
    q.add(new SearchNode(new Node(start), 0.0, null));
    // hashtable to keep track of visited nodes
    Hashtable<NodeType, Double> visited = new Hashtable<>();

    // add all nodes in graph to visited Hashtable originally with infinity as distance values
    for (NodeType node : nodes.keySet()) {
      if (node.equals(start)) {
        visited.put(node, 0.0); // adds start node to visited
      } else {
        visited.put(node, Double.POSITIVE_INFINITY); // all other nodes path cost stored as infinity
                                                     // (no known path yet)
      }
    }
    // iterates through priority queue
    while (!q.isEmpty()) {
      SearchNode current = q.remove();

      // if end node reached
      if (current.node.data.equals(end)) {
        return current;
      }
      // loop through all edges leaving
      Node currentNode = nodes.get(current.node.data);
      for (Edge e : currentNode.edgesLeaving) {
        // if shorter path exists, add it to visited
        double edgeCost = visited.get(current.node.data) + e.data.doubleValue();
        if (visited.get(e.successor.data) > edgeCost) {
          SearchNode nextNode = new SearchNode(e.successor, edgeCost, current);
          q.add(nextNode);
          visited.put(e.successor.data, edgeCost);
        }
      }
    }
    // if no such path found
    throw new NoSuchElementException();
  }

  /**
   * Returns the list of data values from nodes along the shortest path from the node with the
   * provided start value through the node with the provided end value. This list of data values
   * starts with the start value, ends with the end value, and contains intermediary values in the
   * order they are encountered while traversing this shorteset path. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return list of data item from node along this shortest path
   */
  public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    // finds last SearchNode in the shortest path
    SearchNode Node = computeShortestPath(start, end);
    // creates a linked list to store the path
    LinkedList<Node> path = new LinkedList<>();
    // iterates through SearchNodes to create a LinkedList of the path
    while (Node != null) {
      path.addFirst(Node.node);
      Node = Node.predecessor;
    }
    // converts LinkedList into a List containing the path of nodes in order
    List<NodeType> finalPath = new ArrayList<NodeType>();
    while (!path.isEmpty()) {
      finalPath.add(path.remove().data);
    }
    // returns List of nodes in order of the shortest path
    return finalPath;
  }

  /**
   * Returns the cost of the path (sum over edge weights) of the shortest path freom the node
   * containing the start data to the node containing the end data. This method uses Dijkstra's
   * shortest path algorithm to find this solution.
   *
   * @param start the data item in the starting node for the path
   * @param end   the data item in the destination node for the path
   * @return the cost of the shortest path between these nodes
   */
  public double shortestPathCost(NodeType start, NodeType end) {
    // if start and end are same node
    if (start.equals(end)) {
      return 0.0;
    }
    // finds cost of shortest path
    SearchNode Node = computeShortestPath(start, end);
    return Node.cost;
  }

  // TODO: implement 3+ tests in step 8.
  @SuppressWarnings({"unchecked"})
  @Test
  // tests code using an example from lecture
  public void lectureShortestPathTest() {
    // mimics graph from Gary's lecture
    DijkstraGraph<NodeType, EdgeType> lectureGraph = new DijkstraGraph<NodeType, EdgeType>();

    // list of nodes
    lectureGraph.insertNode((NodeType) "A");
    lectureGraph.insertNode((NodeType) "D");
    lectureGraph.insertNode((NodeType) "I");
    lectureGraph.insertNode((NodeType) "H");
    lectureGraph.insertNode((NodeType) "L");
    lectureGraph.insertNode((NodeType) "G");
    lectureGraph.insertNode((NodeType) "E");
    lectureGraph.insertNode((NodeType) "M");
    lectureGraph.insertNode((NodeType) "F");
    lectureGraph.insertNode((NodeType) "B");

    // list of edges
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "B", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "H", (EdgeType) (Double) 8.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "M", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "B", (NodeType) "M", (EdgeType) (Double) 3.0);
    lectureGraph.insertEdge((NodeType) "M", (NodeType) "E", (EdgeType) (Double) 3.0);
    lectureGraph.insertEdge((NodeType) "M", (NodeType) "F", (EdgeType) (Double) 4.0);
    lectureGraph.insertEdge((NodeType) "F", (NodeType) "G", (EdgeType) (Double) 9.0);
    lectureGraph.insertEdge((NodeType) "G", (NodeType) "L", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "L", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "D", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "H", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "I", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "B", (EdgeType) (Double) 6.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "A", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "G", (EdgeType) (Double) 2.0);

    // checks that correct cost was calculated
    assertEquals(11.0, lectureGraph.shortestPathCost((NodeType) "D", (NodeType) "M"), 0.0);

    List<NodeType> path = new ArrayList<NodeType>();
    path.add((NodeType) "D");
    path.add((NodeType) "A");
    path.add((NodeType) "B");
    path.add((NodeType) "M");

    // checks path is correct
    assertEquals(path, lectureGraph.shortestPathData((NodeType) "D", (NodeType) "M"));
  }

  @SuppressWarnings("unchecked")
  @Test
  // tests code using graph from lecture with a start and end node we didn't go over
  public void lectureShortestPathTest2() {
    // mimics graph from Gary's lecture
    DijkstraGraph<NodeType, EdgeType> lectureGraph = new DijkstraGraph<NodeType, EdgeType>();

    // list of nodes
    lectureGraph.insertNode((NodeType) "A");
    lectureGraph.insertNode((NodeType) "D");
    lectureGraph.insertNode((NodeType) "I");
    lectureGraph.insertNode((NodeType) "H");
    lectureGraph.insertNode((NodeType) "L");
    lectureGraph.insertNode((NodeType) "G");
    lectureGraph.insertNode((NodeType) "E");
    lectureGraph.insertNode((NodeType) "M");
    lectureGraph.insertNode((NodeType) "F");
    lectureGraph.insertNode((NodeType) "B");

    // list of edges
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "B", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "H", (EdgeType) (Double) 8.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "M", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "B", (NodeType) "M", (EdgeType) (Double) 3.0);
    lectureGraph.insertEdge((NodeType) "M", (NodeType) "E", (EdgeType) (Double) 3.0);
    lectureGraph.insertEdge((NodeType) "M", (NodeType) "F", (EdgeType) (Double) 4.0);
    lectureGraph.insertEdge((NodeType) "F", (NodeType) "G", (EdgeType) (Double) 9.0);
    lectureGraph.insertEdge((NodeType) "G", (NodeType) "L", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "L", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "D", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "H", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "I", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "B", (EdgeType) (Double) 6.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "A", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "G", (EdgeType) (Double) 2.0);

    // checks that correct cost was calculated
    assertEquals(13.0, lectureGraph.shortestPathCost((NodeType) "A", (NodeType) "G"), 0.0);

    List<NodeType> path = new ArrayList<NodeType>();
    path.add((NodeType) "A");
    path.add((NodeType) "H");
    path.add((NodeType) "I");
    path.add((NodeType) "D");
    path.add((NodeType) "G");


    // checks path is correct
    assertEquals(path, lectureGraph.shortestPathData((NodeType) "A", (NodeType) "G"));
  }

  @SuppressWarnings("unchecked")
  @Test
  // tests code when node exists but path doesn't exist
  public void testNoPath() {
    DijkstraGraph<NodeType, EdgeType> lectureGraph = new DijkstraGraph<NodeType, EdgeType>();

    // list of nodes
    lectureGraph.insertNode((NodeType) "A");
    lectureGraph.insertNode((NodeType) "D");
    lectureGraph.insertNode((NodeType) "I");
    lectureGraph.insertNode((NodeType) "H");
    lectureGraph.insertNode((NodeType) "L");
    lectureGraph.insertNode((NodeType) "G");
    lectureGraph.insertNode((NodeType) "E");
    lectureGraph.insertNode((NodeType) "M");
    lectureGraph.insertNode((NodeType) "F");
    lectureGraph.insertNode((NodeType) "B");

    // list of edges (doesn't include E)
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "B", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "H", (EdgeType) (Double) 8.0);
    lectureGraph.insertEdge((NodeType) "A", (NodeType) "M", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "B", (NodeType) "M", (EdgeType) (Double) 3.0);
    lectureGraph.insertEdge((NodeType) "M", (NodeType) "F", (EdgeType) (Double) 4.0);
    lectureGraph.insertEdge((NodeType) "F", (NodeType) "G", (EdgeType) (Double) 9.0);
    lectureGraph.insertEdge((NodeType) "G", (NodeType) "L", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "L", (EdgeType) (Double) 5.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "D", (EdgeType) (Double) 1.0);
    lectureGraph.insertEdge((NodeType) "I", (NodeType) "H", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "I", (EdgeType) (Double) 2.0);
    lectureGraph.insertEdge((NodeType) "H", (NodeType) "B", (EdgeType) (Double) 6.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "A", (EdgeType) (Double) 7.0);
    lectureGraph.insertEdge((NodeType) "D", (NodeType) "G", (EdgeType) (Double) 2.0);

    // makes sure exception is thrown when trying to find shortest path that doesn't exist
    assertThrows(NoSuchElementException.class,
        () -> lectureGraph.computeShortestPath((NodeType) "A", (NodeType) "E"), "path must exist");
  }
}
