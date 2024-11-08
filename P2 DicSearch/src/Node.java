import java.util.ArrayList;

public class Node implements Comparable<Node> {
  private ArrayList<String> values;

  public Node(ArrayList<String> values) {
    this.values = values;
  }

  public ArrayList<String> getValues() {
    return values;
  }

  public int compareTo(Node node) {
    return values.get(0).compareTo(node.getValues().get(0));
  }
}
