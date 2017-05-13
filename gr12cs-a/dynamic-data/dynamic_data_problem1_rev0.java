//By Artin S
import java.io.*;
import java.util.*;

public class dynamic_data_problem1_rev0 {
    public static void main (String [] args) throws IOException, FileNotFoundException {
        BufferedReader textInp = new BufferedReader (new FileReader ("input.txt"));
        int numLoop = Integer.parseInt (textInp.readLine());
        System.out.println("Finding the number of Substrings");
        
        for (int k = 0; k < numLoop; k++) {
            Set <String> mySet = new HashSet <String> ();
            String str = textInp.readLine();
            System.out.println("String: " + str);
            for (int i = 0; i <= str.length(); i++) {
                for (int j = i; j <= str.length(); j++) {
                    mySet.add(str.substring (i, j));
                }  
            }
            System.out.println("No. of Substrings: " + mySet.size());
        }
    }
}