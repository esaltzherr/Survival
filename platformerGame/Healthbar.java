
/**
 * Write a description of class HUD here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
public class Healthbar
{
    // instance variables - replace the example below with your own
    public static int Health = 100;

    public void tick()
    {

    }

    public static void render(Graphics g, ID id, float x, float y, int maxHealth, int health, float sizeX, Color color)
    {
        /*
        String type = id.toString();
        if(type.equals("Player"))
        {
        g.setColor(Color.green);
        }   
        else if(type.equals("BasicEnemy"))
        {
        g.setColor(Color.red);
        }
        else
        {
        g.setColor(Color.yellow);
        }
         */
        g.setColor(color);

        String type = id.toString();
        if(type.equals("Player"))
        {
            g.setColor(Color.green);
        }   

        
        g.fillRect((int)x - (int)healthLocation(maxHealth, sizeX), (int)y - 15, health, 10);

        g.setColor(Color.white);
        g.drawRect((int)x - (int)healthLocation(maxHealth, sizeX), (int)y - 15, maxHealth , 10);

    }

    public static float healthLocation(int maxHealth, float sizeX)
    {
        return (maxHealth - sizeX) / 2;
    }
}