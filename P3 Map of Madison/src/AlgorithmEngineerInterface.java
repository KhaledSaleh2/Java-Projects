// --== CS400 File Header Information ==--
// Name: Benjamin Gu
// Email: bgu27@wisc.edu
// Group and Team: DK Blue
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.util.List;

/**
 * This interface outlines the AlgorithmEngineer class, which is an extension of the DijkstraGraph
 * class that includes a method to find the shortest path with a stop, a method to find the cost of
 * that path, and a method to find the depth first traversal of a graph
 */
public interface AlgorithmEngineerInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {

  /**
   * This method finds the shortest path between two nodes that passes through a node
   * 
   * @param start  the node where the path begins
   * @param end    the node where the path ends
   * @param stop   the node where the path passes through
   * 
   * @return       a List representation of the shortest path
   */
  public List<NodeType> shortestPathWithStop(NodeType start, NodeType end, NodeType stop);

  /**
   * This method finds the cost of the shortest path between two nodes that passes through a node
   * 
   * @param start  the node where the path begins
   * @param end    the node where the path ends
   * @param stop   the node where the path passes through
   * 
   * @return       a double representation of the cost of the shortest path
   */
  public double shortestPathWithStopCost(NodeType start, NodeType end, NodeType stop);

  /**
   * This method finds the depth first traversal of a graph starting at a given node
   * 
   * @param start  the node where the traversal begins
   * 
   * @return       a String representation of the depth first traversal
   */
  public String depthFirstTraversal(NodeType start);

}
