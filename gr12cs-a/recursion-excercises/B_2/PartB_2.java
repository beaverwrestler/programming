//by Artin S.
import java.util.*;
public class PartB_2 {
    public static void main (String Args[]) {        
        Scanner scan = new Scanner (System.in);
        System.out.println("first number: ");
        int first = scan.nextInt();
        System.out.println("second number: ");
        int second = scan.nextInt();
        scan.close();
        System.out.println(multiplyThese(first, second));        
    }
    
    private static int multiplyThese (int first, int second) { 
        if ((first<0) && (second<0)) 
            return second + multiplyThese(Math.abs(first-1), Math.abs(second));
        if (first<0 || second > 0)
            return first + multiplyThese(first, second-1);
        if (first>0)
            return second + multiplyThese((first-1), second);
        return 0;
    }
}