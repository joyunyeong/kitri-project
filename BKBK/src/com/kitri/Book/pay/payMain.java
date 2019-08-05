package com.kitri.Book.pay;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Book.paymethod.Card;
import com.kitri.Book.paymethod.Cash;
import com.kitri.Book.paymethod.Coupon;
import com.kitri.Main.frame.MainFrame;

public class payMain extends JPanel {
	String s;
	JTextField searchTF;
	JTable jTable;
	
	MainFrame mf;
	public JLabel id;

	// payPanel
	JPanel payPanel = new JPanel();
	public JLabel totalR;
	public JLabel discountR;
	public JLabel payR;
	public JLabel givenR;
	public JLabel changeR;

	// listPanel
	private JPanel listPanel;
	private JScrollPane pane;
	private JLabel memberL;
	
	JButton minus;
	
	
	public DefaultTableModel model;

	JTextField textField;

	// Pay Method
	public JButton cardB = new JButton("신용/체크카드");
	public JButton cashB = new JButton("현금");
	public JButton cardCashB = new JButton("복합결제");
	public JButton couponB;
	public JButton homeB = new JButton("");

	JPanel cardP = new JPanel();

	JPanel couponP = new JPanel();
	Coupon couponPanel = new Coupon();

	public payMainController pmc = new payMainController(this);
	public Card card = new Card();
	public Cash cash = new Cash();
	public Coupon coupon = new Coupon();

