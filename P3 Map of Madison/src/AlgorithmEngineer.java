// --== CS400 File Header Information ==--
// Name: Benjamin Gu
// Email: bgu27@wisc.edu
// Group and Team: DK Blue
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

/**
 * This class is an extension of the DijkstraGraph class that includes a method to find the shortest
 * path with a stop, a method to find the cost of that path, and a method to find the depth first
 * traversal of a graph
 */
public class AlgorithmEngineer<NodeType, EdgeType extends Number> extends
    DijkstraGraph<NodeType, EdgeType> implements AlgorithmEngineerInterface<NodeType, EdgeType> {

  /**
   * This method finds the shortest path between two nodes that passes through a node
   * 
   * @param start  the node where the path begins
   * @param end    the node where the path ends
   * @param stop   the node where the path passes through
   * 
   * @return       a List representation of the shortest path
   */
  public List<NodeType> shortestPathWithStop(NodeType start, NodeType end, NodeType stop) {
    // find the path from start to stop and from stop to end
    List<NodeType> result = new LinkedList<>();
    List<NodeType> startToStop = shortestPathData(start, stop);
    List<NodeType> stopToEnd = shortestPathData(stop, end);

    // get rid of the duplicate "stop"
    stopToEnd.remove(0);

    // merge the two paths into one
    result.addAll(startToStop);
    result.addAll(stopToEnd);

    return result;
  }

  /**
   * This method finds the cost of the shortest path between two nodes that passes through a node
   * 
   * @param start  the node where the path begins
   * @param end    the node where the path ends
   * @param stop   the node where the path passes through
   * 
   * @return       a double representation of the cost of the shortest path
   */
  public double shortestPathWithStopCost(NodeType start, NodeType end, NodeType stop) {
    // find the cost from start to stop and from stop to end
    double startToStop = shortestPathCost(start, stop);
    double stopToEnd = shortestPathCost(stop, end);

    // return the sum of the two costs
    return startToStop + stopToEnd;
  }

  /**
   * This method finds the depth first traversal of a graph starting at a given node
   * 
   * @param start  the node where the traversal begins
   * 
   * @return       a String representation of the depth first traversal
   */
  public String depthFirstTraversal(NodeType start) {
    // setting up
    StringBuilder result = new StringBuilder();
    Set<NodeType> visited = new HashSet<>();

    // call helper method
    depthFirstTraversalHelper(start, visited, result);

    return result.toString().trim();
  }

  /**
   * This method is a helper method to find the depth first traversal of a graph starting at a given node
   * 
   * @param current  the current node that is being processed
   * @param visited  the set of nodes that have already been processed
   * @param result   the String representation of the traversal so far
   */
  private void depthFirstTraversalHelper(NodeType current, Set<NodeType> visited, StringBuilder result) {
    visited.add(current);
    result.append(current.toString()).append(" ");

    // process neighbors of current node
    for (NodeType neighbor : getNeighbors(current)) {
      if (!visited.contains(neighbor)) {
        depthFirstTraversalHelper(neighbor, visited, result);
      }
    }
  }

  /**
   * This method finds the neighbor nodes of a given node
   * 
   * @param current  the node which we want to find the neighbor nodes of
   * @return         a List representation of the neighbor nodes 
   */
  public List<NodeType> getNeighbors(NodeType current) {
    Node currentNode = nodes.get(current);
    List<NodeType> result = new LinkedList<>();

    // neighbors are all nodes at the end of leaving edges from current node
    for (Edge edge : currentNode.edgesLeaving) {
      result.add(edge.successor.data);
    }

    return result;
  }

}
