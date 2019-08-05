//package com.kitri.managerframe;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//public class ManagerFrame extends JFrame {
//
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManagerFrame frame = new ManagerFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public ManagerFrame() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//	}
//}

//<html>잘생긴승훈이 멋졌어<br>그렇게 멋진녀석이...<br></html>
package com.kitri.Main.frame;

import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.kitri.Book.pay.payMain;
import com.kitri.Book.rent.RentalMain;
import com.kitri.Food.FoodPanel;
import com.kitri.Main.EditMember.EditMemberFrame;
import com.kitri.Main.JoinMember.JoinMemberFrame;
import com.kitri.Main.memberDto.MemberDao;
import com.kitri.Main.memberDto.MemberDto;
import com.kitri.Main.swing.box.FPanel;
import com.kitri.Manager.main.MgmtMain;
import com.kitri.Statistics.chart.StatisticsPanel;

public class MainFrame extends JFrame implements Runnable {

	ImageIcon icon;
	BufferedReader in;
	public MainController mc;
	
	public MainFrameService mfs;
	public JoinMemberFrame jmf;
	public EditMemberFrame emf;
	public FPanel[] fp = new FPanel[30];
	public Vector<String> vt = new Vector<String>();
	public DefaultTableModel pay;
	public DefaultTableModel book;
	public static String ID=""; // = new MemberDto(null, null, null, null, null, null, 0, 0, 0);
//	public static String MEMBERID;

	public MemberDao dao;
	JPanel panelTable;
	public JPanel contentPane = new JPanel();
	public JPanel panel = new JPanel();
	public JPanel panelDate = new JPanel();
	JPanel panelManager;
	public CardLayout card = new CardLayout(0, 0);
	public JPanel panelMember = new JPanel(card);
	public JPanel panelLogInMember;
	JPanel panelMemberImage;
	JPanel panelLogIn;
	JTable tablePayment;
	public JTable tableBookRental;
	JComboBox comboBoxVoucher;
	String[] title = { "이용권 선택", "1시간 이용권", "2시간 이용권", "3시간 이용권" };
	JButton buttonGuest;
	JButton buttonLogIn;
	JButton buttonMlogin;
	JButton buttonMcancel;
	JTextField tfMemberID;
	JLabel titleL;
	JLabel dateL;
	JLabel timeL;
	JLabel labelLogInName;
	JLabel labelLogInBirth;
	JLabel labelCouponNum1;
	JLabel labelCouponNum2;
	JLabel image;
	String date;
	String time;

	JPasswordField tfManagerId;
	JLabel fullPrice;
	JLabel label_1;
	JButton buttonMiner;
	JTextField tfPhoneNum;
	private JPanel panelBookJtable;
	
	
//----------------------------------------------------------------------------------------------------------------------------------[h]각 서비스 패널 생성
	public CardLayout serCard = new CardLayout();
	public JPanel panelCard;
	public FoodPanel foodPanel;
	public RentalMain rentalMain;
	public MgmtMain managerMain;
	public payMain pm;
	

	/**
	 * Create the frame.
	 */
//	mfs.currentDate()
//	
	public MainFrame() {
		mc = new MainController(this);
		jmf = new JoinMemberFrame(this);
		emf = new EditMemberFrame(this);
		dao = new MemberDao(this);
		foodPanel = new FoodPanel(this);
		managerMain = new MgmtMain(this);
		pm = new payMain(this);
		

		// 화면 첫번째 줄의 날짜 및 시간 표시_Label
		titleL = new JLabel("//북크북크// 1호점"); // 점포이름
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		titleL.setToolTipText("");
		titleL.setBorder(new LineBorder(new Color(200, 200, 200)));

		dateL = new JLabel(); // 날짜
		dateL.setBorder(new LineBorder(new Color(200, 200, 200)));
		dateL.setHorizontalAlignment(SwingConstants.CENTER);
		dateL.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));

		timeL = new JLabel(); // 시간
		timeL.setBorder(new LineBorder(new Color(200, 200, 200)));
		timeL.setHorizontalAlignment(SwingConstants.CENTER);
		timeL.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));

		panelDate.setLayout(new GridLayout(0, 3, 0, 0));
		panelDate.add(titleL);
		panelDate.add(dateL);
		panelDate.add(timeL);

		contentPane.setLayout(null);

		panelDate.setBorder(new LineBorder(SystemColor.scrollbar, 2));
		panelDate.setBackground(SystemColor.controlHighlight);
		panelDate.setBounds(0, 0, 1494, 35);

		panelCard = new JPanel();
		panelCard.setBounds(0, 36, 1494, 835);
		contentPane.add(panelCard);
		panelCard.setLayout(serCard);
		
