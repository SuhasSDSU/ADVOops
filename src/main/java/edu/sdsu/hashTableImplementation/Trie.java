package edu.sdsu.hashTableImplementation;

public class Trie {

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public boolean insert(String str) {
        TrieNode head = root;
        head.setCount(head.getCount()+1);
        for(char ch : str.toCharArray()){

            if(head.getChild(ch) == null){
                head.setChild(ch);
            }
            head = head.getChild(ch);
            head.setCount(head.getCount()+1);
        }
        if(head.isLeafNode()){
            return false;
        }
        head.setLeafNode(true);
        return true;
    }

    public boolean searchCompleteWord(String str) {
        TrieNode head = root;
        for(char ch : str.toCharArray()){
            head = head.getChild(ch);
            if(head == null){
                return false;
            }
        }
        return head.isLeafNode();
    }

    /**
     * count number of words starting with str
     * @param str
     * @return
     */
    public int searchPartialWord(String str) {
        TrieNode head = root;
        for (char ch : str.toCharArray()) {
            head = head.getChild(ch);
            if (head == null) {
                return 0;
            }
        }
        return head.getCount();
    }
}