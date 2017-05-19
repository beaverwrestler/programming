import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//programs assumes numbers are also words, lower case is same as uppercase

public class Assignment5_rev4 {
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

        Worker_rev1 one = new Worker_rev1 (parts [0]);     //starting workers
        one.start();
        Worker_rev1 two = new Worker_rev1(parts [1]);
        two.start();
        Worker_rev1 three = new Worker_rev1(parts [2]);
        three.start();
        Worker_rev1 four = new Worker_rev1(parts [3]);
        four.start();
        Worker_rev1 five = new Worker_rev1(parts [4]);
        five.start();
        Worker_rev1 six = new Worker_rev1(parts [5]);
        six.start();

        while (one.getStatus() == 0 || two.getStatus() == 0 || three.getStatus() == 0 || four.getStatus() == 0 || five.getStatus() == 0 || six.getStatus() == 0) {}
        //after computation is done

        HashMap <String, Integer> bookMapComb = new HashMap <> (one.getArray());
        HashMap <String, Integer> bookMapTwo = new HashMap <> (two.getArray());
        HashMap <String, Integer> bookMapThree = new HashMap <> (three.getArray());
        HashMap <String, Integer> bookMapFour = new HashMap <> (four.getArray());
        HashMap <String, Integer> bookMapFive = new HashMap <> (five.getArray());
        HashMap <String, Integer> bookMapSix = new HashMap <> (six.getArray());

        Iterator it2 = bookMapTwo.entrySet().iterator();
        Iterator it3 = bookMapThree.entrySet().iterator();
        Iterator it4 = bookMapFour.entrySet().iterator();
        Iterator it5 = bookMapFive.entrySet().iterator();
        Iterator it6 = bookMapSix.entrySet().iterator();

        String tempW;
        Map.Entry info;

        while (it2.hasNext()) {
            info = (Map.Entry) it2.next();
            tempW = (String) info.getKey();
            int num = (Integer) info.getValue();
            if (bookMapComb.containsKey (tempW))
               bookMapComb.put(tempW, num+1);
            else
               bookMapComb.put(tempW, 1);
        }

        while (it3.hasNext()) {
            info = (Map.Entry) it3.next();
            tempW = (String) info.getKey();
            int num = (Integer) info.getValue();
            if (bookMapComb.containsKey (tempW))
                bookMapComb.put(tempW, num+1);
            else
                bookMapComb.put(tempW, 1);
        }

        while (it4.hasNext()) {
            info = (Map.Entry) it4.next();
            tempW = (String) info.getKey();
            int num = (Integer) info.getValue();
            if (bookMapComb.containsKey (tempW))
                bookMapComb.put(tempW, num+1);
            else
                bookMapComb.put(tempW, 1);
        }

        while (it5.hasNext()) {
            info = (Map.Entry) it5.next();
            tempW = (String) info.getKey();
            int num = (Integer) info.getValue();
            if (bookMapComb.containsKey (tempW))
                bookMapComb.put(tempW, num+1);
            else
                bookMapComb.put(tempW, 1);
        }

        while (it6.hasNext()) {
            info = (Map.Entry) it6.next();
            tempW = (String) info.getKey();
            int num = (Integer) info.getValue();
            if (bookMapComb.containsKey (tempW))
                bookMapComb.put(tempW, num+1);
            else
                bookMapComb.put(tempW, 1);
        }

        Set <Map.Entry<String, Integer>> set = bookMapComb.entrySet();
        List <Map.Entry<String, Integer>> list = new ArrayList <> (set);

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println ("Total Time: " + (System.currentTimeMillis() - startTime) + "ms\n20 Most Frequent Words\n");

        int i = 1;
        for (Map.Entry<String, Integer > entry : list) {
            System.out.println(i + ") \"" + entry.getKey() + "\" has " + entry.getValue() + " instances");
            if (i>19)
                break;
            i++;
        }

        System.out.println("\nProgram is Complete");
    }

//    private static HashMap <String, Integer> merge () {
//
//    }
    
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
        String [] words = new String [6];
        for (int j= 6; j>1; j--) {
            for (int i = book.length()/j; i < book.length(); i++)
                if (book.charAt(i) == ' ') {
                    words [(6-j)] = book.substring(0, i);
                    book = book.substring(i);
                    break;
                }
        }
        words [5] = book;
        return words;
    }
}