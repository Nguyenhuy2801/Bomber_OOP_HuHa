package Bomber_OOP.actor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Bomber extends Actor
{
    public static int ALLOW_RUN = 0;
    public static int DISALLOW_RUN = 1;
    protected int sizeBomb, quantityBomb, status, score, heart;


    public Bomber(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.runBomb = DISALLOW_RUN;
        this.orient = orient;
        this.speed = speed;
        this.sizeBomb = sizebomb;
        this.quantityBomb = quantityBomb;
        this.heart = 3;
        this.score = 0;
        this.status = ALIVE;
        this.img = new ImageIcon(getClass().getResource("/Images/player_down_0.png")).getImage();
        width = img.getWidth(null);
        height = img.getHeight(null) - 5;
    }

    public void setNew(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.status = ALIVE;
        this.img = new ImageIcon(getClass().getResource("/Images/player_down_0.png")).getImage();
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void setImg(Image img)
    {
        this.img = img;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getQuantityBomb()
    {
        return quantityBomb;
    }

    public void setQuantityBomb(int quantityBomb)
    {
        if (quantityBomb > 5)
        {
            return;
        }
        this.quantityBomb = quantityBomb;
    }

    public int getSizeBomb()
    {
        return sizeBomb;
    }

    public void setSizeBomb(int sizeBomb)
    {
        if (sizeBomb > 3)
        {
            return;
        }
        this.sizeBomb = sizeBomb;
    }

    public int getType()
    {
        return type;
    }

    public int getHeart()
    {
        return heart;
    }

    public void setHeart(int heart)
    {
        this.heart = heart;
    }

    @Override
    public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox)
    {
        if (status == DEAD)
        {
            return false;
        }
        return super.move(count, arrBomb, arrBox);
    }

    @Override
    public void changeOrient(int orient, int i)
    {
        if (this.status == DEAD)
        {
            return;
        }
        super.changeOrient(orient, i);
        switch (orient)
        {
            case LEFT:

                img = new ImageIcon(getClass().getResource("/Images/player_left_" + ((i/50)%3) + ".png")).getImage();
                break;

            case RIGHT:


                img = new ImageIcon(getClass().getResource("/Images/player_right_" + ((i/50)%3) + ".png")).getImage();
                break;

            case UP:

                img = new ImageIcon(getClass().getResource("/Images/player_up_" + ((i/50)%3) + ".png")).getImage();
                break;

            case DOWN:
                img = new ImageIcon(getClass().getResource("/Images/player_down_" + ((i/50)%3) + ".png")).getImage();
                break;

            default:
                break;
        }
    }

    public boolean isImpactBomberVsActor(Actor actor)
    {
        if (status == DEAD)
        {
            return false;
        }
        Rectangle rec1 = new Rectangle(x, y, width, height);
        Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
        return rec1.intersects(rec2);
    }

}
