public class DLL {
    private Node head;
    private Node current = head;

    public void insertNode(String word) {
        // For now just push
        Node newNode = new Node(word, head, null);
        head = newNode;
        current = head;
    }

    public Node getCurrentNode() {
        return current;    
    }

    public Node getNextNode() {
        // Use this method to iterate throught the list
        current = current.getNext();
        return current;
    }

    public void printList() {
        while(current != null) {
            System.out.println(current.getWord());
            current.getNext();
        }
    }

    public void alphabetizeList() {
        // https://docs.oracle.com/javase/6/docs/api/java/lang/String.html#compareTo%28java.lang.String%29    
    }

    class Node {
        private String word;
        private int count;
        private Node prev;
        private Node next;
        
        Node(String word, Node next, Node prev) {
            this.word = word;
            this.count = 1;
            this.next = next;
            this.prev = prev;
        }

        public String getWord() {
            return this.word;    
        }

        public int getCount() {
            return this.count;    
        }

        public Node getNext() {
            return this.next;    
        }

        public Node getPrev() {
            return this.prev;    
        }

        // Setters, yuck
        public void setNext(Node next) {
            this.next = next;    
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setCount(int count) {
            this.count = count;    
        }
    }    
}
