package BioTools;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Pattern;


public class SeqVis extends JFrame {

    private JLabel bestand = new JLabel("Bestand:");
    private JLabel sequentielbl = new JLabel("Sequentie:");

    private JTextField kies = new JTextField();
    private JButton blader = new JButton("Blader");
    private JButton analyseer = new JButton("Analyseer");
    private JTextArea informatieveld = new JTextArea("", 20, 20);

    private File file;
    private String text = "";

    private JPanel panel = new JPanel();


    public static void main(String[] args) {
        SeqVis frame = new SeqVis();
        frame.setSize(700, 500);
        frame.createGUI();
        frame.setTitle("Translator");
        frame.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(null);


        bestand.setBounds(50, 25, 300, 20);
        window.add(bestand);


        kies.setBounds(150, 25, 320, 20);
        window.add(kies);


        window.add(blader);
        blader.setBounds(475, 25, 100, 20);
        blader.addActionListener(new SeqVis.blader());


        window.add(analyseer);
        analyseer.setBounds(475, 350, 100, 20);
        analyseer.addActionListener(new SeqVis.analyse());


        sequentielbl.setBounds(50, 75, 200, 20);
        window.add(sequentielbl);

        informatieveld.setBounds(150, 75, 425, 250);
        informatieveld.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        window.add(informatieveld);

        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);


        panel.setBounds(30, 400, 600, 25);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setOpaque(true);
        panel.setBorder(border);
        window.add(panel);

    }


    class blader implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            text = "";
            StringBuilder Header = new StringBuilder();
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Choose a directory to save your file: ");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isFile()) {

                    kies.setText(String.valueOf(jfc.getSelectedFile()));
                    file = jfc.getSelectedFile();
                    // file until a null is returned

                }
            }
            String st = "";
            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((st = br.readLine()) != null)
                    System.out.println(st);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileStream != null;
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);

            String line = null;

            assert st != null;


            // Reading line by line from the

            while (true) {
                try {
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert line != null;
                if (!(line.startsWith(">"))) {
                    text = text + line;
                } else {
                    Header.append(line);
                }

            }
            String parsedStr = text.replaceAll("(.{45})", "$1\n");
            String parsedHeader = Header.toString().replaceAll("(.{45})", "$1\n");
            informatieveld.setText(parsedHeader + "\n" + parsedStr);
        }
    }

    class analyse implements ActionListener {
        Sequentie seq1 = null;

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boolean RNA = false;
            boolean DNA = false;
            boolean Protein = false;
            boolean Fouteseq = false;


            for (int i = 0; i < text.length(); i++) {
                char one = text.charAt(i);
                if (Pattern.matches("[U]+", String.valueOf(one))) {
                    RNA = true;
                } else if (Pattern.matches("[ATCG]+", String.valueOf(one))) {
                    DNA = true;
                } else if (Pattern.matches("[RNDQEHILKMFPSWYV]+", String.valueOf(one))) {
                    Protein = true;
                } else {
                    Fouteseq = true;
                }
                // elif geen rna/dna/eiwit throw error

            }

            if (RNA && !Fouteseq) {
                seq1 = new Sequentie.Rna(text);
            } else if (Protein && !Fouteseq) {
                seq1 = new Sequentie.Peptide(text);
            } else if (DNA && !Fouteseq) {
                seq1 = new Sequentie.Dna(text);
            } else if (Fouteseq) {
                try {
                    throw new CorruptFasta("Corrupt letter in DNA or RNA or Protein found");
                } catch (CorruptFasta corruptFasta) {
                    corruptFasta.printStackTrace();
                }
            }
            // bepaalt type object
            System.out.println("Analyse bepaald.");

            Graphics g = panel.getGraphics();

            for (int i = 0; i < seq1.getLenght(); i++) {
                g.setColor(seq1.getColor(i));
                int startpos = (600 * i) / seq1.getLenght();
                int startposVolgende = (600 * (i + 1)) / seq1.getLenght();
                g.fillRect(startpos, 0, startposVolgende - startpos, 25);
            }
        }
    }
}

class CorruptFasta extends Throwable {
    CorruptFasta(String corruption_found) {
        JOptionPane.showConfirmDialog(null, "A corruption in your fasta file has been found"
                , "Corrupt FASTA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

    }
}