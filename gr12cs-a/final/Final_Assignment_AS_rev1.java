import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final_Assignment_AS_rev1 implements ActionListener{
    private JMenuItem openBib, saveBib, exportBib, about, editCit, delCit, copyCit, osCit, MLA, APA, back, newCit;
    private JPanel panelMain, panelSpec, master, cit, MLAinfo, APAinfo;
    private static JFrame frame;
    private JTextField author, dateAcc, datePosted, url, publisher, title, webTitle, authorAPA, datePostedAPA, urlAPA, titleAPA;

    private static int type = 0;        //mla (1) or apa (2) or no active (0)
    private final int leftMargin = 10;
    private int currentOption = -1;
    private static ArrayList <Citation> citations;
    private static ArrayList <JButton> buttonCits = new ArrayList<>();

    public static void main (String [] args) {
        new Final_Assignment_AS_rev1();
    }

    private Final_Assignment_AS_rev1 () {
        citations = new ArrayList<>();

        Saver save = new Saver();
        save.start();

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
        master.setLayout(null);
        master.setLocation(0,0);
        master.setSize(frame.getSize());

        cit = new JPanel();
        cit.setLayout(new BoxLayout(cit, BoxLayout.Y_AXIS));
        cit.setLocation(0, 60);
        cit.setSize(frame.getWidth(),730);

        JScrollPane citationPane = new JScrollPane(cit);
        citationPane.setLocation(10, 60);
        citationPane.setSize(frame.getWidth()-15,710);
        citationPane.getVerticalScrollBar().setUnitIncrement(14);   //scrolling speed was slow as shit before

        //main menu
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.add(menuMain);
        panelMain.setSize(frame.getWidth(), 60);
        panelMain.setLocation(0, 0);
        menuMain.setSize(500, 40);  //original: 250, 40
        menuMain.setLocation(leftMargin, 10);

        JMenu newBib = new JMenu("New Bib");  //3 spaces after
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

        //specific menu
        panelSpec = new JPanel();
        panelSpec.setLayout(null);
        panelSpec.add(menuSpec);
        panelSpec.setSize(frame.getWidth(), 60);
        panelSpec.setLocation(0,0);
        menuSpec.setSize(800, 40);      //org: 350, 40
        menuSpec.setLocation(leftMargin, 10);

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

        menuSpec.add(back);
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

        //text input stuff for citations

        MLAinfo = new JPanel();
        MLAinfo.setLayout(new BoxLayout(MLAinfo, BoxLayout.PAGE_AXIS));
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

        APAinfo = new JPanel();
        APAinfo.setLayout(new BoxLayout(APAinfo, BoxLayout.PAGE_AXIS));

        authorAPA = new JTextField(10);
        datePostedAPA = new JTextField(10);
        urlAPA = new JTextField(10);
        titleAPA = new JTextField(10);

        APAinfo.add(new JLabel("<html><font color='white'>Author: </font></html>"));
        APAinfo.add(authorAPA);
        APAinfo.add(new JLabel("<html><font color='white'>Title: </font></html>"));
        APAinfo.add(titleAPA);
        APAinfo.add(new JLabel("<html><font color='white'>Date Posted: </font></html>"));
        APAinfo.add(datePostedAPA);
        APAinfo.add(new JLabel("<html><font color='white'>URL: </font></html>"));
        APAinfo.add(urlAPA);
    }

    private void updateVisibleCitations (ArrayList <Citation> cits) {
        for (int i = 0; i < citations.size(); i++) {
            if (isCitEmpty(i))
                citations.remove(i);
        }

        if (cits.size() == 0) {
            cit.removeAll();
            JLabel label = new JLabel("<html><font color='white'>Open or create a new bibliography to begin</font></html>");    //fix this
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            cit.add(label);
        }
        else {
            cit.removeAll();
            for (int i = 0; i < cits.size(); i++) {
                JButton cit1;
                if (type == 1)
                    cit1 = new JButton((i + 1) + ")    " + cits.get(i).getFullMLA() + "   ");
                else
                    cit1 = new JButton((i + 1) + ")    " + cits.get(i).getFullAPA() + "   ");
                cit1.setSize((frame.getWidth() - 50), 40);
                cit1.setLocation(leftMargin, (40 * (i)));
                cit1.setHorizontalAlignment(SwingConstants.LEFT);
                cit1.addActionListener(this);
                cit1.setActionCommand(Integer.toString(i));  //set this to a string that will be the uniqu identifier of the button
                cit1.setFocusable(false);
                buttonCits.add(cit1);
                cit.add(cit1);
            }
        }

        cit.revalidate();
        cit.repaint();
    }

    private void save () {
        String fileName = (String)JOptionPane.showInputDialog(frame,
                "<html><font color='white'>Please enter a file name to save</font></html>",
                "Save", JOptionPane.PLAIN_MESSAGE, null, null, "");

        if (fileName == null)
            return;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName +".bib"));
            if (type == 1)
                bw.write("CIT_TYPE=MLA\n");
            else if (type == 2)
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
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Save Successful!</font></html>"));

        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Save Unsuccessful... ¯\\_(ツ)_/¯</font></html>"));
        }
    }

    private void export () {
        String fileName = (String)JOptionPane.showInputDialog(frame,
                "<html><font color='white'>Please enter a file name to save</font></html>",
                "Save", JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (fileName == null || fileName.equals(""))
            return;
        ArrayList <String> theArrayListOfCitationism = new ArrayList<>();

        for (int i = 0; i < citations.size(); i ++) {
            if (type == 1)
                theArrayListOfCitationism.add(citations.get(i).getFullMLA());
            else if (type == 2)
                theArrayListOfCitationism.add(citations.get(i).getFullAPA());
        }
        Collections.sort(theArrayListOfCitationism);

        try {
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

    private void open () {
        if (citations.size()>0)
            saveOptionDialog();

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(frame);
        File sf = null;

        if (result == JFileChooser.APPROVE_OPTION) {
            sf = fc.getSelectedFile();
        }
        if (sf != null) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(sf));

                String inp = read.readLine();

                if (inp == null) {
                    citations.clear();
                    updateVisibleCitations(citations);
                    return;
                }

                if (inp.equals("CIT_TYPE=MLA"))
                    type = 1;
                else if (inp.equals("CIT_TYPE=APA"))
                    type = 2;
                else
                    type = 0;

                citations.clear();
                updateVisibleCitations(citations);
                inp = read.readLine();

                while (inp != null && !inp.equals("")) {
                    String author = inp;
                    String title = read.readLine();
                    String webTitle = read.readLine();
                    String pub = read.readLine();
                    String url = read.readLine();
                    String date_post = read.readLine();
                    String date_acc = read.readLine();

                    citations.add(new Citation(date_post, author, url, pub, title, webTitle, date_acc));
                    inp = read.readLine();
                }
                updateVisibleCitations(citations);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Final_Assignment_AS_rev1.getFrame(), new JLabel("<html><font color='white'>Error, could not open file</font></html>"));
            }
        }
    }

    private void edit (int index) {
        if (type == 1) {    //mla
            updateTextFieldText(index, 1);
            int result = JOptionPane.showConfirmDialog(null, MLAinfo,
                    "Edit Citation", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                citations.get(index).setDateAcc(dateAcc.getText());
                citations.get(index).setAuthor(author.getText());
                citations.get(index).setDatePosted(datePosted.getText());
                citations.get(index).setPublisher(publisher.getText());
                citations.get(index).setUrl(url.getText());
                citations.get(index).setTitle(title.getText());
                citations.get(index).setWebTitle(webTitle.getText());
            } else if (result == JOptionPane.CANCEL_OPTION)
                if (isCitEmpty(index))
                    citations.remove(index);
        }
        else if (type == 2) {   //apa
            updateTextFieldText(index, 2);
            int result = JOptionPane.showConfirmDialog(null, APAinfo,
                    "Edit Citation", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                citations.get(index).setAuthor(authorAPA.getText());
                citations.get(index).setDatePosted(datePostedAPA.getText());
                citations.get(index).setUrl(urlAPA.getText());
                citations.get(index).setTitle(titleAPA.getText());
            } else if (result == JOptionPane.CANCEL_OPTION)
                if (isCitEmpty(index))
                    citations.remove(index);
        }

        updateVisibleCitations(citations);
    }

    private boolean isCitEmpty (int index) {
        if (citations.get(index).getDatePosted().trim().equals("") &&
                citations.get(index).getUrl().trim().equals("") &&
                citations.get(index).getPublisher().trim().equals("") &&
                citations.get(index).getWebTitle().trim().equals("") &&
                citations.get(index).getAuthor().trim().equals("") &&
                citations.get(index).getTitle().trim().equals(""))
            return true;
        return false;
    }

    private void updateTextFieldText (int index, int style) {
        if (style == 1) {   //MLA
            author.setText(citations.get(index).getAuthor());
            dateAcc.setText(citations.get(index).getDateAcc());
            datePosted.setText(citations.get(index).getDatePosted());
            url.setText(citations.get(index).getUrl());
            publisher.setText(citations.get(index).getPublisher());
            title.setText(citations.get(index).getTitle());
            webTitle.setText(citations.get(index).getWebTitle());
        }
        else {  //APA
            authorAPA.setText(citations.get(index).getAuthor());
            datePostedAPA.setText(citations.get(index).getDatePosted());
            urlAPA.setText(citations.get(index).getUrl());
            titleAPA.setText(citations.get(index).getTitle());
        }
    }

    private void newBibMake (boolean which) {
        if (citations.size()>0)
            saveOptionDialog();
        if (which)
            type = 1;
        else
            type = 2;
        citations.clear();
        citations.add(new Citation("", "", "", "", "", "", ""));
        edit(citations.size()-1);
        updateVisibleCitations(citations);
    }

    private void saveOptionDialog () {  //finish this
        int choice = JOptionPane.showOptionDialog(null,"<html><font color='white'>Would you like to save?</font></html>",
                "Save", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null, null );
        if (choice == 0)
            save();
    }

    static ArrayList <Citation> getCitations () {
        return citations;
    }
    static int getType () {
        return type;
    }
    static JFrame getFrame () {
        return frame;
    }

    private void showSpecMenu () {
        master.remove(panelMain);
        master.add(panelSpec);
        master.revalidate();
        master.repaint();
    }

    private void showMainMenu () {
        master.remove(panelSpec);
        master.add(panelMain);
        master.revalidate();
        master.repaint();
    }

    private boolean webOK () {
        try {
            URL url = new URL("https://" + citations.get(currentOption).getUrl());
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000);
            urlConn.connect();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
                return true;
            else
                throw new IOException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Link could not be opened or does not exist</font></html>"));
        }
        return false;
    }

    public void actionPerformed (ActionEvent e){
        Object event = e.getSource();
        String name = e.getActionCommand();

        if (event == openBib)
            open();
        else if (event == editCit)
            edit(currentOption);
        else if (event == about)
            JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>© 2017 Artin S. - Reference Maker 3000</font></html>"));
        else if (event == saveBib)
            save();
        else if (event == exportBib)
            export();
        else if (event == copyCit) {
            if (currentOption>-1) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                if (type == 1)
                    clipboard.setContents(new StringSelection(citations.get(currentOption).getFullMLA()), null);
                else if (type == 2)
                    clipboard.setContents(new StringSelection(citations.get(currentOption).getFullAPA()), null);
                currentOption = -1;
                showMainMenu();
            }
        }
        else if (event == delCit) {
            if (currentOption>-1) {
                citations.remove(currentOption);
                currentOption = -1;
                updateVisibleCitations(citations);
                showMainMenu();
            }
        }
        else if (event == osCit) {
            if (currentOption>-1 && webOK()) {
                String url = citations.get(currentOption).getUrl();
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(url));
                    } catch (IOException | URISyntaxException f) {
                        JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>Link could not be opened or does not exist</font></html>"));
                    }
                }
                currentOption = -1;
                showMainMenu();
            }
        }
        else if (event == back) {
            currentOption = -1;
            showMainMenu();
        }
        else if (event == newCit) {
            if (citations.size()<1)
                JOptionPane.showMessageDialog(frame, new JLabel("<html><font color='white'>You need to make a bib first!</font></html>"));
            else {
                citations.add(new Citation("", "", "", "", "", "", ""));
                edit(citations.size()-1);
            }
        }
        else if (event == MLA)
            newBibMake(true);
        else if (event == APA)
            newBibMake(false);
        else if (currentOption == -1)
            for (int i = 0; i < citations.size(); i++)
                if (name.equals(Integer.toString(i))) {
                    currentOption = i;
                    showSpecMenu();
                }
    }
}
