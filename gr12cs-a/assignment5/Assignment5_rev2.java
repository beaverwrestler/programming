/* Name: Artin S.   
 * Due Date: Friday, May 19, 2017
 * Description: Searches text file to find the top 20 most common words, it does this by first inputting the book from a text file and then splitting it to four approximately equal pieces. These are all simultaneously searched ad then compiled once they are all done. An arrayList is then made and organised with the top 20, then printed.
 * 
 * PROGRAM ASSUMPTIONS:    - Numbers are also words
 *                         - lower case is same as upper case
 *                         - words separated by dashes are considered separate
 *                         - any form of punctuation separates words (~!@#$%^&*()_+{}|<>:"?`=[];, ./-\)
 *                         - words with apostrophes (ie. don't) are considered as one word
 *                         - roman numerals are considered words
*/ 

import java.io.*;
import java.util.*;

public class Assignment5_rev2 {
    //Parameters: String [] args
    //Return: void
    //Function: main method, all the threads are started and managed from here
    public static void main (String [] args) throws IOException {
        System.out.println("Most Frequent Words in a File");
        String fileName = getFileName ();        //gets file name from the user 
        long startTime = System.currentTimeMillis();    //starts timing
        BufferedReader text = new BufferedReader (new FileReader (fileName));
        StringBuilder book = new StringBuilder ();
        System.out.println("File name: " + fileName + "\nBeginning search...");

        String line = text.readLine();
        while (line != null){   //imports the whole book
            book.append(line +" ");
            line = text.readLine();
        }

        text.close();
        String finalStr = book.toString();        //converts the stringbuilder to a string and splits it into four pieces
        String [] parts = splitToFour(finalStr);

        Worker one = new Worker(parts [0]);     //starting external threads
        one.start();
        Worker two = new Worker(parts [1]);
        two.start();
        Worker three = new Worker(parts [2]);
        three.start();
        Worker four = new Worker(parts [3]);
        four.start();

        while (one.getStatus() == 0 || two.getStatus() == 0 || three.getStatus() == 0 || four.getStatus() == 0) {}
        //loops until all threads report as being finished

        HashMap <String, Word> bookMapComb = new HashMap <> (one.getArray());   //makes hashMaps for thread data return
        HashMap <String, Word> bookMapTwo = new HashMap <> (two.getArray());
        HashMap <String, Word> bookMapThree = new HashMap <> (three.getArray());
        HashMap <String, Word> bookMapFour = new HashMap <> (four.getArray());

        bookMapComb = merge(bookMapComb, bookMapTwo.entrySet().iterator());    //merges the individual hashMaps together
        bookMapComb = merge(bookMapComb, bookMapThree.entrySet().iterator());
        bookMapComb = merge(bookMapComb, bookMapFour.entrySet().iterator());

        ArrayList <Word> wordArray = new ArrayList <> (bookMapComb.values());    //sends the hashMap to an ArrayList

        Collections.sort(wordArray, new Comparator<Word>() {    //sorts arrayList
            @Override
            public int compare(Word o1, Word o2) {
                return o2.getInsta() - o1.getInsta();
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms\n20 Most Frequent Words\n");
        for (int i = 1; i <21 &&  i < wordArray.size()+1; i++)    //prints the first 20 elements in the list
            System.out.println(i + ") \"" + wordArray.get(i-1).getWord() + "\" has " + wordArray.get(i-1).getInsta() + " instances");
        System.out.println("\nProgram is Complete");
    }
    
    //Parameters: HashMap of the combined words, Iterator to go through the HashMap
    //Return: HashMap
    //Function: combines one of the thread's hashMaps with the main one
    private static HashMap <String, Word> merge (HashMap <String, Word> main, Iterator it) {
        Word tempW;        //temporary word and entry objects
        Map.Entry info;
        while (it.hasNext()) {
            info = (Map.Entry) it.next();    //gets the next elements in the iterator
            tempW = (Word) info.getValue();
            String temp = tempW.getWord();    //gets the word and val from this element
            int num = tempW.getInsta();
            if (!temp.equals("'")) {    //adds according to whether or not it exists
                if (main.containsKey (temp))
                    main.put(temp, main.get(temp).addBy(num));
                else
                    main.put(temp, new Word (1, temp));
            }
        }
        return main;
    }
    
    //Parameters: none
    //Return: String for valid filename
    //Function: gets a valid file for input
    private static String getFileName () {
        BufferedReader usrIO = new BufferedReader (new InputStreamReader (System.in));
        String name = "";
        
        while (name.isEmpty()) {
            try {
                System.out.print("What is the name of the file you'd like to open?: ");    
                name = usrIO.readLine().trim();     //gets file name
                if (!name.contains (".txt"))    //if user forgets to add ".txt"
                    name += ".txt";
                BufferedReader testing = new BufferedReader (new FileReader (name));    //if it doesn't exist it will throw an exception -> caught -> loops
                testing.close();
                usrIO.close();
            }
            catch (IOException e) {
                name = "";
            }
        }
        return name;
    }

    //Parameters: String of the book
    //Return: String [] for smaller book sections
    //Function: splits the book into four smaller sections for each of the threads
    private static String [] splitToFour (String book) {
        String [] words = new String [4];
        for (int j= 4; j>1; j--) {
            for (int i = book.length()/j; i < book.length(); i++)    //divides the length of the book by the remaining secitons
                if (book.charAt(i) == ' ') {    //searches from an approxiamte point until it finds a whitespace, it's safe to split the book here
                    words [(4-j)] = book.substring(0, i);
                    book = book.substring(i);
                    break;
                }
        }
        words [3] = book;
        return words;
    }
}