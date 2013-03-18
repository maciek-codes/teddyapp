package com.teddy.data;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RelativeLayout;

public class PowerChart {
	private GraphicalView mChartView2;
	 static int count=2;
	 
	int[] Mycolors = new int[] { Color.parseColor("#ff1b3d"), Color.parseColor("#2890b7"), Color.BLUE, Color.parseColor("#ff1b3d"),Color.parseColor("#2890b7"),Color.GRAY };
	 
	 public Intent execute(Context context,RelativeLayout parent, double cost, double save, String textBkcolor) {
	  int[] colors = new int[count];
	  for(int i=0;i<count;i++)
	  {
	   colors[i]=Mycolors[i];
	  }
	  DefaultRenderer renderer = buildCategoryRenderer(colors);
	   renderer.setPanEnabled(false);// Disable User Interaction
	   if(textBkcolor.equals("White")) renderer.setLabelsColor(Color.BLACK);
       else renderer.setLabelsColor(Color.WHITE);
	   renderer.setShowLabels(true);
	   
	   //renderer.setChartTitle("Total Assets");
	   renderer.setLabelsTextSize(12);
	  CategorySeries categorySeries = new CategorySeries("Costs for 15 min");
	  categorySeries.add("Necessary Costs", cost-save);
	  categorySeries.add("Savings", save);
	  
	  mChartView2=ChartFactory.getPieChartView(context, categorySeries,renderer);
	  parent.removeAllViews();
	  parent.addView(mChartView2);
	  
	  return ChartFactory.getPieChartIntent(context, categorySeries, renderer,null);
	 }
	 protected DefaultRenderer buildCategoryRenderer(int[] colors) {
	  DefaultRenderer renderer = new DefaultRenderer();
	  for (int color : colors) {
	  SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	  r.setColor(color);
	  renderer.addSeriesRenderer(r);
	  
	  }
	  return renderer;
	  }
	}