//by Artin Sarkezians
import java.util.Scanner;
public class PartB_1 {
    public static void main (String args[]) {
        System.out.println("Which number in the fibonacci sequence would you like to see?: ");
        Scanner fibby = new Scanner(System.in);
        int fibNum = fibby.nextInt();
        System.out.println(fib(fibNum));  
        fibby.close();
    }
    
    private static int fib (int fibNum) {
        if (fibNum ==1 || fibNum == 2)
            return 1;
        if (fibNum<=0)
            return -1;
        return  fib(fibNum-2) + fib(fibNum-1);
    }
}