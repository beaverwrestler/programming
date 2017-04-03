//Name: Artin Sarkezians
//Question C
//Due Date: Thursday, Feb 23, 2017
//Bonus: can search up to 100k chars in under 35ms (on my computer

import java.io.*;

public class questionC_rev1 {

    //Main method
    //no parameters or return
    public static void main(String args[]) {
        //declaring vars
        System.out.println("Finding the largest palindrome");
        int longest = Integer.MIN_VALUE;
        int startPos = 1;
        String longName = "";
        String chars = "";
        //input file, make sure it works 
        try {
            BufferedReader charInput = new BufferedReader(new FileReader("chars.txt"));         //change file name here if necessary
            chars = charInput.readLine();
            charInput.close();
        } catch (FileNotFoundException e) {     //catching any possible errors
            System.out.println("Please play again (file not found)");
        } catch (IOException e) {
            System.out.println("Please play again (ioe)");
        }

        //the looping mechanism
        for (int i = 1; i < chars.length() - 1; i++) {
            //checks for even palindromes
            if (chars.charAt((i - 1)) == chars.charAt((i + 1))) {
                for (int j = 1; j <= i && j <= (chars.length() - i); j++) {
                    if (chars.charAt(i - j) != chars.charAt(i + j)) {
                        if (((j-1) * 2)+1 > longest) {
                            startPos = i-j+2;
                            longest = chars.substring((i-j+1), (i+j)).length();
                            longName = chars.substring((i-j+1), (i+j));
                        }
                        break;
                    }
                }
            }

            //checks for even palindromes
            if (chars.charAt(i - 1) == chars.charAt(i)) {
                for (int j = 1; j <= i && j <= (chars.length() - i); j++) {
                    if (chars.charAt(i - j-1) != chars.charAt(i + j)) {
                        if (((j-1) * 2) > longest) {        //checks to see if newly found pali is the longest
                            startPos = i - j+1;
                            longest = chars.substring((i - j), (i + j)).length();
                            longName = chars.substring((i - j), (i + j));
                        }
                        break;
                    }
                }
            }
        }

        //the printing mechanism
        System.out.println("Largest Palindrome: ");
        System.out.println(longName);
        System.out.println("Starting Position: " + startPos);
        System.out.println("Length: " + (longName.length()));
        System.out.println("Program is complete");
    }
}