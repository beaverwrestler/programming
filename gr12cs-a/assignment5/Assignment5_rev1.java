import java.util.*;
import java.io.*;

//programs assumes numbers are also words, lower case is same as uppercase

public class Assignment5_rev1 {
    public static void main (String [] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader text = new BufferedReader (new FileReader ("input.txt"));
        StringBuilder book = new StringBuilder ();
        String line = text.readLine();

        while (line != null){   //imports the whole goddamn book
            book.append(line + " ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString().toLowerCase();
        StringTokenizer words = new StringTokenizer (finalStr, "~!@#$%^&*()_+{}|:\"<>?`=[];, ./-\\");
        Map <String, Object> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            Word tempWord;
            String temp = words.nextToken().toLowerCase();
            try {
                tempWord = (Word) bookMap.put (temp, new Word (1));
                if (tempWord.getInsta () >= 1)
                   bookMap.put(temp, (tempWord.getInsta()+1));
            }
            catch (NullPointerException e) {}
            catch (ClassCastException e) {}
        }
        ArrayList <Word> wordArray = new ArrayList<>();
        //wordArray = bookMap.values();

        System.out.println(bookMap.size());
        System.out.println ("Time: " + (System.currentTimeMillis() - startTime));
    }
}