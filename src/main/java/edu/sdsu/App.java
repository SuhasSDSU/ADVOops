/**
 * @author: Suhas Pindiproli
 * Problem Statement:
 * Implement Trie with following functionality
 * 1) Insert String
 * 2) Display the structure
 * 3) Search Pattern
 */

package edu.sdsu;


import edu.sdsu.Trie.SuffixTrie;
import java.util.List;

public class App {
    public static void main(String[] args){
        SuffixTrie suffixTrie = new SuffixTrie("apples");
        System.out.println(suffixTrie.printTree());
        List<String> result = suffixTrie.searchText("les");
        if(result.size() == 0){
            result.add("No Elements Found");
        }
        System.out.println(result);
    }
}
