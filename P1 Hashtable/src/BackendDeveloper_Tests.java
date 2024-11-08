import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

// tester class for CHSearchBD methods
public class BackendDeveloper_Tests {

  /**
   * tests if getStatisticsString() method is working properly
   * 
   * @return true if getStatisticsString() is working properly, false otherwise
   */
  public static boolean testStats() {
    // creates test CHSearchBD
    CHSearchBackendBD test = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(), new PostReaderBD());
    // tests functionality of getStatisticsString() is working properly, that numValues and capacity
    // were initiated properly
    if (!test.getStatisticsString().equals("Number of Values: 0, Capacity: 8")) {
      return false;
    }
    return true;
  }

  /**
   * tests if loadData() is working properly
   * 
   * @return true if loadData() is working properly
   */
  public static boolean testLoadData() {
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    try {
      test.loadData("blah"); // should add 12 values
      if (!test.getStatisticsString().equals("Number of Values: 12, Capacity: 15")) {
        return false; // if loadData didn't work properly
      }
      return true; // if all tests pass
    } catch (FileNotFoundException e) {
      return false; // if unexpected exception is thrown throughout tests
    }
  }

  /**
   * tests functionality of findPostByTitleWords() method
   * 
   * @return true if method working properly, false otherwise
   */
  public static boolean testFindPostByTitleWords() {
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    try {
      test.loadData("blah"); // should add 12 values
      List<String> testList = test.findPostsByTitleWords("the"); // should retrieve one post
      if (testList.size() != 1) {
        return false; // if findPostsByTitleWords not working properly
      }
      if (!testList.get(0).equals("Sett.com")) {
        return false; // if wrong post retrieved
      }
      return true; // if all tests pass
    } catch (FileNotFoundException e) {
      return false; // if unexpected exception is thrown throughout tests
    }
  }

  /**
   * tests functionality of findPostByTitleWords() method
   * 
   * @return true if method working properly, false otherwise
   */
  public static boolean testFindPostByBodyWords() {
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    try {
      test.loadData("blah"); // should add 12 values
      List<String> testList = test.findPostsByBodyWords("subs"); // should retrieve one post
      if (testList.size() != 1) {
        return false; // if findPostsByTitleWords not working properly
      }
      if (!testList.get(0).equals("Subway.com")) {
        return false; // if wrong post retrieved
      }
      return true; // if all tests pass
    } catch (FileNotFoundException e) {
      return false; // if unexpected exception is thrown throughout tests
    }
  }

  /**
   * tests functionality of findPostByTitleOrBodyWords() method
   * 
   * @return true if method working properly, false otherwise
   */
  public static boolean testFindPostByTitleOrBodyWords() {
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    try {
      test.loadData("blah"); // should add 12 values

      List<String> testList = test.findPostsByTitleOrBodyWords("the"); // should retrieve two URLs
                                                                       // from two different posts
      if (testList.size() != 2) {
        return false; // if findPostsByTitleWords not working properly
      }
      // tests tp make sure proper URLs were added and in correct order
      if (!testList.get(0).equals("Sett.com")) {
        System.out.println(testList.get(0));
        return false; // if wrong post retrieved
      }
      if (!testList.get(1).equals("Subway.com")) {
        System.out.println(testList.get(1));
        return false; // if wrong post retrieved
      }
      return true; // if all tests pass
    } catch (FileNotFoundException e) {
      return false; // if unexpected exception is thrown throughout tests
    }
  }

  /**
   * tests functionality of the integration of loadDataCommand() from the CHSearchFrontendFD class
   * which uses uses code from Frontend Developer and BackendDeveloper
   * 
   * @return true if user inputs as guided by the comments and integration works properly, false
   *         otherwise
   */
  public static boolean testLoadDataCommand() {
    // creates scanner that takes input
    Scanner scan = new Scanner(System.in);
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test1 = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    CHSearchBackendInterface back = test1;
    // creates test CHSearchFrontendFD using a CHSearchBD
    CHSearchFrontendFD test = new CHSearchFrontendFD(scan, back);
    test.loadDataCommand(); // doesn't matter what String user enters here
    if (!test1.getStatisticsString().equals("Number of Values: 12, Capacity: 15")) {
      return false; // if not working properly
    }
    return true; // if working properly
  }

  /**
   * tests functionality of the integration of searchTitleCommand() from the CHSearchFrontendFD
   * class which uses uses code from Frontend Developer and BackendDeveloper
   * 
   * @return true if user inputs as guided by the comments and integration works properly, false
   *         otherwise
   */
  public static boolean testSearchTitleCommand() {
    // creates scanner that takes input
    Scanner scan = new Scanner(System.in);
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test1 = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    CHSearchBackendInterface back = test1;
    // creates test CHSearchFrontendFD using a CHSearchBD
    CHSearchFrontendFD test = new CHSearchFrontendFD(scan, back);
    test.loadDataCommand(); // doesn't matter what String user enters here
    try {
      // runs menu cycle
      // user will input T to test if integration between findPostByTitleWords() and
      // searchTitleCommand() doesn't cause any exceptions
      // user should quit after inputting T
      test.runCommandLoop();
      return true; // if makes it through without any exceptions
    } catch (Exception e) {
      return false; // if any unexpected exceptions are thrown while interacting with menu
    }
  }

  /**
   * tests functionality of Frontend Developer method chooseSearchWordsPrompt()
   * 
   * @return true if user inputs as guided by the comments, and chooseSearchWordsPrompt() working
   *         properly, false otherwise
   */
  public static boolean testChooseSearchWordsPrompt() {
    // creates scanner that takes input
    Scanner scan = new Scanner(System.in);
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test1 = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    CHSearchBackendInterface back = test1;
    // creates test CHSearchFrontendFD using a CHSearchBD
    CHSearchFrontendFD test = new CHSearchFrontendFD(scan, back);
    // user will use prompt and enter "Hello Hello World!"
    // one word should be stored
    if (test.chooseSearchWordsPrompt().size() != 1) {
      return false; // if method working properly
    }
    return true; // if method not working properly
  }

  /**
   * tests functionality of mainMenuPrompt() when user enters a blank entry mainMenuPrompt() should
   * return a null character in this case
   * 
   * @return true if user inputs as guided by the comments, and mainMenuPrompt() working properly,
   *         false otherwise
   */
  public static boolean testMainMenuPrompt() {
    // creates scanner that takes input
    Scanner scan = new Scanner(System.in);
    // creates test CHSearchBD with a hashtable with capacity 15
    CHSearchBackendBD test1 = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysBD<String, PostBD>(15), new PostReaderBD());
    CHSearchBackendInterface back = test1;
    // creates test CHSearchFrontendFD using a CHSearchBD
    CHSearchFrontendFD test = new CHSearchFrontendFD(scan, back);
    // user will enter a blank message when prompted for an input
    // should return a null character
    if (test.mainMenuPrompt() != '\0') {
      return false; // if not working properly
    }
    return true; // if working properly
  }

  public static void main(String[] args) {
    System.out.print("Backend Developer Individual Test 1: ");
    if (testStats()) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }


    System.out.print("Backend Developer Individual Test 2: ");
    if (testLoadData()) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }


    System.out.print("Backend Developer Individual Test 3: ");
    if (testFindPostByTitleWords()) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }


    System.out.print("Backend Developer Individual Test 4: ");
    if (testFindPostByBodyWords()) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }


    System.out.print("Backend Developer Individual Test 5: ");
    if (testFindPostByTitleOrBodyWords()) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }


    boolean test = testLoadDataCommand();
    System.out.print("Backend Developer Integration Test 1: ");
    if (test) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }

    test = testSearchTitleCommand();
    System.out.print("Backend Developer Integration Test 2: ");
    if (test) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }
    
    
    test = testChooseSearchWordsPrompt();
    System.out.print("Backend Developer Partner (Frontend Developer) Test 1: ");
    if (test) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }

    
    test = testMainMenuPrompt();
    System.out.print("Backend Developer Partner (Frontend Developer) Test 2: ");
    if (test) {
      System.out.println("passed");
    } else {
      System.out.println("failed");
    }
  }
}
