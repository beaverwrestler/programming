import java.util.Scanner;

public class fibonacci {

	  public int main (int n) {
          
        if (n  == 0)
            return n;
        if (n  == 1)
            return 1;

        return main  (n-1) + main  (n-2);
  
        }
}
