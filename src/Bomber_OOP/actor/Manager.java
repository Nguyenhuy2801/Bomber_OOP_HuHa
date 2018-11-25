package Bomber_OOP.actor;

import sound.GameSound;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Manager
{
    public static int animation = 0;
    private Random random = new Random();
    public Bomber mBomber;
    public ArrayList<Box> arrBox;
    public ArrayList<Box> arrShawDow;
    public ArrayList<Bomb> arrBomb;
    public ArrayList<BombBang> arrBombBang;
    public ArrayList<Monster> arrMonster;
    public ArrayList<Item> arrItem;
    public ArrayList<HightScore> arrHightScore;
    public String Background;
    private int level = 1;
    private int nextLevel = 0;
    private int status = 0;

    public Manager() throws FileNotFoundException
    {
        innitManager();
    }

    public void innitManager() throws FileNotFoundException
    {
        switch (level)
        {
            case 1:
                mBomber = new Bomber(45, 67, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
                innit(pathLeve1);
                nextLevel = 0;
                status = 0;
                break;
            case 2:
                mBomber.setNew(315, 270);
                innit(pathLeve2);
                nextLevel = 0;
                status = 0;
                break;
            case 3:
                mBomber.setNew(630, 260);
                innit(pathLeve3);
                nextLevel = 0;
                status = 0;
                break;

            default:
                break;
        }

    }

    public void innit(String pathLevel) throws FileNotFoundException
    {
        arrBox = new ArrayList<Box>();
        arrShawDow = new ArrayList<Box>();
        arrBomb = new ArrayList<Bomb>();
        arrBombBang = new ArrayList<BombBang>();
        arrMonster = new ArrayList<Monster>();
        arrItem = new ArrayList<Item>();
        arrHightScore = new ArrayList<HightScore>();
        LoadMap(pathLevel);
//        innitArrBox(pathBox, pathShadow);
//        initarrMonster(pathMonster);
//        innitArrItem(pathItem);
        innitArrHightScore("src/hightscore/HightScore.txt");
    }

//    public void innitArrItem(String path)
//    {
//        try
//        {
//            FileReader file = new FileReader(path);
//            BufferedReader input = new BufferedReader(file);
//            String line;
//            while ((line = input.readLine()) != null)
//            {
//                String str[] = line.split(":");
//                int x = Integer.parseInt(str[0]);
//                int y = Integer.parseInt(str[1]);
//                int type = Integer.parseInt(str[2]);
//                String images = str[3];
//                Item item = new Item(x, y, type, images);
//                arrItem.add(item);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

//    public void innitArrBox(String pathBox, String pathShadow)
//    {
//        try
//        {
//            FileReader file = new FileReader(pathBox);
//            BufferedReader input = new BufferedReader(file);
//            Background = input.readLine();
//            String line;
//            while ((line = input.readLine()) != null)
//            {
//                String str[] = line.split(":");
//                int x = Integer.parseInt(str[0]);
//                int y = Integer.parseInt(str[1]);
//                int type = Integer.parseInt(str[2]);
//                String images = str[3];
//                Box box = new Box(x, y, type, images);
//                arrBox.add(box);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        try
//        {
//            FileReader fileShawDow = new FileReader(pathShadow);
//            BufferedReader inputShawDow = new BufferedReader(fileShawDow);
//            String line;
//            while ((line = inputShawDow.readLine()) != null)
//            {
//                String str[] = line.split(":");
//                int x = Integer.parseInt(str[0]);
//                int y = Integer.parseInt(str[1]);
//                int type = Integer.parseInt(str[2]);
//                String images = str[3];
//                Box box = new Box(x, y, type, images);
//                arrShawDow.add(box);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void innitBomb()
    {
        if (mBomber.getStatus() == Bomber.DEAD)
        {
            return;
        }
        int x = mBomber.getX() + mBomber.getWidth() / 2;
        int y = mBomber.getY() + mBomber.getHeart() / 2;
        for (int i = 0; i < arrBomb.size(); i++)
        {
            if (arrBomb.get(i).isImpact(x, y))
            {
                return;
            }
        }

        if (arrBomb.size() >= mBomber.getQuantityBomb())
        {
            return;
        }
        GameSound.getIstance().getAudio(GameSound.BOMB).play();
        Bomb mBomb = new Bomb(x, y, mBomber.getSizeBomb(), 2000);
        arrBomb.add(mBomb);
    }

//    public void initarrMonster(String path)
//    {
//        try
//        {
//            FileReader file = new FileReader(path);
//            BufferedReader input = new BufferedReader(file);
//            String line;
//            while ((line = input.readLine()) != null)
//            {
//                String str[] = line.split(":");
//                int x = Integer.parseInt(str[0]);
//                int y = Integer.parseInt(str[1]);
//                int type = Integer.parseInt(str[2]);
//                int orient = Integer.parseInt(str[3]);
//                int speed = Integer.parseInt(str[4]);
//                int heart = Integer.parseInt(str[5]);
//                String images = str[6];
//                Monster monster = new Monster(x, y, type, orient, speed, heart, images);
//                arrMonster.add(monster);
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void innitArrHightScore(String path)
    {
        try
        {
            FileReader file = new FileReader(path);
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null)
            {
                String str[] = line.split(":");
                String name = str[0];
                int score = Integer.parseInt(str[1]);
                HightScore hightScore = new HightScore(name, score);
                arrHightScore.add(hightScore);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void drawDialog(Graphics2D g2d, int type)
    {
        g2d.setFont(new Font("Arial", Font.BOLD, 70));
        g2d.setColor(Color.RED);
        if (type == 1)
        {
            g2d.drawString("You Lose !!!", 200, 250);
        }
        else
        {
            if (type == 2)
            {
                g2d.drawString("Level " + level, 200, 250);
            }
            else
            {
                g2d.drawString("You Win !!!", 200, 250);
            }
        }
    }

    public void drawAllItem(Graphics2D g2d)
    {
        for (int i = 0; i < arrItem.size(); i++)
        {
            arrItem.get(i).drawItem(g2d);
        }
    }

    public void drawAllBox(Graphics2D g2d)
    {
        for (int i = 0; i < arrBox.size(); i++)
        {
            arrBox.get(i).drawBox(g2d);
        }
    }

    public void drawAllShawDow(Graphics2D g2d)
    {
        for (int i = 0; i < arrShawDow.size(); i++)
        {
            arrShawDow.get(i).drawBox(g2d);
        }
    }

    public void draWBackground(Graphics2D g2d)
    {
        Image imgBackground = new ImageIcon(getClass().getResource("/Images/background_Play.png")).getImage();
        g2d.drawImage(imgBackground, 0, 0, null);
    }

    public void drawInfo(Graphics2D g2d)
    {
        Image imgInfor = new ImageIcon(getClass().getResource("/Images/background_Info.png")).getImage();
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.RED);
        g2d.drawImage(imgInfor, 1395, 0, null);
        g2d.drawString("HEART", 1475, 100);
        Image heart = new ImageIcon(getClass().getResource("/Images/heart_1.png")).getImage();
        if (mBomber.getHeart() == 3)
        {
            g2d.drawImage(heart, 1470, 120, null);
            g2d.drawImage(heart, 1495, 120, null);
            g2d.drawImage(heart, 1520, 120, null);
        }
        if (mBomber.getHeart() == 2)
        {
            g2d.drawImage(heart, 1480, 120, null);
            g2d.drawImage(heart, 1515, 120, null);
        }
        if (mBomber.getHeart() == 1)
        {
            g2d.drawImage(heart, 1495, 120, null);
        }

        g2d.drawString("SCORE : " + mBomber.getScore() * 10, 1465, 200);
    }

    public void drawAllBomb(Graphics2D g2d)
    {
        for (int i = 0; i < arrBomb.size(); i++)
        {
            arrBomb.get(i).drawActor(g2d);
        }
        for (int i = 0; i < arrBombBang.size(); i++)
        {
            arrBombBang.get(i).drawBongBang(g2d);
        }
    }

    public void drawAllMonster(Graphics2D g2d)
    {
        for (int i = 0; i < arrMonster.size(); i++)
        {
            arrMonster.get(i).drawActor(g2d);
        }
    }

    public void drawBoss(Graphics2D g2d)
    {
        for (int i = 0; i < arrMonster.size(); i++)
        {
            arrMonster.get(i).drawBoss(g2d);
        }
    }

    public void checkWinAndLose()
    {
        if (mBomber.getHeart() == 0 && nextLevel == 0)
        {
            level = 1;
            status = 1;
            nextLevel++;
            GameSound.getIstance().getAudio(GameSound.PLAYGAME).stop();
            GameSound.getIstance().getAudio(GameSound.LOSE).play();
            saveScore();
        }
    }

    public void checkDead()
    {
        for (int i = 0; i < arrBombBang.size(); i++)
        {
            if (arrBombBang.get(i).isImpactBombBangVsActor(mBomber) && mBomber.getStatus() == Bomber.ALIVE)
            {
                Image icon = new ImageIcon(getClass().getResource("/Images/bomber_dead.png")).getImage();
                mBomber.setImg(icon);
                if (mBomber.getStatus() == Bomber.DEAD)
                {
                    return;
                }
                mBomber.setHeart(mBomber.getHeart() - 1);
                mBomber.setStatus(Bomber.DEAD);
                GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
            }
        }
        for (int i = 0; i < arrMonster.size(); i++)
        {
            if (mBomber.isImpactBomberVsActor(arrMonster.get(i)))
            {
                Image icon;
                for (int j = 0; j < 3000; j++)
                {
                    icon = new ImageIcon(getClass().getResource("/Images/bomber_dead.png")).getImage();
                    mBomber.setImg(icon);
                }

                if (mBomber.getStatus() == Bomber.DEAD)
                {
                    return;
                }
                mBomber.setHeart(mBomber.getHeart() - 1);
                mBomber.setStatus(Bomber.DEAD);
                GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
            }
        }
    }

    public void checkImpactItem()
    {
        for (int i = 0; i < arrItem.size(); i++)
        {
            if (arrItem.get(i).isImpactItemVsBomber(mBomber))
            {
                if(arrItem.get(i).getType() != Item.Item_Door) {
                    GameSound.instance.getAudio(GameSound.ITEM).play();
                    if (arrItem.get(i).getType() == Item.Item_Bomb) {
                        mBomber.setQuantityBomb(mBomber.getQuantityBomb() + 1);
                        arrItem.remove(i);
                        break;
                    }
                    if (arrItem.get(i).getType() == Item.Item_BombSize) {
                        mBomber.setSizeBomb(mBomber.getSizeBomb() + 1);
                        arrItem.remove(i);
                        break;
                    }
                    if (arrItem.get(i).getType() == Item.Item_Shoe) {
                        mBomber.setSpeed(mBomber.getSpeed() - 1);
                        arrItem.remove(i);
                        break;
                    }
                }

                else if (arrItem.get(i).getType() == Item.Item_Door)
                {
                    if (arrMonster.size() == 0)
                    {
                        GameSound.instance.getAudio(GameSound.ITEM).play();
                        System.out.println("endgame");
                        //end game there
                        if (arrMonster.size() == 0 && nextLevel == 0)
                        {
                            if (level == 3)
                            {
                                status = 3;
                                nextLevel++;
                                GameSound.getIstance().getAudio(GameSound.PLAYGAME).stop();
                                GameSound.getIstance().getAudio(GameSound.WIN).play();
                                saveScore();
                                level = 1;
                                return;
                            }
                            level = level + 1;
                            nextLevel++;
                            status = 2;
                            arrItem.remove(i);
                        }
                    }

//                    arrItem.remove(i);
                    break;
                }
            }
        }
    }

    public void deadLineAllBomb()
    {
        for (int i = 0; i < arrBomb.size(); i++)
        {
            arrBomb.get(i).deadlineBomb();
            if (arrBomb.get(i).getTimeline() == 250)
            {
                BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb.get(i).getY(), arrBomb.get(i).getSize(), arrBox);
                GameSound.getIstance().getAudio(GameSound.BOMB_BANG).play();
                arrBombBang.add(bomBang);
                arrBomb.remove(i);
            }
        }
        for (int j = 0; j < arrMonster.size(); j++)
        {
            for (int i = 0; i < arrBomb.size(); i++)
            {
                if (arrBomb.get(i).isImpactBombvsActor(arrMonster.get(j)) == 2)
                {
                    BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb.get(i).getY(), arrBomb.get(i).getSize(), arrBox);
                    GameSound.getIstance().getAudio(GameSound.BOMB_BANG).play();
                    arrBombBang.add(bomBang);
                    arrBomb.remove(i);
                }
            }
        }

        for (int i = 0; i < arrBombBang.size(); i++)
        {
            for (int j = 0; j < arrBomb.size(); j++)
            {
                if (arrBombBang.get(i).isImpactBombBangvsBomb(arrBomb.get(j)))
                {
                    BombBang bomBang = new BombBang(arrBomb.get(j).getX(), arrBomb.get(j).getY(), arrBomb.get(j).getSize(), arrBox);
                    arrBombBang.add(bomBang);
                    arrBomb.remove(j);
                }
            }
        }
        for (int k = 0; k < arrBombBang.size(); k++)
        {
            arrBombBang.get(k).deadlineBomb();
            for (int j = 0; j < arrMonster.size(); j++)
            {
                if (arrBombBang.get(k).isImpactBombBangVsActor(arrMonster.get(j)))
                {
                    if (arrMonster.get(j).getHeart() > 1)
                    {
                        arrMonster.get(j).setHeart(arrMonster.get(j).getHeart() - 1);
                    }
                    else
                    {
                        if (arrMonster.get(j).getType() == Actor.BOSS)
                        {
                            mBomber.setScore(mBomber.getScore() + 10);
                        }
                        else
                        {
                            mBomber.setScore(mBomber.getScore() + 1);
                        }
                        GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
                        arrMonster.remove(j);
                    }
                }
            }
            if (arrBombBang.get(k).getTimeLine() == 0)
            {
                arrBombBang.remove(k);
            }
        }
        for (int i = 0; i < arrBombBang.size(); i++)
        {
            for (int j = 0; j < arrBox.size(); j++)
            {
                if (arrBombBang.get(i).isImpactBombBangvsBox(arrBox.get(j)))
                {
                    arrBox.remove(j);
                    arrShawDow.remove(j);
                }
            }
        }
        for (int i = 0; i < arrBombBang.size(); i++)
        {
            for (int j = 0; j < arrItem.size(); j++)
            {
                if (arrBombBang.get(i).isImpactBombBangvsItem(arrItem.get(j)) && arrItem.get(j).getType() != Item.Item_Door)
                {
                    arrItem.remove(j);
                }
            }
        }
    }

    public void setRunBomer()
    {
        if (arrBomb.size() > 0)
        {
            if (arrBomb.get(arrBomb.size() - 1).setRun(mBomber) == false)
            {
                mBomber.setRunBomb(Bomber.DISALLOW_RUN);
            }
        }
    }

    public void setNewBomb()
    {
        switch (level)
        {
            case 1:
                mBomber.setNew(45, 67);
                break;
            case 2:
                mBomber.setNew(315, 270);
                break;
            case 3:
                mBomber.setNew(315, 495);
                break;

            default:
                break;
        }
    }

    public void changeOrientAll()
    {
        for (int i = 0; i < arrMonster.size(); i++)
        {
            int orient = random.nextInt(4) + 1;
            arrMonster.get(i).changeOrient(orient, animation++);
        }
    }

    public void moveAllMonster(int count)
    {
        for (int i = 0; i < arrMonster.size(); i++)
        {
            int orient;
            if (arrMonster.get(i).move(count, arrBomb, arrBox) == false)
            {
                orient = random.nextInt(4) + 1;
            }
            else
            {
                orient = arrMonster.get(i).getOrient();
            }
            arrMonster.get(i).changeOrient(orient, animation++);
        }
    }

    public void saveScore()
    {
        System.out.println();
        if (mBomber.getScore() > arrHightScore.get(arrHightScore.size() - 1).getScore())
        {
            String name = JOptionPane.showInputDialog("Please input Your Name");
            HightScore newScore = new HightScore(name, mBomber.getScore());
            arrHightScore.add(newScore);
        }
        Collections.sort(arrHightScore, new Comparator<HightScore>()
        {

            @Override
            public int compare(HightScore score1, HightScore score2)
            {
                if (score1.getScore() < score2.getScore())
                {
                    return 1;
                }
                else
                {
                    if (score1.getScore() == score2.getScore())
                    {
                        return 0;
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        });

        if (arrHightScore.size() > 10)
        {
            arrHightScore.remove(arrHightScore.size() - 1);
        }

        try
        {
            FileOutputStream fileOutput = new FileOutputStream("src/hightscore/HightScore.txt");
            for (int i = 0; i < arrHightScore.size(); i++)
            {
                String content = arrHightScore.get(i).getName() + ":" + arrHightScore.get(i).getScore() + "\n";
                fileOutput.write(content.getBytes());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Box> getArrBox()
    {
        return arrBox;
    }

    public ArrayList<Bomb> getArrBomb()
    {
        return arrBomb;
    }

    public Bomber getmBomber()
    {
        return mBomber;
    }

    public int getStatus()
    {
        return status;
    }

    public void setRound(int round)
    {
        this.level = 1;
    }

    private String pathLeve1 = "src/Map1/Map1.txt";
    private String pathLeve2 = "src/Map2/Map2.txt";
    private String pathLeve3 = "src/Map3/Map3.txt";
    private String pathShadow1 = "/Images/shawdow1.png";
    private String pathBox1 = "/Images/box1.png";
    private String pathShadow2 = "/Images/shawdow2.png";
    private String pathBox2 = "/Images/box2.png";
    private String pathMonster2 = "/Images/balloom_right0.png";
    private String pathItem2 = "/Images/item_bombsize.png";
    private String pathDoor = "/Images/item_door.png";
    private String pathItem1 = "/Images/item_bomb.png";
    private String pathItem3 = "/Images/item_shoe.png";


    public void addItem(int i, int row, int type, String path)
    {
        int itemX = i * 45;
        int itemY = row * 52;

        Item item = new Item(itemX + 5, itemY + 6, type, path);
        this.arrItem.add(item);

        Box box2 = new Box(itemX, itemY + 15, 0, pathBox2);
        this.arrBox.add(box2);

        Box shadowBox2 = new Box(itemX, itemY, 0, pathShadow2);
        this.arrShawDow.add(shadowBox2);
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
                        int shadowX = i * 45;
                        int shadowY = row * 52;
                        int boxX = shadowX;
                        int boxY = shadowY + 15;

                        int type = 1;

                        Box box1 = new Box(boxX, boxY, type, pathBox1);
                        this.arrBox.add(box1);

                        Box shadowBox1 = new Box(shadowX, shadowY, type, pathShadow1);
                        this.arrShawDow.add(shadowBox1);
                        break;
                    }
                    case '*':
                    {
                        int shadowX = i * 45;
                        int shadowY = row * 52;
                        int boxX = shadowX;
                        int boxY = shadowY + 15;

                        int type = 0;

                        Box box2 = new Box(boxX, boxY, type, pathBox2);
                        this.arrBox.add(box2);

                        Box shadowBox2 = new Box(shadowX, shadowY, type, pathShadow2);
                        this.arrShawDow.add(shadowBox2);
                        //TODO:

                        break;
                    }
                    case '1':
                    {
                        //TODO:
                        int monsterX = i * 45;
                        int monsterY = row * 52 +5;

                        int type = 2;
                        int orient = 3;
                        int speed = 10;
                        int heart = 1;

                        Monster monster2 = new Monster(monsterX, monsterY, type, orient, speed, heart, pathMonster2);

                        this.arrMonster.add(monster2);
                        break;
                    }
                    case '2':
                    {
                        //TODO:
                        int monsterX = i * 45;
                        int monsterY = row * 52 +5;

                        int type = 5;
                        int orient = 3;
                        int speed = 10;
                        int heart = 1;

                        Monster monster2 = new Monster(monsterX, monsterY, type, orient, speed, heart, pathMonster2);

                        this.arrMonster.add(monster2);
                        break;
                    }
                    case '3':
                    {
                        //TODO:
                        int monsterX = i * 45;
                        int monsterY = row * 52 +5;

                        int type = 3;
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
