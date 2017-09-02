package org.iii.fsit03.hitobaseball;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RosterFragment extends Fragment {
    private ExpandableListView myList, oppList;
    private List<Map<String,String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};
    private ExpandableListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roster, container, false);
        myList = view.findViewById(R.id.my_roster);
        oppList = view.findViewById(R.id.opp_roster);
        //adapter = new MyExpandableListAdapter(getContext());
        initList();
        return view;
    }

    private void initList() {
        ArrayList players = getActivity().getIntent().getStringArrayListExtra("data");
        data = new LinkedList<>();
        int roster = 9;
        for (int i = 0; i < roster; i++) {
            Map<String, String> d = new HashMap<>();
            d.put(from[0], "第" + (i + 1) + "棒");
            data.add(d);
            //Log.i("brad", "player : " + players.get(i));
        }

        myList.setAdapter(adapter);
        oppList.setAdapter(adapter);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent it = new Intent(M.this, MyIntentService.class);
//                it.putExtra("option", "1");
//                it.putExtra("team", ""+teams.get(i));
//                startService(it);
//            }
//        });

    }
}
