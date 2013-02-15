package com.teddy.data;


import org.json.JSONException;
import org.json.JSONObject;
import android.view.WindowManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
 
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;
 
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.widget.RelativeLayout;


public class Info extends Activity {

	double[] Costs = {-1,-1};
	double powerCost = 0;
	double idleCost = 0;
	String requestUrl="",timeselected="";
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.info);
        
        
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        Bundle extras = getIntent().getExtras(); 
        if(extras !=null)
        {
        	requestUrl = extras.getString("URL");
        	timeselected = extras.getString("period");
        	new GetStatsTask().execute(requestUrl);
        	
        }       
	}
	
	public GraphicalView createIntent() {
        String[] titles = new String[] { "Amount"};
        List<double[]> values = new ArrayList<double[]>();
          
        values.add(Costs);
        int[] colors = new int[] { Color.parseColor("#FF6501")};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(Orientation.HORIZONTAL);
        setChartSettings(renderer, "Costs for "+timeselected, "", "Pounds", 0.5,
            2.5, 0, Costs[0]*1.3, Color.BLACK, Color.BLACK);
        renderer.setXLabels(0);
        renderer.setYLabels(10);
        renderer.addXTextLabel(1, "Costs");
        renderer.addXTextLabel(2, "Possible savings");
       
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
          SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
          seriesRenderer.setDisplayChartValues(true);
        }
 
 
        final GraphicalView grfv = ChartFactory.getBarChartView(Info.this, buildBarDataset(titles, values), renderer,Type.DEFAULT);
   
         
        return grfv;
      }
      protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
            renderer.setAxisTitleTextSize(16);
            renderer.setChartTitleTextSize(20);
            renderer.setLabelsTextSize(15);
            renderer.setLegendTextSize(15);
            renderer.setBarSpacing(1);
             
            renderer.setMarginsColor(Color.parseColor("#EEEDED"));
            renderer.setXLabelsColor(Color.BLACK);
            renderer.setYLabelsColor(0,Color.BLACK);
             
            renderer.setApplyBackgroundColor(true);
            renderer.setBackgroundColor(Color.parseColor("#FBFBFC"));
             
            int length = colors.length;
            for (int i = 0; i < length; i++) {
              SimpleSeriesRenderer r = new SimpleSeriesRenderer();
              r.setColor(colors[i]);
              r.setChartValuesSpacing(15);
              renderer.addSeriesRenderer(r);
            }
            return renderer;
          }
      protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            int length = titles.length;
            for (int i = 0; i < length; i++) {
              CategorySeries series = new CategorySeries(titles[i]);
              double[] v = values.get(i);
              int seriesLength = v.length;
              for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
              }
              dataset.addSeries(series.toXYSeries());
            }
            return dataset;
          }
      protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
              String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
              int labelsColor) {
            renderer.setChartTitle(title);
            renderer.setYLabelsAlign(Align.RIGHT);
            renderer.setXTitle(xTitle);
            renderer.setYTitle(yTitle);
            renderer.setXAxisMin(xMin);
            renderer.setXAxisMax(xMax);
            renderer.setYAxisMin(yMin);
            renderer.setYAxisMax(yMax);
            renderer.setMargins(new int[] { 30, 65, 10, 15 });
            renderer.setAxesColor(axesColor);
            renderer.setLabelsColor(labelsColor);
          }
      
      
      public class GetStatsTask extends AsyncTask<String, Void, JSONObject> {
  		
  		// Progress dialog
  		ProgressDialog prog;
  		    
  		protected void onPreExecute() {
  			prog = new ProgressDialog(Info.this);
  		    prog.setTitle("Waiting...");
  		    prog.setMessage("Retrieving data from the service.");       
  		    prog.setIndeterminate(false);
  		    prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
  		    prog.show();
  		    
  		}
  		
  		protected JSONObject doInBackground(String... urls) {
  			JsonParser parser = new JsonParser();
  			
  			JSONObject jObject = null;
			try {
				jObject = parser.getJSONFromUrl(urls[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  			return jObject;
  			
  		}
  		
  	
  	     protected void onPostExecute(JSONObject result) {
      	 	prog.hide();
     			
  			try {
  				powerCost = result.getDouble("Total_Power_Cost");
  				idleCost = result.getDouble("power_cost_no_idle");
  				} catch (JSONException e) {
  					// TODO Catch exception here if JSON is not formulated well
  					e.printStackTrace();
  				}
  				catch (NullPointerException e) {
  					e.printStackTrace();
  				}	
  			Costs[0]=powerCost;
  	        Costs[1]=idleCost;
  			final GraphicalView gv =createIntent();
  	        RelativeLayout rl=(RelativeLayout)findViewById(R.id.graph);
  	        rl.addView(gv);
  	     }
  	     
  	     
  	     
  	     
  	}
      @Override
      public void onBackPressed() {
  		Intent i = new Intent(getApplicationContext(),  Stats.class);
      	finish();
      	startActivity(i);
      	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
      }
}