import java.util.Scanner;

public class problem_1 {

	  public static void main (String[] args)
	    {
	    
	    Scanner input = new Scanner(System.in); 
	    System.out.println("Enter N Value: ");
	    int thenthing = input.nextInt();
	    int runningTot = 0;
	    
	    for (int i = 0; i < thenthing; i++)
	    {
	        Scanner input1 = new Scanner(System.in); 
	        String thenthingStr = input1.nextLine();
	        
	        String action = thenthingStr.substring(0, thenthingStr.indexOf(' '));
	        String temp = thenthingStr.substring(thenthingStr.indexOf(' ')+1, thenthingStr.length());
	        int quantity = Integer.parseInt(temp);

	        if (action.compareToIgnoreCase("borrow") == 0)
	        	runningTot += quantity;
	        else if (action.compareToIgnoreCase("return") == 0)
	        	runningTot -= quantity;
	        else
	        	System.out.println("this didn't work");
	    }
	    
	    System.out.println(runningTot);
	       
	}	
}