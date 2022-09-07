package edu.sdsu;


import edu.sdsu.directImplementation.SuffixTree;
import java.util.List;

public class App {
    public static void main(String[] args){
        SuffixTree trieobject = new SuffixTree("apple");
        trieobject.printTree();
        List<String> result = trieobject.searchText("pp");
        System.out.println(result);
    }
}
