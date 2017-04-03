import java.io.*;
import java.util.*;

public class CheapestRoute_rev0 {
    
    private static int numGrid = 0;
    private static int numRow = 0;        //these are based off index, not length
    private static int numCol = 0;
    private static int [] pathNums;
    private static String [] pathDir;
    private static int [] [] gps;
    private static int monies = 0;            //on going cost var
    private static int lessMonies = Integer.MAX_VALUE;    // least cost var
    private static int lastY = 0;
    private static int pointOfDev = 0;
    
    //numCol = x
    //numRow = y
    
    public static void main (String args[]) throws FileNotFoundException, NumberFormatException, IOException {
        System.out.println("Finding the Cheapest Routes:");
        BufferedReader file = new BufferedReader(new FileReader ("input.txt"));
        numGrid = Integer.parseInt(file.readLine());     
        
        for (int k = 0; k< numGrid; k++) {
            monies = 0;
            numRow = Integer.parseInt(file.readLine())-1;  
            numCol = Integer.parseInt(file.readLine())-1;
            int [] [] maze = new int [(numRow+1)] [(numCol+1)];
            gps = new int [(numRow+1)] [(numCol+1)];
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
    
    private static void findRoute (int y, int x, int [] [] maze) {  
        if ((y-1)>=0) {
            monies += maze [y] [x];
            pathNums [lastY] = maze [y] [x];
            pathDir [lastY] = "North";
            System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
            lastY++;
            findRoute((y-1), x, maze);
        } 
        System.out.println( " ly " + lastY);
                        pointOfDev = lastY;

        
        if ((x+1)<=numCol) { 
            monies += maze [y] [x];
            pathNums [lastY] = maze [y] [x];
            pathDir [lastY] = "East";
            System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
            lastY++;
            findRoute(y, (x+1), maze);
        } 
        
        if (x == numCol && y == 0) {
            monies += maze [y] [x];
            pathDir [lastY] = null;
            System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
            pathNums [lastY] = maze [y] [x];
            for (int i = lastY+1; i< pathNums.length; i++)
                pathNums [i] = 0;
            if (monies< lessMonies){
               lessMonies = monies;    
            } 
            System.out.println("i've hit the end " + monies);
            
            for (int i = 0; i < pathNums.length; i ++)
            {
                System.out.print (pathNums [i] + " ");
            }
            for (int i =lastY; i > (lastY-pointOfDev); i--) {
                monies -= pathNums [i];
            }
            //lastY = 0;
        }
    }
    
//     private static int findRoute (int y, int x, int [] [] maze) { 
        
//         if ((y-1)>=0) {
//             //monies += maze [y] [x];
//             pathNums [lastY] = maze [y] [x];
//             pathDir [lastY] = "North";
//             System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
//             lastY++;
//             monies = maze [y] [x] + findRoute((y-1), x, maze);
//         } 
        
//         if ((x+1)<=numCol) { 
//             //monies += maze [y] [x];
//             pathNums [lastY] = maze [y] [x];
//             pathDir [lastY] = "East";
//             System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
//             lastY++;
//             monies =  maze [y] [x] + findRoute(y, (x+1), maze);
//         } 

//         if (x == numCol && y == 0) {
//             //monies += maze [y] [x];
//             pathDir [lastY] = null;
//             System.out.println(y + " " + x+ " " + pathDir [lastY] + " " + maze [y] [x]);
//             pathNums [lastY] = maze [y] [x];
//             for (int i = lastY+1; i< pathNums.length; i++)
//                 pathNums [i] = 0;
//             if (monies< lessMonies){
//                lessMonies = monies;    
//             } 
//             System.out.println("i've hit the end " + monies);
//             monies = 0;
//             lastY = 0;
//             return maze [y] [x];
//         }
//         return 0;
//     }
}