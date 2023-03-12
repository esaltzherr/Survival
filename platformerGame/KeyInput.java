
/**
 * Write a description of class KeyInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter
{
    // instance variables - replace the example below with your own
    private Handler handler;
    Game game;
    Leaderboard leaderboard;
    String potName;

    public KeyInput(Handler handler, Game game, Leaderboard leaderboard)
    {
        this.handler = handler;
        this.game = game;
        this.leaderboard = leaderboard;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player)
            {
                //input for the player
                if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
                {
                    tempObject.movingUp(true);

                }
                if(key == KeyEvent.VK_A|| key == KeyEvent.VK_LEFT)
                {
                    tempObject.movingLeft(true);
                }
                if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN )
                {
                    tempObject.movingDown(true);
                }
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
                {
                    tempObject.movingRight(true);
                }
                if(key == KeyEvent.VK_SPACE)
                {

                    tempObject.changeShooting(true);
                }
                if(key == KeyEvent.VK_I)
                {
                    System.out.println(handler.object);
                    System.out.println(tempObject.getSpeed());
                }
            }

        }

        if(key == KeyEvent.VK_P)
        {
            if(game.gameState == State.Game)
            {
                game.gameState = State.Shop;
            }
            else if(game.gameState == State.Shop)
            {
                game.gameState = State.Game;
            }
        }
        if(game.gameState == State.End)
        {
            if(key == KeyEvent.VK_BACK_SPACE)
            {
                if(leaderboard.getPotName().length() > 0)
                {
                    leaderboard.remPotName();
                }
            }
            else
            {
                if(key != 16)
                {
                    leaderboard.potName(Character.toString(e.getKeyChar()));
                }
            }

        }

    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
            {
                tempObject.movingUp(false);
            }
            if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
            {
                tempObject.movingLeft(false);
            }
            if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN )
            {
                tempObject.movingDown(false);
            }
            if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
            {
                tempObject.movingRight(false);
            }
            if(key == KeyEvent.VK_SPACE)
            {
                tempObject.changeShooting(false);
            }
        }

    }
}