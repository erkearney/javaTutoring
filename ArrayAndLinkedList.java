/* Written by Eric Kearney on 10/08/2018
** This program takes a text file as an
** input, and creates an ArrayList and
** a LinkedList containing all the 
** unique words, as well as the 
** frequency of each unique word.
** Finally, it prints how long it
** took the ArrayLists' and the
** LinkedLists' .sort() method
** to complete.
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ArrayAndLinkedList {
    public static ArrayList<Node> arrLi = new ArrayList<Node>();
    public static LinkedList<Node> linLi = new LinkedList<Node>();

    public static void checkUniqueWords(String checkWord) {
        // We only need to check the ArrayList or the LinkedList for unique words, not both
        for(int i = 0; i < linLi.size(); i++) {
            if(linLi.get(i).getWord().equals(checkWord)) {
                // We have a match, increment the count for both lists
                linLi.get(i).incrementCount();
                arrLi.get(i).incrementCount();
                // Exit the loop
                return;
            } 
        }
        // If we get here, there was no match, add the word to both lists
        Node newNode = new Node(checkWord);
        arrLi.add(newNode);
        linLi.add(newNode);
        return;
    }

    public static void main(String args[]) throws IOException {
        if(args.length != 1) {
            System.out.println("arrayAndLinkedList use: <inputFileName>");
            System.exit(1);
        }
        String FILE_INPUT = args[0];


        // Setup input
        File inFile = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            inFile = new File(FILE_INPUT);
            fr = new FileReader(inFile);
            br = new BufferedReader(fr);
        } catch(Exception e) {
            System.out.format("Input file: %s not found!\n", FILE_INPUT);
            System.exit(1);
        }

        // Read the input file
        String line;
        String[] words = new String[50];
        while((line = br.readLine()) != null) {
            words = line.split(" ");
            for(int i = 0; i < words.length; i++) {
                checkUniqueWords(words[i]);    
            }
        }

        // Test how long the .sort() method takes to complete on the ArrayList and the LinkedList
        long arrLiStart = System.nanoTime();
        Collections.sort(arrLi);
        long arrLiEnd = System.nanoTime();
        long arrLiDuration = (arrLiEnd - arrLiStart);
        System.out.format("arrLi: %d\n", arrLiDuration);

        long linLiStart = System.nanoTime();
        Collections.sort(linLi);
        long linLiEnd = System.nanoTime();
        long linLiDuration = (linLiEnd - linLiStart);
        System.out.format("linLi: %d\n", linLiDuration);
    }    
}
