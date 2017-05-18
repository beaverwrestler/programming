import java.util.*;
import java.io.*;

//programs assumes numbers are also words, lower case is same as uppercase
//fully functional

//BOTH VERSIONS STILL CONSIDER A ' AS A WORD, FIX THIS

public class Assignment5_rev1 {
    public static void main (String [] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        String FILENAME = "input.txt";
        System.out.println("Most Frequent Words in a File");
        System.out.println("File name: " + FILENAME);
        BufferedReader text = new BufferedReader (new FileReader (FILENAME));
        StringBuilder book = new StringBuilder ();
        String line = text.readLine();

        while (line != null){   //imports the whole goddamn book
            book.append(line + " ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString().toLowerCase();
        StringTokenizer words = new StringTokenizer (finalStr, "~!@#$%^&*()_+{}|:\"<>?`=[];, ./-\\");
        HashMap <String, Word> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            Word tempWord;
            String temp = words.nextToken().toLowerCase();
            try {
                tempWord = (Word) bookMap.put (temp, new Word (1, temp));
                if (tempWord.getInsta () >= 1)
                   bookMap.put(temp, new Word( tempWord.getInsta()+1, temp));
            }
            catch (NullPointerException e) {}
            catch (ClassCastException e) {}
        }
        ArrayList <Word> wordArray = new ArrayList <> (bookMap.values());
        Collections.sort(wordArray, new Comparator<Word>() {    //i vaguely understand what this code is, it was autocompleted in intellij
            @Override
            public int compare(Word o1, Word o2) {
                return o2.getInsta() - o1.getInsta();
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("20 Most Frequency Words");

        for (int i = 1; i <21; i++) {
            System.out.println(i + ") \"" + wordArray.get(i).getWord() + "\" has " + wordArray.get(i).getInsta() + " instances");
        }

        System.out.println("Program is Complete");
    }
}