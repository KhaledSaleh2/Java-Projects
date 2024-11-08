import java.util.Scanner;

public class run {

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    AlgorithmEngineer<Word> tree = new AlgorithmEngineer();
    WordReaderDW wordReader = new WordReaderDW();
    DicSearchFrontendFD test =
        new DicSearchFrontendFD(scan, new DictionaryBackend(tree, wordReader));
    test.runCommandLoop();
  }

}
