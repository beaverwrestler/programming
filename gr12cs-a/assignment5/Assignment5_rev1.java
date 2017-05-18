import java.util.*;
import java.io.*;

//programs assumes numbers are also words, lower case is same as uppercase
//BOTH VERSIONS STILL CONSIDER A ' AS A WORD, FIX THIS ----> in ALICE 

public class Assignment5_rev1 {
    public static void main (String [] args) throws FileNotFoundException, IOException {
        System.out.println("Most Frequent Words in a File");
        String fileName = getFileName ();        
        long startTime = System.currentTimeMillis();
        System.out.println("File name: " + fileName + "\nBeginning search...");
        BufferedReader text = new BufferedReader (new FileReader (fileName));
        StringBuilder book = new StringBuilder ();
        String line = text.readLine();

        while (line != null){   //imports the whole book
            book.append(line + " ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString().toLowerCase();
        StringTokenizer words = new StringTokenizer (finalStr, "~!@#$%^&*()_+{}|:\"<'>?`=[];, ./-\\");
        HashMap <String, Word> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            String temp = words.nextToken().toLowerCase();
            try {
                if (bookMap.containsKey (temp)) {
                    bookMap.put(temp, bookMap.get(temp).addBy(1));
                }
                else {
                    Word addWord = new Word (1, temp);
                    bookMap.put(temp, addWord);
                }
            }
            catch (NullPointerException e) {}
            catch (ClassCastException e) {}
        }
        
        ArrayList <Word> wordArray = new ArrayList <> (bookMap.values());
        Collections.sort(wordArray, new Comparator<Word>() {    //gotta love autocomplete
            @Override
            public int compare(Word o1, Word o2) {
                return o2.getInsta() - o1.getInsta();
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("20 Most Frequency Words");
        for (int i = 1; i <21; i++)
            System.out.println(i + ") \"" + wordArray.get(i-1).getWord() + "\" has " + wordArray.get(i-1).getInsta() + " instances");
        System.out.println("Program is Complete");
    }
    
    private static String getFileName () {
        BufferedReader usrIO = new BufferedReader (new InputStreamReader (System.in));
        String name = "";
        
        while (name.isEmpty()) {
            try {
                System.out.print("What is the name of the file you'd like to open?: ");
                name = usrIO.readLine();
                if (!name.contains (".txt"))    //if user forgets to add ".txt"
                    name += ".txt";
                BufferedReader testing = new BufferedReader (new FileReader (name));
                testing.close();
                usrIO.close();
            }
            catch (FileNotFoundException e) {
                name = "";
            }
            catch (IOException e) {
                name = "";
            }
        }
        return name;
    }
}