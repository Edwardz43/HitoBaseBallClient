package org.iii.fsit03.hitobaseball;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ManageTeamActivity extends AppCompatActivity {
    private ListView list_my, list_opp;
    private List<Map<String, String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};
    private SimpleAdapter adapter;
    private ArrayList teams;
    private Map<String, String> team_name;
    public MyReceiver myReceiver;
    //private int removeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team);
        list_my = (ListView) findViewById(R.id.list);
        list_opp = (ListView) findViewById(R.id.list2);
        team_name = new HashMap<>();
        initList();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("ManageTeam");
        registerReceiver(myReceiver,filter);
    }

    private void initList() {
        teams = getIntent().getStringArrayListExtra("data");
        data = new LinkedList<>();
        for (int i = 0; i < teams.size(); i++) {
            Map<String, String> d = new HashMap<>();
            d.put(from[0], "" + teams.get(i));
            data.add(d);
            //Log.i("brad", ""+res.get(i));
        }

        adapter = new SimpleAdapter(ManageTeamActivity.this, data, R.layout.layout_item, from, to);
        list_my.setAdapter(adapter);

        list_my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int n = 0; n < list_my.getChildCount(); n ++){
                    list_my.getChildAt(n).setBackgroundColor(Color.BLACK);
                }
                team_name.put("home", ""+teams.get(i));
                list_my.getChildAt(i).setBackgroundColor(Color.BLUE);
                adapter.notifyDataSetChanged();
                Log.i("brad", team_name.get("home"));
            }
        });

        list_opp.setAdapter(adapter);

        list_opp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int n = 0; n < list_my.getChildCount(); n ++){
                    list_opp.getChildAt(n).setBackgroundColor(Color.BLACK);
                }
                team_name.put("opp", ""+teams.get(i));
                list_opp.getChildAt(i).setBackgroundColor(Color.BLUE);
                adapter.notifyDataSetChanged();
                Log.i("brad", team_name.get("opp"));
            }
        });
    }

    public void createTeam(View view){
        Intent it = new Intent(this, MyIntentService.class);
        it.putExtra("option", "1");
        it.putExtra("home", team_name.get("home"));
        it.putExtra("opp", team_name.get("opp"));
        startService(it);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
