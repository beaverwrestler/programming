import java.util.*;
import java.io.*;
import java.util.Map.Entry;

//programs assumes numbers are also words, lower case is same as uppercase
//fully functional

//BOTH VERSIONS STILL CONSIDER A ' AS A WORD, FIX THIS
//alice in winderland should be ~40ms, moby dick should be ~220ms

public class Assignment5_rev0 {
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
        HashMap <String, Integer> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            String temp = words.nextToken();
            temp = temp.toLowerCase();
            try {
                int num = bookMap.put (temp, 1);
                if (num >= 1) 
                    bookMap.put(temp, (num+1));
            }
            catch (NullPointerException e) {}
        }

        Set <Entry<String, Integer>> set = bookMap.entrySet();
        List <Entry<String, Integer>> list = new ArrayList <> (set);

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("20 Most Frequency Words");

        int i = 1;
        for (Entry<String, Integer > entry : list) {
            System.out.println(i + ") \"" + entry.getKey() + "\" has " + entry.getValue() + " instances");
            if (i>19)
                break;
            i++;
        }

        System.out.println("Program is Complete");
    }
}