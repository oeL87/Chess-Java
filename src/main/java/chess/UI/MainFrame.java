package chess.UI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public static final String TITLE_STRING = "TitleScreen";
    public static final String BOARD_SELECTOR = "BoardSelector";
    public static final String GAME_PANEL = "GamePanel";

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setSize(850, 640);
        setResizable(true);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        TitleScreen title = new TitleScreen(this);
        BoardSelectorPanel selector = new BoardSelectorPanel(this);

        cardPanel.add(title, TITLE_STRING);
        cardPanel.add(selector, BOARD_SELECTOR);
        
        add(cardPanel);

        showScreen(TITLE_STRING);
    }

    public final void showScreen(String name) {
        cardLayout.show(cardPanel, name);
    }
}