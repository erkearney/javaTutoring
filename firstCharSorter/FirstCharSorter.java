import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FirstCharSorter {
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

    private ArrayNode[] createList() {
        String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";
        ArrayNode arrayList[] = new ArrayNode[baseString.length()];

        for(int i = 0; i < baseString.length(); i++) {
            LinkedList newList = new LinkedList();
            ArrayNode newNode = new ArrayNode(baseString.charAt(i), newList);
            arrayList[i] = newNode;
        }

        return arrayList;
    }

    private void checkUniqueWord(String checkWord, ArrayNode[] list) { 
        // Get the first character
        String firstChar = Character.toString(checkWord.charAt(0));
        // https://www.geeksforgeeks.org/for-each-loop-in-java/
        for(ArrayNode node : list) {                    
            // TODO fix this disgusting line
            if(firstChar.compareToIgnoreCase(Character.toString(node.getStartChar())) == 0) {
                node.getList().setCurrentToHead();
                while(node.getList().getCurrent() != null) {
                    if(node.getList().getCurrent().getWord().equals(checkWord)) {
                        node.getList().getCurrent().incrementCount();
                        return;
                    }
                    node.getList().getNext();
                }
                // If we get here, the word is not already in the list
                node.getList().addNode(checkWord);
                node.incrementCount();
                return;
            }
        }
        // If we get here, something went wrong. . .
        System.out.format("ERROR: No appropriate node found for %s\n", checkWord);
        System.exit(1);

        return; 
    }

    public static void main(String args[]) throws IOException {
        if(args.length != 2) {
            System.out.println("FirstCharSorter use: <InputFileName> <OutputFileName>");
            System.exit(1);
        }

        FirstCharSorter sorter = new FirstCharSorter();
        // list contains 36 nodes, each node has a character, a list, and a
        // count, the list all the words that begin with the character, the 
        // count is equal to the number of words in the list.
        ArrayNode[] list = sorter.createList();


        // Setup input
        final String FILE_INPUT = args[0];

        File inFile = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            inFile = new File(FILE_INPUT);
            fr = new FileReader(inFile);
            br = new BufferedReader(fr);
        } catch(Exception e) {
            System.out.format("File: %s not found!\n", FILE_INPUT);
            System.exit(1);
        }

        // Read the input file
        String line;
        String[] words = new String[50];
        while((line = br.readLine()) != null) {
            words = line.split(" ");
            for(int i = 0; i < words.length; i++) {
                sorter.checkUniqueWord(words[i], list);
            }
        }

        // Setup output
        final String FILE_OUTPUT = args[1];
        File outFile = new File(FILE_OUTPUT);
        // Create the file if it doesn't already exist
        if(!outFile.exists()) {
            outFile.createNewFile();
        }

        // Write to outFile
        FileWriter fw = new FileWriter(outFile);
        BufferedWriter bw = new BufferedWriter(fw);
        String writeLine;
        for(ArrayNode node : list) {
            writeLine = "There are " + node.getCount() + " word(s) that start with " + node.getStartChar() + ":\n";
            bw.flush();
            bw.write(writeLine);
            node.getList().setCurrentToHead();
            while(node.getList().getCurrent() != null) {
                writeLine = node.getList().getCurrent().getWord() + ", " + node.getList().getCurrent().getCount() + "\n";
                bw.flush();
                bw.write(writeLine);
                node.getList().getNext();
            }
        }
    }
}
