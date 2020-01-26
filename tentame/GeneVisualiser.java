//package tentame;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileSystemView;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * @author Sanne Post
// * @version 1
// * @since 1
// */
//
//
//public class GeneVisualiser extends JFrame {
//    private JPanel panel = new JPanel();
//
//    private JLabel bestand = new JLabel("Bestand:");
//
//    private JTextField kies = new JTextField();
//
//    private JButton blader = new JButton("Blader");
//    private JButton analyseer = new JButton("Analyseer");
//
//    private JTextArea informatieveld = new JTextArea("", 20, 20);
//
//    public File file;
//
//    public static void main(String[] args) {
//        GeneVisualiser frame = new GeneVisualiser();
//        frame.setSize(700, 350);
//        frame.createGUI();
//        frame.setTitle("Translator");
//        frame.setVisible(true);
//    }
//
//    /**
//     * genereerd gui
//     */
//    private void createGUI() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        Container window = getContentPane();
//        window.setLayout(null);
//
//        bestand.setBounds(50, 25, 300, 20);
//        window.add(bestand);
//
//        kies.setBounds(120, 25, 200, 20);
//        window.add(kies);
//
//        window.add(blader);
//        blader.setBounds(330, 25, 150, 20);
//        blader.addActionListener(new GeneVisualiser.Blader());
//
//        window.add(analyseer);
//        analyseer.setBounds(500, 25, 150, 20);
//        analyseer.addActionListener(new Analyseer());
//
//        informatieveld.setBounds(50, 75, 600, 100);
//        informatieveld.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
//        window.add(informatieveld);
//
//        panel.setBounds(50, 200, 600, 100);
//        panel.setLayout(null);
//        panel.setBackground(Color.white);
//        panel.setOpaque(true);
//        window.add(panel);
//    }
//
//    /**
//     * wanneer er op button blader is gedrukt dan begint deze class.
//     * <p>
//     * de klas opent een filechooser, in deze kan het document uitgekozen worden. Deze wordt hierna ingelezen.
//     */
//
//    class Blader implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            JFileChooser kiesFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//            kiesFile.setDialogTitle("Choose a directory to save your file: ");
//            kiesFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//            int teruggeefWaarde = kiesFile.showSaveDialog(null);
//            if (teruggeefWaarde == JFileChooser.APPROVE_OPTION) {
//                if (kiesFile.getSelectedFile().isFile()) {
//
//                    kies.setText(String.valueOf(kiesFile.getSelectedFile()));
//                    file = kiesFile.getSelectedFile();
//                }
//            }
//        }
//    }
//
//    /**
//     * wanneer er op button Analyseer is gedrukt dan begint deze class.
//     * <p>
//     * van het document wordt een 2d lijst gemaakt deze woordt daarna gebruikt om de langste kortste en gemiddelde
//     * lengte van het gen te meten. in deze klas vind ook de visualisatie plaats. ik gebeurd als laatste als al de
//     * gegevens zijn opgehaald.
//     */
//
//    class Analyseer implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            System.out.println("analyseer");
//            Chromosoom c1 = new Chromosoom(1);
//            int start = 0;
//            int end = 0;
//            int lengtechr = 0;
//            int lengte = 0;
//            int aantalchr = 0;
//            int hoogstelengte = 0;
//            int laagstelengte = 0;
//
//            int start2 = 0;
//            int end2 = 0;
//            int lengtechr2 = 0;
//            int aantalchr2 = 0;
//            int gemiddelde = 0;
//            int gemiddelde2 = 0;
//            int hoogstelengte2 = 0;
//            int laagstelengte2 = 0;
//
//            ArrayList<Integer> lengteschrallemaal = new ArrayList<Integer>(0);
//            ArrayList<Integer> lengteschrallemaal2 = new ArrayList<Integer>(0);
//
//            try {
//                Scanner sc = null;
//                try {
//                    BufferedReader br = new BufferedReader(new FileReader(file));
//                    sc = new Scanner(new BufferedReader(new FileReader(file)));
//                    while (br.readLine() != null) {
//                        lengte++;
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                int rows = lengte;
//                int columns = 7;
//                String[][] myArray = new String[rows][columns];
//
//                Chromosoom[] ChromosoomArray = new Chromosoom[26];
//
//                assert sc != null;
//                while (sc.hasNextLine()) {
//                    for (String[] strings : myArray) {
//                        String[] line = sc.nextLine().trim().split("\t");
//                        System.arraycopy(line, 0, strings, 0, line.length);
//                    }
//                }
//                for (int i = 0; i < myArray.length; i++) {
//                    if (myArray[i][4].equals("1")) {
//                        start = 0;
//                        end = 0;
//                        lengtechr = 0;
//                        aantalchr++;
//                        start = Integer.parseInt(myArray[i][1]);
//                        end = Integer.parseInt(myArray[i][2]);
//                        lengtechr = end - start;
//                        if(hoogstelengte < lengtechr ){
//                        hoogstelengte = lengtechr;}
//                        if(laagstelengte < lengtechr ){
//                            laagstelengte = lengtechr;}
//                        gemiddelde = gemiddelde + lengtechr;
//
//
//                    }
//                    if (myArray[i][4].equals("2")) {
//                        start2 = 0;
//                        end2 = 0;
//                        lengtechr2 = 0;
//                        aantalchr2++;
//                        start2 = Integer.parseInt(myArray[i][1]);
//                        end2 = Integer.parseInt(myArray[i][2]);
//                        lengtechr2 = end2 - start2;
//                        if(hoogstelengte2 < lengtechr ){
//                            hoogstelengte2 = lengtechr;}
//                        if(laagstelengte2 < lengtechr ){
//                            laagstelengte2 = lengtechr;}
//
//                        gemiddelde2 = gemiddelde2 + lengtechr2;
////                    if (myArray[i][4] == "MT") {
////                        myArray[i][4] = "25";
////                    }
////                    if (myArray[i][4] == "X") {
////                        myArray[i][4] = "23";
////                    }
////                    if (myArray[i][4] == "Y") {
////                        myArray[i][4] = "24";
////                    }
////                    if (!Pattern.matches("[1234567890]", myArray[i][4])) {
////                        myArray[i][4] = "000000000";
////                    }
////                    if (ChromosoomArray[Integer.parseInt(myArray[i][4])] == null) {
////                        System.out.println(Integer.parseInt(myArray[i][4]));
////                        ChromosoomArray[Integer.parseInt(myArray[i][4])] = new Chromosoom(Integer.parseInt(myArray[i][4]));
////                    }
////                    start = Integer.parseInt(myArray[i][1]);
////                    end = Integer.parseInt(myArray[i][2]);
////                    lengtechr = end - start;
////                    ChromosoomArray[Integer.parseInt(myArray[i][4])].addGene(lengtechr);
////                }
//
//                    }
//                }
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            Graphics g = panel.getGraphics();
//            g.fillRect(50, 10, gemiddelde / 10, 25);
//            g.fillRect(50, 60, gemiddelde2 / 10, 25);
//
//            informatieveld.setText("Chr1: avg " + gemiddelde + " max " + hoogstelengte + " min " + laagstelengte + "\n"
//                    +"Chr2: avg " + gemiddelde2 + " max " + hoogstelengte2 + " min " + laagstelengte2);
//        }
//    }
//}
//
//class CorruptFasta extends Throwable {
//    CorruptFasta(String corruption_found) {
//        JOptionPane.showConfirmDialog(null, "A corruption in your fasta file has been found"
//                , "Corrupt FASTA", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
//
//    }
//}



