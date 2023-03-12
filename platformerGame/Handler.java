
/**
 * Write a description of class Handler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.util.LinkedList;


public class Handler
{
    // instance variables - replace the example below with your own
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }
    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    
    }
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    public void clearEnemys()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy 
            || tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.SmartEnemy
            || tempObject.getID() == ID.WeirdEnemy || tempObject.getID() == ID.HomingBullet
            || tempObject.getID() == ID.MenuParticle)
            {
                removeObject(tempObject);
                i--;
            }
            
            //if(tempObject.getID() == ID.MenuParticle)
            //{
                //removeObject(tempObject);
            //}
        }
    }
    public void clearEverything()
    {
        for(int i = 0; i < object.size(); i++)
        {
            removeObject(object.get(i));
            i--;
        }
    }
    public Boolean checkEnemys()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy 
            || tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.SmartEnemy
            || tempObject.getID() == ID.WeirdEnemy )
            
            {
                return true;
            }   
            
            
            //if(tempObject.getID() == ID.MenuParticle)
            //{
                //removeObject(tempObject);
            //}
        }
        return false;
    }
    
}
