package chess.UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BoardSelectorPanel extends CardPanel {
    public BoardSelectorPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Choose a mode", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        add(title, BorderLayout.CENTER);

        JButton pvpButton = new JButton("Player vs Player");
        pvpButton.addActionListener((ActionEvent e) -> {
            frame.showScreen(MainFrame.GAME_PANEL);
            System.out.println("showing game");
        });

        // JButton backButton = new JButton("Back");
        // backButton.addActionListener((ActionEvent e) -> {
        //     frame.showScreen(MainFrame.TITLE_STRING);
        // }); 

        add(pvpButton);
        // add(backButton);
    }

    @Override
    public void handleClick(MouseEvent e) {

    }
}