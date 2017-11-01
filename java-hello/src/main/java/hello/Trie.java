package hello;

public class Trie {
    private Trie[] subTries = new Trie[256];;
    private boolean end = false;
    public Trie() { }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word.length() == 0) {
            end = true;
            return;
        }
        
        int start = word.charAt(0);
        if (subTries[start] == null) {
            subTries[start] = new Trie();            
        }
     
        Trie sub = subTries[start];
        sub.insert(word.substring(1));
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) return false;
        if (word.length() == 0) return end;	        
        int start = word.charAt(0);
        if (subTries[start] == null) return false;

        return subTries[start].search(word.substring(1));
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) return true;
        int start = prefix.charAt(0);
        if (subTries[start] == null) return false;
        return subTries[start].startsWith(prefix.substring(1));
    }
    
}