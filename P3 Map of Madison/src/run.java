
public class run {

  public static void main (String[] args) {
    DijkstraFrontend run = new DijkstraFrontend();
    run.backend.loadData("Madison.gv");
    run.runCommandLoop();
  }
}
