package com.teddy.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
 
public class ConnectionDetector {
 
    private Context _context;
 
    public ConnectionDetector(Context context){
        this._context = context;
    }
 
    public boolean isConnectingToInternet(){
    	ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null)
			return false;
  
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		
		if (info == null || !info.isAvailable() || !info.isConnected())
			return false;
		  
		return true; 
    }
}