/**
 *GC calculator
 * @Author Sanne Post
 * @Date  september-2019
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.geom.Line2D;
import java.io.*;
//import java.util.Objects;
//import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class GCcalc extends JFrame implements ActionListener {
    // maak onderdelen gui aan
    // labels
    private JLabel bestand = new JLabel("Bestand");
    private JLabel informatie = new JLabel("Informatie");
    private JLabel accessielbl = new JLabel("Accessie Code:");
    private JLabel accesie = new JLabel("");
    private JLabel naam = new JLabel("Naam gen:");
    private JLabel gen = new JLabel("");
    private JLabel van = new JLabel("Van:");
    private JLabel tot = new JLabel("Tot:");
    private JLabel nul = new JLabel("0%");
    private JLabel honderd = new JLabel("100%");
    private JLabel gc = new JLabel("GC%");
    // buttons
    private JButton blader = new JButton("Blader");
    private JButton open = new JButton("Open");
    private JButton berekenen = new JButton("Berekenen \n GC%");
    // textfield
    private JTextField inlezen = new JTextField();
    private JTextField begin = new JTextField();
    private JTextField einde = new JTextField();
    // panel
    private JPanel panel = new JPanel();
    // progress bar
    private JProgressBar percentage = new JProgressBar(0, 100);

    private File file;

    public static void main(String[] args) {
        GCcalc frame = new GCcalc();
        frame.setSize(550, 380);
        frame.createGUI();
        frame.setTitle("GCclc");
        frame.setVisible(true);

    }

    // maak gui aan
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setBackground(Color.gray);
        window.setLayout(null);



// add labels
        bestand.setBounds(25, 25, 200, 20);
        window.add(bestand);

        informatie.setBounds(25, 60, 200, 20);
        window.add(informatie);

        accesie.setBounds(200, 60, 200, 20);
        window.add(accesie);

        accessielbl.setBounds(100, 60, 200, 20);
        window.add(accessielbl);

        naam.setBounds(100, 80, 200, 20);
        window.add(naam);

        gen.setBounds(200, 80, 300, 20);
        window.add(gen);

        van.setBounds(100, 120, 200, 20);
        window.add(van);

        tot.setBounds(100, 150, 200, 20);
        window.add(tot);

        nul.setBounds(15, 225, 50, 20);
        window.add(nul);

        honderd.setBounds(485, 225, 50, 20);
        window.add(honderd);

        gc.setBounds(250, 225, 50, 20);
        window.add(gc);

// add buttons
        window.add(blader);
        blader.setBounds(310, 25, 75, 20);
        blader.setBackground(Color.blue);
        blader.addActionListener(new Blader());

        window.add(open);
        open.setBounds(400, 25, 75, 20);
        open.setBackground(Color.blue);
        open.addActionListener(new Openen());

        window.add(berekenen);
        berekenen.setBounds(250, 120, 175, 50);
        berekenen.setBackground(Color.blue);
        berekenen.addActionListener(new Bereken());

// add textfield
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

        inlezen.setBounds(100, 25, 200, 20);
        window.add(inlezen);
        inlezen.setBackground(Color.white);
        inlezen.setBorder(border);

        begin.setBounds(170, 120, 50, 20);
        window.add(begin);
        begin.setBackground(Color.white);
        begin.setBorder(border);

        einde.setBounds(170, 150, 50, 20);
        window.add(einde);
        einde.setBackground(Color.white);
        einde.setBorder(border);

// lijn

// add panel
        panel.setBounds(15, 250, 500, 50);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setOpaque(true);
        panel.setBorder(border);
        window.add(panel);

// add progress bar
        percentage.setStringPainted(true);
        percentage.setForeground(Color.blue);
        percentage.setBounds(20, 5, 460, 40);
        panel.add(percentage);
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

                    inlezen.setText(String.valueOf(jfc.getSelectedFile()));
                    file = jfc.getSelectedFile();
                }
            }
        }
    }

    class Openen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileStream != null;
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);

//            String line = null;
            String eersteregel = "";
            try {
                eersteregel = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            char eersteletter = eersteregel.charAt(0);
//            char tweedeletter = eersteregel.charAt(1);
//            if (!(Objects.equals(eersteletter, "<")) || Objects.equals(tweedeletter, "<")) {
//                JFrame frame = new JFrame("Currupte fasta");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                String tekst = "<html>Corruptie in bestand gevonden. <br/> Controleer geldigheid .fasta </html>";
//                JLabel textLabel = new JLabel(tekst, SwingConstants.CENTER);
//                textLabel.setPreferredSize(new Dimension(300, 200));
//                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
//                //Display the window.
//                frame.setLocationRelativeTo(null);
//                frame.pack();
//                frame.setVisible(true);
//            }
//

            String[] accessiecode = eersteregel.split(" ");
            String code = accessiecode[0];
            code = code.substring(1, code.length() - 1);
            accesie.setText(code);
            String mystring = eersteregel;
            String[] arr = mystring.split(" ", 2);
            String theRest = arr[1];
            gen.setText(theRest);

        }
    }

    class Bereken implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            float GC = 0;
            int lengte = 0;
            int charpositie = 0;

            int B = 0;
            int E = 0;

            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert fileStream != null;
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);
            String line = null;
            try {
                if (begin.getText().equals("")) {
                    B = 0;
                } else {
                    B = parseInt(begin.getText());
                }
                if (einde.getText().equals("")) {
                    E = Integer.MAX_VALUE;
                } else {
                    E = parseInt(einde.getText());
                }
            } catch (NumberFormatException n) {
                JFrame frame = new JFrame("Currupte posities");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                String tekst = "<html>Corruptie in posities gevonden. <br/> Controleer ingevoerde posities </html>";
                JLabel textLabel = new JLabel(tekst, SwingConstants.CENTER);
                textLabel.setPreferredSize(new Dimension(300, 200));
                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
                //Display the window.
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }

            try {
                String eersteregel = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Reading line by line from the
            // file until a null is returned
            try {
                while (true) {
                    try {
                        if ((line = reader.readLine()) == null) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert line != null;
                    if (!("".equals(line))) {
                        for (int i = 0; i < line.length(); i++) {
                            charpositie++;
                            if (charpositie >= B && charpositie <= E) {
                                lengte++;
                                char one = line.charAt(i);
                                String s = String.valueOf(one);
                                if (s.equals("G") || s.equals("C")) {
                                    GC = GC + 1;
                                    System.out.println(GC);
                                }
                            }
                        }
                    }
                }
            } catch (NumberFormatException n) {
                JFrame frame = new JFrame("Currupte posities");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                String tekst = "<html>Corruptie in posities gevonden. <br/> Controleer ingevoerde posities </html>";
                JLabel textLabel = new JLabel(tekst, SwingConstants.CENTER);
                textLabel.setPreferredSize(new Dimension(300, 200));
                frame.getContentPane().add(textLabel, BorderLayout.CENTER);
                //Display the window.
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
            float procentGC = (100 * GC) / lengte;
            percentage.setValue((int) procentGC);
            percentage.repaint();
            einde.setText(String.valueOf(E));
            begin.setText(String.valueOf(B));
        }
    }
}