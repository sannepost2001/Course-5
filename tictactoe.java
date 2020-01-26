import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


@SuppressWarnings("serial")
public class tictactoe extends JFrame {
    // grote spelboord
    public static final int ROWS = 3;
    public static final int COLS = 3;

    // vast staande afmetingen in het spelboord
    public static final int CELL_SIZE = 200; // grote vierkant
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // tekenen canvas
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;                   // lijn dikte
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // helft lijn dikte
    // Symbolen weergegeven in de spel vierkanten, met padding van de grens
    public static final int CELL_PADDING = CELL_SIZE / 6;
    public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // lengte en hoogte
    public static final int SYMBOL_STROKE_WIDTH = 8; // pen lijn dikte

    // class voor verschillende momenten van het spel
    public enum GameState {
        PLAYING, DRAW, CROSS_WON, NOUGHT_WON
    }
    private GameState currentState;  // tijd waar het spel zich nu in verkeerd

    // class voor de huidige spelen
    public enum Seed {
        EMPTY, CROSS, NOUGHT
    }
    private Seed currentPlayer;  // huidige speler

    private Seed[][] board   ; // spelboord
    private DrawCanvas canvas; // canvas voor spelboord
    private JLabel statusBar;  // wie er aan de beurt is

    public tictactoe() {
        canvas = new DrawCanvas();  // canvas voor spel
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Het canvas (JPanel) start een MouseEvent bij een mouse-click
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
                int mouseX = e.getX();
                int mouseY = e.getY();
                // krijg row en column
                int rowSelected = mouseY / CELL_SIZE;
                int colSelected = mouseX / CELL_SIZE;

                if (currentState == GameState.PLAYING) {
                    if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                            && colSelected < COLS && board[rowSelected][colSelected] == Seed.EMPTY) {
                        board[rowSelected][colSelected] = currentPlayer; // maak zet
                        updateGame(currentPlayer, rowSelected, colSelected); // update in wel moment van het spel je zit
                        // Switch speler
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                    }
                } else {       // game over
                    initGame(); // restart het spel
                }
                // Refresh het canvas
                repaint();
            }
        });

        // Setup de status balk (JLabel) om het status van het spel weer te geven
        statusBar = new JLabel("  ");
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // stop alles in een JFrame
        setTitle("Tic Tac Toe");
        setVisible(true);  // laat deze JFrame zien

        board = new Seed[ROWS][COLS]; //
        initGame(); // start board en instelling op
    }

    public void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = Seed.EMPTY; // alles spelblokken zijn leeg
            }
        }
        currentState = GameState.PLAYING; // klaar om te spelen
        Random rand = new Random(); // random zorgt ervoor dat niet altijd dezelfde persoon begint
        int n = rand.nextInt(50);
        if (25 <= n) {
            currentPlayer = Seed.NOUGHT;       // rondje begint
        } else {
            currentPlayer = Seed.CROSS;       // kruis begint
        }
    }


    public void updateGame(Seed theSeed, int rowSelected, int colSelected) {
        if (hasWon(theSeed, rowSelected, colSelected)) {  // check of er al iemand heeft gewonnen
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (isDraw()) {
            currentState = GameState.DRAW;
        }
        // anders blijft gamestate hetzelfde en gaat het spel gewoon door
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        return (board[rowSelected][0] == theSeed  // 3-in-de-row
                && board[rowSelected][1] == theSeed
                && board[rowSelected][2] == theSeed
                || board[0][colSelected] == theSeed      // 3-in-de-column
                && board[1][colSelected] == theSeed
                && board[2][colSelected] == theSeed
                || rowSelected == colSelected            // 3-in-de-diagonal
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                || rowSelected + colSelected == 2  // 3-in-de-opposite-diagonal
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }

    class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {  // aangeroepen via repaint()
            super.paintComponent(g);    // vul background
            setBackground(Color.BLUE); // zet background color

            // teken de spel lijnen
            g.setColor(Color.black);
            for (int row = 1; row < ROWS; ++row) {

                g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                        CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
            }
            for (int col = 1; col < COLS; ++col) {
                g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                        GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
            }

            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));  // Graphics2D only
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    int x1 = col * CELL_SIZE + CELL_PADDING;
                    int y1 = row * CELL_SIZE + CELL_PADDING;
                    if (board[row][col] == Seed.CROSS) {
                        g2d.setColor(Color.RED);
                        int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                        int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                        g2d.drawLine(x1, y1, x2, y2);
                        g2d.drawLine(x2, y1, x1, y2);
                    } else if (board[row][col] == Seed.NOUGHT) {
                        g2d.setColor(Color.BLACK);
                        g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                    }
                }
            }

            // Print status-bar bericht
            if (currentState == GameState.PLAYING) {
                statusBar.setForeground(Color.BLACK);
                if (currentPlayer == Seed.CROSS) {
                    statusBar.setText("X's beurt");
                } else {
                    statusBar.setText("O's beurt");
                }
            } else if (currentState == GameState.DRAW) {
                statusBar.setForeground(Color.RED);
                statusBar.setText("Het is gelijk spel! Click om nogmaals te spelen.");
            } else if (currentState == GameState.CROSS_WON) {
                statusBar.setForeground(Color.RED);
                statusBar.setText("'X' heeft gewonnen! Click om nogmaals te spelen.");
            } else if (currentState == GameState.NOUGHT_WON) {
                statusBar.setForeground(Color.RED);
                statusBar.setText("'O' heeft gewonnen! Click om nogmaals te spelen.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new tictactoe();
            }
        });
    }
}