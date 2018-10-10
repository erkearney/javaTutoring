/* Written by Eric Kearney on 9/19/2018
** This program takes a text file as an
** input and writes out all the unique
** words, along with the frequency of
** each word out to a file, using a
** Doublely linked list.
*/
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ListReader {
    private static String FILE_INPUT;
    private static String FILE_OUTPUT;
    // Create a list to hold each unique word and its frequency
    private static DLL list = new DLL();

    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.out.println("ListReader use: <inputFileName> <outputFileName>");
            System.exit(1);
        } 
        
        FILE_INPUT = args[0];
        FILE_OUTPUT = args[1];

        // Setup input
        File inFile = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            inFile = new File(FILE_INPUT);
            fr = new FileReader(inFile);
            br = new BufferedReader(fr);
            // https://stackoverflow.com/questions/9648811/specific-difference-between-bufferedreader-and-filereader
        } catch(Exception e) {
            System.out.format("File: %s not found!\n", FILE_INPUT);
            System.exit(1);
        }

        // Setup output
        File outFile = new File(FILE_OUTPUT);
        // Create the file if it doesn't already exist
        if(!outFile.exists()) {
            outFile.createNewFile();    
        }

        FileWriter fw = new FileWriter(outFile);
        BufferedWriter bw = new BufferedWriter(fw);

        // Read the input file
        String line;
        String[] words = new String[50];
        while((line = br.readLine()) != null) {
            words = line.split(" ");
            for(int i = 0; i < words.length; i++) {
                checkUniqueWords(words[i]);    
            }
        }

        // Write to outFile
        String writeLine;
        while(list.getCurrentNode() != null) {
            writeLine = list.getCurrentNode().getWord() + ", " + list.getCurrentNode().getCount() + "\n";

            bw.flush();
            bw.write(writeLine);
            list.getNextNode();
        }
        bw.close();
    }    

    public static void checkUniqueWords(String checkWord) {
        while(list.getCurrentNode() != null) {
            if(list.getCurrentNode().getWord().equals(checkWord)) {
                // We have a match
                list.getCurrentNode().setCount(list.getCurrentNode().getCount() + 1);
                return;
            } else {
                list.getNextNode();    
            }    
        }    
        // If we get here, there was no match
        list.insertNode(checkWord);
    }
}
