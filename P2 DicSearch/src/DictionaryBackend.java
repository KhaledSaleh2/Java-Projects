import java.util.List;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;

public class DictionaryBackend implements DictionaryBackendInterface {
  private AlgorithmEngineer<Word> tree;
  private WordReaderDW wordReader;
  private int wordCount;

  public DictionaryBackend(AlgorithmEngineer<Word> tree, WordReaderDW wordReader) {
    this.tree = tree;
    this.wordReader = wordReader;
    wordCount = 0;
  }

  public boolean loadData(String filename) {
    try {
      List<WordInterface> words = wordReader.readWordsFromFile(filename);
      for (WordInterface word : words) {
        insert(word.getWord().toLowerCase(), word.getDefinition(), word.getType());
      }
      return true;
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      return false;
    }
  }

  public boolean insert(String word, String definition, String type) {
    wordCount++;
    return tree.insert(new Word(word, definition, type));
  }

  public void remove(String word) throws NoSuchElementException {
    Word w = tree.get(new Word(word, null, null));
    if (w == null) {
      throw new NoSuchElementException();
    }
    wordCount--;
    tree.removeNode(w);
  }

  public String getDefinition(String word) throws NoSuchElementException {
    Word w = (Word) tree.get(new Word(word, "", ""));
    if (w == null) {
      throw new NoSuchElementException();
    }
    return w.getDefinition();
  }

  public String getType(String word) throws NoSuchElementException {
    Word w = tree.get(new Word(word, "", ""));
    if (w == null) {
      throw new NoSuchElementException();
    }
    return w.getType().toLowerCase();
  }

  public void setDefinition(String word, String definition) throws NoSuchElementException {
    Word w = tree.get(new Word(word, "", ""));
    if (w == null) {
      throw new NoSuchElementException();
    }
    w.setDefinition(definition);
  }

  public void setType(String word, String type) throws NoSuchElementException {
    Word w = (Word) tree.get(new Word(word, null, null));
    if (w == null) {
      throw new NoSuchElementException();
    }
    w.setType(type);
  }

  public int getWordCount() {
    return wordCount;
  }

  public String getStatistics() {
    return "There are " + wordCount + " words in this dictionary.";
  }
}