package tentame;


import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Sanne Post
 * @version 1
 * @since 1
 */


public class GeneVisualiser extends JFrame {
    private JPanel panel = new JPanel();

    private JLabel bestand = new JLabel("Bestand:");

    private JTextField kies = new JTextField();

    private JButton blader = new JButton("Blader");
    private JButton analyseer = new JButton("Analyseer");

    private JTextArea informatieveld = new JTextArea("", 20, 20);

    public File file;
    private String text = "";

    public static void main(String[] args) {
        GeneVisualiser frame = new GeneVisualiser();
        frame.setSize(700, 350);
        frame.createGUI();
        frame.setTitle("Translator");
        frame.setVisible(true);
    }

    /**
     * genereerd gui
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(null);


        bestand.setBounds(50, 25, 300, 20);
        window.add(bestand);


        kies.setBounds(120, 25, 200, 20);
        window.add(kies);


        window.add(blader);
        blader.setBounds(330, 25, 150, 20);
        blader.addActionListener(new GeneVisualiser.Blader());


        window.add(analyseer);
        analyseer.setBounds(500, 25, 150, 20);
        analyseer.addActionListener(new Analyseer());


        informatieveld.setBounds(50, 75, 600, 100);
        informatieveld.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        window.add(informatieveld);

//        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);


        panel.setBounds(50, 200, 600, 100);
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setOpaque(true);
//        panel.setBorder(border);
        window.add(panel);

    }

    /**
     * wanneer er op button blader is gedrukt dan begint deze class.
     * <p>
     * de klas opent een filechooser, in deze kan het document uitgekozen worden. Deze wordt hierna ingelezen.
     */

