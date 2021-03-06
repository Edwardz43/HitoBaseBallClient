package org.iii.fsit03.hitobaseball;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MyIntentService extends IntentService {
    private String option;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.i("brad", "roger");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        option = intent.getStringExtra("option");
        String target ="";
        switch (option){
            case "0":
                target="Main";
                option = "0";
                break;
            case "1":
                target="ManageTeam";
                String home = intent.getStringExtra("home");
                String opp = intent.getStringExtra("opp");
                option = "1&home=" + home + "&opp=" + opp;
                Log.i("brad", home+":"+opp);
                break;
        }
        ArrayList data = getData(option);
        Intent it = new Intent(target);
        it.putExtra("data", data);
        it.putExtra("target", target);
        sendBroadcast(it);
        //Log.i("brad", "over");
    }

    public ArrayList getData(String option) {
        URL url = null;
        ArrayList data = new ArrayList();
        try {
            url = new URL("http://10.0.2.2:8080/HitoBaseBall/ConnectToServer?option=" + option);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            ObjectInputStream oin = new ObjectInputStream(conn.getInputStream());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    oin, "UTF-8"));
            String json = (String)oin.readObject();
//            reader.close();
            ArrayList array = new Gson().fromJson(json, ArrayList.class);



            for(int i = 0; i < array.size(); i++){
                data.add(array.get(i));
            }
            Log.i("brad", data.toString());
        }catch (Exception e){
            Log.i("brad", e.toString());}
        return data;
    }
}
