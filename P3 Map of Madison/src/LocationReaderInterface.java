
import java.io.FileNotFoundException;
import java.util.List;

public interface LocationReaderInterface {

  // public LocationReader();
  // make an arraylist to get Locations, put into graph by AE

  public void readGraphFromFile(String filename)
      throws FileNotFoundException;
  
  public List<String> getNodes();

  public List<List<String>> getEdges();

}
