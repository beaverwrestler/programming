import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Worker extends Thread{
    private String book;
    private int status = 0;
    private ArrayList<Word> wordArray;
    private HashMap <String, Word> bookMap = new HashMap <> ();

    public Worker (String book) {
        this.book = book;
    }

    @Override
    public void run() {
        StringTokenizer words = new StringTokenizer (book, "~!@#$%^&*()_+{}|<>:\"?`=[];, ./-\\");
        while (words.hasMoreTokens()) {
            String temp = words.nextToken().toLowerCase();
            try {
                if (!temp.equals("'")) {
                    if (bookMap.containsKey (temp))
                        bookMap.put(temp, bookMap.get(temp).addBy(1));
                    else
                        bookMap.put(temp, new Word (1, temp));
                }
            }
            catch (NullPointerException e) {}
        }
        status =1;
    }

    public HashMap <String, Word> getArray () {
        return bookMap;
    }
    public int getStatus () {
        return status;
    }
}
