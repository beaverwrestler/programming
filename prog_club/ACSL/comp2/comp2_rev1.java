import java.util.Scanner;

public class comp2_rev1 {

    public static void main (String args[]) {
        String raw_inp;
        int highNum = 0;

        for (int j = 0; j <5; j++) {
            Scanner nums = new Scanner(System.in);
            if (j!=0)       //Because spacing must be perfect
                System.out.println();
            System.out.println("Plz enter yo numero's: ");
            raw_inp = nums.nextLine();
            highNum = 0;

            for (int k = 0; k <= raw_inp.length()+1; k++) {
                for (int i = 1; i <= raw_inp.length(); i++)
                    if (Integer.parseInt(raw_inp.substring(0, i)) > highNum) {
                        System.out.print(remLedZ(raw_inp.substring(0, i)) + " ");
                        highNum = Integer.parseInt(raw_inp.substring(0, i));
                        raw_inp = raw_inp.substring(i, raw_inp.length());
                        break;
                    }

                if (raw_inp.length() > 0) {
                    String thing2 = "";
                    for (int i = 0; i < raw_inp.length(); i++)
                        thing2 += raw_inp.substring(raw_inp.length() - i - 1, raw_inp.length() - i);
                    raw_inp = thing2;
                }
           }
        }
    }

    public static String remLedZ (String removey) {
        for (int i = 0; i < removey.length(); i ++)
            if (Integer.parseInt(removey.substring(i, i+1))== 0)
                removey = removey.substring(i+1, removey.length());
        return removey;
    }
}