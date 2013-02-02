package com.teddy.data;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Data extends Activity{
	
	public void showlist(String selected,String selected2)	{

		String T="null";
		try { T = (String)HTTPfunction("http://service-teddy2012.rhcloud.com/log"); }
		catch(Exception e){ T="No internet connection"; }
	
		final String[] A ={T};
		if(!T.contains("null")&& selected.contains("MVB") && selected2.contains("Day") ) A[0]=new String(T); 
		ListView list = (ListView)findViewById(R.id.statslist);
		
		ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, A);
		//ArrayAdapter<CharSequence> adapterlist = ArrayAdapter.createFromResource(this,R.array.period_array, android.R.layout.simple_spinner_item);
		adapterlist.setDropDownViewResource(android.R.layout.simple_list_item_1);
		list.setAdapter(adapterlist);
		list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Intent i = new Intent(getApplicationContext(), Info.class);
				i.putExtra("info", A[arg2]);
				startActivity(i);
			}
		});
	}
	
	
	public String HTTPfunction(String getURL) {
		try {
		    HttpClient client = new DefaultHttpClient();  
		    HttpGet get = new HttpGet(getURL);
		    HttpResponse responseGet = client.execute(get);  
		    HttpEntity resEntityGet = responseGet.getEntity();  
		    String response="No internet connection";
		    if (resEntityGet != null) {  
		        // do something with the response
		        response = EntityUtils.toString(resEntityGet);
		        //Log.i("GET RESPONSE", response);
		        return response;
		    }
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println(e.getMessage());
		}
	     return "No internet connection";
	}
}

