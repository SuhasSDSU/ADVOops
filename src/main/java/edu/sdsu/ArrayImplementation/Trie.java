package edu.sdsu.ArrayImplementation;
import java.util.*;

public class Trie {
    private final Node root;
    public Trie(){
        root = new Node('\0');
    }
    public void insertString (String word){
        word = word.toLowerCase();
        Node currentNode = root;
        for(int i = 0; i < word.length(); i++){
            char characterFromWord = word.charAt(i);
            int index = characterFromWord - 'a';
            System.out.println(index);
            if(currentNode.children[index] == null){
                currentNode.children[index] = new Node(characterFromWord);
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isWord = true; // pushed
    }// end insert

    public boolean search(String pattern){
        Node currentNode = getNode(pattern);
        return currentNode != null && currentNode.isWord;
    }// end search

    public boolean startsWith(String prefix){
        return getNode(prefix) != null;
    }
    public Node getNode(String word){
        Node currentNode = root;
        char characterFromWord ;
        for(int i = 0; i < word.length(); i++){
            characterFromWord = word.charAt(i);
            int index = characterFromWord - 'a';
            if(currentNode.children[index] == null){
                return null;
            }
            currentNode = currentNode.children[index];
        }
        return currentNode;
    }// end getNode

    public List<String> getAllNodes(){
        Node currentNode = root;
//        String result = String.valueOf(new StringBuilder(""));
        List<String> result = new ArrayList<>();
        int index = 0;
        while(currentNode.isWord ){
            currentNode = currentNode.children[index];
            result.add(String.valueOf(currentNode.children[index].characterFromWord));
            System.out.println(currentNode.children[index]);
            index++;
        }
        System.out.println(result);
        return result;
    }
}
