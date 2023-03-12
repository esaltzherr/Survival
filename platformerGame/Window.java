
/**
 * Write a description of class Window here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;



public class Window extends Canvas
{
    // instance variables - replace the example below with your own
    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}

















