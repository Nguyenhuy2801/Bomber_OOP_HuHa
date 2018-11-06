package Bomber_OOP.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Option extends JPanel implements ActionListener{
	private MyContainer mContainer;
	private JLabel lbbackground;
	private ImageIcon backgroundIcon;
	private JButton Button_OK;
	
	public Option(MyContainer mContainer) {
		this.mContainer = mContainer;
		setBackground(Color.YELLOW);
		setLayout(null);
		initCompts();
	}
	
	public void initCompts(){
		lbbackground = new JLabel();
		lbbackground.setBounds(95, -40, GUI.WIDTHJF, GUI.HEIGHTJF);
		lbbackground.setBackground(Color.BLACK);
		backgroundIcon = new ImageIcon(getClass().getResource("/Images/background_option.png"));
		lbbackground.setIcon(backgroundIcon);
		add(lbbackground);
		Button_OK = new JButton();
		Button_OK.setText("OK");
		Button_OK.setBounds(400, 520, 100, 50);
		Button_OK.addActionListener(this);
		add(Button_OK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Button_OK){
			mContainer.setShowMenu();
		}	
	}
	
}
