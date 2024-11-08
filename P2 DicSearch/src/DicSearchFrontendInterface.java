// --== CS400 Project Two File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none



/**
 * interface for Frontend class
 * 
 * @author Khaled Saleh
 *
 */
public interface DicSearchFrontendInterface {

  // public DicSearchFrontendXX(Scanner userInput, DicSearchBackendInterface backend)

  public void runCommandLoop();

  public char mainMenuPrompt();

  public void loadDataCommand();

  public String chooseWordPrompt();

  public void searchWord();

  public String searchTypeSpeech(String word);

  public String searchDefinition(String word);

  public void addWord();

  public void removeWord();

  public void addDefinition();

  public void addType();

  public void displayStatsCommand();


}
