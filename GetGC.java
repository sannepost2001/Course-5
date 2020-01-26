import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class GetGC extends JFrame {
    public JLabel bestand;
    public JLabel informatie;
    public JLabel percentage;
    public JLabel gclbl;
    public JLabel begin;
    public JLabel einde;
    public JTextField kies;
    public JTextField begingcijfer;
    public JTextField eindcijfer;
    public JButton blader;
    public JButton analyseer;
    public JTextArea informatieveld;
    public File file;

    private JProgressBar gcbar = new JProgressBar(0, 100);

    public static void main(String[] args) {
        GetGC frame = new GetGC();
        frame.setSize(700, 500);
        frame.GIU();
        frame.setTitle("GC%");
        frame.setVisible(true);
    }

    void GIU() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(null);

        bestand = new JLabel("Bestand:");
        bestand.setBounds(50, 25, 200, 20);
        window.add(bestand);

        kies = new JTextField();
        kies.setBounds(150, 25, 200, 20);
        window.add(kies);

        blader = new JButton("Blader");
        window.add(blader);
        blader.setBounds(360, 25, 100, 20);
        blader.addActionListener(new blader());

        analyseer = new JButton("Analyseer");
        window.add(analyseer);
        analyseer.setBounds(475, 25, 100, 20);
        analyseer.addActionListener(new analyse());

        begin = new JLabel("Begin:");
        window.add(begin);
        begin.setBounds(150, 50, 100, 20);

        einde = new JLabel("Einde:");
        window.add(einde);
        einde.setBounds(350, 50, 100, 20);

        begingcijfer = new JTextField();
        window.add(begingcijfer);
        begingcijfer.setBounds(200, 50, 100, 20);

        eindcijfer = new JTextField();
        window.add(eindcijfer);
        eindcijfer.setBounds(400, 50, 100, 20);

        informatie = new JLabel("Informatie:");
        informatie.setBounds(50, 75, 200, 20);
        window.add(informatie);

        informatieveld = new JTextArea("", 20, 20);
        informatieveld.setBounds(150, 75, 425, 250);
        informatieveld.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        window.add(informatieveld);

        percentage = new JLabel("Percentage:");
        percentage.setBounds(50, 325, 200, 20);
        window.add(percentage);

        gclbl = new JLabel("GC%:");
        gclbl.setBounds(160, 325, 50, 20);
        window.add(gclbl);

        gcbar.setStringPainted(true);
        gcbar.setForeground(Color.RED);
        gcbar.setBounds(215, 329, 259, 25);
        window.add(gcbar);

    }

    class blader implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setDialogTitle("Choose a directory to save your file: ");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isFile()) {

                    kies.setText(String.valueOf(jfc.getSelectedFile()));
                    file = jfc.getSelectedFile();
                }
            }
        }
    }

    class analyse implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String st = "";
            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((st = br.readLine()) != null) ;
//                    System.out.println(st);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileStream != null;
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);
            String line = null;

            assert st != null;

            int lengte = 0;
            int charpositie = 0;

            float countWord = 0;
            float sentenceCount = 0;
            float characterCount = 0;
            float paragraphCount = 1;
            float whitespaceCount = 0;
            float GC = 0;

            int B = 0;
            int E = 0;

            if (begingcijfer.getText().equals("")) {
                B = 0;
            } else {
                B = parseInt(begingcijfer.getText());
            }
            if (eindcijfer.getText().equals("")) {
                E = Integer.MAX_VALUE;
            } else {
                E = parseInt(eindcijfer.getText());
            }

            String DNA = "";
            String eersteregel = "";
            try {
                eersteregel = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Reading line by line from the
            // file until a null is returned
            while (true) {
                try {
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line.equals("")) {
                    paragraphCount++;
                }

                if (!(line.equals(""))) {
                    for (int i = 0; i < line.length(); i++) {
                        charpositie++;
                        if (charpositie >= B && charpositie <= E) {
                            lengte++;
                            char one = line.charAt(i);
                            String s = String.valueOf(one);
                            //                        System.out.println(s);
                            if (Pattern.matches("[ATCG]+", s)) {
                                DNA = "Alle nucleotides zijn juist";
                                //                            System.out.println("its all good");
                            } else {
                                try {
                                    throw new NotAnAA("Dit is een niet bestaand nucleotide: " + s);
                                } catch (NotAnAA notAnAA) {
                                    notAnAA.printStackTrace();
                                }
                            }
                            if (s.equals("G") || s.equals("C")) {
                                GC = GC + 1;
                                System.out.println(GC);
                            }
                        }
                    }
                }
                if (!(line.equals(""))) {

                    characterCount += line.length();

                    // \\s+ is the space delimiter in java
                    String[] wordList = line.split("\\s+");

                    countWord += wordList.length;
                    whitespaceCount += countWord - 1;

                    // [!?.:]+ is the sentence delimiter in java
                    String[] sentenceList = line.split("[!?.:]+");

                    sentenceCount += sentenceList.length;
                }
            }

            float procentGC = (100 * GC) / lengte;
            gcbar.setValue((int) procentGC);
            gcbar.repaint();

            String[] accessiecode = eersteregel.split(" ");
            String accessie = accessiecode[0];
            accessie = accessie.substring(1, accessie.length() - 1);

            if (eindcijfer.getText().equals("")) {
                E = Integer.MAX_VALUE;
            } else {
                E = (int) characterCount;
            }


            String posities = "De begin locaties van de geanalyseerde sequentie" + "\nis " + B +
                    " en de eind locatie is " + E;

            String GCprocent = "GC% in this sequence = " + procentGC;
            String Wordcount = "Total word count = " + countWord;
            String countScentence = "Total number of sentences = " + sentenceCount;
            String countCharacter = "The total of characters = " + characterCount;
            String countParagraph = "Number of paragraphs = " + paragraphCount;
            String countWithspace = "Total number of whitespaces = " + whitespaceCount;
            String analyselengte = "Analysed length = " + lengte;

//            System.out.println(polair);
//            System.out.println(apoliar);
//            System.out.println(Wordcount);
//            System.out.println(countScentence);
//            System.out.println(countCharacter);
//            System.out.println(countParagraph);
//            System.out.println(countWithspace);
//            System.out.println(aminozuur);
            String text = accessie + "\n" + posities + "\n" + Wordcount + "\n" + countCharacter + "\n" + analyselengte;
            informatieveld.setText(text);
        }
    }

    static class NotAnAA extends Exception {
        NotAnAA(String err) {
            super(err);
        }
    }
}