    class Blader implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
//            StringBuilder Header = new StringBuilder();
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
            System.out.println(file);
        }
    }

    /**
     * wanneer er op button Analyseer is gedrukt dan begint deze class.
     * <p>
     * van het document wordt een 2d lijst gemaakt deze woordt daarna gebruikt om de langste kortste en gemiddelde
     * lengte van het gen te meten. in deze klas vind ook de visualisatie plaats. ik gebeurd als laatste als al de
     * gegevens zijn opgehaald.
     */
    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    class Analyseer implements ActionListener {
        Map<Integer, Chromosoom> map = new HashMap<Integer, Chromosoom>();


        @Override

        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Analyseer.");
            String st = "";
            int start = 0;
            int end = 0;
            int ChromNr=0;
            int lengtechr = 0;
            int lengte = 0;
            try {
                Scanner sc = null;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    sc = new Scanner(new BufferedReader(new FileReader(file)));
                    while ((st = br.readLine()) != null) {
                        lengte++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int rows = lengte;
                int columns = 7;
                String[][] myArray = new String[rows][columns];
                Chromosoom[] ChromosoomArray = new Chromosoom[26];

                assert sc != null;
                while (sc.hasNextLine()) {
                    for (String[] strings : myArray) {
                        String[] line = sc.nextLine().trim().split("\t");
                        System.arraycopy(line, 0, strings, 0, line.length);
                    }
                }
                for (int i = 0; i < myArray.length; i++) {
                    //System.out.println("Values at arr[" + i + "] chr is " + myArray[i][4]);
                    System.out.println(myArray[i][4]);

                    if (isNumeric(myArray[i][4])) {
                        start = Integer.parseInt(myArray[i][1]);
                        end = Integer.parseInt(myArray[i][2]);
                        ChromNr=Integer.parseInt(myArray[i][4]);
                        lengtechr = end - start;
                        if(!map.containsKey(ChromNr)){
                            map.put(ChromNr,new Chromosoom(ChromNr));
                        }
                        Chromosoom c =map.get(ChromNr);
                        c.addGene(lengtechr);
                    }
//                }
                }
                // System.out.println(Arrays.deepToString(myArray));
                for (Integer key : map.keySet()) {
                    Chromosoom c = map.get(key);
                    System.out.println("Key = " + key + ", Value = " + c._print());
                }

                System.out.println(Arrays.deepToString(ChromosoomArray));
                //System.out.println(c1.getAverageGenLength());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}