package com.kitri.Food;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.kitri.Food.service.FoodDao;

public class InstFoodView extends JPanel {
	public String bt[];
	public JButton instfoodbtn[];
	/**
	 * Create the panel.
	 */
	public InstFoodView() {
		setLayout(new GridLayout(5, 5));
		setBounds(713, 123, 656, 583);
		bt = new String[25];
		
		String foodctg = "À½½Ä";//??????????
		List<String> foodnamelist = FoodDao.getInstance().getFoodnamelist(foodctg);
		
		int len = foodnamelist.size();
		instfoodbtn = new JButton[25];
		for(int i=0;i<25;i++) {
			instfoodbtn[i] = new JButton("+");
			instfoodbtn[i].setVisible(false);
			add(instfoodbtn[i]);
		}
		for(int i=0; i<len; i++) {
			instfoodbtn[i].setText(foodnamelist.get(i));
			instfoodbtn[i].setFont(new Font("±¼¸²", Font.BOLD, 14));
			instfoodbtn[i].setForeground(Color.WHITE);
			instfoodbtn[i].setBackground(new Color(52, 152, 219));
			instfoodbtn[i].setVisible(true);
		}
	}

}
