
/**
 * Write a description of class HUD here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Color;
import java.awt.Graphics;
public class HUD
{
    // instance variables - replace the example below with your own

    private static int score = 0;
    private static int level = 1;
    
    public void tick()
    {
        //score++;
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.drawString("Level: " + level, 15,15);
        g.drawString("Score: " + score, 15,35);
    }
    public static void changeScore(int newScore)
    {
        score = newScore;
    }
    public static int getScore()
    {
        return score;
    }
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
}