//----------------------------------------------------------------------------------------------------------------------------------[h]각 서비스 전환 패널 cardlayout
		
		//[h]Main Panel
		panelCard.add(panel, "Main");
		//[h]Food Panel
		panelCard.add(foodPanel, "Food");
		panelCard.add(managerMain, "Manager");
		panelCard.add(managerMain.mmc.statisticsPanel, "Statistics");
		panelCard.add(pm,"pay");
		serCard.show(panelCard, "Main");
		
		rentalMain = new RentalMain(this);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setLocation(screenSize.width/2, screenSize.height/2);
		setTitle("북크북크");
		Image img = kit.getImage(".\\src\\com\\kitri\\Manager\\image\\bkbk.jpg");
		setIconImage(img);
		
//-----------------------------------------------------------------------------------------------------------------------------------//[h]		
		
		panel.setBackground(SystemColor.window);

		panel.setLayout(null);
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 653, 300, 25);
		panel.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel labelBR = new JLabel("도서 대여 확인");
		panel_3.add(labelBR);
		labelBR.setBackground(new Color(240, 230, 140));
		// ----------------------------------------------------------------------------------
		panelManager = new JPanel();
		panelManager.setBorder(new LineBorder(new Color(255, 175, 175), 10));
		panelManager.setBackground(Color.WHITE);
		panelManager.setBounds(900, 300, 300, 199);
		panel.add(panelManager);
		panelManager.setLayout(null);
		panelManager.setVisible(false);

		tfManagerId = new JPasswordField();
		tfManagerId.setBounds(59, 114, 186, 21);
		panelManager.add(tfManagerId);

		JLabel lblNewLabel = new JLabel("관리자 번호");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(59, 38, 186, 66);
		panelManager.add(lblNewLabel);

		buttonMlogin = new JButton("LogIn");
		buttonMlogin.setBackground(new Color(219, 112, 147));
		buttonMlogin.setBounds(166, 155, 97, 23);
		panelManager.add(buttonMlogin);

		buttonMcancel = new JButton("취소");
		buttonMcancel.setBounds(39, 155, 97, 23);
		panelManager.add(buttonMcancel);
		// ------------------------------------------------------------------------------
		JButton buttonB = new JButton("도서대출");
		buttonB.setForeground(SystemColor.textHighlightText);
		buttonB.setBackground(SystemColor.activeCaption);
		buttonB.setBounds(1336, 79, 148, 61);
		panel.add(buttonB);

		JButton buttonJM = new JButton("회원등록");
		buttonJM.setForeground(SystemColor.textHighlightText);
		buttonJM.setBackground(SystemColor.activeCaption);
		buttonJM.setBounds(1336, 162, 148, 61);
		panel.add(buttonJM);

		JButton buttonEM = new JButton("회원정보수정");
		buttonEM.setForeground(SystemColor.textHighlightText);
		buttonEM.setBackground(SystemColor.activeCaption);
		buttonEM.setBounds(1336, 246, 148, 61);
		panel.add(buttonEM);

		JButton buttonF = new JButton("음식주문");
		buttonF.setForeground(SystemColor.textHighlightText);
		buttonF.setBackground(SystemColor.activeCaption);
		buttonF.setBounds(1336, 458, 148, 61);
		panel.add(buttonF);

		JButton buttonManager = new JButton("관리자");
		buttonManager.setForeground(SystemColor.textHighlightText);
		buttonManager.setBackground(SystemColor.activeCaption);
		buttonManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonManager.setBounds(1336, 764, 148, 61);
		panel.add(buttonManager);
		// ---------------------------------------------------콤보박스
		comboBoxVoucher = new JComboBox();
		comboBoxVoucher.setModel(new DefaultComboBoxModel(title));
		comboBoxVoucher.setBounds(1336, 529, 148, 48);
		panel.add(comboBoxVoucher);

		JButton buttonP = new JButton("결제");
		buttonP.setForeground(SystemColor.text);
		buttonP.setBackground(new Color(220, 20, 60));
		buttonP.setBounds(12, 769, 300, 50);
		panel.add(buttonP);
		panelMember.setBackground(SystemColor.inactiveCaptionBorder);

		// -------------------------------------------------------------------------------회원로그인부분

		panelMember.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMember.setBounds(12, 29, 300, 140);
		panel.add(panelMember);

		panelLogIn = new JPanel();
		panelLogIn.setBorder(new CompoundBorder());
		panelLogIn.setBackground(SystemColor.inactiveCaptionBorder);
		panelMember.add(panelLogIn, "LogIn");
		panelLogIn.setLayout(null);

		JLabel labelID = new JLabel("회원 이름 : ");
		labelID.setBounds(58, 23, 89, 15);
		panelLogIn.add(labelID);

		tfMemberID = new JTextField("");
		tfMemberID.setBackground(SystemColor.text);
		tfMemberID.setBounds(129, 20, 116, 21);
		panelLogIn.add(tfMemberID);
		tfMemberID.setColumns(15);

		buttonGuest = new JButton("비회원");
		buttonGuest.setForeground(SystemColor.text);
		buttonGuest.setBackground(SystemColor.controlShadow);
		buttonGuest.setBounds(25, 93, 108, 23);
		panelLogIn.add(buttonGuest);

		buttonLogIn = new JButton("회원로그인");
		buttonLogIn.setBackground(SystemColor.control);
		buttonLogIn.setBounds(167, 93, 108, 23);
		panelLogIn.add(buttonLogIn);

		tfPhoneNum = new JTextField();
		tfPhoneNum.setBounds(129, 51, 116, 21);
		panelLogIn.add(tfPhoneNum);
		tfPhoneNum.setColumns(10);

		JLabel label = new JLabel("\uC804\uD654 \uBC88\uD638 : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(47, 54, 89, 15);
		panelLogIn.add(label);

		// ------------------------------------------------------------

		panelLogInMember = new JPanel();
		panelLogInMember.setBackground(SystemColor.inactiveCaptionBorder);
		panelMember.add(panelLogInMember, "회원로그인");
		panelLogInMember.setLayout(null);

		labelLogInName = new JLabel("");
		labelLogInName.setFont(new Font("굴림", Font.PLAIN, 18));
		labelLogInName.setBounds(127, 31, 90, 21);
		panelLogInMember.add(labelLogInName);

		labelLogInBirth = new JLabel("");
		labelLogInBirth.setBounds(127, 52, 90, 15);
		panelLogInMember.add(labelLogInBirth);

		// try {
		// in = new BufferedReader(new InputStreamReader(
		// new FileInputStream(new
		// File("C:\\Users\\Administrator\\Pictures\\memberIcon", "Guest.png"))));
		// } catch (FileNotFoundException e1) {
		//
		// e1.printStackTrace();
		// }

		panelMemberImage = new JPanel();
		panelMemberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMemberImage.setBounds(30, 26, 85, 79);
		panelMemberImage.setPreferredSize(new Dimension(30, 26));
		panelLogInMember.add(panelMemberImage);
		panelMemberImage.setLayout(null);

		image = new JLabel("이미지 없음");
		image.setBounds(0, 0, 85, 79);
		panelMemberImage.add(image);

		JLabel labelCoupon1 = new JLabel("생일쿠폰");
		labelCoupon1.setBounds(127, 77, 57, 15);
		panelLogInMember.add(labelCoupon1);

		JLabel labelCoupon2 = new JLabel("천원쿠폰");
		labelCoupon2.setBounds(196, 77, 57, 15);
		panelLogInMember.add(labelCoupon2);

		labelCouponNum1 = new JLabel("");
		labelCouponNum1.setBounds(127, 90, 57, 15);
		panelLogInMember.add(labelCouponNum1);

		labelCouponNum2 = new JLabel("");
		labelCouponNum2.setBounds(196, 90, 57, 15);
		panelLogInMember.add(labelCouponNum2);

		JButton btnLogout = new JButton("Logout");

		btnLogout.setBounds(206, 113, 87, 15);
		panelLogInMember.add(btnLogout);
		// ----------------------------------------------------결제내역
		JPanel panelPH = new JPanel();
		panel.add(panelPH);
		panelPH.setLayout(null);
		panelPH.setBackground(new Color(245, 222, 179));
		panelPH.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPH.setBounds(12, 179, 300, 580);

		JLabel labelPM = new JLabel("결제 내역");
		labelPM.setBounds(121, 0, 57, 25);
		panelPH.add(labelPM);
		
		String[] columnPay = { "번호", "상품종류", "주문상품", "갯수", "가격" };
		String[][] rowData = {};
		pay = new DefaultTableModel(rowData, columnPay);
		tablePayment = new JTable(pay);
//		JScrollPane jp = new JScrollPane(tablePayment);

		JPanel paneljtable = new JPanel();
		paneljtable.setForeground(new Color(0, 0, 0));
		paneljtable.setBorder(new LineBorder(UIManager.getColor("Button.focus")));
		paneljtable.setBackground(new Color(245, 222, 179));
		paneljtable.setBounds(0, 24, 300, 377);
		panelPH.add(paneljtable);
		paneljtable.setLayout(new BorderLayout(0, 0));

		paneljtable.add(tablePayment.getTableHeader(), BorderLayout.NORTH);
		paneljtable.add(tablePayment, BorderLayout.CENTER);
		tablePayment.setBackground(new Color(245, 222, 179));
		tablePayment.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePayment.setBounds(0, 22, 300, 370);
		tablePayment.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablePayment.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablePayment.getColumnModel().getColumn(2).setPreferredWidth(280);
		tablePayment.getColumnModel().getColumn(2).setMinWidth(40);
		tablePayment.getColumnModel().getColumn(3).setPreferredWidth(45);

		panelBookJtable = new JPanel();
		panelBookJtable.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBookJtable.setBackground(new Color(245, 245, 220));
		panelBookJtable.setBounds(0, 498, 300, 82);
		panelPH.add(panelBookJtable);
		panelBookJtable.setLayout(new BorderLayout(0, 0));

		String[] columnBook = { "책 이름", "반납날짜", "연체금액", "연체일" };
		String[][] rowData2 = {};
		book = new DefaultTableModel(rowData2, columnBook);
		tableBookRental = new JTable(book);
//		JScrollPane jp2 = new JScrollPane(tableBookRental);
		tableBookRental.setBounds(1, 498, 298, 80);
		tableBookRental.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tableBookRental.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableBookRental.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableBookRental.getColumnModel().getColumn(2).setPreferredWidth(100);
		// tableBookRental.getColumnModel().getColumn(3).setPreferredWidth(70);
		panelBookJtable.add(tableBookRental.getTableHeader(), BorderLayout.NORTH);
		panelBookJtable.add(tableBookRental, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 0, 300, 25);
		panelPH.add(panel_2);

		fullPrice = new JLabel("");
		fullPrice.setOpaque(true);
		fullPrice.setBackground(new Color(255, 255, 255));
		fullPrice.setBounds(73, 410, 162, 23);
		panelPH.add(fullPrice);

		label_1 = new JLabel("\uCD1D\uACC4 : ");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBackground(new Color(255, 255, 255));
		label_1.setBounds(12, 410, 74, 23);
		panelPH.add(label_1);

		buttonMiner = new JButton("-");
		buttonMiner.setForeground(Color.WHITE);
		buttonMiner.setBackground(SystemColor.activeCaptionBorder);
		buttonMiner.setBounds(237, 410, 49, 23);
		panelPH.add(buttonMiner);
		buttonGuest.addActionListener(mc);
		buttonLogIn.addActionListener(mc);
		buttonEM.addActionListener(mc);
		buttonJM.addActionListener(mc);
		buttonP.addActionListener(mc);
		buttonB.addActionListener(mc);
		buttonF.addActionListener(mc);
		buttonManager.addActionListener(mc);
		comboBoxVoucher.addActionListener(mc);
		btnLogout.addActionListener(mc);
		tfMemberID.addActionListener(mc);
		tfPhoneNum.addActionListener(mc);
		buttonMcancel.addActionListener(mc);
		buttonMlogin.addActionListener(mc);
		buttonMiner.addActionListener(mc);
		tfManagerId.addActionListener(mc);
		contentPane.add(panelDate);

		icon = new ImageIcon("C:\\Users\\Administrator\\Pictures\\memberIcon\\배경화면.jpg");
		panelTable = new JPanel();
//		{
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon.getImage(), 0, 0, null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
		panelTable.setForeground(Color.LIGHT_GRAY);
		panelTable.setBackground(Color.WHITE);
		panelTable.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		panelTable.setBounds(324, 10, 1000, 800);
		panel.add(panelTable);
		panelTable.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		// "<html>이름<br>이용시간<br><br><br><br><br><br>금액</html>";<h1
		// style="text-align:center;">
		int len = fp.length;
		for (int i = 0; i < len; i++) {

			fp[i] = new FPanel(/* mc */);
			fp[i].setPreferredSize(new Dimension(150, 150));
			panelTable.add(fp[i]);
			fp[i].addMouseListener(mc);
			fp[i].setName(i + "");

		}

		setContentPane(contentPane);
		setBounds(100, 100, 1500, 900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Launch the application.
	 */
	@Override
	public void run() {
		while (true) {
			mc.mfs.currentDate();
			mc.mfs.currentTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public JLabel getDateL() {
		return dateL;
	}

	public void setDateL(JLabel dateL) {
		this.dateL = dateL;
	}

	public JLabel getTimeL() {
		return timeL;
	}

	public void setTimeL(JLabel timeL) {
		this.timeL = timeL;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					Thread t = new Thread(frame);
					t.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
