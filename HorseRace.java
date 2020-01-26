import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;


public class HorseRace {
    private JFrame frame;
    private JProgressBar horse1 = new JProgressBar(0,100);
    private JProgressBar horse2 = new JProgressBar(0,100);
    private JProgressBar horse3 = new JProgressBar(0,100);
    private JProgressBar horse4 = new JProgressBar(0,100);
    private JProgressBar horse5 = new JProgressBar(0,100);
    private JLabel msg = new JLabel("");
    private static boolean runRaceButtonPressed = false;
//    private static boolean resetRaceButtonPressed = false;
    private static int winningHorse = 0;
    private static boolean winner = false;

    private synchronized void finish(int i){
        msg.setVisible(true);
        msg.setText("Horse #" +winningHorse+ " won the race.");
        if (i == 100){
            winner = true;
            System.out.println("Horse #" +winningHorse+ " won the race.");
        }
        frame.getContentPane().add(msg);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HorseRace window = new HorseRace();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private HorseRace() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100,100,598,430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        horse1.setStringPainted(true);
        horse1.setForeground(Color.PINK);
        horse1.setBounds(150,129,259,14);
        frame.getContentPane().add(horse1);

        horse2.setStringPainted(true);
        horse2.setForeground(Color.orange);
        horse2.setBounds(150,159,259,14);
        frame.getContentPane().add(horse2);

        horse3.setStringPainted(true);
        horse3.setForeground(Color.yellow);
        horse3.setBounds(150,189,259,14);
        frame.getContentPane().add(horse3);

        horse4.setStringPainted(true);
        horse4.setForeground(Color.GREEN);
        horse4.setBounds(150,219,259,14);
        frame.getContentPane().add(horse4);

        horse5.setStringPainted(true);
        horse5.setForeground(Color.red);
        horse5.setBounds(150,249,259,14);
        frame.getContentPane().add(horse5);

        msg.setBounds(85,100,410,14);
        msg.setVisible(false);
        frame.getContentPane().add(msg);

        JButton btnStart = new JButton("Run Race");
        btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnStart.addActionListener(new RunRace());
        btnStart.setBounds(50,287,155,40);
        frame.getContentPane().add(btnStart);

//        JButton btnReset = new JButton("Reset Race");
//        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        btnReset.addActionListener(new ResetRace());
//        btnReset.setBounds(205,287,155,40);
//        frame.getContentPane().add(btnReset);

        JButton btnQuit = new JButton("Quit Race");
        btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnQuit.addActionListener(new QuitRace());
        btnQuit.setBounds(360,287,155,40);
        frame.getContentPane().add(btnQuit);
    }

    class RunRace implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
            if(!runRaceButtonPressed){
                msg.setVisible(false);
//                resetRaceButtonPressed = false;
                runRaceButtonPressed = true;
                H1 horse1 = new H1();
                horse1.start();
                H2 horse2 = new H2();
                horse2.start();
                H3 horse3 = new H3();
                horse3.start();
                H4 horse4 = new H4();
                horse4.start();
                H5 horse5 = new H5();
                horse5.start();
            }
        }
    }
//    class ResetRace implements ActionListener{
//        public void actionPerformed(ActionEvent arg0) {
//            if (!resetRaceButtonPressed) {
//                msg.setVisible(false);
//                resetRaceButtonPressed = true;
//                runRaceButtonPressed = true;
//                winner = false;
//                H1 horse1 = new H1();
//                horse1.reset();
//                H2 horse2 = new H2();
//                horse2.reset();
//                H3 horse3 = new H3();
//                horse3.reset();
//                H4 horse4 = new H4();
//                horse4.reset();
//                H5 horse5 = new H5();
//                horse5.reset();
//            }
//        }
//    }

    static class QuitRace implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    class H1 extends Thread{
        void reset(){
            horse1.setValue(0);
            horse1.repaint();
        }
        public void run(){
            for (int i=0;i<101;i++){
                if(winner){
                    break;
                }
                horse1.setValue(i);
                horse1.repaint();
                if(i==100){
                    winningHorse = 1;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
    class H2 extends Thread{
        void reset(){
            horse2.setValue(0);
            horse2.repaint();
        }
        public void run(){
            for (int i=0;i<101;i++){
                if(winner){
                    break;
                }
                horse2.setValue(i);
                horse2.repaint();
                if(i==100){
                    winningHorse = 2;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
    class H3 extends Thread{
        void reset(){
            horse3.setValue(0);
            horse3.repaint();
        }
        public void run(){
            for (int i=0;i<101;i++){
                if(winner){
                    break;
                }
                horse3.setValue(i);
                horse3.repaint();
                if(i==100){
                    winningHorse = 3;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
    class H4 extends Thread{
        void reset(){
            horse4.setValue(0);
            horse4.repaint();
        }
        public void run(){
            for (int i=0;i<101;i++){
                if(winner){
                    break;
                }
                horse4.setValue(i);
                horse4.repaint();
                if(i==100){
                    winningHorse = 4;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
    class H5 extends Thread{
        void reset(){
            horse5.setValue(0);
            horse5.repaint();
        }
        public void run(){
            for (int i=0;i<101;i++){
                if(winner){
                    break;
                }
                horse5.setValue(i);
                horse5.repaint();
                if(i==100){
                    winningHorse = 5;
                    finish(i);
                }
                try{
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits())%60);
                }catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
}
