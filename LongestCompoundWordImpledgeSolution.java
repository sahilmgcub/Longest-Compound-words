
package longestcompoundwordimpledgesolution;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
My Java code will calculate the longest and the second longest compound words in a file.
A compounded word is one that can be constructed by combining (concatenating) shorter words
also found in the same file. 
Total amount of time to run this program can also be calculated.
*/
class Pair<S> {
	
	private S firstpart;
	private S secondpart;
	
	public Pair(S firstpart, S secondpart) {
		this.firstpart = firstpart;
		this.secondpart = secondpart;
	}
	
	// return firstpart element
	public S firstpart() {
		return firstpart;
	}
	
	// return secondpart element
	public S secondpart() {
		return secondpart;
	}
}

class Trie {
	
	// inner class, only for the use of Trie
	private class TrieNode {
		@SuppressWarnings("unused")
		private char val;			// character stored in the node
		private HashMap<Character, TrieNode> totalchild;	// map name of string to the node
														// which has the string as value
		private boolean isWord;		// if the node is at the end of a word
		
		// constructor
		public TrieNode(char val) {
			this.val = val;
			totalchild = new HashMap<Character, TrieNode>();
			isWord = false;
		}
		
		// add child to trienode
		public void addChild(char child) {
			totalchild.put(child, new TrieNode(child));
		}
		
		// get child of trienode that has the same character as the give one
		public TrieNode getChild(char child) {
			if (!totalchild.keySet().contains(child)) {
				return null;
			}
			
			return totalchild.get(child);
		}
		
		// return true if child exists
		public boolean containsChild(char child) {
			return totalchild.keySet().contains(child);
		}
	}
	
	private TrieNode root;
	private TrieNode curr;
	
	public Trie() {
		root = new TrieNode(' ');	// root
		curr = root;
	}
	
	// insert a word to trie
	public void insert(String s) {
		char letter;
		curr = root;
		
		// traverse every letter of a word
		// update trie if a letter is not in the structure
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			
			if (!curr.containsChild(letter)) {
				curr.addChild(letter);
			} 
			
			curr = curr.getChild(letter);
		}
		
		// mark last letter as the end of a word
		curr.isWord = true;
	}
	
	// return starting indices of all suffixes of a word
	public List<Integer> getSuffixesStartIndices(String s) {
		List<Integer> indices = new LinkedList<Integer>();	// store indices
		char letter;
		curr = root;	// start from root
		
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			

			if (!curr.containsChild(letter))
				return indices;
			
			// move on to the child node
			curr = curr.getChild(letter);
			
			// if the letter is an end to a word, it means after the letter is a suffix
			// update indices
			if (curr.isWord)
				indices.add(i + 1);
		}
		
		return indices;
	}
	
}

// used to store a pair of word and 

public class LongestCompoundWordImpledgeSolution {

    public static void main(String[] args) throws FileNotFoundException{
        
        long start = System.currentTimeMillis();

		// change Txtfile name accordingly
            File Txtfile = new File("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\words for problem.txt");

		// Trie
            Trie trie = new Trie();
            LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		
		// used to calculate the total amount of compound words
            HashSet<String> compoundWords = new HashSet<String>();
		
		// scan the TxtTxtfile
            @SuppressWarnings("resource")
            Scanner s = new Scanner(Txtfile);

            String word;		// a word
            List<Integer> sufIndices;	// indices of suffixes of a word
		
		/* read words from the Txtfile
		fill up the queue with words which have suffixes, who are
		candidates to be compound words
		insert each word in trie
                */ 
            while (s.hasNext()) {
		word = s.next();		
		sufIndices = trie.getSuffixesStartIndices(word);
		
		for (int i : sufIndices) {
                    if (i >= word.length())		// if index is out of bound
			break;				// it means suffixes of the word has
							// been added to the queue if there is any
                    queue.add(new Pair<String>(word, word.substring(i)));
                    }
	
                    trie.insert(word);
		}
		
            Pair<String> p;			// a pair of word and its remaining suffix
            int maxLength = 0;			// longest compound word length
		//int sec_maxLength = 0;		// second longest compound word length		
            String longest = "";		// longest compound word
            String sec_longest = "";	// second longest compound word

            while (!queue.isEmpty()) {
		p = queue.removeFirst();
		word = p.secondpart();
			
		sufIndices = trie.getSuffixesStartIndices(word);
			
			// if no suffixes found, which means no prefixes found
			// discard the pair and check the next pair
		if (sufIndices.isEmpty()) {
                    continue;
		}
			
			//System.out.println(word);
		for (int i : sufIndices) {
                    if (i > word.length()) { // sanity check 
			break;
                    }
				
                    if (i == word.length()) { // no suffix, means it is a compound word
			// check if the compound word is the longest
			// if it is update both longest and second longest
			// words records
			if (p.firstpart().length() > maxLength) {
			//sec_maxLength = maxLength;
                            sec_longest = longest;
                            maxLength = p.firstpart().length();
                            longest = p.firstpart();
			}
			
			compoundWords.add(p.firstpart());	// the word is compound word
					
			} 
                    else {		
                        queue.add(new Pair<String>(p.firstpart(), word.substring(i)));
			}
                    }
		}
		
		// print out the results
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
                // some time passes
                long end = System.currentTimeMillis();
                long elapsedTime = end - start;
                System.out.println("Total time to run in mili seconds is: " + elapsedTime + "ms");

		
    }
    
}
