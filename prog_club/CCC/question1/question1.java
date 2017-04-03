import java.util.Arrays;
import java.util.Scanner;

public class question1 {

    public static void main(String args[]) {
        Scanner whatIsN = new Scanner(System.in);
        int n = whatIsN.nextInt();
        String ns = "";
        String ns2 = "";
        while (ns.isEmpty() || ns.equals(" "))
            ns = whatIsN.nextLine();
        while (ns2.isEmpty() || ns.equals(" "))
            ns2 = whatIsN.nextLine();
        whatIsN.close();
        int t;

        int[] nums = new int[n];
        int[] nums2 = new int[n];
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

        for (int j = 0; j < n; j++) {
            try {
                ns2 = ns2.trim();
                nums2[j] = Integer.parseInt(ns);
            } catch (Exception e) {
            }
            for (int i = 0; i < ns2.length(); i++) {
                ns2 = ns2.trim();
                if (ns2.charAt(i) == ' ') {
                    nums2[j] = Integer.parseInt(ns2.substring(0, i));
                    ns2 = ns2.substring(i, ns2.length());
                    break;
                }
            }
        }
        Arrays.sort(nums2);
        /*above here works*/

        for (int i = n-1; i > -1; i--) {
            int sw = 0;
            int sp = 0;

            for (int j = i; j > -1; j--) {
                sw += nums[j];
                sp+= nums[j];

                if (sp == sw) {
                    System.out.println(i+1);
                    break;
                }
            }

        }
    }
}
