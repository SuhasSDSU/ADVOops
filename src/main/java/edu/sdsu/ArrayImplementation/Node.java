package edu.sdsu.ArrayImplementation;

import java.util.ArrayList;

public class Node {
//    public Node[] children;
    public Node[] children;
    public boolean isWord;
    public char characterFromWord;

    public Node(char characterFromWord){
        this.children = new Node[26];
        this.characterFromWord = characterFromWord;
        this.isWord = false;
    }
}
