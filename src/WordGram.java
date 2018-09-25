import java.util.Arrays;

/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Nicholas Panjwani
 *
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram 
	 * @param source
	 * @param start
	 * @param size
	 */
	public WordGram(String[] source, int start, int size) {
		/* A bug in this block caused the rest of the code
		 * to not run until it was resolved. There was 
		 * confusion about the indices of the for loop
		 * which forced incorrect sizing of arrays. */
		myWords = new String[size];
		myToString = null;
		myHash = 0;
		for(int k = 0; k < size; k++) {  // Start at index 0 and loop until array of size 3 is created
			myWords[k] = source[start+k]; // Elements 0-2 are filled with source values from start to end of loop
		}
		
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * @return integer denoting size of object
	 */
	public int length(){
		return this.myWords.length; // Copies length of string array myWords
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		
		
		for(int j = 0; j < wg.length(); j++) {
			if (! this.myWords[j].equals(wg.myWords[j])) {
				return false;
			}
		}
		
		return true;
		
	}

	@Override
	public int hashCode(){
		if(myHash == 0) {
			myHash = this.toString().hashCode();
		}
		return myHash;
	}
	

	/**
	 * @param last is last String of returned WordGram
	 * @return new WordGram which has shifted the last two items up 
	 * and inserted a new item at index 2
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		for(int i = 0; i < myWords.length - 1; i++) {
			wg.myWords[i] = this.myWords[i+1];
		}
		wg.myWords[wg.length() - 1] = last;
		return wg;
	}

	@Override
	public String toString(){
		if (myToString == null) {
			myToString = String.join(" ", myWords);
		}
		return myToString;
	}
}
