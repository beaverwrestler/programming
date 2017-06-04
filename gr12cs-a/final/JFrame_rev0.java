import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrame_rev0 extends JFrame implements ActionListener {
    private JMenuItem openBib, saveBib, exportBib, settingsApp, editCit, delCit, copyCit, osCit, MLA, APA;
    private JMenu newBib;
    private JPanel panelMain, panelSpec;
    private JFrame frame;

    public static void main(String[] args) {
        new JFrame_rev0();
    }

    private JFrame_rev0() {
        MaterialLookAndFeel ui = new MaterialLookAndFeel (GUITheme.DARK_THEME);
        frame = new JFrame();

        //make sure the program exits when the frame closes
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Reference Maker 3000");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuMain = new JMenuBar();
        JMenuBar menuSpec = new JMenuBar();

        //main
        panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.add(menuMain);
        menuMain.setSize(250, 40);
        menuMain.setLocation(10, 10);

        newBib = new JMenu("New   ");
        MLA = new JMenuItem("MLA - Website");
        MLA.addActionListener(this);
        APA = new JMenuItem("APA - Website");
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

        //specific
        panelSpec = new JPanel();
        panelSpec.setLayout(null);
        panelSpec.add(menuSpec);
        menuSpec.setSize(350, 40);
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

        frame.add(panelMain);
        frame.setVisible(true);
        menuMain.setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == saveBib) {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(panelSpec);
            frame.revalidate();
        }
    }
}
