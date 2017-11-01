package hello;

public class HelloWorld {
    public static void main(String[] args) {
    	Trie obj = new Trie();
    	String word = "hi";
    	String prefix = "h";
    	obj.insert("Trie");
    	obj.insert("insert");
    	obj.insert("search");
    	obj.insert("startsWith");
    	boolean param_2 = obj.search("ab");
    	obj.startsWith("ab");
    	boolean param_21 = obj.search("a");
    	boolean param_3 = obj.startsWith(prefix);
    	System.out.println("xxxxx");
    }
}