	/**
	 * Create the panel.
	 */
	public payMain(MainFrame mf) {

		this.mf = mf;
		pmc = new payMainController(this);
		setBackground(Color.WHITE);
		setBounds(0, 36, 1494, 835); // 이 크기대로 패널 생성해서 cardLayout으로 구성~~
		setLayout(null);

		setBackground(Color.WHITE);
		
		

		payPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		payPanel.setBackground(Color.WHITE);
		payPanel.setBounds(32, 499, 622, 302);
		add(payPanel);
		payPanel.setLayout(null);

		JLabel totalL = new JLabel("\uCD1D \uAE08\uC561");
		totalL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalL.setHorizontalAlignment(SwingConstants.CENTER);
		totalL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		totalL.setBounds(29, 23, 131, 42);
		payPanel.add(totalL);

		JLabel discountL = new JLabel("\uD560\uC778\uAE08\uC561");
		discountL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		discountL.setHorizontalAlignment(SwingConstants.CENTER);
		discountL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		discountL.setBounds(29, 82, 131, 42);
		payPanel.add(discountL);

		JLabel payL = new JLabel("\uBC1B\uC744\uAE08\uC561");
		payL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		payL.setHorizontalAlignment(SwingConstants.CENTER);
		payL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		payL.setBounds(29, 134, 131, 42);
		payPanel.add(payL);

		JLabel givenL = new JLabel("\uACB0\uC81C\uAE08\uC561");
		givenL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		givenL.setHorizontalAlignment(SwingConstants.CENTER);
		givenL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		givenL.setBounds(29, 186, 131, 42);
		payPanel.add(givenL);

		totalR = new JLabel("15000");
		totalR.setHorizontalAlignment(SwingConstants.CENTER);
		totalR.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		totalR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalR.setBounds(182, 23, 401, 42);
		payPanel.add(totalR);

		discountR = new JLabel("0");
		discountR.setHorizontalAlignment(SwingConstants.CENTER);
		discountR.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		discountR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		discountR.setBounds(182, 82, 401, 42);
		payPanel.add(discountR);

		payR = new JLabel("");
		payR.setText(String.valueOf(Integer.parseInt(totalR.getText()) - Integer.parseInt(discountR.getText())));
		payR.setBackground(new Color(255, 255, 153));
		payR.setHorizontalAlignment(SwingConstants.CENTER);
		payR.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		payR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		payR.setBounds(182, 134, 401, 42);
		payPanel.add(payR);

		givenR = new JLabel("");
		givenR.setHorizontalAlignment(SwingConstants.CENTER);
		givenR.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		givenR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		givenR.setBounds(182, 186, 401, 42);
		payPanel.add(givenR);

		JLabel changeL = new JLabel("\uAC70\uC2A4\uB984\uB3C8");
		changeL.setHorizontalAlignment(SwingConstants.CENTER);
		changeL.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		changeL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		changeL.setBounds(29, 238, 131, 42);
		payPanel.add(changeL);

		changeR = new JLabel("");
		changeR.setHorizontalAlignment(SwingConstants.CENTER);
		changeR.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		changeR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		changeR.setBounds(182, 238, 401, 42);
		payPanel.add(changeR);

		listPanel = new JPanel();
		listPanel.setBorder(null);
		listPanel.setBackground(SystemColor.text);
		listPanel.setBounds(12, 57, 1470, 391);
		add(listPanel);
		listPanel.setLayout(null);

		memberL = new JLabel("\uD68C\uC6D0 ID : ");
		memberL.setBounds(22, 36, 57, 15);
		listPanel.add(memberL);
		String[] columnPay = { "No.", "상품종류", "상품명", "단가", "수량", "금액","비고" };
		String[][] rowData = {};
		model = new DefaultTableModel(rowData, columnPay);
		jTable = new JTable(model);
		pane = new JScrollPane(jTable);
		pane.setBounds(22, 61, 1412, 319);
		listPanel.add(pane);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBorder(new EmptyBorder(0, 0, 0, 0));
		pane.setFont(new Font("굴림", Font.PLAIN, 20));

		JPanel panel_1 = new JPanel();
		pane.setColumnHeaderView(panel_1);

		minus = new JButton("-");
		minus.setBounds(1113, 10, 47, 41);
		listPanel.add(minus);
		minus.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		minus.setForeground(new Color(255, 255, 255));
		minus.setBackground(new Color(0, 0, 0));
		minus.setFont(new Font("굴림", Font.BOLD, 15));

		homeB.setBackground(Color.WHITE);
		homeB.setIcon(new ImageIcon((".\\src\\com\\kitri\\Manager\\image\\home.png")));
		homeB.setBounds(1381, 43, 59, 59);
		listPanel.add(homeB);
		
//		buttonMain = new JButton("\uBA54\uC778\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
//		buttonMain.setBounds(1277, 10, 157, 41);
//		listPanel.add(buttonMain);
//		buttonMain.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		buttonMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
//		buttonMain.setBackground(new Color(0, 0, 0));
//		buttonMain.setForeground(new Color(255, 255, 255));
//		buttonMain.setFont(new Font("굴림", Font.PLAIN, 15));
//		
		id = new JLabel("");
		id.setBounds(90, 36, 57, 15);
		listPanel.add(id);

		JPanel infoP = new JPanel();
		infoP.setLayout(null);
		infoP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		infoP.setBounds(691, 499, 568, 173);
		add(infoP);

		JLabel info1 = new JLabel(
				"\u261E \uC0C1\uC138\uACB0\uC81C (\uD604\uAE08+\uCE74\uB4DC) \uB610\uB294 (\uCE74\uB4DC+\uCE74\uB4DC) \uCC98\uB9AC\uC21C\uC11C\n");
		info1.setForeground(new Color(204, 0, 0));
		info1.setFont(new Font("굴림", Font.BOLD, 13));
		info1.setBounds(24, 10, 382, 43);
		infoP.add(info1);

		JLabel info2 = new JLabel(
				"1. CL \uBC84\uD2BC\uC744 \uB20C\uB7EC \uBC1B\uC744 \uAE08\uC561\uB780\uC744 \uC9C0\uC6B4\uD6C4 \uC22B\uC790\uD328\uB4DC\uB97C \uC774\uC6A9\uD558\uC5EC \uBC1B\uC744 \uAE08\uC561\uC744 \uC785\uB825\uD569\uB2C8\uB2E4.");
		info2.setBounds(34, 36, 514, 43);
		infoP.add(info2);

		JLabel info3 = new JLabel(
				"2. \uC2E0\uC6A9\uCE74\uB4DC \uB610\uB294 \uD604\uAE08 \uBC84\uD2BC\uC744 \uB20C\uB7EC \uC77C\uBD80 \uACB0\uC81C\uB97C \uC644\uB8CC\uD569\uB2C8\uB2E4.");
		info3.setBounds(34, 57, 467, 43);
		infoP.add(info3);

		JLabel info4 = new JLabel(
				"3. \uB0A8\uC740 \uC794\uC561\uC774 [\uBC1B\uC744 \uAE08\uC561]\uC5D0 \uB2E4\uC2DC \uB098\uD0C0\uB0A9\uB2C8\uB2E4.");
		info4.setBounds(34, 78, 291, 43);
		infoP.add(info4);

		JLabel info5 = new JLabel(
				"4. \uC2E0\uC6A9\uCE74\uB4DC \uB610\uB294 \uD604\uAE08 \uBC84\uD2BC\uC744 \uB20C\uB7EC\uC11C \uB0A8\uC740 \uC794\uC561\uC744 \uCC98\uB9AC\uD569\uB2C8\uB2E4.");
		info5.setBounds(34, 99, 467, 43);
		infoP.add(info5);

		JLabel info6 = new JLabel(
				"5. \uACB0\uC81C \uAE08\uC561\uC744 \uBAA8\uB450 \uBC1B\uC744 \uB54C\uAE4C\uC9C0 1\uBC88\uBD80\uD130 4\uBC88\uAE4C\uC9C0 \uBC18\uBCF5\uD569\uB2C8\uB2E4.");
		info6.setBounds(34, 120, 410, 43);
		infoP.add(info6);

		/////////////////////////////////////////////////////// coupon 기본*/
		couponP.setBounds(830, 699, 622, 107);
		couponP.setLayout(null);
		couponP.add(new Coupon());
		couponPanel.setSize(568, 107);
		couponPanel.setLocation(691, 694);
		couponPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(couponPanel);

		/////////////////////////////////////////////////////// 결제방법
		cardB.setFont(new Font("굴림", Font.BOLD, 15));
		cardB.setBounds(1293, 649, 156, 69);
		cardB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cardB.setBackground(new Color(106, 90, 205));
		cardB.setForeground(new Color(255, 255, 255));
		add(cardB);

		cashB.setFont(new Font("굴림", Font.BOLD, 15));
		cashB.setBounds(1293, 574, 156, 69);
		cashB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cashB.setBackground(new Color(106, 90, 205));
		cashB.setForeground(new Color(255, 255, 255));
		add(cashB);

		cardCashB.setFont(new Font("굴림", Font.BOLD, 15));
		cardCashB.setForeground(new Color(255, 255, 255));
		cardCashB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cardCashB.setBackground(new Color(106, 90, 205));
		cardCashB.setBounds(1293, 499, 156, 69);
		add(cardCashB);

		couponB = new JButton("\uCFE0  \uD3F0");
		couponB.setFont(new Font("굴림", Font.BOLD, 15));
		couponB.setBounds(1293, 726, 156, 69);
		add(couponB);
		couponB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		couponB.setBackground(new Color(255, 127, 80));
		couponB.setForeground(new Color(255, 255, 255));

		// eventListener
		this.cardB.addActionListener(pmc);
		this.cashB.addActionListener(pmc);
		this.cardCashB.addActionListener(pmc);
		minus.addActionListener(pmc);
		homeB.addActionListener(pmc);

	}

	
}
