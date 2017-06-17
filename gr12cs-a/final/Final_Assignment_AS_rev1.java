/* Name: Artin S.
 * Due Date: Friday, June 16, 2017
 * Description: (management program) This'll help you make, edit, and use MLA or APA internet references. It allows
 *              you to input data for either format, save the files for future use, and export the files to .txt. There
 *              is a background thread that saves the citations in .tmp format so if the program where to crash, all the
 *              data is maintained. The GUI is changed because Java's is shit. It asks before erasing the citations if
 *              the user wants to save it. It allows the link for a citation to be opened directly and copied to the clipboard.
 *              There are many other features.
 */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//DA CLASS
public class Final_Assignment_AS_rev1 implements ActionListener{
    //JFrame items and variables
    private JMenuItem openBib, saveBib, exportBib, about, editCit, delCit, copyCit, osCit, MLA, APA, back, newCit;
    private JPanel panelMain, panelSpec, master, cit, MLAinfo, APAinfo;
    private static JFrame frame;
    private JTextField author, dateAcc, datePosted, url, publisher, title, webTitle, authorAPA, datePostedAPA, urlAPA, titleAPA;

    //variables, arraylists for stuff
    private static int type = 0;        //mla (1) or apa (2) or no active (0)
    private final int leftMargin = 10;
    private int currentOption = -1;
    private static ArrayList <Citation> citations;  //this is the arraylist for the citations
    private static ArrayList <JButton> buttonCits = new ArrayList<>();

    public static void main (String [] args) {
        new Final_Assignment_AS_rev1();     //create a new instance of this object
    }

    //Constructor, takes care of almost all the JFrame stuff
    private Final_Assignment_AS_rev1 () {
        citations = new ArrayList<>();

        Saver save = new Saver();   //starts the thread that prevents data loss
        save.start();

        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.DARK_THEME); //changes the GUI, because Java
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

        //sets the size and location of the main panel
        master = new JPanel();
        master.setLayout(null);
        master.setLocation(0,0);
        master.setSize(frame.getSize());

        //delcare and init the panel for the citations
        cit = new JPanel();
        cit.setLayout(new BoxLayout(cit, BoxLayout.Y_AXIS));
        cit.setLocation(0, 60);
        cit.setSize(frame.getWidth(),730);

        //delcare and init the scroll pane that contains the citations, if there are too many for the display
        JScrollPane citationPane = new JScrollPane(cit);
        citationPane.setLocation(10, 60);
        citationPane.setSize(frame.getWidth()-15,710);
        citationPane.getVerticalScrollBar().setUnitIncrement(14);   //scrolling speed was slow as shit before

        //main menu setup (the top bar)
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.add(menuMain);
        panelMain.setSize(frame.getWidth(), 60);
        panelMain.setLocation(0, 0);
        menuMain.setSize(500, 40);
        menuMain.setLocation(leftMargin, 10);

        //adding menu items, listeners
        JMenu newBib = new JMenu("New Bib");
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
        newCit = new JMenuItem("New Citation");
        newCit.addActionListener(this);

        menuMain.add(newBib);
        newBib.add(MLA);
        newBib.add(APA);
        menuMain.add(newCit);
        menuMain.add(openBib);
        menuMain.add(saveBib);
        menuMain.add(exportBib);
        menuMain.add(about);

        //specific menu (one you see when you click a citation)
        panelSpec = new JPanel();
        panelSpec.setLayout(null);
        panelSpec.add(menuSpec);
        panelSpec.setSize(frame.getWidth(), 60);
        panelSpec.setLocation(0,0);
        menuSpec.setSize(800, 40);      //org: 350, 40
        menuSpec.setLocation(leftMargin, 10);

        //init and adding listeners
        editCit = new JMenuItem("Edit");
        editCit.addActionListener(this);
        delCit = new JMenuItem("Delete");
        delCit.addActionListener(this);
        copyCit = new JMenuItem("Copy to Clipboard");
        copyCit.addActionListener(this);
        osCit = new JMenuItem("Open Original Source");
        osCit.addActionListener(this);
        back = new JMenuItem("Back");
        back.addActionListener(this);

