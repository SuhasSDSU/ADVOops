package edu.sdsu.Trie;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String text;
    protected List<Node> children;
    private int position;

    public Node(String word, int position) {
        this.text = word;
        this.position = position;
        this.children = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Node> getChildren() {
        return children;
    }

    /**
     *
     * @param depthIndicator -- Starting from the root Node to search each character
     *                       provided in the search method
     * @return String -- returns the string if found
     */
    public String printTree(String depthIndicator) {
        String resultString = "";
        String positionStr = position > -1 ? "{" + String.valueOf(position) + "}" : "";
        resultString += depthIndicator + text + positionStr + "\n";

        StringBuilder stringBuilder = new StringBuilder(resultString);
        for (int i = 0; i < children.size(); i++) {
            stringBuilder.append(children.get(i)
                    .printTree(depthIndicator + "\t"));
        }
        resultString = stringBuilder.toString();
        return resultString;
    }

    /**
     * Overriding to use this in the print statement
     * @return String -- Provide the Trie
     */
    @Override
    public String toString() {
        return printTree("");
    }
}
