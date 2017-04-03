//by Artin Sarkezians
import java.util.Scanner;
public class PartB_6 {
    public static void main (String args[]) {
        System.out.println("what's the value of n: ");
        Scanner ny = new Scanner(System.in);
        int nVal = ny.nextInt();
        ny.close();
        System.out.println("there are " + nCalc(nVal) + " combinations");  
    }
    
    private static int nCalc (int nVal) {
        if (nVal ==1)
            return 1;
        if (nVal == 2)
            return 2;
        if (nVal<=0)
            return -1;
        return  nCalc(nVal-2) + nCalc(nVal-1);
    }
}