        //adding to panel
        menuSpec.add(back);
        menuSpec.add(editCit);
        menuSpec.add(delCit);
        menuSpec.add(copyCit);
        menuSpec.add(osCit);

        //add buttons for initial
        updateVisibleCitations();

        //adding sub-frames to larger-frames
        master.add(panelMain);
        master.add(citationPane);
        frame.add(master);
        frame.setVisible(true);

        //text input stuff for citations (MLA)
        MLAinfo = new JPanel();
        MLAinfo.setLayout(new BoxLayout(MLAinfo, BoxLayout.PAGE_AXIS)); //layout
        author = new JTextField(10);
        dateAcc = new JTextField(10);
        datePosted = new JTextField(10);
        url = new JTextField(10);
        title = new JTextField(10);
        webTitle = new JTextField(10);
        publisher = new JTextField(10);

        MLAinfo.add(new JLabel("<html><font color='white'>Author: </font></html>"));
        MLAinfo.add(author);
        MLAinfo.add(new JLabel("<html><font color='white'>Title: </font></html>"));
        MLAinfo.add(title);
        MLAinfo.add(new JLabel("<html><font color='white'>Publisher: </font></html>"));
        MLAinfo.add(publisher);
        MLAinfo.add(new JLabel("<html><font color='white'>Date Posted: </font></html>"));
        MLAinfo.add(datePosted);
        MLAinfo.add(new JLabel("<html><font color='white'>Date Accessed: </font></html>"));
        MLAinfo.add(dateAcc);
        MLAinfo.add(new JLabel("<html><font color='white'>URL: </font></html>"));
        MLAinfo.add(url);

        //text field input for citations (APA)
        APAinfo = new JPanel();
        APAinfo.setLayout(new BoxLayout(APAinfo, BoxLayout.PAGE_AXIS));

        authorAPA = new JTextField(10);
        datePostedAPA = new JTextField(10);
        urlAPA = new JTextField(10);
        titleAPA = new JTextField(10);

