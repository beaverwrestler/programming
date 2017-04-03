//Assignment #2 aka "late as all hell"
//Name: Artin Sarkezians
//This programs takes in an input file with one or more grids and finds the cheapest route from the start to end coordinates, where each 
//position has a "cost"

import java.io.*;
import java.util.*;

public class CheapestRoute_rev3 {
    
    private static int numGrid = 0;
    private static int numRow = 0;        //these are based off index, not conventional java length
    private static int numCol = 0;
    private static int [] pathNums;    //arrays for calulating path mid-search
    private static String [] pathDir;
    private static int lessMonies = Integer.MAX_VALUE;        
    private static int pathIndex = 0;
    private static int [] cheapNums;    //final position arrays
    private static String [] cheapDir;
    private static String dirF;    //directions to navigate

    //Main Method, no parameters, no return
    //Responsible for inputing data from the text file, starting the recursion search process, as well as output at the end of each grid
    public static void main (String args[]) throws FileNotFoundException, NumberFormatException, IOException {
        System.out.println("Finding the Cheapest Routes:");
        BufferedReader file = new BufferedReader(new FileReader ("input.txt"));
        numGrid = Integer.parseInt(file.readLine());     
        
        //loops for each of the grids
        for (int k = 0; k< numGrid; k++) {
            numRow = Integer.parseInt(file.readLine())-1;  
            numCol = Integer.parseInt(file.readLine())-1;
            int [] [] maze = new int [(numRow+1)] [(numCol+1)];

            //theorietical longest possible path length (and then some)
            pathNums = new int [(numRow+1)*(numCol+1)];        
            pathDir = new String [(numRow+1)*(numCol+1)];
            cheapNums = new int [(numRow+1)*(numCol+1)];
            cheapDir = new String [(numRow+1)*(numCol+1)];

            System.out.println("Grid #" + (k+1));
            //inputs the grid from the file, prints it in the console, and saves it to an array
            for (int i = 0; i <= numRow; i++) {        
                StringTokenizer inp = new StringTokenizer (file.readLine());
                for (int j = 0; inp.hasMoreTokens(); j++) {
                    maze [i] [j] = Integer.parseInt(inp.nextToken());   
                    System.out.print(maze [i] [j] + " ");
                }
                System.out.println();
            }
            
            //if the grid is 1 x 1, considered a special case, prints special stuff, otherwise starts standard process
            if (numRow ==0 && numCol==0) {
                System.out.println("Cheapest Route: " + maze [0] [0]);
                System.out.println("Direction: n/a");
                System.out.println("Cheapest Cost: $" +  maze [0] [0]);
            }
            else {
                findRoute(numRow, 0, maze, 0, 0, "", "");        //call the recursive method
            
                System.out.print("Cheapest Route: ");        //various output after calculation is finished
                for (int i = 0; i< cheapNums.length; i++) {
                    if (cheapNums[i] !=0)
                        System.out.print(cheapNums[i] + " ");
                }
                System.out.println();
                System.out.println("Direction: " + dirF);
                System.out.println("Cheapest Cost: $" + lessMonies );
            }
        } 
    }
    
    //Recursive Method, find the cheapest route
    //Parameters: x & y cooridnates, maze/grid array, cost counter, index of the position in the path, sting of directions and the specific direction to add
    //Return: void, uses a combination of passed and global variables
    private static void findRoute (int y, int x, int [] [] maze, int pesos, int pathIndex, String dir, String specDir) { 
        if ((y) <0 || (x)>numCol)        //checks to see if the new position is out of bounds
            return;
        if (x == numCol && y == 0) {        //is ending position?
            pathNums [pathIndex] = maze [y] [x];        //assign necessary values
            pesos += maze [y] [x];
            dir += specDir;
            if (pesos < lessMonies) {        //compare to find which is less big, move data around as necessary
                lessMonies = pesos;
                dirF = dir;
                for (int i =0; i < cheapDir.length; i++) {
                    cheapDir[i] = pathDir [i];
                    cheapNums [i] = pathNums [i];
                }
            }
            return;
        }

        //increments vars and assigns values to arrays
        pesos += maze [y] [x];
        dir += specDir;
        pathNums [pathIndex] = maze [y] [x];
        pathIndex++;

        findRoute((y-1), x, maze, pesos, pathIndex, dir, "NORTH ");    //next position is up (north)
        findRoute(y, (x+1), maze, pesos, pathIndex, dir, "EAST ");    //next position is right (east)
     }
}