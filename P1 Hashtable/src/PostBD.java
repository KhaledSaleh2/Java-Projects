// --== CS400 Project One File Header ==--
// Name: Khaled Saleh
// CSL Username: ksaleh
// Email: khsaleh@wisc.edu
// Lecture #: 4, MWF 3:30-4:20
// Notes to Grader: none

// Placeholder class for Post object
public class PostBD {

  private String title; // title of this post
  private String url; // url for this post
  private String body; // body for this post

  /**
   * constructor
   * 
   * @param title this Post's title
   * @param url   this Post's url
   * @param body  this Post's body
   */
  public PostBD(String title, String url, String body) {
    this.title = title;
    this.url = url;
    this.body = body;
  }

  // getter for this posts title
  public String getTitle() {
    return title;
  }

  // getter for this posts url
  public String getUrl() {
    return url;
  }

  // getter for this posts body
  public String getBody() {
    return body;
  }


}
