import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;
    public static final String MAIN_MENU = "MainMenu";
    public static final String GAME = "Game";

    public GameWindow() {
        super("Memory Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 720);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainMenuPanel menu = new MainMenuPanel(this);
        GamePanel game = new GamePanel(this);

        mainPanel.add(menu, MAIN_MENU);
        mainPanel.add(game, GAME);

        setContentPane(mainPanel);
        showScreen(MAIN_MENU);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
}
