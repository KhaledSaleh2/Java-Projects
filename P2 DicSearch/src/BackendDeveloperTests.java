import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  public void test1() {
    WordReaderDW wordReader = new WordReaderDW();
    AlgorithmEngineer AE = new AlgorithmEngineer();
    DictionaryBackend BD = new DictionaryBackend(AE, wordReader);

    BD.loadData("File");
    assertEquals("Type", BD.getType("Word"));
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  public void test2() {
    WordReaderDW wordReader = new WordReaderDW();
    AlgorithmEngineer AE = new AlgorithmEngineer();
    DictionaryBackend BD = new DictionaryBackend(AE, wordReader);

    BD.loadData("File");
    assertEquals("Definition", BD.getDefinition("Word"));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  public void test3() {
    WordReaderDW wordReader = new WordReaderDW();
    AlgorithmEngineer AE = new AlgorithmEngineer();
    DictionaryBackend BD = new DictionaryBackend(AE, wordReader);

    BD.loadData("File");
    BD.setType("Word", "Type");
    assertEquals("Type", BD.getType("Word"));
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  public void test4() {
    WordReaderDW wordReader = new WordReaderDW();
    AlgorithmEngineer AE = new AlgorithmEngineer();
    DictionaryBackend BD = new DictionaryBackend(AE, wordReader);

    BD.loadData("File");
    BD.setDefinition("Word", "Definition");
    assertEquals("Definition", BD.getDefinition("Word"));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  public void test5() {
    WordReaderDW wordReader = new WordReaderDW();
    AlgorithmEngineer AE = new AlgorithmEngineer();
    DictionaryBackend BD = new DictionaryBackend(AE, wordReader);

    BD.loadData("File");
    BD.insert("Word", "Type", "Definition");
    assertEquals(4, BD.getWordCount());
    assertEquals("There are 4 words in this dictionary.", BD.getStatistics());
    BD.remove("Word");
    assertEquals(3, BD.getWordCount());
    assertEquals("There are 3 words in this dictionary.", BD.getStatistics());
  }
}
