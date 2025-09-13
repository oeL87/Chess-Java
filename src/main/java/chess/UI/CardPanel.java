package chess.UI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class CardPanel extends JPanel {
    public CardPanel(CardLayout layout) {
        super(layout);
    }

    public CardPanel() {
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        GradientPaint BACKGROUND = new GradientPaint(0,0, new Color(0x00ffff), getWidth(), getHeight(), new Color(0x000000));
        g2D.setPaint(BACKGROUND);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }

    public abstract void handleClick(MouseEvent e);

    /*
     * class made to test how gradients look as a background
     */
    public static class TestPanel extends CardPanel {
        public TestPanel() {
            add(new JButton("Click Me"));
        }

        @Override
        public void handleClick(MouseEvent e) {
            System.out.println("Clicked");
        }

        @Override
        public void paintComponent(Graphics g) {
            super.repaint();
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.BLACK);
            g2D.fillRect(0, 0, 20, 30);
        }
    }

}