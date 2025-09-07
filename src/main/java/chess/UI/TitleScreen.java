package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class TitleScreen extends CardPanel {
    public final Font titleFont = new Font("Yu Gothic UI Semibold", Font.BOLD, 40);
    public TitleScreen(MainFrame frame) {
        setLayout(new BorderLayout());

        JButton button = new JButton("Start");
        button.setSize(100, 50);
        button.addActionListener((ActionEvent e) -> {
            frame.showScreen(MainFrame.BOARD_SELECTOR);
        });
        add(button, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        String title = "Chess Game";
        g2D.setBackground(CardPanel.BACKGROUND);
        g2D.clearRect(0, 0, getWidth(), getHeight());
        g2D.setFont(titleFont);
        g2D.drawString(title, (getWidth() - g2D.getFontMetrics().stringWidth(title))/2, getHeight()/2);
    }

    @Override
    public void handleClick(MouseEvent e) {

    }
}