package org.iii.fsit03.hitobaseball;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DatagetterThread extends Thread {
    private int option;
    private ArrayList data;

    public DatagetterThread(String option){
        switch (option){
            case "team":
                this.option = 0;
                break;
            case "player":
                this.option = 1;
                break;
        }
    }

    public ArrayList getData(){
        return data;
    }

    @Override
    public void run() {
        URL url = null;
        LinkedList data = new LinkedList<>();
        try {
            url = new URL("http://10.0.2.2:8080/HitoBaseBall/ConnectToServer?option=" + option);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String json = reader.readLine();
            reader.close();
            Gson gson = new Gson();
            ArrayList tmp = (ArrayList)gson.fromJson(json, Object.class);

            for(int i = 0; i < tmp.size(); i++){
                data.add(tmp.get(i));
            }

        }catch (Exception e){Log.i("brad", e.toString());}
    }
}
