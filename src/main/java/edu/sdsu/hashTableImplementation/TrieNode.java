package edu.sdsu.hashTableImplementation;

//import java.util.Map;
//import java.util.HashMap;
import edu.sdsu.abstractData.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    boolean isLeafNode;
    int count;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        count = 0;
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }

    public TrieNode setChild(char ch) {
        return children.add(ch, new TrieNode());
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
