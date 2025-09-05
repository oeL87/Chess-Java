package chess;

import chess.UI.MainFrame;

/**
 * This will be the starting point to running the program
 * 
 */
public class App 
{
    public static void main( String[] args )
    {
        //TODO: Start the UI, create a board, and start playing
        // GamePanel main = new GamePanel();
        // main.addMouseListener(main);
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame main = new MainFrame();
            main.setVisible(true);
        });
        
    }
}
