import java.util.NoSuchElementException;

public interface AlgorithmEngineerInterface<nodeType> {
  // public RedBlackTreeAE();//week 3
  // public RedBlackTreeInterface();//week 4

  /* gets the node that is equal to the argument, found by the .equal() method. */
  public nodeType get(nodeType node);

  /*
   * removes the node that is equal to the argument, found by the .equal() method. Throws exception
   * if no such node exists.
   */
  public void removeNode(nodeType node) throws NoSuchElementException;

}
