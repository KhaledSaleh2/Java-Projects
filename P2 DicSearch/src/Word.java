public class Word implements WordInterface, Comparable<Word> {

  private String word;
  private String definition;
  private String type;

  public Word(String word, String definition, String type) {
    this.word = word;
    this.definition = definition;
    this.type = type;
  }

  public String getWord() {
    return word;
  }

  public String getDefinition() {
    return definition;
  }

  public String getType() {
    return type;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public int compareTo(Word word) {
    return this.word.compareTo(word.getWord());
  }

}
