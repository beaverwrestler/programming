import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Saver extends Thread{
    private boolean safety = true;
    public void run () {
        int counter = 1;
        while (true) {
            ArrayList <Citation> citations = new ArrayList <> (Final_Assignment_AS_rev1.getCitations());

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(counter +".tmp", false));
                if (Final_Assignment_AS_rev1.getType() == 1)
                    bw.write("CIT_TYPE=MLA\n");
                else if (Final_Assignment_AS_rev1.getType() == 2)
                    bw.write("CIT_TYPE=APA\n");

                for (int i = 0; i < citations.size(); i ++) {
                    bw.write(citations.get(i).getAuthor() + "\n");
                    bw.write(citations.get(i).getTitle() + "\n");
                    bw.write(citations.get(i).getWebTitle() + "\n");
                    bw.write(citations.get(i).getPublisher() + "\n");
                    bw.write(citations.get(i).getUrl() + "\n");
                    bw.write(citations.get(i).getDatePosted() + "\n");
                    bw.write(citations.get(i).getDateAcc() + "\n");
                }

                bw.close();
                if (counter > 1)
                    safety = new File ((counter-1) + ".tmp").delete();

                if (!safety) {
                    JOptionPane.showMessageDialog(Final_Assignment_AS_rev1.getFrame(), new JLabel("<html><font color='white'>Fatal Error, save file and restart program</font></html>"));
                    break;
                }
            }
            catch (IOException e) {}

            letsTakeFive();
            counter++;
        }
    }

    private void letsTakeFive () {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {}
    }
}
