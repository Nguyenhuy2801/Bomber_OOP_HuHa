package Bomber_OOP.LoadMap;
import Bomber_OOP.actor.Box;
import Bomber_OOP.actor.Item;
import Bomber_OOP.actor.Manager;
import Bomber_OOP.actor.Monster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadMap extends Manager
{
    private String pathLevel1 = "src/Map1/Map1.txt";
    private String pathShadow1 = "/Images/shadow1.png";
    private String pathBox1 = "/Images/box1.png";
    private String pathShadow2 = "/Images/shadow2.png";
    private String pathBox2 = "/Images/box2.png";
    private String pathMonster2 = "/Images/balloom_right0.png";
    private String pathItem2 = "/Images/item_bombsize.png";
    private String pathDoor = "/Images/item_door.png";
    private String pathItem1 = "/Images/item_bomb.png";
    private String pathItem3 = "/Images/item_shoe.png";

//    public String strToWrite (int x, int y, int type,  String path)
//    {
//        return (x*45) +
//    }

    public void addItem(int i, int row, int type, String path)
    {
        int itemX = i * 40;
        int itemY = row * 52;

        Item item = new Item(itemX, itemY, type, path);
        this.arrItem.add(item);
    }


    public void LoadMap(String path) throws FileNotFoundException
    {
        File map = new File(path);
        Scanner sc = new Scanner(map);
        String s;
        int row = 0;
        while (sc.hasNextLine())
        {
            s = sc.nextLine();
            for (int i = 0; i < s.length(); i++)
            {
                switch (s.charAt(i))
                {
                    case '#':
                    {
                        //TODO:
                        int shadowX = i * 40;
                        int shadowY = row * 52;
                        int boxX = shadowX;
                        int boxY = shadowY + 15;

                        int type = 1;

                        Box box1 = new Box(boxX, boxY, type, pathBox1);
                        this.arrBox.add(box1);

                        Box shadowBox1 = new Box(shadowX, shadowY, type, pathShadow1);
                        this.arrBox.add(shadowBox1);
                        break;
                    }
                    case '*':
                    {
                        int shadowX = i * 40;
                        int shadowY = row * 52;
                        int boxX = shadowX;
                        int boxY = shadowY + 15;

                        int type = 0;

                        Box box2 = new Box(boxX, boxY, type, pathBox2);
                        this.arrBox.add(box2);

                        Box shadowBox2 = new Box(shadowX, shadowY, type, pathShadow2);
                        this.arrBox.add(shadowBox2);
                        //TODO:

                        break;
                    }
                    case '1':
                    {
                        //TODO:
                        int monsterX = i * 40;
                        int monsterY = row * 52;

                        int type = 2;
                        int orient = 3;
                        int speed = 10;
                        int heart = 1;

                        Monster monster2 = new Monster(monsterX, monsterY, type, orient, speed, heart, pathMonster2);

                        this.arrMonster.add(monster2);
                        break;
                    }
                    case 'f':
                    {
                        //TODO:
                        addItem(i, row, 2, pathItem2);
                        break;
                    }
                    case 'x':
                    {
                        addItem(i, row, 4, pathDoor);
                        //TODO:
                        break;
                    }
                    case 'b':
                    {
                        //TODO:
                        addItem(i, row, 1, pathItem1);
                        break;
                    }
                    case 's':
                    {
                        //TODO:
                        addItem(i, row, 3, pathItem3);
                        break;
                    }
                }
            }

            row++;
        }
    }
}
