// --== CS400 Project Two File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Driver class that allows user to interact with dictionary takes input from user and calls methods
 * from backend to enact the proper function
 * 
 * @author Khaled Saleh
 *
 */
public class DicSearchFrontendFD implements DicSearchFrontendInterface {


  private Scanner input; // scanner used to get input from user
  private DictionaryBackendInterface backend; // used to call methods from backend

  // constructor
  public DicSearchFrontendFD(Scanner userInput, DictionaryBackendInterface backend) {
    input = userInput;
    this.backend = backend;
  }

  @Override
  /**
   * Loop that provides the user with prompts to use the dictionary Carries out the desired command
   * of user based on their input Driver of program
   */
  public void runCommandLoop() {
    // displays welcome message
    System.out.println("Welcome to the Dictionary Search App!\n");

    char command = '0';
    String word;
    while (command != 'Q') {
      // gets command from user
      command = mainMenuPrompt();
      // L to load file data
      if (command == 'L') {
        loadDataCommand();
        command = quitPrompt();
        // W to search for word
      } else if (command == 'W') {
        searchWord();
        command = quitPrompt();
        // T to search a words type of speech
      } else if (command == 'T') {
        System.out.println("Enter the word you want to search\n");
        word = chooseWordPrompt();
        System.out.println("This words type of speech is: " + searchTypeSpeech(word));
        command = quitPrompt();
        // A to add a word
      } else if (command == 'A') {
        addWord();
        command = quitPrompt();
        // R to remove a word
      } else if (command == 'R') {
        removeWord();
        command = quitPrompt();
        // D to add a definition
      } else if (command == 'D') {
        addDefinition();
        command = quitPrompt();
        // B to add a type of speech to a word
      } else if (command == 'B') {
        addType();
        command = quitPrompt();
        // S to display stats of dictionary
      } else if (command == 'S') {
        displayStatsCommand();
        command = quitPrompt();
        // if command does not correlate with any of the commands displayed prompt for another
        // command
      } else {
        System.out.println("This is not one of the commands, please try again");
        String i = input.nextLine().trim();
        if (i.length() > 0) {
          command = Character.toUpperCase(i.charAt(0));
        } else {
          command = 'n';
        }
      }
    }
    System.out.println("Thank you for using our dictionary!");


  }

  @Override
  /**
   * prompts user for their command
   * 
   * @return the users command
   */
  public char mainMenuPrompt() {
    System.out.println("Here are the possible operations:\n");
    // L to load data
    System.out.println("L: load data/many words from the file into dictionary\n");
    // W to search for a word
    System.out.println("W: search for a word\n");
    // T to search for a type of speech
    System.out.println("T: search type of speech\n");
    // A to add a words
    System.out.println("A: add a word\n");
    // R to remove a word
    System.out.println("R: remove a word\n");
    // D to change the definition to a word
    System.out.println("D: change the definition to a word\n");
    // B to change the type of speech to a word
    System.out.println("B: change the type of speech to a word\n");
    // S to display stats of dictionary
    System.out.println("S: to display stats of the dictionary\n");

    // read in user's choice, and trim away any leading or trailing whitespace
    System.out.println("Choose command: ");
    String i = input.nextLine().trim();
    if (i.length() == 0) // if user's choice is blank, return null character
      return '\0';
    // otherwise, return an uppercase version of the first character in input
    return Character.toUpperCase(i.charAt(0));
  }

