package com.teddy.data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class About extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        VideoView myVideoView = (VideoView) findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myVideoView);
        try{//try to fetch vid from sdcard
            //String SrcPath = "/sdcard/week17boy.avi";//put your video in sdcard
            String vidpath = "android.resource://" + getPackageName() + "/" + R.raw.about;
            myVideoView.setVideoURI(Uri.parse(vidpath));
            //myVideoView.setVideoPath(SrcPath);
            myVideoView.setMediaController(new MediaController(this));
            myVideoView.requestFocus();
            myVideoView.start();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
        }
    
    }
    @Override
    public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
    	finish();
    	startActivity(i);
    	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
}