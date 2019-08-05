package com.kitri.Food;

import javax.swing.JPanel;

import com.kitri.Food.service.FoodDao;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class DrinkView extends JPanel {
	/**
	 * Create the panel.
	 */
	public String bt[];
	public JButton drinkbtn[];
	public DrinkView() {
		setLayout(new GridLayout(5, 5, 1,1));
		setBounds(713, 123, 656, 583);
		
		String foodctg = "À½·á";//??????????
		List<String> foodnamelist = FoodDao.getInstance().getFoodnamelist(foodctg);
		
		int len = foodnamelist.size();
		drinkbtn = new JButton[25];
		for(int i=0;i<25;i++) {
			drinkbtn[i] = new JButton("+");
			drinkbtn[i].setVisible(false);
			add(drinkbtn[i]);
		}
		for(int i=0; i<len; i++) {
			drinkbtn[i].setText(foodnamelist.get(i));
			drinkbtn[i].setFont(new Font("±¼¸²", Font.BOLD, 14));
			drinkbtn[i].setForeground(Color.WHITE);
			drinkbtn[i].setBackground(new Color(52, 152, 219));
			drinkbtn[i].setVisible(true);
		}

	}

}