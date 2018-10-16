public class LinkedList {
    private Node head = null;
    private Node current = head;

    public String addNode(String word) {
        Node newNode = new Node(word);
        // Check if the list is empty
        if(head != null) {
            current.setNext(newNode);
            current = newNode;
        } else {
            head = newNode;    
        }
        return word;
    } 

    class Node {
        // Each word in the list is held in a Node object, which also holds
        // the frequency of that word, and a pointer to the next Node
        private String word;
        private int count;
        private Node nextNode = null;

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

        public Node getNext() {
            return this.nextNode;
        }

        public void setNext(Node nextNode) {
            this.nextNode = nextNode;
        }

        public int incrementCount() {
            return this.count++;
        }
    }
}
