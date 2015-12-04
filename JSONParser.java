package com.example.academy_intern.testingsms;



import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
/**
 * Created by Academy_Intern on 2015/12/03.
 */
public class JSONParser {

   // public static String[] ids;
    public static String[] message;
    public static String[] contacts;

    public static final String JSON_ARRAY = "result";
    //public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE = "name";
    public static final String KEY_CONTACTS = "email";

    private JSONArray users = null;

    private String json;

    public JSONParser(String json){
        this.json = json;
    }

    protected void JSONParser(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            //ids = new String[users.length()];
            message = new String[users.length()];
            contacts = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                //ids[i] = jo.getString(KEY_ID);
                message[i] = jo.getString(KEY_MESSAGE);
                contacts[i] = jo.getString(KEY_CONTACTS);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
