import java.io.*;
import java.util.*;

public class dynamic_data_problem1_rev0 {
    public static void main (String [] args) throws IOException, FileNotFoundException {
        BufferedReader textInp = new BufferedReader (new FileReader ("imput.txt"));
        int numLoop = Integer.parseInt (textInp.readLine());
        Set <String> mySet = new HashSet <String> ();
        
        for (int i = 0; i < numloop; i++) {
                    Set <String> mySet = new HashSet <String> ();

            String str = textInp.readLine();
            for (int i = 0; i < str.readLine(); i++) {
                for (int j = 0; j < str.readLine(); j++) {
                    mySet.add(str.substring (i, j));
                }  
            }
                    System.out.println(mySet);
            
        }
        
    }
}