package Bomber_OOP.gui;

import sound.GameSound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

public class GUI extends JFrame
    {
    public static final int WIDTHJF = 1470;
    public static final int HEIGHTJF = 720;
    private MyContainer mContainer;
    private WindowAdapter mwindow = new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            GameSound.getIstance().stop();
            PlayGame.IS_RUNNING = false;
        }
    };

    public GUI() throws FileNotFoundException
    {
        setSize(WIDTHJF, HEIGHTJF);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mContainer = new MyContainer(this);
        add(mContainer);
        addWindowListener(mwindow);
    }
}