        //labels and adding to panel
        APAinfo.add(new JLabel("<html><font color='white'>Author: </font></html>"));
        APAinfo.add(authorAPA);
        APAinfo.add(new JLabel("<html><font color='white'>Title: </font></html>"));
        APAinfo.add(titleAPA);
        APAinfo.add(new JLabel("<html><font color='white'>Date Posted: </font></html>"));
        APAinfo.add(datePostedAPA);
        APAinfo.add(new JLabel("<html><font color='white'>URL: </font></html>"));
        APAinfo.add(urlAPA);
    }

    //no parameters or return
    //function is to add all the citations as buttons to the panel/scrollpane, assign listeners, etc.
    private void updateVisibleCitations () {
        for (int i = 0; i < citations.size(); i++) {    //removes any blank citations, why would you want these?
            if (isCitEmpty(i))
                citations.remove(i);
        }

        if (citations.size() == 0) {    //does not paint buttons if there are no citations, prompts usr to make new bib
            cit.removeAll();
            JLabel label = new JLabel("<html><font color='white'>Open or create a new bibliography to begin</font></html>");    //fix this
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            cit.add(label);
        }
        else {  //if there are citations....
            cit.removeAll();    //it gets rid of them all, forever
            for (int i = 0; i < citations.size(); i++) {    //jk, then it starts adding them again
                JButton cit1;
                if (type == 1)  //gets the appropriate type of citation (mla/apa)
                    cit1 = new JButton((i + 1) + ")    " + citations.get(i).getFullMLA() + "   ");
                else
                    cit1 = new JButton((i + 1) + ")    " + citations.get(i).getFullAPA() + "   ");
                cit1.setSize((frame.getWidth() - 50), 40);
                cit1.setLocation(leftMargin, (40 * (i)));
                cit1.setHorizontalAlignment(SwingConstants.LEFT);   //alignment and listeners
                cit1.addActionListener(this);
                cit1.setActionCommand(Integer.toString(i));  //set this to a string that will be the uniqu identifier of the button
                cit1.setFocusable(false);
                buttonCits.add(cit1);
                cit.add(cit1);
            }
        }

        cit.revalidate();   //REPAINT!!!!!!
        cit.repaint();
    }

    //no parameters or return
    //function is to save the file in a format that can later be opened by the program for further editing
    private void save () {
        String fileName = (String)JOptionPane.showInputDialog(frame,
                "<html><font color='white'>Please enter a file name to save</font></html>",
                "Save", JOptionPane.PLAIN_MESSAGE, null, null, ""); //asks for file name

        if (fileName == null)   //if the file name is null, does nothing
            return;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName +".bib"));      //ooooooooohhhh, custom file types; this is what i'm most proud of
            if (type == 1)
                bw.write("CIT_TYPE=MLA\n"); //writes the type of citation at the top of the file so it can later be ID'd
            else if (type == 2)
                bw.write("CIT_TYPE=APA\n");

            for (int i = 0; i < citations.size(); i ++) {   //writes info
                bw.write(citations.get(i).getAuthor() + "\n");
                bw.write(citations.get(i).getTitle() + "\n");
                bw.write(citations.get(i).getWebTitle() + "\n");
                bw.write(citations.get(i).getPublisher() + "\n");
                bw.write(citations.get(i).getUrl() + "\n");
                bw.write(citations.get(i).getDatePosted() + "\n");
                bw.write(citations.get(i).getDateAcc() + "\n");
            }

            bw.close(); //close and success message
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Save Successful!</font></html>"));

        }
        catch (IOException e) { //no success message
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Save Unsuccessful... ¯\\_(ツ)_/¯</font></html>"));
        }
    }

    //no parameters or return
    //funstion is to export to a human-readable .txt file that can actually be used
    private void export () {
        String fileName = (String)JOptionPane.showInputDialog(frame,
                "<html><font color='white'>Please enter a file name to save</font></html>",
                "Save", JOptionPane.PLAIN_MESSAGE, null, null, ""); //file name
        if (fileName == null || fileName.equals(""))
            return;
        ArrayList <String> theArrayListOfCitationism = new ArrayList<>();   //creates an arraylist where all the citations will be copied into

        for (int i = 0; i < citations.size(); i ++) {   //fills said arraylist
            if (type == 1)
                theArrayListOfCitationism.add(citations.get(i).getFullMLA());
            else if (type == 2)
                theArrayListOfCitationism.add(citations.get(i).getFullAPA());
        }
        Collections.sort(theArrayListOfCitationism);    //sorts said arraylist

        try {       //finally, it writes the said arraylist to a file that can be opened at a later hour for the convince and comfort of the user
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName +".txt"));
            for (int i = 0; i < theArrayListOfCitationism.size(); i ++)
                bw.write(theArrayListOfCitationism.get(i) + "\n");
            bw.close();
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Export Successful!</font></html>"));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Export Unsuccessful... ¯\\_(ツ)_/¯</font></html>"));
        }
    }

    //no parameters or return
    //function is to open the file the user had previously saved
    private void open () {
        if (citations.size()>0) //asks the user to save of citations exist
            saveOptionDialog();

        JFileChooser fc = new JFileChooser();   //creates a new file chooser to find the file the user wants to find
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(frame);
        File sf = null;

        if (result == JFileChooser.APPROVE_OPTION) {    //pick a file, any file
            sf = fc.getSelectedFile();
        }
        if (sf != null) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(sf));   //creates reader from that file
                String inp = read.readLine();

                if (inp == null) {
                    citations.clear();  // if the file is empty, clears list and paints nothing
                    updateVisibleCitations();
                    return;
                }

                if (inp.equals("CIT_TYPE=MLA"))     //gets the type of the citation
                    type = 1;
                else if (inp.equals("CIT_TYPE=APA"))
                    type = 2;
                else
                    type = 0;

                citations.clear();  //resets the arrayList
                updateVisibleCitations();
                inp = read.readLine();

                while (inp != null && !inp.equals("")) {    //inputs the data from the file
                    String author = inp;
                    String title = read.readLine();
                    String webTitle = read.readLine();
                    String pub = read.readLine();
                    String url = read.readLine();
                    String date_post = read.readLine();
                    String date_acc = read.readLine();

                    citations.add(new Citation(date_post, author, url, pub, title, webTitle, date_acc));    //creates new citation object
                    inp = read.readLine();
                }
                updateVisibleCitations();   //updates the list visibly
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Final_Assignment_AS_rev1.getFrame(), new JLabel("<html><font color='white'>Error, could not open file</font></html>"));
            }
        }
    }

    //no return, there is parameter for the index of the citation to edit
    //function is to edit a pre-made citation
    private void edit (int index) {
        if (type == 1) {    //mla
            updateTextFieldText(index, 1);  //updates the text fields to show the data that is already there
            int result = JOptionPane.showConfirmDialog(null, MLAinfo,
                    "Edit Citation", JOptionPane.OK_CANCEL_OPTION); //shows the edit pane
            if (result == JOptionPane.OK_OPTION) {  //gathers the info entered
                citations.get(index).setDateAcc(dateAcc.getText());
                citations.get(index).setAuthor(author.getText());
                citations.get(index).setDatePosted(datePosted.getText());
                citations.get(index).setPublisher(publisher.getText());
                citations.get(index).setUrl(url.getText());
                citations.get(index).setTitle(title.getText());
                citations.get(index).setWebTitle(webTitle.getText());
            } else if (result == JOptionPane.CANCEL_OPTION) //if a new, empty citation is made, it is deleted
                if (isCitEmpty(index))
                    citations.remove(index);
        }
        else if (type == 2) {   //apa
            updateTextFieldText(index, 2);  //updates text fields
            int result = JOptionPane.showConfirmDialog(null, APAinfo,
                    "Edit Citation", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {  //gets new info, sets it to class
                citations.get(index).setAuthor(authorAPA.getText());
                citations.get(index).setDatePosted(datePostedAPA.getText());
                citations.get(index).setUrl(urlAPA.getText());
                citations.get(index).setTitle(titleAPA.getText());
            } else if (result == JOptionPane.CANCEL_OPTION) //removes if empty
                if (isCitEmpty(index))
                    citations.remove(index);
        }
        updateVisibleCitations();   //updates visibly
    }

    //returns a boolean, parameter is the index to check
    //function is to check and see if a citation is empty or not (ergo whether or not it can be deleted)
    private boolean isCitEmpty (int index) {
        if (citations.get(index).getDatePosted().trim().equals("") &&
                citations.get(index).getUrl().trim().equals("") &&
                citations.get(index).getPublisher().trim().equals("") &&
                citations.get(index).getWebTitle().trim().equals("") &&
                citations.get(index).getAuthor().trim().equals("") &&
                citations.get(index).getTitle().trim().equals(""))
            return true;    //if empty, return true
        return false;   //otherwise return false
    }

    //returns zip, zero, nada, nothing, NILL; parameters are the index of the citation and the style (mla or apa)
    //function is to get info from class and show it in the input field so it can be edited
    private void updateTextFieldText (int index, int style) {
        if (style == 1) {   //MLA
            author.setText(citations.get(index).getAuthor());   //gets the info from the citation's class and sets it as the text for the field
            dateAcc.setText(citations.get(index).getDateAcc());
            datePosted.setText(citations.get(index).getDatePosted());
            url.setText(citations.get(index).getUrl());
            publisher.setText(citations.get(index).getPublisher());
            title.setText(citations.get(index).getTitle());
            webTitle.setText(citations.get(index).getWebTitle());
        }
        else {  //APA
            authorAPA.setText(citations.get(index).getAuthor());    //same same, only different
            datePostedAPA.setText(citations.get(index).getDatePosted());
            urlAPA.setText(citations.get(index).getUrl());
            titleAPA.setText(citations.get(index).getTitle());
        }
    }

    //returns nothing, parameters is a boolean that decided if mla or apa
    //function is to start the creation of a new bibliography
    private void newBibMake (boolean which) {
        if (citations.size()>0) //if citations exist, ask to save
            saveOptionDialog();
        if (which)
            type = 1;   //mla
        else
            type = 2;   //apa
        citations.clear();  //removes all
        citations.add(new Citation("", "", "", "", "", "", ""));
        edit(citations.size()-1);   //add new and them immediately edit that one
        updateVisibleCitations();   //update visibly
    }

    //returns nothing, no parameters
    //function is to save the file before moving on, asks user for file name and saves
    private void saveOptionDialog () {
        int choice = JOptionPane.showOptionDialog(null,"<html><font color='white'>Would you like to save?</font></html>",
                "Save", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null, null );
        if (choice == 0)    //if the option chosen is to save, thoust program shall saveth
            save();
    }

    //getters
    static ArrayList <Citation> getCitations () {
        return citations;
    }
    static int getType () {
        return type;
    }
    static JFrame getFrame () {
        return frame;
    }

    //no parameters, no return
    //removes specific (edit, clipboard, etc) panel and shows main (new, save, etc)
    private void showSpecMenu () {
        master.remove(panelMain);
        master.add(panelSpec);
        master.revalidate();
        master.repaint();
    }

    //no parameters, no return
    //function is literally the opposite of the method before
    private void showMainMenu () {
        master.remove(panelSpec);
        master.add(panelMain);
        master.revalidate();
        master.repaint();
    }

    //returns a boolean (okay, not okay), no parameters
    //function is to ping a website and see if it responds, if it does, it can be opened
    private boolean webOK () {
        try {
            URL url = new URL("https://" + citations.get(currentOption).getUrl());  //sets the URL
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000);    //sets Timeout (efficiency!)
            urlConn.connect();  //attempts to connect
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) //if signal is good, return true
                return true;
            else
                throw new IOException();
        } catch (Exception e) { //if signal is no good, tell usr
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Link could not be opened or does not exist</font></html>"));
        }
        return false;
    }

    //returns nothing, parameters is an ActionEvent e
    //function is to respond to button clicks, do stuff depending on what that button is
    public void actionPerformed (ActionEvent e){
        Object event = e.getSource();   //gets the source and name of the button that was activated
        String name = e.getActionCommand();

        if (event == openBib)   //open file command
            open();
        else if (event == editCit)  //edit file command
            edit(currentOption);
        else if (event == about)    //shows about menu
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>© 2017 Artin S. - Reference Maker 3000</font></html>"));
        else if (event == saveBib)  //save option
            save();
        else if (event == exportBib)    //export option
            export();
        else if (event == copyCit) {    //this copies the fullcitaion to the clipboard so it can be pasted elsewhere
            if (currentOption>-1) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                if (type == 1)      //gets according to the type of citation
                    clipboard.setContents(new StringSelection(citations.get(currentOption).getFullMLA()), null);
                else if (type == 2)
                    clipboard.setContents(new StringSelection(citations.get(currentOption).getFullAPA()), null);
                currentOption = -1;
                showMainMenu(); //goes back to main menu
            }
        }
        else if (event == delCit) { //deletes the citation
            if (currentOption>-1) {
                citations.remove(currentOption);
                currentOption = -1;
                updateVisibleCitations();   //removes it visually
                showMainMenu();
            }
        }
        else if (event == osCit) {  //opens the website of the citation
            if (currentOption>-1 && webOK()) {  //does it pass the ping test!?!?!?!
                String url = citations.get(currentOption).getUrl();
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(url));   //opens it
                    } catch (IOException | URISyntaxException f) {  //error message if the link could not be opened
                        JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Link could not be opened or does not exist</font></html>"));
                    }
                }
                currentOption = -1;
                showMainMenu();
            }
        }
        else if (event == back) {   //goes back to the main menu
            currentOption = -1;
            showMainMenu();
        }
        else if (event == newCit) { //creates a new citation
            if (citations.size()<1)
                JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>You need to make a bib first!</font></html>"));
            else {
                citations.add(new Citation("", "", "", "", "", "", ""));
                edit(citations.size()-1);   //makes new object and adds to arrayList, edits
            }
        }
        else if (event == MLA)  //new MLA bib
            newBibMake(true);
        else if (event == APA)  //new APA bib
            newBibMake(false);
        else if (currentOption == -1)   //if one of the buttons for the citations are clicked, we go here
            for (int i = 0; i < citations.size(); i++)  //loops through until the number/name of the citation is found
                if (name.equals(Integer.toString(i))) {
                    currentOption = i;
                    showSpecMenu();
                }
    }
}
//That's all folks!


//Ms. Wong, if I forget to say this later, it's been a pleasure having been in your classes over the past two years. I will,
//without a doubt, carry what you have taught me for the rest of my life. Thanks, Artin :)