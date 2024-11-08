// --== CS400 Project One File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Backend Developer to efficiently store and retrieve information about posts by keywords that are
// included in their
// title, or body, or both their title and body. It also provides a method that retrieves statistics
// about the dataset that is being searched over.
public class CHSearchBackendBD implements CHSearchBackendInterface {

  private HashtableWithDuplicateKeysBD<String, PostBD> hashtable; // this CHSearch hashtable
  private PostReaderBD postReader; // this CHSearch post reader
  private List<PostBD> list; // the list to search from

  /**
   * constructor
   * 
   * @param hashtable  to be used to store all keys and corresponding Lists
   * @param postReader to be used to read files to load data in
   */
  public CHSearchBackendBD(HashtableWithDuplicateKeysBD<String, PostBD> hashtable,
      PostReaderBD postReader) {
    this.hashtable = hashtable;
    this.postReader = postReader;
  }

  @Override
  /**
   * takes data from a file and loads it into the hashtable
   * 
   * Not properly implemented as of now, hard coded for testing purposes
   */
  public void loadData(String filename) throws FileNotFoundException {
    list = postReader.readPostsFromFile(filename);
    for (int i = 0; i < list.size(); i++) {
      String[] titleWords = findWords(list.get(i).getTitle()).split(" ");
      String[] bodyWords = findWords(list.get(i).getBody()).split(" ");

      for (int j = 0; j < titleWords.length; j++) {
        if (hashtable.containsKey("TITLE:" + titleWords[j])) {
          if (!hashtable.get("TITLE:" + titleWords[j]).contains(list.get(i))) {
            hashtable.putOne("TITLE:" + titleWords[j], list.get(i));
          }
        } else {
          hashtable.putOne("TITLE:" + titleWords[j], list.get(i));
        }

      }
      for (int k = 0; k < bodyWords.length; k++) {
        if (hashtable.containsKey("BODY:" + bodyWords[k])) {
          if (!hashtable.get("BODY:" + bodyWords[k]).contains(list.get(i))) {
            hashtable.putOne("BODY:" + bodyWords[k], list.get(i));
          }
        } else {
          hashtable.putOne("BODY:" + bodyWords[k], list.get(i));
        }
      }
    }

  }

  @Override
  /**
   * finds all URLs that correspond with the posts found when searching for key words in the title
   * 
   * @param words the keywords to be searched for in the title
   * @return a List of all the URLs that correspond with matching posts
   */
  public List<String> findPostsByTitleWords(String words) {
    List<String> posts = new ArrayList<String>(); // initiates List of Strings to be returned later
    String[] realWords = findWords(words).split(" "); // splits inputed parameter into individual
                                                      // words
    // iterates through all words that were inputed and searches them in the hashtable one by one
    for (int i = 0; i < realWords.length; i++) {
      // if word inputed exists in hashtable as a title word it adds the corresponding Post's URL to
      // be returned later
      if (hashtable.containsKey("TITLE:" + realWords[i])) {
        // finds every Post that corresponds with inputed word and stores in a List
        List<PostBD> tempList = (hashtable.get("TITLE:" + realWords[i]));
        // adds all URLs to String to be returned later if they're not already on there
        for (int j = 0; j < tempList.size(); j++) {
          if (!posts.contains(tempList.get(j).getUrl())) {
            posts.add(tempList.get(j).getUrl());
          }
        }
      }
    }
    return posts; // List of Posts that have a corresponding key word that was inputed
  }

  @Override
  /**
   * finds all URLs that correspond with the posts found when searching for key words in the body
   * 
   * @param words the keywords to be searched for in the body
   * @return a List of all the URLs that correspond with matching posts
   */
  public List<String> findPostsByBodyWords(String words) {
    List<String> posts = new ArrayList<String>(); // initiates List of Strings to be returned later
    String[] realWords = findWords(words).split(" "); // splits inputed parameter into individual
                                                      // words
    // iterates through all words that were inputed and searches them in the hashtable one by one
    for (int i = 0; i < realWords.length; i++) {
      // if word inputed exists in hashtable as a body word it adds the corresponding Post's URL to
      // be returned later
      if (hashtable.containsKey("BODY:" + realWords[i])) {
        // finds every Post that corresponds with inputed word and stores in a List
        List<PostBD> tempList = (hashtable.get("BODY:" + realWords[i]));
        // adds all URLs to String to be returned later if they're not already on there
        for (int j = 0; j < tempList.size(); j++) {
          if (!posts.contains(tempList.get(j).getUrl())) {
            posts.add(tempList.get(j).getUrl());
          }
        }
      }
    }
    return posts; // List of Posts that have a corresponding key word that was inputed
  }

  @Override
  /**
   * finds all URLs that correspond with the posts found when searching for key words in the title
   * or body
   * 
   * @param words the keywords to be searched for in the title or body
   * @return a List of all the URLs that correspond with matching posts
   */
  public List<String> findPostsByTitleOrBodyWords(String words) {
    List<String> posts = new ArrayList<String>(); // initiates List of Strings to be returned later
    String[] realWords = findWords(words).split(" "); // splits inputed parameter into individual
                                                      // words
    // iterates through all words that were inputed and searches them in the hashtable one by one
    for (int i = 0; i < realWords.length; i++) {
      // if word inputed exists in hashtable as a title word it adds the corresponding Post's URL to
      // be returned later
      if (hashtable.containsKey("TITLE:" + realWords[i])
          || hashtable.containsKey("BODY:" + realWords[i])) {
        // finds every Post that corresponds with inputed word and stores in a List
        List<PostBD> tempList1 = (hashtable.get("TITLE:" + realWords[i]));
        List<PostBD> tempList2 = (hashtable.get("BODY:" + realWords[i]));

        // adds all URLs to String to be returned later if they're not already on there
        for (int j = 0; j < tempList1.size(); j++) {
          if (!posts.contains(tempList1.get(j).getUrl())) {
            posts.add(tempList1.get(j).getUrl());
          }
        }
        // adds all URLs to String to be returned later if they're not already on there
        for (int k = 0; k < tempList2.size(); k++) {
          if (!posts.contains(tempList2.get(k).getUrl())) {
            posts.add(tempList2.get(k).getUrl());
          }
        }
      }
    }
    return posts; // List of Posts that have a corresponding key word that was inputed
  }

  @Override
  /**
   * retrieves the number of values and capacity of the hashtable as a String
   * 
   * @retrun the String that details the number of values in the hashtable and its capacity
   */
  public String getStatisticsString() {
    return "Number of Values: " + hashtable.getNumberOfValues() + ", Capacity: "
        + hashtable.getCapacity();
  }

  /**
   * helper method for all other methods in this class other than getStatisticsString() removes all
   * non-letter characters in a String and puts all letters to lowercase
   * 
   * @param words the String of words to be altered
   * @return the original String passed through with all non-letter characters removed and all
   *         lowercase letters
   */
  public String findWords(String words) {
    return words.replaceAll("[^a-zA-Z ]", "").toLowerCase();
  }

}
