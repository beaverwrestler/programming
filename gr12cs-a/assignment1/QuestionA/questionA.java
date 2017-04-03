import java.util.*;

public class questionA {

    private static String proName = "";
    private static Scanner qA = new Scanner(System.in);
    private static double totVal = 0.0;
    private static double highVal = 0.0;
    private static String highValStr = "";
    private static int proQuan = -1;
    private static double proPrice = -1;

    public static void main(String args[]){
        String cont;
        System.out.println("Inventory Management Program");
        System.out.println();

        do {
            name();
            price();
            quantity();
            double val = (Math.round((proQuan * proPrice) * 100) / 100);
            totVal += val;
            
            if (val > highVal) {
                highValStr = proName;
                highVal = val;
            }
            
            System.out.println("You have " + proQuan + " " + proName + "(s) @ $" + proPrice + " for a total value of: $" + val);
            System.out.print("Do you have any more products? (y/n): ");       //this doesn't work, fix it
            cont = qA.nextLine();
            cont = cont.trim();
            System.out.println();

            if (cont.equalsIgnoreCase("n")) {
                System.out.println("The total value of all inventory is: $" + totVal);
                System.out.println("The item with the highest value is $" + highVal + " for " + highValStr + ".");
                System.out.println("Thank you for using the Inventory Management Program");
                break;
            }
        } while (!cont.equalsIgnoreCase("y") && !cont.equalsIgnoreCase("n"));

        qA.close();
    }

    private static int quantity() {
        System.out.print("How many " + proName + " do you have on hand?: ");
        while (proQuan < 0) {
            try {
                proQuan = Integer.parseInt(qA.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("How many " + proName + " do you have on hand?: ");
            } catch (Exception f) {
                System.out.print("sorry, i dun goofed, pls try again: ");
            }
        }
        return proQuan;
    }

    //reporter - "sir, sir, what do you think this method does???" me -"no comment" (hehe, lol)
    private static String name() {
        while (proName == null || proName.isEmpty()) {
            System.out.print("Please enter the name of the next product: ");
            proName = qA.nextLine();
        }
        return proName;
    }

    private static double price() {
        while (proPrice < 0) {
            try {
                System.out.print("Please enter the price for " + proName + ": $");
                proPrice = Double.parseDouble(qA.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter the price for " + proName + ": $");
            } catch (Exception f) {
                System.out.print("sorry, i dun goofed, pls try again: ");
            }
        }
        return proPrice;
    }
}