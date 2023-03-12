
/**
 * Write a description of class Shop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
public class Shop extends MouseAdapter
{
    Handler handler;
    HUD hud;
    GameObject player;
    private int healthX = 100, healthY = 100, healthW = 100, healthH = 80;
    private int speedX = 250, speedY = 100, speedW = 100, speedH = 80;
    private int refillX = 400, refillY = 100, refillW = 100, refillH = 80;
    private int costH = 100, costS = 100, costR = 100;
    public Shop(Handler handler, HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
        
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 0, 48));
        g.drawString("Shop", Game.width/2-100, 50);
        
        g.setFont(new Font("arial", 0, 20));
        g.setColor(Color.orange);
        g.drawString("Points: " + hud.getScore(), 10,30);
        
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 12));
        
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + costH, 110, 140);
        g.drawRect(healthX, healthY, healthW, healthH);
        
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + costS, 260, 140);
        g.drawRect(speedX, speedY, speedW, speedH);
        
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + costR, 410, 140);
        g.drawRect(refillX, refillY, refillW, refillH);
    }
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player)
            {
                player = tempObject;
            }
        }
        if(mouseOver(mx,my, healthX, healthY, healthW, healthH))
        {
            if(hud.getScore() >= costH)
            {
                hud.changeScore(hud.getScore() - costH);
                costH*=2;
                costR+= 100;
                player.changeMaxHealth(player.getMaxHealth() + 10);
            }
        }
        if(mouseOver(mx,my, speedX, speedY, speedW, speedH))
        {
            if(hud.getScore() >= costS)
            {
                hud.changeScore(hud.getScore() - costS);
                costS*=2;
                player.changeSpeed(player.getSpeed() + 1);
            }
        }
        if(mouseOver(mx,my, refillX, refillY, refillW, refillH))
        {
            if(hud.getScore() >= costR)
            {
                hud.changeScore(hud.getScore() - costR);
                //costR*=2;
                player.changeHealth(player.getMaxHealth());
            }
        }
        
    }
    private Boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if(mx > x && mx < x + width)
        {
            if(my > y && my < y + height)
            {
                return true;
            }
        }
        return false;

    }
    public void reset()
    {
       costH = 100;
       costS = 100;
       costR = 100; 
    }
}
