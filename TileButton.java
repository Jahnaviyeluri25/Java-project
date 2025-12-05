import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private int row, col;
    private int value;
    private boolean revealed = false;
    private boolean matched = false;

    public TileButton(int r, int c) {
        super("");
        this.row = r; 
        this.col = c;
        setFont(new Font("Arial", Font.BOLD, 24));
        hideValue();
    }

    public void setValue(int v) { this.value = v; }
    public int getValue() { return value; }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public void showValue() {
        revealed = true;
        setText(Integer.toString(value));
        setEnabled(false);
    }

    public void hideValue() {
        if (!matched) {
            revealed = false;
            setText("");
            setEnabled(true);
        }
    }

    public void setMatched(boolean m) {
        matched = m;
        if (m) {
            setBackground(Color.LIGHT_GRAY);
            setEnabled(false);
        }
    }

    public boolean isMatched() { return matched; }
    public boolean isRevealed() { return revealed; }
}
