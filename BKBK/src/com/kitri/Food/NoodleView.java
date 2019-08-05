package com.kitri.Food;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.kitri.Food.service.FoodDao;

public class NoodleView extends JPanel {
	public String bt[];
	public JButton noodlebtn[];
	/**
	 * Create the panel.
	 */
	public NoodleView() {
		setLayout(new GridLayout(5, 5));
		setBounds(713, 123, 656, 583);

		String foodctg = "¶ó¸é";//??????????
		List<String> foodnamelist = FoodDao.getInstance().getFoodnamelist(foodctg);
		
		int len = foodnamelist.size();
		noodlebtn = new JButton[25];
		for(int i=0;i<25;i++) {
			noodlebtn[i] = new JButton("+");
			noodlebtn[i].setVisible(false);
			add(noodlebtn[i]);
		}
		for(int i=0; i<len; i++) {
			noodlebtn[i].setText(foodnamelist.get(i));
			noodlebtn[i].setFont(new Font("±¼¸²", Font.BOLD, 14));
			noodlebtn[i].setForeground(Color.WHITE);
			noodlebtn[i].setBackground(new Color(52, 152, 219));
			noodlebtn[i].setVisible(true);
		}
		
		
	}

}
