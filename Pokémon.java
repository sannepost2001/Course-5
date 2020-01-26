import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Pokémon extends JFrame implements ActionListener {
    // gui onderdelen. window
    //label
    private JLabel pokemon = new JLabel("Pokémon:");
    private JLabel gekozen = new JLabel("...");
    private JLabel bestand = new JLabel("Bestand:");
    private JLabel name = new JLabel("Name:");
    private JLabel nameantwoord = new JLabel("");
    private JLabel ID = new JLabel("ID:");
    private JLabel IDantwoord = new JLabel("");
    private JLabel advies = new JLabel("Advies:");
    private JLabel adviesantwoord = new JLabel("");
    //textfield
    private JTextField pokémoningeven = new JTextField();
    private JTextField bestandinlezen = new JTextField();
    //button
    private JButton kies = new JButton("Kies:");
    private JButton blader = new JButton("Blader");
    private JButton open = new JButton("Open");
    // gui onderdelen panel
    //label
    private JLabel base = new JLabel("Base:");
    private JLabel height = new JLabel("Height:");
    private JLabel weight = new JLabel("Weight:");
    private JLabel basegetal = new JLabel("");
    private JLabel heightgetal = new JLabel("");
    private JLabel weightgetal = new JLabel("");
    // panel
    private JPanel panel = new JPanel();
    // progress bar
    private JProgressBar baseprb = new JProgressBar(0, 100);
    private JProgressBar heightprb = new JProgressBar(0, 100);
    private JProgressBar weightprb = new JProgressBar(0, 100);

    private File file;


    public static void main(String[] args) {
        Pokémon frame = new Pokémon();
        frame.setSize(550, 380);
        frame.createGUI();
        frame.setTitle("Pokébox");
        frame.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setBackground(Color.LIGHT_GRAY);
        window.setLayout(null);
        // labels
        pokemon.setBounds(25, 25, 200, 20);
        window.add(pokemon);

        gekozen.setBounds(400, 25, 75, 20);
        window.add(gekozen);

        bestand.setBounds(25, 60, 200, 20);
        window.add(bestand);

        name.setBounds(25, 120, 200, 20);
        window.add(name);

        nameantwoord.setBounds(80, 120, 200, 20);
        window.add(nameantwoord);

        advies.setBounds(300, 120, 200, 20);
        window.add(advies);

        adviesantwoord.setBounds(350, 120, 200, 20);
        window.add(adviesantwoord);

        ID.setBounds(45, 150, 200, 20);
        window.add(ID);

        IDantwoord.setBounds(80, 150, 200, 20);
        window.add(IDantwoord);
//

// add buttons
        window.add(kies);
        kies.setBounds(310, 25, 75, 20);
        kies.setBackground(Color.blue);
        kies.setForeground(Color.white);
        kies.addActionListener(new Kies());

        window.add(blader);
        blader.setBounds(310, 60, 75, 20);
        blader.setBackground(Color.blue);
        blader.addActionListener(new Blader());
        blader.setForeground(Color.white);

        window.add(open);
        open.setBounds(400, 60, 75, 20);
        open.setBackground(Color.blue);
        open.setForeground(Color.white);
        open.addActionListener(new Openen());

// add textfield
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        pokémoningeven.setBounds(100, 25, 200, 20);
        window.add(pokémoningeven);
        pokémoningeven.setBackground(Color.white);
        pokémoningeven.setBorder(border);

        bestandinlezen.setBounds(100, 60, 200, 20);
        window.add(bestandinlezen);
        bestandinlezen.setBackground(Color.white);
        bestandinlezen.setBorder(border);


// lijn

// add panel
        panel.setBounds(15, 175, 500, 150);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setOpaque(true);
        panel.setBorder(border);
        window.add(panel);

// add progress bar
//        baseprb.setStringPainted(true);
        baseprb.setForeground(Color.blue);
        baseprb.setBounds(110, 20, 300, 30);
        panel.add(baseprb);

//        heightprb.setStringPainted(true);
        heightprb.setForeground(Color.red);
        heightprb.setBounds(110, 60, 300, 30);
        panel.add(heightprb);

//        weightprb.setStringPainted(true);
        weightprb.setForeground(Color.green);
        weightprb.setBounds(110, 100, 300, 30);
        panel.add(weightprb);

// add label
        base.setBounds(15, 20, 200, 20);
        panel.add(base);

        height.setBounds(15, 60, 200, 20);
        panel.add(height);

        weight.setBounds(15, 100, 200, 20);
        panel.add(weight);

        basegetal.setBounds(450, 20, 200, 20);
        panel.add(basegetal);

        heightgetal.setBounds(450, 60, 200, 20);
        panel.add(heightgetal);

        weightgetal.setBounds(450, 100, 200, 20);
        panel.add(weightgetal);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    class Blader implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Choose a directory to save your file: ");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isFile()) {

                    bestandinlezen.setText(String.valueOf(jfc.getSelectedFile()));
                    file = jfc.getSelectedFile();
                }
            }
        }
    }

    class Kies implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String keuzen = pokémoningeven.getText();
            gekozen.setText(keuzen);
        }
    }

    class Openen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String namestr = "";
            String idstr = "";
            String speciesstr = "";
            int heightstr = 0;
            int weightstr = 0;
            int basestr = 0;
            String orderstr = "";
            int hoogstbase = 0;
            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileStream != null;
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line; (line = br.readLine()) != null; ) {
                    if (!line.startsWith("ID")) {
                        String[] regel = line.split(";");
                        String pokenaam = regel[1];
//                        System.out.println(pokenaam);
                        String zoeknaam = pokémoningeven.getText();
//                        System.out.println(zoeknaam);
                        if (zoeknaam.equals(pokenaam)) {
                            idstr = regel[0];
                            namestr = regel[1];
                            speciesstr = regel[2];
                            heightstr = Integer.parseInt(regel[3]);
                            weightstr = Integer.parseInt(regel[4]);
                            basestr = Integer.parseInt(regel[5]);
                            orderstr = regel[6];

                        }
//                    else {
//                        throw new CorruptFasta("Corrupt letter in DNA found");
//                    }
                        hoogstbase = Integer.parseInt(regel[5]);
                    }
                }
                if (idstr.equals("")) {
                    throw new NoPoki("No pokemon found with that name");
                }
            } catch (IOException | NoPoki e) {
                e.printStackTrace();
            }
            nameantwoord.setText(namestr);
            IDantwoord.setText(idstr);
            int vangenwaarde = (int) (hoogstbase * 0.5);
            if (vangenwaarde < basestr) {
                adviesantwoord.setText("Vangen");
            } else {
                adviesantwoord.setText("Niet vangen");
            }

            basegetal.setText(String.valueOf(basestr));
            baseprb.setValue(basestr);
            baseprb.repaint();

            heightgetal.setText(String.valueOf(heightstr));
            heightprb.setValue(heightstr);
            heightprb.repaint();

            weightgetal.setText(String.valueOf(weightstr));
            weightprb.setValue(weightstr);
            weightprb.repaint();
        }
    }


    class NoPoki extends Throwable {
        NoPoki(String NoPokemon) {
            JOptionPane.showConfirmDialog(null, "No pokemon found with that name"
                    , "NoPoki", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
}

