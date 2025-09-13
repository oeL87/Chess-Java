package chess.UI;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;


public class MainFrame extends JFrame implements MouseInputListener {
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
        GamePanel game = new GamePanel(this);

        cardPanel.add(title, TITLE_STRING);
        cardPanel.add(selector, BOARD_SELECTOR);
        cardPanel.add(game, GAME_PANEL);
        
        add(cardPanel);

        showScreen(TITLE_STRING);
    }

    public CardPanel getCurrentPanel() {
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible() && comp instanceof CardPanel) {
                return (CardPanel) comp;
            }
        }

        return null;
    }

    public final void showScreen(String name) {
        cardLayout.show(cardPanel, name);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        cardPanel.repaint();
        CardPanel panel = getCurrentPanel();
        if (panel != null) {
            panel.handleClick(e);
        }

        
    }
    //TODO: allow user to use mouse to do a bunch of things

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}