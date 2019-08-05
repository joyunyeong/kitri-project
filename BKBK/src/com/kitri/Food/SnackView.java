package com.kitri.Food;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.kitri.Food.service.FoodDao;

import java.awt.Color;
import java.awt.Font;

public class SnackView extends JPanel {
	public String bt[];
	public JButton snackbtn[];
	/**
	 * Create the panel.
	 */
	public SnackView() {
		setLayout(new GridLayout(5, 5, 1, 1));
		setBounds(713, 123, 656, 583);
		
		String foodctg = "°úÀÚ";//??????????
		List<String> foodnamelist = FoodDao.getInstance().getFoodnamelist(foodctg);
		
		int len = foodnamelist.size();
		snackbtn = new JButton[25];
		for(int i=0;i<25;i++) {
			snackbtn[i] = new JButton("+");
			snackbtn[i].setVisible(false);
			add(snackbtn[i]);
		}
		for(int i=0; i<len; i++) {
			snackbtn[i].setText(foodnamelist.get(i));
			snackbtn[i].setFont(new Font("±¼¸²", Font.BOLD, 14));
			snackbtn[i].setForeground(Color.WHITE);
			snackbtn[i].setBackground(new Color(52, 152, 219));
			snackbtn[i].setVisible(true);
		}
	}

}
