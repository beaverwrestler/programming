import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JFrame_rev0 extends JFrame implements ActionListener {
    private JMenuItem openBib, saveBib, exportBib, about, editCit, delCit, copyCit, osCit, MLA, APA;
    private JMenu newBib;
    private JPanel panelMain, panelSpec, master, cit;
    private JFrame frame;
    private JScrollPane citationPane;

    private final int leftMargin = 10;

    private ArrayList <Citation> citations;

    public static void main(String[] args) {
        new JFrame_rev0();
    }

    private JFrame_rev0 () {
        //start temp

        citations = new ArrayList<>();

        Citation citationOne = new Citation("February 21, 1995", "Bob Joe", "www.donaldtrump.com",
                "Donald trump is bestest", "Donald Trump is a jerk", "Donald Duck");
        Citation citationTwo = new Citation("January 12, 2012", "Steve Mobs", "www.theinternet.com",
                "Pear Company", "The internet is awesome", "Wikipedia");

        citations.add(citationOne);
        citations.add(citationTwo);

        //end temp

        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.DARK_THEME);
        frame = new JFrame();

        //make sure the program exits when the frame closes & other frame stuff
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Reference Maker 3000");
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //init stuff
        JMenuBar menuMain = new JMenuBar();
        JMenuBar menuSpec = new JMenuBar();

        master = new JPanel();
        master.setLayout(null);     //new BoxLayout(master, BoxLayout.Y_AXIS));
        master.setLocation(0,0);
        master.setSize(frame.getSize());

        cit = new JPanel();
        cit.setLayout(new BoxLayout(cit, BoxLayout.Y_AXIS));
        cit.setLocation(0, 60);
        cit.setSize(frame.getWidth(),730);

        citationPane = new JScrollPane(cit);
        citationPane.setLocation(10, 60);
        citationPane.setSize(frame.getWidth()-15,710);
        citationPane.getVerticalScrollBar().setUnitIncrement(14);   //scrolling speed was slow as shit before

        //main menu
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.add(menuMain);
        panelMain.setSize(frame.getWidth(), 60);
        panelMain.setLocation(0, 0);
        menuMain.setSize(350, 40);  //original: 250, 40
        menuMain.setLocation(leftMargin, 10);

        newBib = new JMenu("New");  //3 spaces after
        MLA = new JMenuItem("MLA");
        MLA.addActionListener(this);
        APA = new JMenuItem("APA");
        APA.addActionListener(this);
        openBib = new JMenuItem("Open");
        openBib.addActionListener(this);
        saveBib = new JMenuItem("Save");
        saveBib.addActionListener(this);
        exportBib = new JMenuItem("Export");
        exportBib.addActionListener(this);
        about = new JMenuItem("About");
        about.addActionListener(this);

        menuMain.add(newBib);
        newBib.add(MLA);
        newBib.add(APA);
        menuMain.add(openBib);
        menuMain.add(saveBib);
        menuMain.add(exportBib);
        menuMain.add(about);

        //specific menu
        panelSpec = new JPanel();
        panelSpec.setLayout(null);
        panelSpec.add(menuSpec);
        panelSpec.setSize(frame.getWidth(), 60);
        panelSpec.setLocation(0,0);
        menuSpec.setSize(350, 40);      //org: 350, 40
        menuSpec.setLocation(leftMargin, 10);

        editCit = new JMenuItem("Edit");
        editCit.addActionListener(this);
        delCit = new JMenuItem("Delete");
        delCit.addActionListener(this);
        copyCit = new JMenuItem("Copy to Clipboard");
        copyCit.addActionListener(this);
        osCit = new JMenuItem("Open Original Source");
        osCit.addActionListener(this);

        menuSpec.add(editCit);
        menuSpec.add(delCit);
        menuSpec.add(copyCit);
        menuSpec.add(osCit);

        //add buttons for initial
        updateVisibleCitations(citations);

        //frame stuff
        master.add(panelMain);
        master.add(citationPane);
        frame.add(master);
        frame.setVisible(true);
    }

    private void updateVisibleCitations (ArrayList <Citation> cits) {
        if (cits.size() == 0) {
            System.out.println("put nothing here");     //don't do this
        }
        else
            for (int i = 0; i < cits.size(); i ++) {
                JButton cit1 = new JButton((i + 1) + ")    " + cits.get(i).getFullMLA() + "   ");
                cit1.setSize((frame.getWidth()-50), 40);
                cit1.setLocation(leftMargin, (40 * (i)));
                cit1.setHorizontalAlignment(SwingConstants.LEFT);
                cit1.setFocusable(false);
                cit.add(cit1);
            }
    }

    private void saveExport (boolean latter) {
        String fileName = (String)JOptionPane.showInputDialog(frame,
                "<html><font color='white'>Please enter a file name to save</font></html>",
                "Save", JOptionPane.PLAIN_MESSAGE, null, null, "");

        if (!fileName.contains(".txt")) {
            fileName += ".txt";
        }

        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(fileName));

            if (latter) {
                ArrayList <Citation> copyCit = new ArrayList<>(citations);

                //code to sort
            }
            else {
                for (int i = 0; i < citations.size(); i ++) {
                    file.write(citations.get(i).getFullMLA() + "\n");
                }
            }
            System.out.println("asdasD");
            file.close();
        }
        catch (IOException e) {

        }
    }

    public void actionPerformed (ActionEvent e){
        Object event = e.getSource();
        if (event == openBib) { //change this to the actual button for the citation
            master.remove(panelMain);
            master.add(panelSpec);
            master.revalidate();
            master.repaint();
        }
        else if (event == editCit) {
            master.remove(panelSpec);
            master.add(panelMain);
            master.revalidate();
            master.repaint();
        }
        else if (event == about) {
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>© 2017 Bullshit Industries, Reference Maker 3000</font></html>"));
        }
        else if (event == saveBib) {
            //change this, is useless, make the save function save the code so the program can open it again
            saveExport(false);
        }
        else if (event == exportBib) {
            saveExport(true);
        }
    }
}
