//Assignment #2
//Name: Artin Sarkezians
//This programs takes in an input file with one or more grids and finds the cheapest route from the start to end coordinates, where each 
//position has a "cost"

import java.io.*;
import java.util.*;

public class CheapestRoute_rev2 {
    
    private static int numGrid = 0;
    private static int numRow = 0;        //these are based off index, not length
    private static int numCol = 0;
    private static int [] pathNums;
    private static String [] pathDir;
    private static int [] gpsX;
        private static int [] gpsY;

    private static int monies = 0;        //ongoing cost
    private static int lessMonies = Integer.MAX_VALUE;        
    private static int pathIndex = 0;
    private static int cost = 0;
    //private static int prevPathIndex = 0;

    
    //Main Method, no parameters, no return
    //Responsible for inputing data from the text file, starting the recursion search process, as well as output at the end of each grid
    public static void main (String args[]) throws FileNotFoundException, NumberFormatException, IOException {
        System.out.println("Finding the Cheapest Routes:");
        BufferedReader file = new BufferedReader(new FileReader ("input.txt"));
        numGrid = Integer.parseInt(file.readLine());     
        
        for (int k = 0; k< numGrid; k++) {
            monies = 0;
            numRow = Integer.parseInt(file.readLine())-1;  
            numCol = Integer.parseInt(file.readLine())-1;
            int [] [] maze = new int [(numRow+1)] [(numCol+1)];
            gpsY = new int [numRow+1*numCol+1];
                        gpsX = new int [numRow+1*numCol+1];

            pathNums = new int [numRow+1*numCol+1];
            pathDir = new String [numRow+1*numCol+1];

            System.out.println("Grid #" + (k+1));
            for (int i = 0; i <= numRow; i++) {
                StringTokenizer inp = new StringTokenizer (file.readLine());
                for (int j = 0; inp.hasMoreTokens(); j++) {
                    maze [i] [j] = Integer.parseInt(inp.nextToken());   
                    System.out.print(maze [i] [j] + " ");
                }
                System.out.println();
            }
            
            findRoute(numRow, 0, maze);
            
            System.out.print("Cheapest Route: ");
            for (int i = 0; i< pathNums.length; i++) {
                if (pathNums[i] !=0)
                    System.out.print(pathNums[i] + " ");
            }
            System.out.println();
            System.out.print("Direction: ");
            for (int i = 0; i< pathDir.length; i++) {
                if (pathDir[i] != null)
                    System.out.print(pathDir[i] + " ");
            }
            System.out.println();
            System.out.println("Cheapest Cost: $" + lessMonies );
        } 
    }
    
    //Recursive method
    //return is void; paramteters are the start x and y coordinates, as well as the grid itself
    //operates recurively to determine the cheapest route through the maze
    private static void findRoute (int y, int x, int [] [] maze) { 
        if ((y-1)>=0) {
            monies += maze [y] [x];
            pathNums [pathIndex] = maze [y] [x];
            gpsX[pathIndex] = x;
            gpsY[pathIndex] = y;
            pathDir [pathIndex] = "North";
            System.out.println(y + " " + x+ " pi: " + pathIndex + " "+ pathDir [pathIndex] + " " + maze [y] [x]);
            pathIndex++;
            findRoute((y-1), x, maze);
        } 
        
        if ((x+1)<=numCol) { 
            monies += maze [y] [x];
            pathNums [pathIndex] = maze [y] [x];
            gpsX[pathIndex] = x;
            gpsY[pathIndex] = y;
            pathDir [pathIndex] = "East";
            System.out.println(y + " " + x + " pi: " + pathIndex + " " + pathDir [pathIndex] + " " + maze [y] [x]);
            pathIndex++;
            findRoute(y, (x+1), maze);
        } 

        if (x == numCol && y == 0) {
            monies += maze [y] [x];
            pathDir [pathIndex] = null;
            System.out.println(y + " " + x + " pi: "  +pathIndex + " " + pathDir [pathIndex] + " " + maze [y] [x]);
            pathNums [pathIndex++] = maze [y] [x];
            for (int i = 0; i < pathIndex; i++) {        //this will find the cost after the path has been calculated
                cost += pathNums [i];
            }
            for (int i = pathIndex+1; i< pathNums.length; i++)
                pathNums [i] = 0;
            if (cost< lessMonies){        //compare to find which is smaller
               lessMonies = cost;    
            } 
            
            //while ()
            System.out.println("i've hit the end " + cost);
            pathIndex = 0;
            monies = 0;
            cost = 0;
        }
    }
}