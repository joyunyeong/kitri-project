package com.kitri.Statistics.chart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ChartBookController implements ActionListener{

	ChartBook chartBook;
	ChartBookService chartBookService;
	
	public ChartBookController(ChartBook chartBook) {
		this.chartBook = chartBook;
		chartBookService = new ChartBookService(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == chartBook.search) {
			chartBookService.setGraphbestBook();
			
			JFreeChart chart = chartBookService.setGraphbestBook();
			
			ChartPanel pchart = new ChartPanel(chart);
			chartBook.chartpanel.add(pchart);
			pchart.setSize(1150, 450);
			pchart.setVisible(true);
		}
		
	}

}
