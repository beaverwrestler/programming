import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Worker_rev1 extends Thread{
    private String book;
    private int status = 0;
    private HashMap <String, Integer> bookMap = new HashMap <> ();

    public Worker_rev1(String book) {
        this.book = book;
    }

    @Override
    public void run() {
        StringTokenizer words = new StringTokenizer (book, "~!@#$%^&*()_+{}|<>:\"?`=[];, ./-\\");
        while (words.hasMoreTokens()) {
            String temp = words.nextToken();
            try {
                if (!temp.equals("'")) {
                    if (bookMap.containsKey (temp))
                        bookMap.put(temp.toLowerCase(), bookMap.get(temp)+1);
                    else
                        bookMap.put(temp.toLowerCase(), 1);
                }
            }
            catch (NullPointerException e) {}
        }
        status =1;
    }

    public HashMap <String, Integer> getArray () {
        return bookMap;
    }
    public int getStatus () {
        return status;
    }
}
