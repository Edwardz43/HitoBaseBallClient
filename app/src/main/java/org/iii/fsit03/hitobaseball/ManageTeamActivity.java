package org.iii.fsit03.hitobaseball;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
    private UIHandler handler;
    private int removeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team);
        list = (ListView)findViewById(R.id.list);

    }

//    private void initList(){
//
//        data = new LinkedList<>();
//        for(int i = 0; i < res.size(); i ++){
//            Map<String,String> d = new HashMap<>();
//            d.put(from[0],""+res.get(i));
//            data.add(d);
//            //Log.i("brad", ""+res.get(i));
//        }
//
//        adapter = new SimpleAdapter(ManageTeamActivity.this, data, R.layout.layout_item, from , to);
//        list.setAdapter(adapter);
//    }

    public void createTeam(View view){

        Intent it = new Intent(this, CreateTeamActivity.class);
        startActivity(it);
    }

    public UIHandler createHandler(){
        UIHandler handler = new UIHandler();
        return handler;
    }

    private class UIHandler extends Handler {

        public UIHandler getHandler(){
            return this;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
    }
}
