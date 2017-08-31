package org.iii.fsit03.hitobaseball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ManageTeamActivity extends AppCompatActivity {
    private ListView list;
    private List<Map<String,String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};
    private SimpleAdapter adapter;
    private int removeIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team);
        list = (ListView)findViewById(R.id.list);
        initList();
    }

    private void initList() {
        String teamID = "1";
        ArrayList teams = getData(teamID);
        data = new LinkedList<>();

        for(int i = 0; i < teams.size(); i ++){
            Map<String,String> d = new HashMap<>();
            d.put(from[0],""+teams.get(i));
            data.add(d);
        }

        adapter = new SimpleAdapter(this, data, R.layout.layout_item, from , to);
        list.setAdapter(adapter);
    }

    private ArrayList getData(String teamID){
        ArrayList res = new ArrayList();
        final String id = teamID;
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url =
                            new URL("http://10.0.2.2:8080/HitoBaseBall/ConnectToServer?teamID=1");
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    conn.connect();
                    Log.i("brad", conn.getContent().toString());

                }catch (Exception e){
                    Log.i("brad", e.toString());
                }
            }
        }.start();
        return res;
    }

    public void createTeam(View view){
        Intent it = new Intent(this, CreateTeamActivity.class);
        startActivity(it);
    }
}
