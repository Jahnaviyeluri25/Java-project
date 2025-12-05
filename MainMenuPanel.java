import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    private GameWindow parent;

    public MainMenuPanel(GameWindow parent) {
        this.parent = parent;
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 192, 203)); // Pink background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Title
        JLabel title = new JLabel("MEMORY FLIP GAME");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(75, 0, 130)); // Indigo color
        add(title, gbc);

        // Start Game Button
        gbc.gridy++;
        JButton startBtn = new JButton("Start Game");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        startBtn.setBackground(new Color(255, 105, 180)); // Hot pink
        startBtn.setForeground(Color.BLACK);
        startBtn.setFocusPainted(false);
        startBtn.setPreferredSize(new Dimension(200, 50));
        startBtn.addActionListener(e -> parent.showScreen(GameWindow.GAME));
        add(startBtn, gbc);

        // Instructions Button
        gbc.gridy++;
        JButton instructionsBtn = new JButton("Instructions");
        instructionsBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        instructionsBtn.setBackground(new Color(255, 255, 0)); // Yellow
        instructionsBtn.setForeground(Color.BLACK);
        instructionsBtn.setFocusPainted(false);
        instructionsBtn.setPreferredSize(new Dimension(200, 50));
        instructionsBtn.addActionListener(e -> showInstructions());
        add(instructionsBtn, gbc);

        // Exit Button
        gbc.gridy++;
        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        exitBtn.setBackground(new Color(255, 99, 71)); // Tomato/Red
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.setPreferredSize(new Dimension(200, 50));
        exitBtn.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(MainMenuPanel.this);
            if (w != null) w.dispose();
        });
        add(exitBtn, gbc);
    }

    private void showInstructions() {
        JOptionPane.showMessageDialog(this,
            "Memory Match Game Instructions:\n\n" +
            "1. Click on tiles to reveal numbers\n" +
            "2. Try to find matching pairs\n" +
            "3. Match all pairs to win\n" +
            "4. The fewer moves, the better!",
            "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
}
