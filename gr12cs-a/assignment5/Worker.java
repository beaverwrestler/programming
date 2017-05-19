import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Worker extends Thread{
    private String book;
    private int status = 0;
    private ArrayList<Word> wordArray;
    public Worker (String book) {
        this.book = book;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        StringTokenizer words = new StringTokenizer (book, "~!@#$%^&*()_+{}|<>:\"?`=[];, ./-\\");
        HashMap<String, Word> bookMap = new HashMap <> ();

        while (words.hasMoreTokens()) {
            String temp = words.nextToken().toLowerCase();
            try {
                if (!temp.equals("'")) {
                    if (bookMap.containsKey (temp))
                        bookMap.put(temp, bookMap.get(temp).increment());
                    else
                        bookMap.put(temp, new Word (1, temp));
                }
            }
            catch (NullPointerException e) {}
        }
        wordArray = new ArrayList <> (bookMap.values());
        status =1;
        System.out.println("Time" + (System.currentTimeMillis()-startTime));
    }

    public ArrayList <Word> getArray () {
        return wordArray;
    }
    public int getStatus () {
        return status;
    }
}
