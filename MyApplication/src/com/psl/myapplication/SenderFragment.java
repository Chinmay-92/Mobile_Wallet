package com.psl.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SenderFragment extends Fragment {
	public static SenderFragment newInstance(String imageUrl) {

		final SenderFragment mf = new SenderFragment ();

		    final Bundle args = new Bundle();
		    args.putString("somedata", "somedata");
		    mf.setArguments(args);

		    return mf;
		}

		public SenderFragment() {}

		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    //String data = getArguments().getString("somedata");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		    // Inflate and locate the main ImageView
		    final View v = inflater.inflate(R.layout.activity_main_lib, container, false);
		     
		    //... 
		    return v;
		}
}
