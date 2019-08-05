package com.kitri.Statistics.chart;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


import com.kitri.Statistics.StatFoodDto;
import com.kitri.Statistics.StatMemberDto;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class ChartFood extends JPanel {

	
	public static JPanel pShowGraph = null;
	String[] year ={"2017", "2018", "2019"};
	String[] month ={"01", "02", "03", "04","05", "06", "07", "08","09", "10", "11", "12"};
	String[] year2 ={"2017", "2018", "2019"};
	String[] month2 ={"01", "02", "03", "04","05", "06", "07", "08","09", "10", "11", "12"};

	public Vector<String> column1 = new Vector<String>();
	public Vector rows = new Vector();
	public DefaultTableModel model;
	static JComboBox startyear;
	static JComboBox startmonth;
	static JComboBox lastyear;
	static JComboBox lastmonth;

	static JPanel chartpanel;
	
	JButton search;
	public LayoutManager graphCard;
	public Object spShowTable;
	public Object tableResult;
	
	/**
	 * Create the panel.
	 */
	public ChartFood() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(203, 49, 1240, 713);

		JLabel lblNewLabel = new JLabel("상품 매출 순위");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(473, 58, 319, 37);
		add(lblNewLabel);

		JLabel label = new JLabel("\uC870\uD68C\uC77C\uC790:");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(180, 131, 101, 37);
		add(label);

		startyear = new JComboBox(year);
		startyear.setBounds(278, 138, 124, 27);
		add(startyear);
		
		JLabel label_1 = new JLabel("년");
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		label_1.setBounds(405, 139, 20, 21);
		add(label_1);

		startmonth = new JComboBox(month);
		startmonth.setBounds(437, 138, 124, 27);
		add(startmonth);

		JLabel label_2 = new JLabel("월");
		label_2.setFont(new Font("굴림", Font.PLAIN, 20));
		label_2.setBounds(564, 139, 20, 21);
		add(label_2);

		lastyear = new JComboBox(year2);
		lastyear.setBounds(628, 138, 124, 27);
		add(lastyear);
		
		JLabel label_4 = new JLabel("\uB144");
		label_4.setFont(new Font("굴림", Font.PLAIN, 20));
		label_4.setBounds(755, 139, 20, 21);
		add(label_4);
		
		lastmonth = new JComboBox(month2);
		lastmonth.setBounds(787, 138, 124, 27);
		add(lastmonth);
		
		JLabel label_5 = new JLabel("\uC6D4");
		label_5.setFont(new Font("굴림", Font.PLAIN, 20));
		label_5.setBounds(914, 139, 20, 21);
		add(label_5);
		
		JLabel label_7 = new JLabel("~");
		label_7.setFont(new Font("굴림", Font.PLAIN, 20));
		label_7.setBounds(596, 139, 20, 21);
		add(label_7);
		
		search = new JButton("\uC870\uD68C");
		search.setBounds(946, 138, 64, 27);
		add(search);
		
	

		// 3) 차트를 붙일 J패널 생성하기
		chartpanel = new JPanel();
		chartpanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartpanel.setBounds(50, 214, 560, 450);
		chartpanel.setBackground(Color.WHITE);
		chartpanel.setLayout(null);


		// 5) J패널을 프레임에 붙이기
		add(chartpanel);

		

		ChartFoodController chartFoodController;

		
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 0, 0, 0);
		add(panel2);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBackground(Color.WHITE);
		panel2.setVisible(true);

		
		// 컬럼명을 만들어준다.
		column1.addElement("매출순위");
		column1.addElement("상품종류");
		column1.addElement("상품명");
		column1.addElement("상품가격");
		column1.addElement("판매수량");
		column1.addElement("매출합계");
		
		model = new DefaultTableModel(rows, column1);
		
		// 추가된 데이터들을 모델에 추가하고 모델을 테이블 인자로 넣어준다.
		JTable table = new JTable(model);
		table.setGridColor(Color.GRAY);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		JScrollPane jScollPane = new JScrollPane(table);
		add(jScollPane);
		jScollPane.setViewportView(table);

		jScollPane.setBackground(Color.WHITE);
		jScollPane.setPreferredSize(new Dimension(560, 450));
		jScollPane.setBounds(622, 214, 560, 450);
		jScollPane.setForeground(Color.WHITE);
		
		chartFoodController = new ChartFoodController(this);
		search.addActionListener(chartFoodController);
		
		table.setFillsViewportHeight(true);

	}
}
