// --== CS400 Project Two File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 * placeholder class for backend methods either empty or hardcoded for testing purposes
 * 
 * @author Khaled Saleh
 *
 */
public class DictionaryBackendFD implements DictionaryBackendInterface {

  public DictionaryBackendFD() {

  }

  @Override
  public boolean insert(String word, String definition, String type) {
    System.out.println(word + ", " + type + ", " + definition);
    return true;
  }

  public void setDefinition(String word, String definition) throws NoSuchElementException {
    throw new NoSuchElementException();

  }

  public String getStatistics() {
    return "There are 5 words in this dictionary";
  }

  public String getType(String word) throws NoSuchElementException {
    throw new NoSuchElementException();
  }

  public String getDefinition(String word) throws NoSuchElementException {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean loadData(String filename) throws FileNotFoundException {
    return false;

  }

  public void remove(String word) throws NoSuchElementException {
    // TODO Auto-generated method stub

  }

  public void setType(String word, String type) throws NoSuchElementException {
    throw new NoSuchElementException();

  }

}
