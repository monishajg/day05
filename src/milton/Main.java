package milton; // basic 1

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main { // basic 2

    public static final String HEADER = "word,count\n"; // basic 3

    public static void main(String[] args) throws Exception {
        String fileName = args[0];

        System.out.printf("Processing %s\n", fileName); // end of step 1.1
        
        // Open the file, read the first 100 lines print out each line
        // Close the file when done
        // If the file is less than 100 lines, close when done
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);             // end of step 1.2 

        Integer totalWords = 0;         // define variable to be determined, start from 0
        Map<String, Integer> wordMap = new HashMap<>(); // String= word, Integer= count

        for (Integer i =1; i <= 100; i++) {
            String line = br.readLine();    // call on the lines from br using .ReadLine function
            if (null == line)               // if there is no line, end
                break;
            //System.out.printf("%d : %s", i, line.toUpperCase());
            String[] words =line.trim().split(" ");
            totalWords += words.length;
            for (String w: words) {
                String clearWord = w.replaceAll(",", "");
                Integer v = wordMap.getOrDefault(w,0);
                v++;
                wordMap.put(clearWord,v);
                //if (wordMap.containsKey(w)) {
                //    wordMap.put(w, wordMap.get(w) + 1);
                //} else
                //    wordMap.put(w, 1);
            } // for
        } // for

        br.close();
        fr.close();

        Set<String> uniqueWords = wordMap.keySet();
        
        //for (String w: uniqueWords)
        //  System.out.printf("> %s: %d\n", w, wordMap.get(w));

        System.out.printf("The number of words in the first 100 lines: %d\n", totalWords);

        System.out.printf("Number of unique words: %D\n", uniqueWords.size());

        // Create CSV file
        FileOutputStream fos = new FileOutputStream(args[1]);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        osw.write(HEADER);
        for (String w: wordMap.keySet()) {
            String line = String.format("%s, %d\n", w, wordMap.get(w));
            osw.write(line);
        }

        osw.flush();
        osw.close();
        fos.close();

    }// main

}

