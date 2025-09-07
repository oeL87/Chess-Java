package chess.UI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public abstract class CardPanel extends JPanel {
    public static final Color BACKGROUND = new Color(0xff4e6a6e);

    public CardPanel(CardLayout layout) {
        super(layout);
    }

    public CardPanel() {
        super();
    }

    public abstract void handleClick(MouseEvent e);
}