package translate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Aminozuren extends JFrame implements ActionListener {
    private JLabel vertaal = new JLabel("Vertaal:");
    private JLabel vertaling = new JLabel("Vertaling:");
    private JTextField vertaalveld = new JTextField();
    private JButton button = new JButton("Vertaal");
    private JTextField vertalingveld = new JTextField();


    public static void main(String[] args) {
        Aminozuren frame = new Aminozuren();
        frame.setSize(500, 500);
        frame.createGUI();
        frame.setTitle("One2Three");
        frame.setVisible(true);
    }

    private void createGUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(null);

        vertaal.setBounds(150, 25, 50, 20);
        window.add(vertaal);

        vertaalveld.setBounds(225, 25, 100, 20);
        window.add(vertaalveld);

        vertaling.setBounds(150, 150, 100, 20);
        window.add(vertaling);

        vertalingveld.setBounds(225, 150, 100, 20);
        window.add(vertalingveld);

        window.add(button);
        button.setBounds(225, 100, 100, 20);
        button.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String letters = vertaalveld.getText();
        letters = letters.toUpperCase();
        System.out.println(letters);
        try {
            StringBuilder translation = Translator.one2three(letters);
            translation.deleteCharAt(0);
            System.out.println(translation);
            vertalingveld.setText(String.valueOf(translation));
        } catch (NotAnAA notAnAA) {
            notAnAA.printStackTrace();
        }
    }
}