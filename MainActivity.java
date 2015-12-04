package com.example.academy_intern.testingsms;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Response;
import java.util.HashMap;
import java.net.URL;
import java.util.Map;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText message;
    private EditText contacts;
    private Button btnInsert;
    //10.5.0.44

    private static final String REGISTER_URL = "http://10.5.0.44/fifi/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText) findViewById(R.id.txtMessage);
        contacts = (EditText) findViewById(R.id.txtContacts);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnInsert){
            RegisterUser();
        }

    }
    private void RegisterUser() {
        String name = message.getText().toString().trim().toLowerCase();
        String username = contacts.getText().toString().trim().toLowerCase();


        register(name,username);
    }

    private static final String TAG = "MyActivity";

    private void register(final String message, final String contacts) {
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            SmsUser ruc = new SmsUser();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                String response = s.toString();
                try {
                    JSONObject obj = new JSONObject(response);
                    String message = "";
                    String Contacts  ="";
                    String result = "";
                    String com = "";
                        try {
                            Log.d("TAG","Object ->" +obj );
                            message = obj.getString("Message").toString();
                            Contacts  = obj.getString("Contacts").toString();
                            result = obj.getString("result").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    com = " Message "+ message + " "+"\n Contacts"+ " "+  Contacts + " "+ "\n Result" + " "+  result;
                    Toast.makeText(getApplicationContext(), com , Toast.LENGTH_LONG).show();

                } catch (Throwable t) {
                    Log.e("My App", "Could not parse malformed JSON:" + response);
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("txtMessage", params[0]);
                data.put("txtContacts", params[1]);
                String result = ruc.sendPostRequest(REGISTER_URL, data);
                return result;
             }


        }

        RegisterUser ru = new RegisterUser();
        ru.execute(message, contacts);
    }
}
