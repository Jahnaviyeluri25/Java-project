import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GamePanel extends JPanel {

    private GameWindow parent;
    private JPanel gridPanel;
    private JLabel movesLabel;

    private TileButton firstSelected = null;
    private TileButton secondSelected = null;

    private int moves = 0;
    private int pairsFound = 0;

    private int rows = 4, cols = 4;
    private TileButton[][] grid;

    private Timer flipBackTimer;

    public GamePanel(GameWindow parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        // Top panel
        JPanel top = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Memory Match", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title, BorderLayout.CENTER);

        movesLabel = new JLabel("Moves: 0", SwingConstants.CENTER);
        movesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        top.add(movesLabel, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);

        // Grid
        gridPanel = new JPanel(new GridLayout(rows, cols, 8, 8));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(gridPanel, BorderLayout.CENTER);

        // Bottom buttons
        JPanel bottom = new JPanel(new FlowLayout());
        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> {
            reset();
            parent.showScreen(GameWindow.MAIN_MENU);
        });
        bottom.add(backBtn);

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> reset());
        bottom.add(resetBtn);

        add(bottom, BorderLayout.SOUTH);

        initializeBoard();

        flipBackTimer = new Timer(800, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flipBackTimer.stop();
                if (firstSelected != null) firstSelected.hideValue();
                if (secondSelected != null) secondSelected.hideValue();
                firstSelected = secondSelected = null;
            }
        });
        flipBackTimer.setRepeats(false);
    }

    private void initializeBoard() {
        gridPanel.removeAll();
        grid = new TileButton[rows][cols];

        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 1; i <= (rows * cols / 2); i++) {
            values.add(i);
            values.add(i);
        }
        Collections.shuffle(values);
        Iterator<Integer> it = values.iterator();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                TileButton tb = new TileButton(r, c);
                tb.setValue(it.next());
                tb.addActionListener(e -> onTileClick(tb));
                grid[r][c] = tb;
                gridPanel.add(tb);
            }
        }

        moves = 0;
        pairsFound = 0;
        movesLabel.setText("Moves: 0");
        revalidate();
        repaint();
    }

    private void onTileClick(TileButton b) {
        if (b.isRevealed() || b.isMatched()) return;
        if (flipBackTimer.isRunning()) return;

        if (firstSelected == null) {
            firstSelected = b;
            b.showValue();
        } 
        else if (secondSelected == null && b != firstSelected) {
            secondSelected = b;
            b.showValue();

            moves++;
            movesLabel.setText("Moves: " + moves);

            if (firstSelected.getValue() == secondSelected.getValue()) {
                firstSelected.setMatched(true);
                secondSelected.setMatched(true);
                firstSelected = secondSelected = null;
                pairsFound++;
                checkGameOver();
            } else {
                flipBackTimer.restart();
            }
        }
    }

    private void checkGameOver() {
        if (pairsFound >= (rows * cols / 2)) {
            JOptionPane.showMessageDialog(this, "You won! Total moves: " + moves);
        }
    }

    public void reset() {
        initializeBoard();
    }
}
