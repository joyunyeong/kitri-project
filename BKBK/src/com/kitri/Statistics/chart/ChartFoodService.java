package com.kitri.Statistics.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;


import com.kitri.Statistics.StatFoodDto;
import com.kitri.Statistics.StatMemberDto;

public class ChartFoodService {

	
	public static Component chart;


	StatDao statDao = new StatDao(); // Dao 객체

	ChartFoodController chartFoodController;
	private ChartFood cf;

	public ChartFoodService(ChartFoodController chartFoodController) {
		this.chartFoodController = chartFoodController;
		cf = chartFoodController.chartFood;
	}

	// [기능 메소드]

	// <조회 버튼 클릭> 이벤트
	public void search() {


		System.out.println("일별 조회 버튼이 눌렸습니다.");

		String startyear = (String) ChartFood.startyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String startmonth = (String) ChartFood.startmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String lastyear = (String) ChartFood.lastyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String lastmonth = (String) ChartFood.lastmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴

		// 시작일
		System.out.println(startyear.concat(startmonth));
		// 종료일
		System.out.println(lastyear.concat(lastmonth));

		/////////////////////////////////////////////////////////////////////
		String startdate = startyear.concat(startmonth); // 시작일
		String lastdate = lastyear.concat(lastmonth); // 종료일
		System.out.println(startdate + " 마지막 " + lastdate);

		StatDao statDao = new StatDao(); // Dao 객체


		chartFoodController.chartFood.rows = statDao.selectFoodRanking(startdate, lastdate);
		
		
		System.out.println("사이즈" + chartFoodController.chartFood.rows.size());


		chartFoodController.chartFood.model.setDataVector(chartFoodController.chartFood.rows,
				chartFoodController.chartFood.column1);
		
		
		setGraphbestProducts();

	}

	static JFreeChart setGraphbestProducts() {

	
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			 StatDao dao = new StatDao();
			 
			 Vector<StatFoodDto> list = new Vector<StatFoodDto>();
			  
			  Vector<String> productName = new Vector<String>();        // 10대
			  Vector<Integer> totalSellPrice = new Vector<Integer>(); // 2
			  
			  
			  
			  String startyear = (String) ChartFood.startyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
				String startmonth = (String) ChartFood.startmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
				String lastyear = (String) ChartFood.lastyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
				String lastmonth = (String) ChartFood.lastmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴

				// 시작일
				System.out.println(startyear.concat(startmonth));
				// 종료일
				System.out.println(lastyear.concat(lastmonth));

				/////////////////////////////////////////////////////////////////////
				String startdate = startyear.concat(startmonth); // 시작일
				String lastdate = lastyear.concat(lastmonth); // 종료일
				
			 list = dao.productTotalGraph(startdate, lastdate);
			  
			  // 행 세팅 
			  int size = list.size(); 
			  for (int i = 0; i < size; i++) { 
			   
				  productName.addElement(list.get(i).getProductName()); //상품이름
				  totalSellPrice.addElement(list.get(i).getTotalSellPrice()); //상품 총 가격
			   
			  // 값, 범례, 카테고리 지정 
				  dataset.setValue(totalSellPrice.get(i),  "매출합계", productName.get(i)); 
			  } 
			  
			 
			JFreeChart chart = ChartFactory.createBarChart	(
				"상품 매출","FoodName", "sum", dataset,
				PlotOrientation.VERTICAL, false,true, false);
			chart.setBackgroundPaint(Color.white);
			chart.getTitle().setPaint(Color.BLACK); 
			chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 25));
		
			
			CategoryPlot p = chart.getCategoryPlot(); 
			p.setRangeGridlinePaint(Color.yellow); 
			
			// X 축의 도메인 설정입니다.(이거다!!!!!!!!!!!!!!1)
			p.getDomainAxis().setTickLabelFont(new Font("맑은고딕", Font.PLAIN, 8));
			
			return chart;
	}
}
	
	
	
	
	
	