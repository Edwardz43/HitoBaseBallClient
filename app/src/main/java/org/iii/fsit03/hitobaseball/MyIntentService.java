package org.iii.fsit03.hitobaseball;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        String team = intent.getStringExtra("team");
        Log.i("brad", ""+team);
        String target ="";
        switch (option){
            case "0":
                target="Main";
                option = "0";
                break;
            case "1":
                target="ManageTeam";
                option = "1&team=" + intent.getStringExtra("team");
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String json = reader.readLine();
            reader.close();
            Gson gson = new Gson();
            ArrayList tmp = (ArrayList)gson.fromJson(json,ArrayList.class);

            for(int i = 0; i < tmp.size(); i++){
                data.add(tmp.get(i));
            }
            Log.i("brad", data.toString());
        }catch (Exception e){
            Log.i("brad", e.toString());}
        return data;
    }
}
