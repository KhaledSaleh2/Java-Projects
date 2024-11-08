// --== CS400 Project Two File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none


import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import org.junit.Test;

/**
 * class used to run JUnit tests for FrontendDeveloper
 * 
 * @author Khaled Saleh
 *
 */
public class FrontendDeveloperTests {

  // menu prompt
  String s = "Welcome to the Dictionary Search App!\n\nHere are the possible operations:\n\nL: "
      + "load data/many words from the file into dictionary\n\nW: search for a word\n\nT: search type of speech\n\nA: add a word\n\nR: "
      + "remove a word\n\nD: change the definition to a word\n\nB: change the type of speech to a word\n\nS: to display stats of the dictionary\n\nChoose command: \n";
  // quit prompt
  String q =
      "\nType Q if you would like to quit\nType another character if you would like to continue\n"
          + "Thank you for using our dictionary!\n";

  @Test
  /**
   * tests if the display stats command is working properly
   */
  public void displayStatsTest() {
    // runs program in which user displays stats of the dictionary
    TextUITester tester = new TextUITester("s\nQ");
    Scanner scan = new Scanner(System.in);
    DicSearchFrontendFD test = new DicSearchFrontendFD(scan, new DictionaryBackendFD());
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s + "There are 5 words in this dictionary\n" + q, output);
    scan.close();
  }

  @Test
  /**
   * tests if insert command working properly
   */
  public void insertTest() {
    // runs program in which user inserts a word then quits
    TextUITester tester = new TextUITester("a\nred\nnoun\na color\nQ");
    Scanner scan = new Scanner(System.in);
    DicSearchFrontendFD test = new DicSearchFrontendFD(scan, new DictionaryBackendFD());
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(
        s + "Enter the word you want to add\nEnter the type of speech for this word"
            + "\nEnter the definition of this word\nred, noun, a color\nWord inserted\n" + q,
        output);
  }

  @Test
  /**
   * tests if add definition command working properly
   */
  public void addDefinitionTest() {
    // runs program in which user adds a definition to a word that doesn't exist then quits
    TextUITester tester = new TextUITester("d\nred\na color\nQ");
    Scanner scan = new Scanner(System.in);
    DicSearchFrontendFD test = new DicSearchFrontendFD(scan, new DictionaryBackendFD());
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s
        + "Enter the word you want to change the definition to\nEnter the definition you want to change to for this word\nred doesn't exist in"
        + " the dictionary\n" + q, output);
  }

  @Test
  /**
   * tests if search word command working properly
   */
  public void searchWordTest() {
    // runs program in which user searches up a word that doesn't exist then quits
    TextUITester tester = new TextUITester("w\nred\nQ");
    Scanner scan = new Scanner(System.in);
    DicSearchFrontendFD test = new DicSearchFrontendFD(scan, new DictionaryBackendFD());
    test.runCommandLoop();
    // tracks output
    String output = tester.checkOutput();
    // checks if output is correct
    assertEquals(
        s + "Enter the word to search\n\nred:\nThis word does not exist in the dictionary\n" + q,
        output);
  }

  @Test
  /**
   * tests if add type command working properly
   */
  public void addTypeTest() {
    // runs program with placeholder classes in which user adds a type of speech to a word that
    // doesn't exist then quits
    TextUITester tester = new TextUITester("b\nred\na color\nQ");
    Scanner scan = new Scanner(System.in);
    DicSearchFrontendFD test = new DicSearchFrontendFD(scan, new DictionaryBackendFD());
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s
        + "Enter the word you want to change the type of speech to\nEnter the type of speech you want to change to\nred doesn't exist in"
        + " the dictionary\n" + q, output);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  /**
   * Integration test 1 Tests loadData method which uses integration from all 3 roles
   */
  public void loadDataIntegrationTest() {
    // runs program without using any placeholder classes
    // user inputs the name for a file to be added
    TextUITester tester = new TextUITester("l\nDictionaryWordsToTest.csv\nQ");
    Scanner scan = new Scanner(System.in);
    AlgorithmEngineer<Word> tree = new AlgorithmEngineer();
    WordReaderDW wordReader = new WordReaderDW();
    DicSearchFrontendFD test =
        new DicSearchFrontendFD(scan, new DictionaryBackend(tree, wordReader));
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s
        + "Enter the name of the file to load: \nFile successfully loaded!\nThere are 20 words in this dictionary.\n"
        + q, output);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  /**
   * Integration test 2 Tests user adding a word which uses integration from all 3 roles
   */
  public void addWordIntegrationTest() {
    // runs program without using any placeholder classes
    // user inputs the name for a file to be added
    TextUITester tester = new TextUITester("a\nred\nnoun\na color\nq");
    Scanner scan = new Scanner(System.in);
    AlgorithmEngineer<Word> tree = new AlgorithmEngineer();
    WordReaderDW wordReader = new WordReaderDW();
    DicSearchFrontendFD test =
        new DicSearchFrontendFD(scan, new DictionaryBackend(tree, wordReader));
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s
        + "Enter the word you want to add\nEnter the type of speech for this word\nEnter the definition of this word\n"
        + "Word inserted\n" + q, output);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  /**
   * Code review test 1 Tests BackendDevelopers loadData method throws an exception when the file
   * doesn't exist
   */
  public void codeReviewOfBackendDeveloper1() {
    // runs program without using any placeholder classes
    // user inputs the name for a file to be added
    TextUITester tester = new TextUITester("l\nDFAKEFILE\nQ");
    Scanner scan = new Scanner(System.in);
    AlgorithmEngineer<Word> tree = new AlgorithmEngineer();
    WordReaderDW wordReader = new WordReaderDW();
    DicSearchFrontendFD test =
        new DicSearchFrontendFD(scan, new DictionaryBackend(tree, wordReader));
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s + "Enter the name of the file to load: \nFile not found.\n" + q, output);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Test
  /**
   * Code review test 2 Tests BackendDevelopers getStatistics command states there's 0 words if no
   * words are inserted
   */
  public void codeReviewOfBackendDeveloper2() {
    // runs program without using any placeholder classes
    // user inputs the name for a file to be added
    TextUITester tester = new TextUITester("s\nQ");
    Scanner scan = new Scanner(System.in);
    AlgorithmEngineer<Word> tree = new AlgorithmEngineer();
    WordReaderDW wordReader = new WordReaderDW();
    DicSearchFrontendFD test =
        new DicSearchFrontendFD(scan, new DictionaryBackend(tree, wordReader));
    test.runCommandLoop();
    // tracks output and checks if it's correct
    String output = tester.checkOutput();
    assertEquals(s + "There are 0 words in this dictionary.\n" + q, output);
  }
}
