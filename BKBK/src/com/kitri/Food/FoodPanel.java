package com.kitri.Food;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Main.frame.MainFrame;

public class FoodPanel extends JPanel {

//	   public JPanel mainPanel = new JPanel();//main panel //--------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함

	   
	   //////////////////////////////////////////////
	   //카테고리 버튼들
	   public JButton drinkBtn = new JButton("\uC74C\uB8CC");
	   public JButton snackBtn = new JButton("\uACFC\uC790");
	   public JButton noodleBtn = new JButton("\uB77C\uBA74");
	   public JButton instfoodBtn = new JButton("\uC74C\uC2DD");
	   
	   //메뉴판 panel
	   JPanel menuPanel;
	   //메뉴판 View
	   public DrinkView dv = new DrinkView();
	   public SnackView sv = new SnackView();
	   public NoodleView nv = new NoodleView();
	   public InstFoodView ifv = new InstFoodView();
	   
	   CardLayout card = new CardLayout();
	   //////////////////////////////////
	   //주문취소,완료버튼
	   JButton cancelBtn = new JButton("\uCDE8\uC18C");
	   JButton completeBtn = new JButton("\uC644\uB8CC");
	   ////////////////////////////////////
	   //주문list
	   public JPanel orderPanel = new JPanel();
	   public JScrollPane tableP;
	   public JTable listT;
	   public DefaultTableModel tM;
	   ///////////////////////////////////////////
	   //총금액 textfield
	   JTextField textField;
	   public JTextField sumprice;
	   //주문수정버튼
	   JButton plusBtn = new JButton("+");
	   JButton minusBtn = new JButton("-");
	   JButton oneCancel = new JButton("X");
	   JButton totalCancel = new JButton("\uC804\uCCB4 \uCDE8\uC18C");
	
	   ///////////////////////////////////////
	   //Event Controller
	   public FoodController foodController;
//--------------------------------------------------------------------------------------------------------------------------------------------[h] MainFrame을 받아와서 control >> Service까지 넘겨줌	
	   public MainFrame mf;
	/**
	 * Create the panel.
	 */
	public FoodPanel(MainFrame mainFrame) {
		this.mf = mainFrame;
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
		
	      /////////////////////////////////////////////////////////////////
	      //main panel
//	      mainPanel.setBackground(Color.WHITE);
//	      mainPanel.setBounds(0, 36, 1494, 835); // 이 크기대로 패널 생성해서 cardLayout으로 구성~~
//	      mainPanel.setLayout(null);
		
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함
	      setBackground(Color.WHITE);
	      setBounds(0, 36, 1494, 835); // 이 크기대로 패널 생성해서 cardLayout으로 구성~~
	      setLayout(null);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]	      
	      
