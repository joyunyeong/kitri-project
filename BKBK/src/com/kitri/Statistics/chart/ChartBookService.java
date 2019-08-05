package com.kitri.Statistics.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

import com.kitri.Statistics.StatBookDto;
import com.kitri.Statistics.StatMemberDto;

public class ChartBookService {

	Vector<StatMemberDto> bests = null; // Best5 상품 그래프용 저장 백터
	Vector<StatMemberDto> results = null; // 검색 결과 상품 테이블용 저장 백터

	StatDao statDao = new StatDao(); // Dao 객체

	ChartBookController chartBookController;
	private ChartBook cb;

	public ChartBookService(ChartBookController chartBookController) {
		this.chartBookController = chartBookController;
		cb = chartBookController.chartBook;
	}

	
	public JFreeChart setGraphbestBook() {

		 // 데이터 생성
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1
       
        StatDao dao = new StatDao();

		Vector<StatBookDto> list = new Vector<StatBookDto>();

		Vector<Integer> totalcount = new Vector<Integer>(); // 2
		Vector<String> bookName = new Vector<String>(); // 10대

		String startyear = (String) ChartBook.startyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String startmonth = (String) ChartBook.startmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String lastyear = (String) ChartBook.lastyear.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴
		String lastmonth = (String) ChartBook.lastmonth.getSelectedItem();// 버튼 눌려질때 콤보박스의 값을 읽어옴

		// 시작일
		System.out.println(startyear.concat(startmonth));
		// 종료일
		System.out.println(lastyear.concat(lastmonth));

		/////////////////////////////////////////////////////////////////////
		String startdate = startyear.concat(startmonth); // 시작일
		String lastdate = lastyear.concat(lastmonth); // 종료일

		list = dao.BookTotalGraph(startdate, lastdate);

		// 행 세팅
		int size = list.size();
		for (int i = 0; i < size; i++) {

			
			totalcount.addElement(list.get(i).getTotalcount()); // 상품 총 가격
			bookName.addElement(list.get(i).getBookName()); // 상품이름


			// 값, 범례, 카테고리 지정
			dataset1.addValue(totalcount.get(i), "대여수", bookName.get(i));
		}
        
       
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();

        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER 
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("맑은고딕", Font.BOLD, 14);
        Font axisF = new Font("맑은고딕", Font.PLAIN, 14);
        
        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.VERTICAL));
        renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white, 0.0f, 0.0f, Color.blue));
        renderer.setSeriesPaint(0, new Color(0,162,255));
 
        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
        
        // plot 에 데이터 적재
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

 
        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);             // 그래프 표시 방향
//        plot.setRangeGridlinesVisible(true);                       // X축 가이드 라인 표시여부
//        plot.setDomainGridlinesVisible(true);                      // Y축 가이드 라인 표시여부
 
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());              // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());                 // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
        
        
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("인기 도서 순위"); // 차트 타이틀
        chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 25));
        return chart;

        
	}
}
