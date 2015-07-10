package com.example.adhaar;

/*import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

	*//** Called when the activity is first created. *//*
	TextView aadhaarText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_second);
	    aadhaarText = (TextView) findViewById(R.id.textView1);
	    // TODO Auto-generated method stub
	    String passedArg = getIntent().getExtras().getString("arg");
	    passedArg = "Hello" + passedArg;
	    aadhaarText.setText(passedArg);
	}

}*/


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends Activity {
    //EditText aadhaarText;
    //EditText pincodeText;
    EditText otp;
    Button getDetails;
    TextView aadhaarText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        aadhaarText = (TextView) findViewById(R.id.textView1);
        String passedArg = getIntent().getExtras().getString("arg");
	    passedArg = "Hello" + passedArg;
	    aadhaarText.setText(passedArg);
	   // setContentView(aadhaarText);      
        // Set the text view as the activity layout
       /* setContentView(aadhaarText);
        TextView text1 = new TextView(this);
        text1.setTextSize(40);
        text1.setText(passedArg);
        setContentView(text1);
    
        setContentView(aadhaarText);*/
        otp = (EditText) findViewById(R.id.otpText);
        getDetails = (Button) findViewById(R.id.sendOTP);
    
        getDetails.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String aadhaarId = getIntent().getExtras().getString("arg");
                //String pincode= pincodeText.getText().toString();
                String otpcode=otp.getText().toString();
                NetworkHandler nh = new NetworkHandler();
                //nh.execute(aadhaarId);
                //nh.execute(pincode);
                nh.execute(aadhaarId,otpcode);

            }
        });
      
  
       
      

    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class NetworkHandler extends AsyncTask<String, String, String> {

        //public static final String OTP_GENERATION_URL = "https://ac.khoslalabs.com/hackgate/hackathon/otp";
        public static final String OTP_VERIFICATION_URL = "https://ac.khoslalabs.com/hackgate/hackathon/kyc/raw";
        String sResponse = "";

        @Override
        protected String doInBackground(String... params) {
            String aadhaarNo = (String) params[0];
            //String pincodeNo = (String) params[1];
            //String aadhaarNo="714486858146";
        	String otpNo = (String) params[1];

            try {
                JSONObject detailsObj = new JSONObject();
                detailsObj.put("consent", "Y");
                JSONObject requestObj = new JSONObject();
                requestObj.put("aadhaar-id", aadhaarNo);
                requestObj.put("modality", "otp");
                requestObj.put("otp", otpNo);
                requestObj.put("device-id", "public");
                requestObj.put("certificate-type", "preprod");
                JSONObject locationObj = new JSONObject();
                locationObj.put("type", "pincode");
                locationObj.put("pincode", "421503");
                requestObj.put("location", locationObj);
                requestObj.put("channel", "SMS");
                detailsObj.put("auth-capture-request", requestObj);

                KeyStore trustStore = KeyStore.getInstance(KeyStore
                        .getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                DefaultHttpClient client = new DefaultHttpClient();
                HttpProtocolParams.setVersion(client.getParams(),
                        HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(client.getParams(),
                        HTTP.UTF_8);
                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("http", PlainSocketFactory
                        .getSocketFactory(), 80));
                registry.register(new Scheme("https", sf, 443));

                ClientConnectionManager mgr = new ThreadSafeClientConnManager(
                        client.getParams(), registry);

                DefaultHttpClient httpClient = new DefaultHttpClient(mgr,
                        client.getParams());

                HttpPost postRequest = new HttpPost(OTP_VERIFICATION_URL);
                StringEntity strEntity = new StringEntity(
                        detailsObj.toString());
                Log.d("format", detailsObj.toString());
                postRequest.setEntity(strEntity);

                HttpResponse response = httpClient.execute(postRequest);

                Log.v("MSG", response.getStatusLine().toString());
                HttpEntity entity = response.getEntity();
                sResponse = EntityUtils.toString(entity);
                Log.i("MSG", sResponse);

                httpClient.getConnectionManager().shutdown();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {

                Log.e(e.getClass().getName(), e.getMessage());

            }

            return sResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
            	Log.d("result", result);
                JSONObject respObject=new JSONObject(result);
            	Log.d("result", respObject.getJSONObject("kyc").getJSONObject("poi").getString("name"));
                if(respObject.getBoolean("success")){
                    Toast.makeText(SecondActivity.this, respObject.getString("aadhaar-reference-code"), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SecondActivity.this, "Some issues arising", Toast.LENGTH_SHORT).show();
                }
                

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }

    }


    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port,
                                   boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host,
                    port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }

}