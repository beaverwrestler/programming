import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//programs assumes numbers are also words, lower case is same as uppercase
//THIS IS THE ACTIVE REVISION

public class Assignment5_rev2 {
    public static void main (String [] args) throws IOException {
        System.out.println("Most Frequent Words in a File");
        String fileName = getFileName ();
        long startTime = System.currentTimeMillis();
        BufferedReader text = new BufferedReader (new FileReader (fileName));
        StringBuilder book = new StringBuilder ();
        System.out.println("File name: " + fileName + "\nBeginning search...");

        String line = text.readLine();
        while (line != null){   //imports the whole book
            book.append(line +" ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString();
        String [] parts = splitToFour(finalStr);

        Worker one = new Worker(parts [0]);     //starting workers
        one.start();
        Worker two = new Worker(parts [1]);
        two.start();
        Worker three = new Worker(parts [2]);
        three.start();
        Worker four = new Worker(parts [3]);
        four.start();

        while (one.getStatus() == 0 || two.getStatus() == 0 || three.getStatus() == 0 || four.getStatus() == 0) {}
        //after computation is done

        System.out.println ("combining: " + (System.currentTimeMillis() - startTime) + "ms\n20 Most Frequent Words\n");
        //make this faster ^^^^

        HashMap <String, Word> bookMapComb = new HashMap <> (one.getArray());
        HashMap <String, Word> bookMapTwo = new HashMap <> (two.getArray());
        HashMap <String, Word> bookMapThree = new HashMap <> (three.getArray());
        HashMap <String, Word> bookMapFour = new HashMap <> (four.getArray());

        Iterator it2 = bookMapTwo.entrySet().iterator();
        Iterator it3 = bookMapThree.entrySet().iterator();
        Iterator it4 = bookMapFour.entrySet().iterator();

        bookMapComb = merge(bookMapComb, it2);
        bookMapComb = merge(bookMapComb, it3);
        bookMapComb = merge(bookMapComb, it4);

        ArrayList <Word> wordArray = new ArrayList <> (bookMapComb.values());

        Collections.sort(wordArray, new Comparator<Word>() {    //gotta love autocomplete
            @Override
            public int compare(Word o1, Word o2) {
                return o2.getInsta() - o1.getInsta();
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms\n20 Most Frequent Words\n");
        for (int i = 1; i <21 && i < wordArray.size()+1; i++)
            System.out.println(i + ") \"" + wordArray.get(i-1).getWord() + "\" has " + wordArray.get(i-1).getInsta() + " instances");
        System.out.println("\nProgram is Complete");
    }

    private static HashMap <String, Word> merge (HashMap <String, Word> main, Iterator it) {
        Word tempW;
        Map.Entry info;

        while (it.hasNext()) {
            info = (Map.Entry) it.next();
            tempW = (Word) info.getValue();
            String temp = tempW.getWord();
            int num = tempW.getInsta();
            if (main.containsKey (temp))
                main.put(temp, main.get(temp).addBy(num));
            else
                main.put(temp, new Word (1, temp));
        }
        return main;
    }
    
    private static String getFileName () {
        BufferedReader usrIO = new BufferedReader (new InputStreamReader (System.in));
        String name = "";
        
        while (name.isEmpty()) {
            try {
                System.out.print("What is the name of the file you'd like to open?: ");
                name = usrIO.readLine().trim();
                if (!name.contains (".txt"))    //if user forgets to add ".txt"
                    name += ".txt";
                BufferedReader testing = new BufferedReader (new FileReader (name));
                testing.close();
                usrIO.close();
            }
            catch (IOException e) {
                name = "";
            }
        }
        return name;
    }

    private static String [] splitToFour (String book) {
        String [] words = new String [4];
        for (int j= 4; j>1; j--) {
            for (int i = book.length()/j; i < book.length(); i++)
                if (book.charAt(i) == ' ') {
                    words [(4-j)] = book.substring(0, i);
                    book = book.substring(i);
                    break;
                }
        }
        words [3] = book;
        return words;
    }
}