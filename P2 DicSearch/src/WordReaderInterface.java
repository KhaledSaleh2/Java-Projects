import java.io.FileNotFoundException;
import java.util.List;

public interface WordReaderInterface {
  public List<WordInterface> readWordsFromFile(String filename) throws FileNotFoundException;
}
