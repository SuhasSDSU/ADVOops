package edu.sdsu.Trie;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class SuffixTrie {

    private static final String TERMINATION_CHARACTER = "#";
    private static final int POSITION_UNDEFINED = -1;
    public Node root;
    private final String fullText;

    /**
     * Initialize the text
     * @param text -- String entered by the user
     */
    public SuffixTrie(String text) {
        text = text.toLowerCase();
        root = new Node("", POSITION_UNDEFINED);
        for (int i = 0; i < text.length(); i++) {
            addSuffix(text.substring(i) + TERMINATION_CHARACTER, i);
        }
        fullText = text;
    }

    /**
     * Search the pattern provided in the trie
     * @param pattern -- String to search word
     * @return List
     */
    public List<String> searchText(String pattern) {
        pattern = pattern.toLowerCase();
        List<String> result = new ArrayList<>();
        List<Node> nodes = getAllNodes(pattern, root, true);

        if (nodes.size() > 0) {
            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null) {
                List<Integer> positions = getPositions(lastNode);
                positions = positions.stream()
                        .sorted()
                        .collect(Collectors.toList());
                String finalPattern = pattern;
                positions.forEach(m -> result.add((markPatternInText(m, finalPattern))));
            }
        }
        return result;
    }

    /**
     * Splits the word into nodes and continues to process
     * each character present in the substring of word
     * @param suffix -- Character that is generated into a Node
     * @param position -- Location of Character in the Trie
     */
    private void addSuffix(String suffix, int position) {
        suffix = suffix.toLowerCase();
        List<Node> nodes = getAllNodes(suffix, root, true);
        if (nodes.size() == 0) {
            addChildNode(root, suffix, position);
        } else {
            // splits the first character of each suffix
            Node lastNode = nodes.remove(nodes.size() - 1);
            String newText = suffix;
            if (nodes.size() > 0) {
                String existingSuffixUptoLastNode = nodes.stream()
                        .map(Node::getText)
                        .reduce("", String::concat);

                // Remove prefix from newText already included in parent
                newText = newText.substring(existingSuffixUptoLastNode.length());
            }
            extendNode(lastNode, newText);

        }
    }

    /**
     * Useful in Trie Traversal to get the positions of the
     * character and return those
     *
     * @param node -- The Node that stores the individual character
     * @return List -- Provides the location of each character
     */
    private List<Integer> getPositions(Node node) {
        List<Integer> positions = new ArrayList<>();
        if (node.getText()
                .endsWith(TERMINATION_CHARACTER)) {
            positions.add(node.getPosition());
        }
        for (int i = 0; i < node.getChildren()
                .size(); i++) {
            positions.addAll(getPositions(node.getChildren()
                    .get(i)));
        }
        return positions;
    }

    /**
     * Useful in marking the search pattern present in the word
     * which is present in the trie
     * @param startPosition -- int value that would be used in specifying the start point
     *                      of pattern
     * @param pattern -- the search pattern that may exist in the trie
     * @return String -- return the word where the pattern exists
     */
    private String markPatternInText(int startPosition, String pattern) {
        String matchingTextLHS = fullText.substring(0, startPosition);
        String matchingText = fullText.substring(startPosition, startPosition + pattern.length());
        String matchingTextRHS = fullText.substring(startPosition + pattern.length());
        return matchingTextLHS + "{" + matchingText + "}" + matchingTextRHS;
    }

    /**
     * Used in creating childNodes for each character that is not present in
     * the trie
     * @param parentNode -- the root Node of the child
     * @param text -- entered String that would be used in creating Character Nodes
     * @param position -- positions of character Node in the trie structure
     */
    private void addChildNode(Node parentNode, String text, int position) {
        text = text.toLowerCase();
        parentNode.getChildren()
                .add(new Node(text, position));
    }

    /**
     * Checks for the common prefix to create connection with the
     * suffix
     * @param node
     * @param newText
     */
    private void extendNode(Node node, String newText) {
        String currentText = node.getText();
        String commonPrefix = getLongestCommonPrefix(currentText, newText);

        if (!commonPrefix.equals(currentText)) {
            String parentText = currentText.substring(0, commonPrefix.length());
            String childText = currentText.substring(commonPrefix.length());
            splitNodeToParentAndChild(node, parentText, childText);
        }

        String remainingText = newText.substring(commonPrefix.length());
        addChildNode(node, remainingText, SuffixTrie.POSITION_UNDEFINED);
    }

    /**
     * Splits the word and adds TERMINATION_CHARACTER to each node that
     * is split
     * @param parentNode
     * @param parentNewText
     * @param childNewText
     */
    private void splitNodeToParentAndChild(Node parentNode, String parentNewText, String childNewText) {
        Node childNode = new Node(childNewText, parentNode.getPosition());

        if (parentNode.getChildren()
                .size() > 0) {
            while (parentNode.getChildren()
                    .size() > 0) {
                childNode.getChildren()
                        .add(parentNode.getChildren()
                                .remove(0));
            }
        }

        parentNode.getChildren()
                .add(childNode);
        parentNode.setText(parentNewText);
        parentNode.setPosition(POSITION_UNDEFINED);
    }

    /**
     * Checks the common pattern that could be present
     * in both str1 and str2
     * @param str1 -- Word present in the trie
     * @param str2 -- pattern that needs to be searched in the trie
     * @return
     */
    private String getLongestCommonPrefix(String str1, String str2) {
        int compareLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < compareLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, compareLength);
    }

    /**
     * Used in Performing DFS for given Node
     *
     * @param pattern -- Search Pattern entered by the user
     * @param startNode -- The Node that has the character
     * @param isAllowPartialMatch --
     * @return
     */
    private List<Node> getAllNodes(String pattern, Node startNode, boolean isAllowPartialMatch) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < startNode.getChildren()
                .size(); i++) {
            Node currentNode = startNode.getChildren()
                    .get(i);
            String nodeText = currentNode.getText();
            if (pattern.charAt(0) == nodeText.charAt(0)) {
            /** For a partial match, if the pattern is shorter or equal in
             length to the node text, we add the current node to our nodes list and
             stop here:
             */
                if (isAllowPartialMatch && pattern.length() <= nodeText.length()) {
                    nodes.add(currentNode);
                    return nodes;
                }

                int compareLength = Math.min(nodeText.length(), pattern.length());
                for (int j = 1; j < compareLength; j++) {
                    if (pattern.charAt(j) != nodeText.charAt(j)) {
                        if (isAllowPartialMatch) {
                            nodes.add(currentNode);
                        }
                        return nodes;
                    }
                }

                nodes.add(currentNode);
                if (pattern.length() > compareLength) {
                    /**
                     * But if pattern length is greater then perform
                     * Recursive search on the child Nodes
                     */
                    List<Node> nodes2 = getAllNodes(pattern.substring(compareLength), currentNode, isAllowPartialMatch);
                    if (nodes2.size() > 0) {
                        nodes.addAll(nodes2);
                    } else if (!isAllowPartialMatch) {
                        nodes.add(null);
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }

    public String printTree() {
        return root.printTree("");
    }
}