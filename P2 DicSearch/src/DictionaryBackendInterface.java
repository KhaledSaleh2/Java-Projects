import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public interface DictionaryBackendInterface {

  public boolean loadData(String filename) throws FileNotFoundException;

  public boolean insert(String word, String definition, String type);

  public void remove(String word) throws NoSuchElementException;

  public String getDefinition(String word) throws NoSuchElementException;

  public String getType(String word) throws NoSuchElementException;

  public void setDefinition(String word, String definition) throws NoSuchElementException;

  public void setType(String word, String type) throws NoSuchElementException;

  public String getStatistics();

}
