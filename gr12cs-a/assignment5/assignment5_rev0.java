import java.util.*;
import java.io.*;

//programs assumes numbers are also words, lower case is same as uppercase

public class assignment5_rev0 {
    public static void main (String [] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader text = new BufferedReader (new FileReader ("input.txt"));
        String book = "";
        String line = text.readLine();

        while (line != null){   //imports the whole goddamn book
            book += " " + line.toLowerCase().trim();
            line = text.readLine();
        }

        text.close();
        StringTokenizer words = new StringTokenizer (book, "~!@#$%^&*()_+{}|:\"<>?`=[];, ./-\\");
        Map <String, Integer> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            String temp = words.nextToken();
            temp = temp.toLowerCase();
            int num = 1;
            try {
                num = bookMap.put (temp, 1);
                if (num >= 1) {
                    bookMap.put(temp, (num+1));
                }
            }
            catch (NullPointerException e) {}
        }

        System.out.println(bookMap);
        System.out.println ("Time: " + (System.currentTimeMillis() - startTime));
    }
}