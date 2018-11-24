package Bomber_OOP.actor;

import javax.swing.*;
import java.awt.*;

public class Monster extends Actor
{
    private int heart;

    public Monster(int x, int y, int type, int orient, int speed, int heart, String images)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.runBomb = Bomber.DISALLOW_RUN;
        this.orient = orient;
        this.speed = speed;
        this.heart = heart;
        this.img = new ImageIcon(getClass().getResource(images)).getImage();
        width = img.getWidth(null);
        height = img.getHeight(null) - 5;
    }

    public void drawBoss(Graphics2D g2d)
    {
        if (type == Actor.BOSS)
        {
            g2d.drawImage(img, x, y - 38, null);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x + 13, y - 54, width - 26, 12);
            Image imgHeartBoss = new ImageIcon(getClass().getResource("/Images/heart_boss.png")).getImage();
            int a = 0;
            for (int i = 0; i < (heart - 1) / 250 + 1; i++)
            {
                g2d.drawImage(imgHeartBoss, x + 18 + a, y - 52, null);
                a = a + 10;
            }
        }
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
    public void changeOrient(int orient, int i)
    {
        super.changeOrient(orient, i);
        if (type == Actor.MONSTER)
        {
            switch (orient)
            {
                case LEFT:
                    img = new ImageIcon(getClass().getResource("/Images/balloom_left" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case RIGHT:
                    img = new ImageIcon(getClass().getResource("/Images/balloom_right" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case UP:
                    img = new ImageIcon(getClass().getResource("/Images/balloom_left" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case DOWN:
                    img = new ImageIcon(getClass().getResource("/Images/balloom_right" + (i/1000 % 3) + ".png")).getImage();
                    break;
                default:
                    break;
            }
        }

        else  if (type == Actor.KONDORIA)
        {
            switch (orient)
            {
                case LEFT:
                    img = new ImageIcon(getClass().getResource("/Images/kondoria_left" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case RIGHT:
                    img = new ImageIcon(getClass().getResource("/Images/kondoria_right" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case UP:
                    img = new ImageIcon(getClass().getResource("/Images/kondoria_left" + (i/1000 % 3) + ".png")).getImage();
                    break;
                case DOWN:
                    img = new ImageIcon(getClass().getResource("/Images/kondoria_right" + (i/1000 % 3) + ".png")).getImage();
                    break;
                default:
                    break;
            }
        }


        else
        {
            switch (orient)
            {
                case LEFT:
                    img = new ImageIcon(getClass().getResource("/Images/boss_left.png")).getImage();
                    break;
                case RIGHT:
                    img = new ImageIcon(getClass().getResource("/Images/boss_right.png")).getImage();
                    break;
                case UP:
                    img = new ImageIcon(getClass().getResource("/Images/boss_up.png")).getImage();
                    break;
                case DOWN:
                    img = new ImageIcon(getClass().getResource("/Images/boss_down.png")).getImage();
                    break;
                default:
                    break;
            }
        }
    }
}
