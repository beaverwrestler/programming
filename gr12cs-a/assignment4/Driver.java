import java.io.*;
import java.util.*;

public class Driver {
    private static ArrayList <CD> cds = new ArrayList();

    public static int displayMenu (int menuNum, BufferedReader stdIn) throws IOException {
    	if (menuNum == 0) {
	        System.out.println ("----------  MAIN MENU  -----------");
	        System.out.println ("1) Accessing your list of CDs");
	        System.out.println ("2) Accessing within a particular CD");
	        System.out.println ("3) Exit");
	        System.out.println ("----------------------------------");
	    }
	    else if (menuNum == 1) {    
	        System.out.println ("\n---------  SUB-MENU #1  ----------");
	        System.out.println ("1) Display all of your CDs"); // Just the CD titles, numbered in order
	        System.out.println ("2) Add a CD"); // Adds a CD by reading from input file
    	    System.out.println ("3) Remove a CD");
    	    System.out.println ("4) Copy a CD");
    	    System.out.println ("5) Create a sub-CD");
    	    System.out.println ("6) List songs in common between two CDs");
    	    System.out.println ("7) Return back to main menu.");
    	    System.out.println ("----------------------------------");
    	}
    	else {
    	    System.out.println ("\n---------  SUB-MENU #2  ----------");
    	    System.out.println ("1) Display all songs (in the last sorted order) ");
    	    System.out.println ("2) Display info on a particular song ");
    	    System.out.println ("3) Add song");
    	    System.out.println ("4) Remove Song (4 options)");
    	    System.out.println ("5) Sort songs (3 options)");
    	    System.out.println ("6) Return back to main menu");
    	    System.out.println ("----------------------------------");
    	}
	    System.out.print ("\n\nPlease enter your choice:  ");
	    try {
            int choice = Integer.parseInt (stdIn.readLine ());
            return choice;

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Input, try again...");
        }
        return -1;
    }

    public static void main (String[] args) throws IOException {
    	BufferedReader stdIn = new BufferedReader (new InputStreamReader (System.in));
    	int mainMenuChoice, subMenuChoice;
    	mainMenuChoice = displayMenu (0, stdIn);

    	if (mainMenuChoice == 1)
    	    subMenuChoice = displayMenu (1, stdIn);
    	else if (mainMenuChoice == 2)
    	    subMenuChoice = displayMenu (2, stdIn);
    }
}