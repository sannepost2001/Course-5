import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class H2O extends JFrame implements ActionListener{

    private JTextField x;
    private JTextField y;
    private JButton button;
    private JPanel frame;
    private JPanel frame2;
    private JLabel labelx;
    private JLabel labely;

    public static void main(String[] args) {
        H2O frame = new H2O();
        frame.setSize(500, 500);
        ImageIcon img = new ImageIcon("water.jpg");
        frame.setIconImage(img.getImage());
        frame.createGUI();
        frame.setTitle("Water molecule ARTIST");
        frame.setVisible(true);
    }

    private void createGUI() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        frame = new JPanel();
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setBackground(Color.WHITE);
        window.add(frame);
        frame2 = new JPanel();
        window.add(frame2);
        frame2.setPreferredSize(new Dimension(300, 200));
        labelx = new JLabel("x:");
        x = new JTextField();
        x.setPreferredSize(new Dimension(50, 20));
        frame2.add(labelx);
        frame2.add(x);
        labely = new JLabel("y:");
        y = new JTextField();
        y.setPreferredSize(new Dimension(50, 20));
        frame2.add(labely);
        frame2.add(y);
        button = new JButton("DRAW");
        frame2.add(button);
        button.addActionListener(this);
    }
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int xcor = Integer.parseInt(x.getText());
        int ycor = Integer.parseInt(y.getText());
        playSound("waterdrop.wav");
        Graphics paper = frame.getGraphics();
        paper.setColor(Color.WHITE);
        paper.fillRect(0,0,300,300);
        paper.setColor(Color.CYAN);
        paper.fillOval(xcor, ycor,100,100);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("water2.png"));
        frame.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(xcor, ycor, size.width, size.height);
    }
}

