
/**
 * Write a description of class Menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;
    private HUD hud;
    private Leaderboard leaderboard;
    private Shop shop;
    private Random r = new Random();

    private int playX = 210, playY = 150, playW = 200, playH = 64;
    private int helpX = 210, helpY = 250, helpW = 200, helpH = 64;
    private int quitX = 210, quitY = 350, quitW = 200, quitH = 64;
    private int backX = 210, backY = 350, backW = 200, backH = 64;
    private int leadX = 450, leadY = 150, leadW = 64, leadH = 64;
    private int endX = 190, endY = 350, endW = 220, endH = 64;
    public Menu(Game game, Handler handler, HUD hud, Leaderboard leaderboard, Shop shop)
    {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.leaderboard = leaderboard;
        this.shop = shop;
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState == State.Menu)
        {
            //Play
            if(mouseOver(mx, my, playX, playY, playW, playH))
            {
                //System.out.println(game.gameState);
                game.gameState = State.Game;
                handler.addObject(new Player(Game.width/2,Game.height/2,ID.Player, handler, game, hud));

                handler.clearEnemys();
                handler.addObject(new Wall(0,0,ID.Wall, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                //handler.clearEnemys();
            }
            //Help
            if(mouseOver(mx, my, helpX, helpY, helpW, helpH))
            {
                game.gameState = State.Help;
            }
            //Back - in Help

            //Quit
            if(mouseOver(mx, my, quitX, quitY, quitW, quitH))
            {
                System.exit(1);
            }
            // leaderboard
            if(mouseOver(mx, my, leadX, leadY, leadW, leadH))
            {
                game.gameState = State.Leaderboard;
            }
        }
        if(game.gameState == State.Help)
        {
            if(mouseOver(mx, my, backX, backY, backW, backH))
            {
                game.gameState = State.Menu;
            }
        }
        if(game.gameState == State.End)
        {
            if(mouseOver(mx, my, endX, endY, endW, endH))
            {
                game.gameState = State.Menu;
                leaderboard.addScore(leaderboard.getPotName(), hud.getScore());
                hud.changeScore(0);
                shop.reset();
            }
        }
        if(game.gameState == State.Leaderboard)
        {
            if(mouseOver(mx, my, backX, backY, backW, backH))
            {
                game.gameState = State.Menu;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {

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

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        if(game.gameState == State.Menu)
        {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);

            g.setColor(Color.white);
            g.drawRect(playX,playY,playW,playH);
            g.drawString("Play", 270, 190);

            g.drawRect(helpX,helpY,helpW,helpH);
            g.drawString("Help", 270, 290);

            g.drawRect(quitX,quitY,quitW,quitH);
            g.drawString("Quit", 270, 390);

            g.drawRect(leadX,leadY,leadW,leadH);
            //g.drawString("LeaderBoard", 270, 190);
        }
        else if (game.gameState == State.Help)
        {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt2);

            g.drawString("WASD or Arrow Keys to move", 100, 150);
            g.drawString("Space to Shoot", 200, 200);
            g.drawString("P to toggle Shop", 190, 250);

            
            
            g.setColor(Color.cyan);
            g.drawRect(backX, backY, backW, backH);
            g.drawString("Back", 270, 390);
        }
        else if (game.gameState == State.End)
        {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.cyan);
            g.drawString("Game Over", 170, 70);

            g.setFont(fnt2);

            g.drawString("You ended with a score of: " + hud.getScore(), 100, 250);
            g.drawString("Name: " + leaderboard.getPotName(), 200, 300);
            g.setColor(Color.white);
            g.drawRect(endX, endY, endW, endH);
            g.drawString("Back to Menu", 200, 390);
        }
        else if(game.gameState == State.Leaderboard)
        {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            //Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Leaderboard", 170, 70);

            g.setFont(fnt2);
            for(int i = 0; i < leaderboard.getLength(); i ++)
            {
                g.drawString(leaderboard.getName(i), 150, 130 +(i * 30));
                g.drawString("" + leaderboard.getScore(i), 400, 130 +(i * 30));
            }

            g.setColor(Color.cyan);
            g.drawRect(backX, backY, backW, backH);
            g.drawString("Back", 270, 390);
        }
    }

}
