package com.psl.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Title extends Activity implements OnClickListener {
ImageButton icon,cart,login ,email;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mytitle);
		
		Log.d("in title.java","hi");
	
}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}