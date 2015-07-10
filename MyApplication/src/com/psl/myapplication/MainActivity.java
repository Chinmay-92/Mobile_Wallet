package com.psl.myapplication;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
	                finish();
			        Intent intent = new Intent(MainActivity.this,Login.class);
			        startActivity(intent);
				}
			};
		 	Timer timer = new Timer();
		 	timer.schedule(task, 5000);
	}

	
}
