// --== CS400 Project One File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
// placeholder class for PostReader object type
public class PostReaderBD {
  // constructor
  public PostReaderBD() {

  }

  /**
   * takes and reads a file of posts, stores them into a List and returns that list
   * 
   * @param filename the String of the file to be read
   * @return the List of all the posts inside the file
   * @throws FileNotFoundException if file cannot be found
   */
  public List<PostBD> readPostsFromFile(String filename) throws FileNotFoundException {
    List<PostBD> list = new ArrayList<PostBD>(); // stores posts extracted from file
    PostBD test1 = new PostBD("Panda Express", "P.com", "Chinese food");
    PostBD test2 = new PostBD("Subway", "Subway.com", "The Best Subs");
    PostBD test3 = new PostBD("The Sett", "Sett.com", "Union South");

    list.add(test1);
    list.add(test2);
    list.add(test3);

    return list; // List of all posts inside file
  }
}
