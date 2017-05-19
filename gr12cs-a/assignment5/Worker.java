/* Name: Artin S.   
 * Due Date: Friday, May 19, 2017
 * Description: A thread that takes a small portion of the book, tokenizes it, and sorts them into a HashMap
 */
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Worker extends Thread{
    private String book;
    private int status = 0;
    private ArrayList<Word> wordArray;
    private HashMap <String, Word> bookMap = new HashMap <> ();

    //constructor to get the book string
    public Worker (String book) {
        this.book = book;
    }

    @Override
    //Parameters: none
    //Return: void
    //Function: method called to start the thread
    public void run() {
        StringTokenizer words = new StringTokenizer (book, "~!@#$%^&*()_+{}|<>:\"?`=[];, ./-\\");
        while (words.hasMoreTokens()) {        //makes and gets tokens
            String temp = words.nextToken().toLowerCase();
            try {
                if (!temp.equals("'")) {
                    if (bookMap.containsKey (temp))    //checks if the key already exists and adds it accordingly 
                        bookMap.put(temp, bookMap.get(temp).addBy(1));
                    else
                        bookMap.put(temp, new Word (1, temp));
                }
            }
            catch (NullPointerException e) {}
        }
        status =1;    //indicates that the thread is complete
    }

    //getters
    public HashMap <String, Word> getArray () {
        return bookMap;
    }
    public int getStatus () {
        return status;
    }
}
