public class Node implements Comparable<Node> {
    private String word;
    private int count;

    public Node(String word) {
        this.word = word;
        this.count = 1;
    }

    public String getWord() {
        return this.word;    
    }

    public int getCount() {
        return this.count;    
    }

    public void incrementCount() {
        this.count++;    
    }

    // https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
    @Override
    public int compareTo(Node compareNode) {
        return this.word.compareTo(compareNode.getWord());    
    }
}
