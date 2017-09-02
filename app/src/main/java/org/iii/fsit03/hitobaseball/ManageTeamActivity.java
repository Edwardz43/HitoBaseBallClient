package org.iii.fsit03.hitobaseball;

import android.content.Intent;
import android.content.IntentFilter;
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
    private ListView list;
    private List<Map<String, String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};
    private SimpleAdapter adapter;
    private ArrayList teams;
    public MyReceiver myReceiver;
    //private int removeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team);
        list = (ListView) findViewById(R.id.list);
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
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(ManageTeamActivity.this, MyIntentService.class);
                it.putExtra("option", "1");
                it.putExtra("team", ""+teams.get(i));
                startService(it);
            }
        });
    }

    public void createTeam(View view){
        Intent it = new Intent(this, CreateTeamActivity.class);
        startActivity(it);
    }

    @Override
    public void finish() {
        unregisterReceiver(myReceiver);
        super.finish();
    }
}
