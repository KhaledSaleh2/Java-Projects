import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordReaderDW implements WordReaderInterface {

  @Override
  public List<WordInterface> readWordsFromFile(String filename) throws FileNotFoundException {
    List<WordInterface> result = new ArrayList<WordInterface>();
    Scanner in = new Scanner(new File(filename), ("UTF-8"));
    final String UTF8_BOM = "\uFEFF";

    while (in.hasNextLine()) {
      List<String> end = new ArrayList<String>();
      String input = in.nextLine();
      if (input.startsWith(UTF8_BOM)) {
        input = input.substring(1);
      }
      int start = 0;
      boolean inQuotes = false;
      for (int current = 0; current < input.length(); current++) {
        if (input.charAt(current) == '\"')
          inQuotes = !inQuotes; // toggle state
        else if (input.charAt(current) == ',' && !inQuotes) {
          end.add(input.substring(start, current));
          start = current + 1;
        }
      }
      end.add(input.substring(start));

      result.add(new Word(end.get(0), end.get(2).replaceAll("^\"|\"$", ""), end.get(1)));
    }
    in.close();

    return result;
  }

  public static void main(String[] args) {
    WordReaderDW t = new WordReaderDW();
    try {
      t.readWordsFromFile("DictionaryWordsToinput.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}

