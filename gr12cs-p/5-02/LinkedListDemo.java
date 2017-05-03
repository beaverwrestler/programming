import java.io.*;
import java.util.*;

public class LinkedListDemo {
    public static void main (String [] args) {
        
        LinkedList <String> myList = new LinkedList <String> ();
        
        myList.addFirst("peach");
        myList.addLast("rasp");
        myList.addLast("straw");
        myList.addLast("apple");
        myList.addLast("banana");
        myList.addLast("pear");
        myList.addLast("kiwi");
        
        System.out.println("thing: " + myList.removeFirst());
        System.out.println(myList);
    }
}