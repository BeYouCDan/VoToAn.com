package hw8;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class SpellCheckerTester {

	private SpellChecker spellChecker;

	@Before
	public void setUp() throws Exception {
		spellChecker = new SpellChecker();
		
		String data = "about\nabove\nabsolutely\nacceptable\nadd\nadjacent\nafter\nalgorithm\nall\nalong\nalso\nan";
		
		spellChecker.readDictionary(new StringReader(data));
	}

	@Test
	public void testCorrectWord() {
		String[] variants = spellChecker.checkWord("acceptable");
		assertNull(variants);
	}
	@Test
	public void testMisspelledWord() {
		String[] expected={"above"};
		//System.out.println("I am here in the mispell");
		String[] variants = spellChecker.checkWord("abuve");
		//System.out.println("the variants" + variants[4]);
		assertArrayEquals(variants, expected);
	}
	@Test
	public void testMisspelledWordWithNoVariant() {
		String[] expected={};
		//System.out.println("length of the expected" + expected.length);
		String[] variants = spellChecker.checkWord("bbbbb");
		//System.out.println("length of the variant" + variants.length);
		assertArrayEquals(variants, expected);
	}

}
