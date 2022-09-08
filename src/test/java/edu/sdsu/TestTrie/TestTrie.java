package edu.sdsu.TestTrie;
import org.junit.jupiter.api.BeforeEach;
import edu.sdsu.Trie.SuffixTrie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
public class TestTrie {
    private SuffixTrie trie;
    @BeforeEach
    public void init() {
        trie = new SuffixTrie("apples");
    }

    @Test
    @DisplayName("List Elements in Trie")
    public void displayTrie(){
        String result = trie.printTree();
        String testingTrie = "\n\tapples#{0}\n" +
        "\tp\n" + "\t\tples#{1}\n" + "\t\tles#\n" + "\tles#{3}\n" + "\tes#{4}\n" + "\ts#{5}\n";
        assertEquals(testingTrie, result);
    }
    @Test
    @DisplayName("Display word present in the pattern")
    public void searchPattern(){
        String pattern = "pp";
        List<String> testingWord = new ArrayList<>();
        testingWord.add("a{pp}les");
        List<String> resultWord = trie.searchText(pattern);
        assertEquals(testingWord, resultWord);
    }
}
