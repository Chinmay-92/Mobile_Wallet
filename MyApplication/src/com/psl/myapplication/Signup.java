package com.psl.myapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class Signup extends ActionBarActivity implements OnClickListener {
	/** Called when the activity is first created. */

	static String json;
	EditText uname, pass, name, email, repass, mob;
	Button signup;
	String password, repassword;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		super.onCreate(savedInstanceState);

		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

		/*ImageButton home = (ImageButton) findViewById(R.id.logo);
		home.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), TabActivity.class);
				startActivity(intent);
			}
		});*/

		name = (EditText) findViewById(R.id.etnm);
		uname = (EditText) findViewById(R.id.etuser);
		pass = (EditText) findViewById(R.id.password);
		repass = (EditText) findViewById(R.id.etrpass);
		email = (EditText) findViewById(R.id.etem);
		mob = (EditText) findViewById(R.id.mob);

		signup = (Button) findViewById(R.id.sign);
		signup.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.sign) {

			if (!pass.getText().toString().equals(repass.getText().toString())) {
				pass.setError("Password Doesnt match");
				repass.setText("");
			} else {

				final String dbURI = "mongodb://zaid:zaid@ds047802.mongolab.com:47802/aadhar";
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);

				MongoClientURI uri = new MongoClientURI(dbURI);
				MongoClient mongoClient = null;
				// mongoClient = new MongoClient(new
				// MongoClientURI(dbURI),"localhost",8080);
				try {
					mongoClient = new MongoClient(uri);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Success");

				DB db = mongoClient.getDB("aadhar");

				DBCollection table = db.getCollection("User");

				DBCursor curs = table.find();
				boolean exists = false;
				while (curs.hasNext()) {
					DBObject o = curs.next();
					String Uid = (String) o.get("Uid");
					if (Uid.equals(uname.getText().toString())) {
						uname.setError("UserId already exists");
						exists = true;
					} else {
						continue;
					}
				}

				if (!exists) {

					BasicDBObject document = new BasicDBObject();
					document.put("Uid", uname.getText().toString());
					document.put("Password", pass.getText().toString());
					document.put("Name", name.getText().toString());
					document.put("Email", email.getText().toString());
					document.put("Mobile no", mob.getText().toString());

					// document.put("createdDate", new Date(0));
					table.insert(document);

					Intent intent = new Intent(this, Login.class);
					startActivity(intent);

				}
			}
		}

	}

}
