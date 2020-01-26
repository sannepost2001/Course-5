import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Pattern;

public class Swing extends JFrame {
    private JLabel bestand = new JLabel("Bestand:");
    private JLabel informatie = new JLabel("Informatie:");
    private JLabel percentage = new JLabel("Percentage:");

    private JLabel polairlbl = new JLabel("Polair:");
    private JLabel apolairlbl = new JLabel(" Apolair:");
    private JTextField kies = new JTextField();
    private JButton blader = new JButton("Blader");
    private JButton analyseer = new JButton("Analyseer");
    private JTextArea informatieveld;
    private JPanel polpanel = new JPanel();
    public File file;

    private JProgressBar polair = new JProgressBar(0, 100);
    private JProgressBar apolaire = new JProgressBar(0, 100);

    public static void main(String[] args) {
        Swing frame = new Swing();
        frame.setSize(700, 500);
        frame.createGUI();
        frame.setTitle("Translator");
        frame.setVisible(true);
    }

    void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(null);


        bestand.setBounds(50, 25, 200, 20);
        window.add(bestand);


        kies.setBounds(150, 25, 200, 20);
        window.add(kies);


        window.add(blader);
        blader.setBounds(360, 25, 100, 20);
        blader.addActionListener(new blader());


        window.add(analyseer);
        analyseer.setBounds(475, 25, 100, 20);
        analyseer.addActionListener(new analyse());


        informatie.setBounds(50, 75, 200, 20);
        window.add(informatie);

        informatieveld = new JTextArea("", 20, 20);
        informatieveld.setBounds(150, 75, 425, 250);
        informatieveld.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        window.add(informatieveld);


        percentage.setBounds(50, 335, 200, 20);
        window.add(percentage);


        polpanel.setBounds(150, 335, 340, 100);
        polpanel.setLayout(null);
        polpanel.setBackground(Color.yellow);
        polpanel.setOpaque(true);
        window.add(polpanel);


        polairlbl.setBounds(10, 5, 50, 20);
        polpanel.add(polairlbl);

        polair.setStringPainted(true);
        polair.setForeground(Color.RED);
        polair.setBounds(60, 5, 259, 25);
        polpanel.add(polair);


        apolairlbl.setBounds(5, 35, 60, 20);
        polpanel.add(apolairlbl);

        apolaire.setStringPainted(true);
        apolaire.setForeground(Color.BLUE);
        apolaire.setBounds(60, 35, 259, 25);
        polpanel.add(apolaire);


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
                while ((st = br.readLine()) != null)
                    System.out.println(st);
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

            float countWord = 0;
            float sentenceCount = 0;
            float characterCount = 0;
            float paragraphCount = 1;
            float whitespaceCount = 0;
            float positives = 0;
            float negatives = 0;
            String aminozuur = "";

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
                        char one = line.charAt(i);
                        String s = String.valueOf(one);
//                        System.out.println(s);
                        if (Pattern.matches("[ARNDCQEGHILKMFPSTWYV]+", s)) {
                            aminozuur = "Alle aminozuren zijn juist";
//                            System.out.println("its all good");
                        } else {
                            try {
                                throw new NotAnAA("Dit is een niet bestaand aminozuur: " + s);
                            } catch (NotAnAA notAnAA) {
                                notAnAA.printStackTrace();
                            }
                        }
                        if (s.equals("D") || s.equals("E")) {
                            positives = positives + 1;
                            System.out.println(positives);
                        }
                        if (s.equals("R") || s.equals("K")) {
                            negatives = negatives + 1;
                            System.out.println(negatives);
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


            float procentpol = (positives * 100) / characterCount;
            float procentneg = (100 * negatives) / characterCount;
            polair.setValue((int) procentpol);
            polair.repaint();
            apolaire.setValue((int) procentneg);
            apolaire.repaint();
            String polair = procentpol + "% van de aminozuren is polair en";
            String apoliar = procentneg + "% is apolair ";
//            String Wordcount = "Total word count = " + countWord;
//            String countScentence = "Total number of sentences = " + sentenceCount;
            String countCharacter = "Het totaal aantal aminozuren = " + characterCount;
//            String countParagraph = "Number of paragraphs = " + paragraphCount;
//            String countWithspace = "Total number of whitespaces = " + whitespaceCount;

//            System.out.println(positives);
//            System.out.println(negatives);
//            System.out.println(procentpol);
//            System.out.println(procentneg);
//            System.out.println(polair);
//            System.out.println(apoliar);
//            System.out.println(Wordcount);
//            System.out.println(countScentence);
//            System.out.println(countCharacter);
//            System.out.println(countParagraph);
//            System.out.println(countWithspace);
//            System.out.println(aminozuur);
            String text = countCharacter + "\n" + polair + "\n" + apoliar;
            informatieveld.setText(text);
        }
    }

    static class NotAnAA extends Exception {
        NotAnAA(String err) {
            super(err);
        }
    }
}