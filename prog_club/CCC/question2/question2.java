import java.util.*;
//this is absolute garbage, sorry
public class question2 {

    public static void main(String args[]) {
        Scanner whatIsN = new Scanner(System.in);
        int n = whatIsN.nextInt();
        String ns = "";
        while (ns.isEmpty() || ns.equals(" "))
            ns = whatIsN.nextLine();
        whatIsN.close();
        int t;
        if (n%2 == 0)
            t = n/2;
        else
            t = (n/2)+1;

        int[] nums = new int[n];
        int[] lowT = new int[n];
        int[] highT = new int[n];

        for (int j = 0; j < n; j++) {
            try {
                ns = ns.trim();
                nums[j] = Integer.parseInt(ns);
            } catch (Exception e) {
            }
            for (int i = 0; i < ns.length(); i++) {
                ns = ns.trim();
                if (ns.charAt(i) == ' ') {
                    nums[j] = Integer.parseInt(ns.substring(0, i));
                    ns = ns.substring(i, ns.length());
                    break;
                }
            }
        }
        Arrays.sort(nums);
        /*above here works*/

        for (int i = 0; i < t; i++) {
            lowT[i] = nums[i];
        }
        for (int i = t; i < n; i++) {
            highT[i] = nums[i];
        }
        Arrays.sort(highT);
        Arrays.sort(lowT);
        int lLastS = 0;
        int hLastS = 0;

        String out = "";

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 || i ==0) {
                out += lowT[n-lLastS-1] + " ";
                lLastS++;
            }
            else {
                out += highT[t+hLastS] + " ";
                hLastS++;
            }
        }
        System.out.println(out);
    }
}