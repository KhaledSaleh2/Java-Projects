// --== CS400 File Header Information ==--
// Name: Khaled Saleh
// Email: khsaleh@wisc.edu
// Group and Team: DK_blue
// Group TA: Yuye Jiang
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// class to read specified locations from a DOT file stored as edges
public class LocationReader implements LocationReaderInterface {

  Scanner s; // used to scan files
  List<String> nodes;
  List<List<String>> edges;

  // constructor
  public LocationReader(Scanner scan) {
    s = scan;
    edges = new ArrayList<List<String>>();
    nodes = new ArrayList<String>();
  }

  @Override
  /**
   * method to read edges from a DOT file in the following format: <Name of node A> -- <Name of node
   * B> [Weight=<Double value>] Meant for undirected weighted graphs
   * 
   * @param filename name of the file to be read
   * @return ArrayList of edges in the graph specified by the file
   */
  public void readGraphFromFile(String filename) throws FileNotFoundException {
    s = new Scanner(new File(filename));
    // loops through file and adds all edges
    while (s.hasNextLine()) {
      String current = s.nextLine();
      String[] split = current.split("->");
      String node1 = split[0].trim(); // finds name of node 1
      String node2 = split[1].split("\\[")[0].trim(); // finds name of node 2
      String cost = split[1].split("=")[1].replace("]", "").trim(); // find
                                                                    // weight of
                                                                    // edge

      // stores nodes into list if don't already exist
      if (nodes.isEmpty() || !nodes.contains(node1)) {
        nodes.add(node1);
      }
      if (!nodes.contains(node2)) {
        nodes.add(node2);
      }
      // stores edge as a 2d array of Strings
      List<String> edge = new ArrayList<String>();
      edge.add(node1);
      edge.add(node2);
      edge.add(cost);
      
      // adds edges to 2d array of edges
      edges.add(edge);

    }
    s.close();
  }

  @Override
  // getter for nodes from graph
  public List<String> getNodes() {
    return nodes;
  }

  @Override
  // getter for list of edges from graph
  public List<List<String>> getEdges() {
    return edges;
  }
}