	      drinkBtn.setForeground(Color.WHITE);
	      drinkBtn.setBackground(Color.DARK_GRAY);
	      //////////////////////////////////////////////
	      //카테고리 버튼들
	      drinkBtn.setBounds(1374, 71, 97, 50);
//	      mainPanel.add(drinkBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(drinkBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      drinkBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      snackBtn.setForeground(Color.WHITE);
	      snackBtn.setBackground(Color.DARK_GRAY);
	      snackBtn.setBounds(1374, 131, 97, 50);
//	      mainPanel.add(snackBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함
	      add(snackBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      snackBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      noodleBtn.setForeground(Color.WHITE);
	      noodleBtn.setBackground(Color.DARK_GRAY);
	      noodleBtn.setBounds(1374, 191, 97, 50);
//	      mainPanel.add(noodleBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(noodleBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      noodleBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      instfoodBtn.setForeground(Color.WHITE);
	      instfoodBtn.setBackground(Color.DARK_GRAY);
	      instfoodBtn.setBounds(1374, 251, 97, 50);
//	      mainPanel.add(instfoodBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(instfoodBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]	
	      
	      instfoodBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      ///////////////////////////////////////////////
	      //menu판 버튼들 들어가는 panel : menuPanel
	      menuPanel = new JPanel();
	      menuPanel.setBounds(717, 67, 656, 583);
	            
	      menuPanel.setLayout(card);//menuPanel에 cardLayout 씌우기
	      
	      dv.setLocation(715, 67);//각 카테고리 panel
	      menuPanel.add("drinkview",dv);
	      sv.setLocation(715, 67);
	      menuPanel.add("snackview",sv);
	      nv.setLocation(715, 67);
	      menuPanel.add("noodleview",nv);
	      ifv.setLocation(715, 67);
	      menuPanel.add("instfoodview",ifv);
	      
	      card.show(menuPanel, "drinkview");//첫페이지에 보이는 panel : drink
//	      mainPanel.add(menuPanel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(menuPanel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      //////////////////////////////////////////////
	      //주문 취소,완료 버튼
	      cancelBtn.setForeground(Color.WHITE);
	      cancelBtn.setBackground(new Color(52, 152, 219));
	      cancelBtn.setBounds(883, 708, 235, 47);
	      cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//	      mainPanel.add(cancelBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(cancelBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]

	      completeBtn.setForeground(Color.WHITE);
	      completeBtn.setBackground(new Color(52, 152, 219));
	      completeBtn.setBounds(1138, 708, 235, 47);
	      completeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//	      mainPanel.add(completeBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(completeBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      //////////////////////////////////////
	      //주문list
	      orderPanel.setBounds(48, 67, 630, 583);
	      orderPanel.setLayout(new BorderLayout(0, 0));
	      //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	      //Jtable
	      //컬럼 명
	      Vector<String> column1 = new Vector<String>();
	      column1.addElement("No");

	      column1.addElement("카테고리");
	      column1.addElement("메뉴");
	      column1.addElement("수량");
	      column1.addElement("가격");
	      
	      //추가한 컬럼명으로 모델 생성하고 인자 넣음
	      tM = new DefaultTableModel(column1, 0);
//	      boolean tt = tM.isCellEditable(0, column1);
	      
	      
	      //JTable에 넣음.
	      listT = new JTable(tM);
	      listT.setFillsViewportHeight(true);
	      
	      //table 부착
	      tableP = new JScrollPane(listT);
	      orderPanel.add(tableP);
	      
	      
//	      mainPanel.add(orderPanel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(orderPanel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	      
	      textField = new JTextField();
	      textField.setBackground(new Color(52, 152, 219));
	      textField.setForeground(Color.WHITE);
	      textField.setHorizontalAlignment(SwingConstants.CENTER);
	      textField.setText("\uCD1D\uAE08\uC561");
	      textField.setBounds(48, 681, 225, 37);
//	      mainPanel.add(textField);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(textField);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]

	      textField.setColumns(10);
	      textField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	      //총금액
	      sumprice = new JTextField();
	      sumprice.setText("0");
	      sumprice.setHorizontalAlignment(SwingConstants.CENTER);
	      sumprice.setBounds(48, 718, 225, 37);
//	      mainPanel.add(sumprice);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(sumprice);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      sumprice.setColumns(10);
	      sumprice.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	      plusBtn.setForeground(Color.WHITE);
	      plusBtn.setBackground(Color.DARK_GRAY);
	      
	      plusBtn.setBounds(285, 680, 75, 75);
//	      mainPanel.add(plusBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(plusBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      plusBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
	      minusBtn.setForeground(Color.WHITE);
	      minusBtn.setBackground(Color.DARK_GRAY);
	      minusBtn.setBounds(372, 680, 75, 75);
//	      mainPanel.add(minusBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(minusBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      minusBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
	      oneCancel.setForeground(Color.WHITE);
	      oneCancel.setBackground(Color.DARK_GRAY);
	      oneCancel.setBounds(459, 680, 75, 75);
//	      mainPanel.add(oneCancel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(oneCancel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      oneCancel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	      totalCancel.setForeground(Color.WHITE);
	      totalCancel.setBackground(Color.DARK_GRAY);
	      totalCancel.setBounds(546, 680, 132, 75);
//	      mainPanel.add(totalCancel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel을 상속받고 main panel을 this로 대체함	      
	      add(totalCancel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      totalCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	      //////////////////////////////////////
	      
	     
	      /////////////////////////////
	      //Event Listener
	      foodController = new FoodController(this);
	      
	      drinkBtn.addActionListener(foodController);
	      snackBtn.addActionListener(foodController);
	      noodleBtn.addActionListener(foodController);
	      instfoodBtn.addActionListener(foodController);
	      
	      plusBtn.addActionListener(foodController);
	      minusBtn.addActionListener(foodController);
	      oneCancel.addActionListener(foodController);
	      totalCancel.addActionListener(foodController);
	      
	      cancelBtn.addActionListener(foodController);
	      completeBtn.addActionListener(foodController);
	      
	      //DrinkView 
	      int drinklen = dv.drinkbtn.length;
	      for(int i=0;i<drinklen;i++) {
	    	   dv.drinkbtn[i].addActionListener(foodController);
	      }
	      //SnackView
	      int snacklen = sv.snackbtn.length;
	      for(int i=0;i<snacklen;i++) {
	    	   sv.snackbtn[i].addActionListener(foodController);
	      }
	      //NoodleView
	      int noodlelen = nv.noodlebtn.length;
	      for(int i=0;i<noodlelen;i++) {
	    	   nv.noodlebtn[i].addActionListener(foodController);
	      }
	      //InstFoodView
	      int instlen = ifv.instfoodbtn.length;
	      for(int i=0;i<instlen;i++) {
	    	  ifv.instfoodbtn[i].addActionListener(foodController);
	      }
	      
	}

}
