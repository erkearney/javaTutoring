public class FirstCharSorter {

    public ArrayNode[] createList() {
        String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";
        ArrayNode arrayList[] = new ArrayNode[baseString.length()];

        for(int i = 0; i < baseString.length(); i++) {
            LinkedList newList = new LinkedList();
            ArrayNode newNode = new ArrayNode(baseString.charAt(i), newList);
            arrayList[i] = newNode;
        }

        return arrayList;
    }

    private class ArrayNode {
        private char startChar;
        private LinkedList list;
        private int wordCount;

        public ArrayNode(char startChar, LinkedList list) {
            this.startChar = startChar;
            this.list = list;
            this.wordCount = 0;
        }

        public char getStartChar() {
            return this.startChar;
        }

        public LinkedList getList() {
            return this.list;
        }

        public int getCount() {
            return this.wordCount;
        }

        public void incrementCount() {
            this.wordCount++;
        }
    }

    public static void main(String args[]) {
        FirstCharSorter sorter = new FirstCharSorter();
        // list contains 36 nodes, each node has a character, a list, and a
        // count, the list all the words that begin with the character, the 
        // count is equal to the number of words in the list.
        ArrayNode[] list = sorter.createList();
        // Add our findUnique, file input, etc.
    }
}
