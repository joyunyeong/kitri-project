package com.kitri.Statistics.chart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ChartFoodController implements ActionListener{

	ChartFood chartFood;
	ChartFoodService chartFoodService;
	
	public ChartFoodController(ChartFood chartFood) {
		this.chartFood = chartFood;
		chartFoodService = new ChartFoodService(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == chartFood.search) {
			chartFoodService.search();
			JFreeChart chart = chartFoodService.setGraphbestProducts();
			
			ChartPanel pchart = new ChartPanel(chart);
			chartFood.chartpanel.add(pchart);
			pchart.setSize(560, 450);
			pchart.setVisible(true);
		}
		
	}

}
