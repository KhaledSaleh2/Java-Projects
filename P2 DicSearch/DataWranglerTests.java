import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataWranglerTests {

	@Test
	public void testGetWord() {
		Word test = new Word("Word","Description","Type");
		assertEquals("Word",test.getWord());
	}
	
	@Test
	public void testGetDescription() {
		Word test = new Word("Word","Description","Type");
		assertEquals("Description", test.getDescription().get(0));
	}
	
	@Test
	public void testGetType() {
		Word test = new Word("Word","Description","Type");
		assertEquals("Type",test.getType());
	}
	
	@Test
	public void testWordReaderMissingFile() {
		WordReaderDW reader = new WordReaderDW();
		assertThrows(FileNotFoundException.class, ()-> reader.readWordsFromFile("NotTheRightFile"));		
	}
	
	@Test
	public void testWordReader() throws FileNotFoundException {
		WordReaderDW reader = new WordReaderDW();
		List<WordInterface> words = reader.readWordsFromFile("DictionaryWordsToTest.csv");
		assertEquals("Bored",words.get(0).getWord());
		assertEquals("Adjective",words.get(0).getType());
	}
}
