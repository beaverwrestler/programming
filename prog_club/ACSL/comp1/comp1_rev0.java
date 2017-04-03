//I apologize in advance for the tears that this barely functioning, duct-tape enabled code will bring to your eyes -Artin

import java.util.Scanner;

public class comp1_rev0 {

    public static void main (String[] args)
    {
        for (int i = 0; i <5; i++)      //will loop five times for each game
        {
            String[] card = what_are_the_cards();

            String[] nums = new String [] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
            String[] suits =  new String[] {"C", "D", "H", "S"};

            int numToBeat = 0;
            int indexOfCardPlay = 20;
            int suitToBeat = 0;

            for (int l = 0; l<13; l++)       //Index of card to play
                if (card[0].substring(0,1).equals(nums[l]))
                    numToBeat = l;

            for (int l = 0; l<4; l++)       //Index of suit to play
                if (card[0].substring(1).equals(suits[l]))
                    suitToBeat = l;

            int temp = 40;
            int tNumToBeat = 0;
            int oldTemp = 40;
            int otnum = 40;

            for (int j = 1; j <6; j++)
            {

                for (int l = 0; l<4; l++)       //Index of suit to match
                    if (card[j].substring(1).equals(suits[l]))
                        temp = l;

                for (int l = 0; l<13; l++)       //Index of current card
                    if (card[j].substring(0,1).equals(nums[l]))
                        tNumToBeat = l;

                if (temp == suitToBeat)
                {
                    if (numToBeat < tNumToBeat && tNumToBeat < indexOfCardPlay)       //slightly above
                    {
                        indexOfCardPlay = j;
                    }
                    else if(numToBeat > tNumToBeat)     //lower in suit
                    {
                        indexOfCardPlay = j;
                    }
                }

                else if (tNumToBeat!= numToBeat)    //lowest card
                {
                    if (tNumToBeat < otnum && temp < oldTemp)
                    {
                        indexOfCardPlay = j;
                        otnum = tNumToBeat;
                        oldTemp = temp;
                    }
                }

                else if (tNumToBeat == numToBeat)       //order in suit
                {
                   if (temp < oldTemp)
                   {
                       indexOfCardPlay = j;
                       oldTemp = temp;
                   }
                }
            }

            System.out.println(card[indexOfCardPlay].substring(0,1) + " of " + card[indexOfCardPlay].substring(1));
        }
    }

    public static String [] what_are_the_cards()
    {
        String [] card = new String [6];
        Scanner cardz = new Scanner(System.in);
        System.out.println("Plz enter yo cardz: ");
        String hand = cardz.nextLine();

        for (int i = 0; i<6; i++)
            card[i]= hand.substring((i*4), ((i*4)+2));

        return card;
    }
}
