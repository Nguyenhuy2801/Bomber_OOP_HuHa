package Bomber_OOP.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.*;

import Bomber_OOP.actor.HightScore;


public class HightScorePanel extends JPanel implements ActionListener{
	private MyContainer mContainer;
	private JButton Button_OK;
	private ArrayList<HightScore> arrHightScore;
	
	public HightScorePanel(MyContainer mContainer) {
		this.mContainer = mContainer;
		setBackground(Color.YELLOW);
		setLayout(null);
		initCompts();
	}
	
	public void initCompts(){
		try {
			ReadFileHightScore();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Button_OK = new JButton();
		Button_OK.setText("OK");
		Button_OK.setBounds(400, 520, 100, 50);
		Button_OK.addActionListener(this);
		add(Button_OK);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D )g;
		drawImage(g2d);
		drawHightSore(g2d);
	}
	
	public void drawImage(Graphics2D g2d){
		Image background = new ImageIcon(getClass().getResource("/Images/background_hightscore.png")).getImage();
		g2d.drawImage(background, 0, 0, null);
	}
	
	public void drawHightSore(Graphics2D g2d){
		g2d.setStroke(new java.awt.BasicStroke(2));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Arial", Font.BOLD, 30));
		g2d.setColor(Color.YELLOW);
		int y=100;
		for(int i=0;i< 10;i++){
			g2d.drawString(""+(i+1), 270, y);
			g2d.drawString(""+arrHightScore.get(i).getName(), 380, y);
			g2d.drawString(""+arrHightScore.get(i).getScore(), 600, y);
			y=y+40;
		}
		
	}
	
	public void ReadFileHightScore() throws  FileNotFoundException{
		arrHightScore = new ArrayList<HightScore>();
			File file = new File("src/hightscore/HightScore.txt");
			Scanner sc = new Scanner(file, "utf-8");
			while (sc.hasNextLine()) {
				String fullLine = sc.nextLine();
				String[] str = fullLine.split(":");
				String name = str[0];
				int score = Integer.parseInt(str[1]);
				HightScore hightScore = new HightScore(name, score);
				arrHightScore.add(hightScore);
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Button_OK){
			mContainer.setShowMenu();
		}	
	}
}
