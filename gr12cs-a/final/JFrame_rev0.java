import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JFrame_rev0 extends JFrame implements ActionListener {
    private JMenuItem openBib, saveBib, exportBib, settingsApp, editCit, delCit, copyCit, osCit, MLA, APA;
    private JMenu newBib;
    private JPanel panelMain, panelSpec, master, cit;
    private JTable table;
    private JScrollPane scrollPane;
    private JFrame frame;

    public static void main(String[] args) {
        new JFrame_rev0();
    }

    private JFrame_rev0() {
        //TEMPORARY integrated stuff
        ArrayList <Citation> citations = new ArrayList<>();

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        //keep stuff
        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.DARK_THEME);
        frame = new JFrame();

        //make sure the program exits when the frame closes
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Reference Maker 3000");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        //init stuff
        JMenuBar menuMain = new JMenuBar();
        JMenuBar menuSpec = new JMenuBar();

        master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));

        cit = new JPanel();
        cit.setLayout(new BoxLayout(cit, BoxLayout.X_AXIS));
        cit.setLocation(10, 100);

        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //main menu
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.add(menuMain);
        menuMain.setSize(275, 40);  //original: 250, 40
        menuMain.setLocation(10, 10);

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
        settingsApp = new JMenuItem("Settings");
        settingsApp.addActionListener(this);

        menuMain.add(newBib);
        newBib.add(MLA);
        newBib.add(APA);
        menuMain.add(openBib);
        menuMain.add(saveBib);
        menuMain.add(exportBib);
        menuMain.add(settingsApp);

        //specific menu
        panelSpec = new JPanel();
        panelSpec.setLayout(null);
        panelSpec.add(menuSpec);
        menuSpec.setSize(400, 40);      //org: 350, 40
        menuSpec.setLocation(10, 10);

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

        //buttons
        JButton cit1 = new JButton("1) ");
        cit1.setLocation(15, 200);
        cit.add(cit1);


        //frame stuff
        master.add(cit);
        master.add(panelMain);
        master.add(scrollPane);
        master.add(table);
        frame.add(master);
        frame.setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == saveBib) { //change this to the actual button for the citation
            master.remove(panelMain);
            master.add(panelSpec);
            master.revalidate();
            master.repaint();
        }

        if (e.getSource() == editCit) {
            master.remove(panelSpec);
            master.add(panelMain);
            master.revalidate();
            master.repaint();
        }
    }
}
