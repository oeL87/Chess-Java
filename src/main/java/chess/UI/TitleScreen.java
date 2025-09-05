package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleScreen extends JPanel {
    public TitleScreen(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Chess Game", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        add(title, BorderLayout.CENTER);

        JButton button = new JButton("Start");
        button.addActionListener((ActionEvent e) -> {
            frame.showScreen(MainFrame.BOARD_SELECTOR);
        });
        add(button, BorderLayout.SOUTH);
    }
}