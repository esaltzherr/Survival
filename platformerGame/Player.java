
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Rectangle;
public class Player extends GameObject
{
    // instance variables - replace the example below with your own
    Random r = new Random();
    Handler handler;
    Color color;
    Game game;
    HUD hud;
    public Player(float x, float y, ID id, Handler handler, Game game, HUD hud)
    {
        // initialise instance variables
        super(x,y,id);
        
        this.game = game;
        this.hud = hud;
        
        speed = 5;
        color = Color.white;
        maxHealth = 50;
        sizeX = 20;
        sizeY = 20;
        health = maxHealth;
        this.handler = handler;
        setLastVelX(5);
    }

    public void tick()
    {
        Lose();
        
        
        if (getRight()){setVelX(getVelX() + speed);}
        if (getLeft()){setVelX(getVelX() - speed);}
        if (getDown()){setVelY(getVelY() + speed);}
        if (getUp()){setVelY(getVelY() - speed);}
        x += velX;
        y += velY;
        
        if(getVelX() != 0 || getVelY() != 0)
        {
            //setLastVelX(velX); 
            //setLastVelY(velY);  
            if(getVelX() != getLastVelX())
            {
               
               setLastVelX(getVelX()); 
               //System.out.println("change in X");
            }
            if(getVelY() != getLastVelY())
            {
                setLastVelY(getVelY()); 
                
            }
        }
        x = Game.clamp(x, 0 , Game.width - 35);
        y = Game.clamp(y, 0 , Game.height - 57);
        
        
        
        setVelX(0);
        setVelY(0);

        collision();
        handler.addObject(new Trail(x, y, ID.Trail,color, sizeX, sizeY, 0.1f, handler));
        if(getHealth() <= 0)
        {
            //System.out.println("You died");
        }
        if(isShooting())
        {
            Shoot();
        }
        
    }

    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy 
            || tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.BossEnemy
            || tempObject.getID() == ID.WeirdEnemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    //collision code
                    this.changeHealth(this.getHealth() - 1);
                }
            }
        }

    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x,(int)y,(int)sizeX,(int)sizeY);
        Healthbar.render(g, getID(), x, y, maxHealth, health, sizeX, color);

        //show the collision area
        //Graphics2D g2d = (Graphics2D) g;
        //g.setColor(Color.red);
        //g2d.draw(getBounds());

        /*
        g.setColor(Color.green);
        g.fillRect(x - healthLocation(), y - 15, health, 10);
        healthLocation();

        g.setColor(Color.white);
        g.drawRect(x - healthLocation(), y - 15, maxHealth , 10);
         */
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }

    public void Shoot()
    {
        //System.out.println("VelX: " + getVelX() + "VelY: " + getVelY());
        handler.addObject(new Bullet(getX()+(sizeX/2), getY()+(sizeY/2),ID.Bullet, handler, getLastVelX(),  getLastVelY()));
        //System.out.println("X: "+ getLastVelX() + "Y: "+ getLastVelY());
    }

    public void Lose()
    {
        if(getHealth() <= 0)
        {
            game.gameState = State.End;
            handler.clearEverything();
            hud.setLevel(1);
            
        }
    }
    
}
