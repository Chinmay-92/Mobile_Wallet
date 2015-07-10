package com.psl.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends ActionBarActivity implements OnClickListener {

	Button login;
	EditText userName, password;
	TextView tv1;

	public void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

		userName = (EditText) findViewById(R.id.etun1);
		password = (EditText) findViewById(R.id.etps);
		login = (Button) findViewById(R.id.login);
		tv1 = (TextView) findViewById(R.id.signin);
		login.setOnClickListener(this);
		tv1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.signin) {

			Intent intent = new Intent(this, Signup.class);
			startActivity(intent);
		}

		else if (v.getId() == R.id.login) {
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
			boolean success = false, pass = false,uname=false;
			while (curs.hasNext()) {
				DBObject o = curs.next();
				String Uid = (String) o.get("Uid");
				if (Uid.equals(userName.getText().toString())) {
					String Password = (String) o.get("Password");
					if (Password.equals(password.getText().toString())) {
						Toast.makeText(getApplicationContext(),
								"successful login", Toast.LENGTH_LONG).show();
						pass = true;
						uname=true;
						success = true;
						break;
					} else {
						uname = true;
						pass = false;
						break;
					}

				} else {
					continue;
				}

			}
			if (!pass) {

				if (uname) {
					password.setError("Password doesnt exists");
				} else {
					userName.setError("UserId doesnt exists");

				}

			} else if(success) {
				Intent intent = new Intent(this, TabActivity.class);
				startActivity(intent);
			}

		}

	}

}