  @Override
  /**
   * loads file of data into dictionary
   */
  public void loadDataCommand() {
    System.out.println("Enter the name of the file to load: ");
    String filename = input.nextLine().trim();
    try {
      if (backend.loadData(filename)) {
        System.out.println("File successfully loaded!");
        displayStatsCommand();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
    }

  }

  @Override
  /**
   * prompts user for a word/String used in most methods
   */
  public String chooseWordPrompt() {
    return input.nextLine().trim();
  }

  @Override
  /**
   * searches and displays the types of speech and definition of a word inputted by user prints
   * error message if word doesn't exist in dictionary
   */
  public void searchWord() {

    // prompts user for word to search
    System.out.println("Enter the word to search");
    String word = chooseWordPrompt().toLowerCase();
    try {
      // searches and prints types of speech and definitions of this word
      System.out.println("\n" + word + ":");
      System.out.println(searchTypeSpeech(word));
      System.out.println("Definition: " + searchDefinition(word));
    } catch (NoSuchElementException e) {
      // error message if word doesn't exist in dictionary
      System.out.println("This word does not exist in the dictionary");
    }
  }

  @Override
  /**
   * finds all types of speech of a word specified by user
   * 
   * @param word to be searched
   * @return arrayList of types of speech for the word
   * @throws NoSuchElementException if word doesn't exist
   */
  public String searchTypeSpeech(String word) {
    try {
      String type = backend.getType(word);
      return type;
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException();
    }


  }

  @Override
  /**
   * finds all definitions of a word specified by user
   * 
   * @param word to be searched for
   * @return arrayList of all definitions of a word
   * @throws NoSuchelementException if word doesn't exist
   */
  public String searchDefinition(String word) {
    try {
      String def = backend.getDefinition(word);
      return def;
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException();
    }
  }

  @Override
  /**
   * adds word to dictionary
   */
  public void addWord() {
    // prompts user for word
    System.out.println("Enter the word you want to add");
    String word = chooseWordPrompt().toLowerCase();
    // adds type of speech for this word
    System.out.println("Enter the type of speech for this word");
    String type = chooseWordPrompt();
    System.out.println("Enter the definition of this word");
    // adds definition to this word
    String def = chooseWordPrompt();
    // adds word
    if (backend.insert(word, def, type) == true) {
      System.out.println("Word inserted");
    } else
      // if word already exists in dictionary
      System.out.println("Word not added: this word already exists");
  }

  @Override
  /**
   * removes word from dictionary
   */
  public void removeWord() {
    // prompts user for word to be removed
    System.out.println("Enter the word to remove");
    String word = chooseWordPrompt().toLowerCase();
    backend.remove(word); // removes word
    System.out.println(word + " removed from dictionary");
  }

  @Override
  /**
   * adds definition to existing word in dictionary
   */
  public void addDefinition() {
    // prompts user for word to add definition to
    System.out.println("Enter the word you want to change the definition to");
    String word = chooseWordPrompt().toLowerCase();
    // prompts user for definition to be added
    System.out.println("Enter the definition you want to change to for this word");
    String def = chooseWordPrompt();
    try {
      backend.setDefinition(word, def); // adds definition to word
      System.out.println("Definition of " + word + " changed");
    } catch (NoSuchElementException e) {
      System.out.println(word + " doesn't exist in the dictionary");
    }
  }

  @Override
  /**
   * add type of speech to word that already exists in dictionary
   */
  public void addType() {
    // prompts user for word to add definition to
    System.out.println("Enter the word you want to change the type of speech to");
    String word = chooseWordPrompt().toLowerCase();
    // prompts user for type of speech to be added
    System.out.println("Enter the type of speech you want to change to");
    String type = chooseWordPrompt();
    try {
      backend.setType(word, type); // adds type of speech to word
      System.out.println("Type of speech for " + word + " changed");
    } catch (NoSuchElementException e) {
      System.out.println(word + " doesn't exist in the dictionary");
    }
  }

  @Override
  /**
   * prints out statistics of dictionary
   */
  public void displayStatsCommand() {
    System.out.println(backend.getStatistics());
  }

  /**
   * helper method that asks user if they want to quit
   */
  public char quitPrompt() {
    System.out.println("\nType Q if you would like to quit");
    System.out.println("Type another character if you would like to continue");
    String i = input.nextLine().trim();
    if (i.length() == 0) // if user's choice is blank, return null character
      return '\0';
    // otherwise, return an uppercase version of the first character in input
    return Character.toUpperCase(i.charAt(0));
  }
}
