/*
Name: Artin S.
Submission: Assignment #3 (April 7-ish, 2017)
Description: Takes input of movies and their rankings from a text file and organises them into a database, creates interface for user to search and observe data
Bonus: Not much, it gets rid of additional spaces between the titles if that counts
 */

import java.io.*;
import java.util.*;

public class Assignment_3_rev0 {
    private static ArrayList <Movie> film_alpha = new ArrayList();      //all the ArrayLists that are needed to sort/organise the data
    private static ArrayList <Movie> film_rank = new ArrayList();
    private static ArrayList <Movie> film_genre = new ArrayList();
    private static ArrayList <Movie> temp = new ArrayList();
    private static ArrayList <Movie> toSort = new ArrayList();

    //Parameters: String (movie name to look for)
    //Return: void
    //Function: searches arrayLists, calls printMe, prints "not found" if the movie does not exist
    public static void main (String [] args) throws IOException {
        System.out.println("Processing...");
        System.out.println(makeObjects() + " line(s) had errors and was/were skipped...");  //calls to make objects
        System.out.println ("Generating sorted lists...\n");

        film_rank = new ArrayList<>(film_alpha);        //copies data for additional ArrayLists
        film_genre = new ArrayList<>(film_alpha);

        //sorting in terms of rating, name, and genre for later use
        Collections.sort(film_alpha, new Compareor());
        Collections.sort(film_genre, new Gcomp());
        Collections.sort(film_rank, new Rcomp());

        //initialise user input reader
        BufferedReader readInp = new BufferedReader (new InputStreamReader (System.in));
        String usrInp = "", firstDecision = "";

        while (!usrInp.equals("EXIT") && !firstDecision.equals("TITLE") && !firstDecision.equals("GENRE")) {    //loop
            System.out.println("Title, Genre, or Exit?: ");     //asks user what to search by
            firstDecision = readInp.readLine().toUpperCase();

            if (firstDecision.equals("TITLE")) {            //If the user wants to sort by movie title
                System.out.println("Enter the movie you'd like to search for: ");
                usrInp = readInp.readLine().toUpperCase();
                optionA(usrInp);
            }
            else if (firstDecision.equals("GENRE"))  {   //If the user wants to sort by genre
                System.out.println("Enter the genre you'd like to search for: ");
                usrInp = readInp.readLine().toUpperCase();
                optionB(usrInp);
            }
            else if (firstDecision.equals("EXIT"))  //exit breaks out and ends the program
                break;
            else
                System.out.println("Command did not match any of those provided, perhaps there was a typo?");       //advanced AI

            String  cont = "";

            if (usrInp.equals("EXIT") || firstDecision.equals("TITLE") ||firstDecision.equals("GENRE"))     //would you like to continue?
                while (!cont.equals("Y") && !cont.equals("YES")&& !cont.equals("N")&&!cont.equals("NO")) {
                    System.out.println("Would you like to continue searching? (y/n): ");
                    cont = readInp.readLine().toUpperCase();
                    if (cont.equals("Y") || cont.equals("YES")) {
                        usrInp = "";        //resets user input so it doesn't mess with the while loop
                        firstDecision = "";
                    }
                }
        }
        System.out.println ("That's all folks!");   //closing regards
        readInp.close();    //close to prevent memory leaks
    }

    //Parameters: String (movie name to look for)
    //Return: void
    //Function: searches arrayLists, calls printMe, prints "not found" if the movie does not exist
    private static void optionA (String usrInp) {
        int index;
        int found = Collections.binarySearch(film_alpha, new Movie(usrInp, "", 0), new Compareor());
        if (found<0)
            System.out.println("No movie found with the name " + usrInp);
        else {
            index = film_rank.indexOf(film_alpha.get(found));
            printMe(found, index, false);
        }
    }

    //Parameters: String (movie name to look for)
    //Return: void
    //Function: searches arrayLists, calls printMe, prints movies in genre
    private static void optionB (String usrInp) {
        temp = new ArrayList<>(film_genre);     //creates a copy of the sorted list of movies so it can be edited without data loss
        int index;

        while (true){
            index = Collections.binarySearch(temp, new Movie("", usrInp, 0), new Gcomp());    //looks for genre
            if (index<0){
                break;   
            }
            toSort.add(temp.get(index));        //adds it to ArrayList for sorting
            temp.remove(index);        //removes it from the temp as to not duplicate movies
        }

        Collections.sort(toSort, new Compareor());
        for (int i = 0; i < toSort.size(); i ++) {    //gets ranking
            int temp1 = film_rank.indexOf(toSort.get(i));
            printMe(i, temp1, true);
        }
        temp.clear();       //clears temp ArrayLists so genres don't overlap
        toSort.clear();
    }

    //Parameters: bool (says which list to get data from), 2 ints (one is index of data and other is index for sorting/ranking)
    //Return: void
    //Function: prints info in relation to ranking
    private static void printMe (int i, int j, boolean check) {
        if (!check) {       //option A
            System.out.println(film_alpha.get(i));
            System.out.println("Ranking: " + (j+1) + " out of " + film_alpha.size() + "\n");
        }
        else {      //Option B
            System.out.println(toSort.get(i));
            System.out.println("Ranking: " + (j+1) + " out of " + film_alpha.size() + "\n");
        }
    }

    //Parameters: none
    //Return: int, the number of lines skipped, to show to the user
    //Function: imports data from text file and creates ArrayList of Movie objects
    private static int makeObjects () throws FileNotFoundException {
        Scanner fileInput = new Scanner (new File ("input.txt"));       //CHANGE FILE NAME HERE

        int irrelevantInt = 0;      //Data variables
        double rating = 0;
        String categ = "";
        String name = "";
        
        while (fileInput.hasNextLine()) {       //reads all lines of file
            String currLine = fileInput.nextLine();
            StringTokenizer counter = new StringTokenizer (currLine, "% ");     //Tokenize's the raw input string
            if (counter.countTokens()<3)    //skips line if less than 3 tokens
                irrelevantInt++;
            else {
                try {
                    rating = Double.parseDouble (counter.nextToken());      //input and round percentage
                    rating*=10; //I tried doing this all on one line but it wouldn't work for some reason, it just gave me 0.0 or 95.0, never the actual decimal, funny...
                    rating = Math.round(rating);
                    rating/=10;
                }
                catch (NumberFormatException e) {   //skips line if percentage does not parse properly
                    irrelevantInt++;
                }
            
                while (counter.hasMoreTokens()) {       //takes name of movie
                    String test = counter.nextToken();
                    if (!counter.hasMoreTokens())   //the last token is the genre
                        categ = test;
                    else 
                        name += test + " "; 
                }
                
                name = name.trim();     //trims and creates objects
                categ = categ.trim();
                film_alpha.add(new Movie (name, categ, rating));
                
                name = "";      //resets vars
                categ = "";
                rating = 0.0;
            }
        }    
        fileInput.close();      //close file to prevent memory leaks
        return irrelevantInt;   //return the number of lines skipped, for housekeeping
    }
}