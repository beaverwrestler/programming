import java.util.*;

public class questionB {
    private static int n;
    private static String works = "These work: ";

    public static void main(String args[]) {
        Scanner whatIsN = new Scanner(System.in);
        System.out.print("What would you like the value of n to be? (1<=n<=7): ");
        n = whatIsN.nextInt();
        long start = System.currentTimeMillis();

        for (int i = (int) (Math.pow(10, n) - 1) / 3; i >= (Math.pow(10, (n - 1))); i--)
            if (checkBack(i))
                works = works + i + ", ";

        if (works.equals("These work: "))
            System.out.println("None exist");
        else
            System.out.println(works.substring(0, works.length() - 2));

        System.out.println(System.currentTimeMillis() - start);
    }

    private static boolean checkBack(int checky) {
        int[] threeArr = new int[n];
        int[] orgArr = new int[n];
        int threeArrNum = checky * 3;

        for (int i = 0; i < n; i++) {
            threeArr[i] = threeArrNum % 10;
            threeArrNum /= 10;
        }
        Arrays.sort(threeArr);

        for (int i = 0; i < n; i++) {
            orgArr[i] = checky % 10;
            checky /= 10;
        }
        Arrays.sort(orgArr);

        for (int i = 0; i < n; i++)
            if (orgArr[i] != threeArr[i])
                return false;
        return true;
    }
}