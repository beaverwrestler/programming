/* Name: Artin S.
 * Due Date: Friday, June 16, 2017
 * Description: This is the class that is responsible for preventing any accidental data loss by
 *              saving a copy of the citations arraylist into a temp file every 10 seconds
 */

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Saver extends Thread{
    private boolean safety = true;      //variable to declare whether or not a file was safely deleted
    public void run () {
        int counter = 1;    //var to create unique file names
        while (true) {
            //an copy of the citations arraylist is made as to not interfere with the other class's data
            ArrayList <Citation> citations = new ArrayList <> (Final_Assignment_AS_rev1.getCitations());

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(counter +".tmp", false));
                if (Final_Assignment_AS_rev1.getType() == 1)    //declares the type of citation in the file
                    bw.write("CIT_TYPE=MLA\n");
                else if (Final_Assignment_AS_rev1.getType() == 2)
                    bw.write("CIT_TYPE=APA\n");

                for (int i = 0; i < citations.size(); i ++) {   //writes citation information into file
                    bw.write(citations.get(i).getAuthor() + "\n");
                    bw.write(citations.get(i).getTitle() + "\n");
                    bw.write(citations.get(i).getWebTitle() + "\n");
                    bw.write(citations.get(i).getPublisher() + "\n");
                    bw.write(citations.get(i).getUrl() + "\n");
                    bw.write(citations.get(i).getDatePosted() + "\n");
                    bw.write(citations.get(i).getDateAcc() + "\n");
                }

                bw.close();
                if (counter > 1)    //deletes the previous temp file to save disk space
                    safety = new File ((counter-1) + ".tmp").delete();

                if (!safety) {  //if the file could not be deleted, an error is shown and the user is told to save and restart
                    JOptionPane.showMessageDialog(Final_Assignment_AS_rev1.getFrame(), new JLabel("<html><font color='white'>Fatal Error, save file and restart program</font></html>"));
                    break;
                }
            }
            catch (IOException e) {}

            letsTakeFive();
            counter++;
        }
    }

    //no parameters, no return
    //simply stops the thread for ten seconds until the next saving period
    private void letsTakeFive () {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {}
    }
}
