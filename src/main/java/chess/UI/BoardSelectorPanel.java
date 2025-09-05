package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardSelectorPanel extends JPanel {
    public BoardSelectorPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Choose a mode", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        add(title, BorderLayout.CENTER);

        JButton pvpButton = new JButton("Player vs Player");
        pvpButton.addActionListener((ActionEvent e) -> {
            frame.showScreen(MainFrame.GAME_PANEL);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent e) -> {
            frame.showScreen(MainFrame.TITLE_STRING);
        }); 

        add(pvpButton);
        add(backButton);
    }
}