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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class RosterFragment extends Fragment {
    private ExpandableListView myList, oppList;
    private List<String> childList;
    private Map<String, List<String>> parentListItems;
    private MyExpandableListAdapter adapter_my, adapter_opp;
    private LayoutInflater myInflater;
    private View myView, convertView;
    private Map<String, List<String>> parentList_home, parentList_opp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_roster, container, false);
//        myList = view.findViewById(R.id.my_roster);
//        oppList = view.findViewById(R.id.opp_roster);
        convertView = inflater.inflate(R.layout.layout_parent_roster, null);
        myInflater = inflater;
        myList = myView.findViewById(R.id.my_roster);
        oppList = myView.findViewById(R.id.opp_roster);
        initList();
        return myView;
    }

    private void initList() {
        ArrayList players_home = getActivity().getIntent().getStringArrayListExtra("data");
        parentList_home = new HashMap<>();
        parentList_opp = new HashMap<>();
        int roster = 9;

        //set home team list
        ArrayList<String> batingOrder = new ArrayList<>();
        ArrayList<String> batter = new ArrayList();
        for (int i = 0; i < roster; i++) {
            batingOrder.add(""+(i + 1) + "棒");
            batter.add("");
            //Log.i("brad", "parent :" + batingOrder.size());
        }
        parentList_home.put("battingOrder", batingOrder);
        parentList_home.put("batter", batter);

        parentListItems = new HashMap<String, List<String>>();
        int n = 0;
        for (int i = 0; i < parentList_home.get("battingOrder").size(); i ++) {
            loadChild(players_home);
            parentListItems.put(parentList_home.get("battingOrder").get(i), childList);
            //Log.i("brad", "size : " + parentList_home.get("battingOrder").size());
        }

        //set opp team list
        batingOrder = new ArrayList<>();
        batter = new ArrayList();
        for (int i = 0; i < roster; i++) {
            batingOrder.add(""+(i + 1) + "棒");
            batter.add("");
            //Log.i("brad", "parent :" + batingOrder.size());
        }
        parentList_opp.put("battingOrder", batingOrder);
        parentList_opp.put("batter", batter);

        parentListItems = new HashMap<String, List<String>>();
        for (int i = 0; i < parentList_opp.get("battingOrder").size(); i ++) {
            loadChild(players_home);
            parentListItems.put(parentList_opp.get("battingOrder").get(i), childList);
            Log.i("brad", "size : " + parentList_opp.get("battingOrder").size());
        }

        adapter_my = new MyExpandableListAdapter(myInflater, parentList_home, parentListItems);
        adapter_opp = new MyExpandableListAdapter(myInflater, parentList_opp, parentListItems);

        myList.setAdapter(adapter_my);
        oppList.setAdapter(adapter_opp);

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(
                    ExpandableListView expandableListView, View view, int i, int i1, long l) {
                final String selected = (String) adapter_my.getChild(i, i1);
                parentList_home.get("batter").set(i, selected);
                Log.i("brad", i+":"+i1+":"+selected+" tv : " + selected);
                adapter_my.notifyDataSetChanged();
                myList.collapseGroup(i);
                return true;
            }
        });

        oppList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(
                    ExpandableListView expandableListView, View view, int i, int i1, long l) {
                final String selected = (String) adapter_opp.getChild(i, i1);
                parentList_opp.get("batter").set(i, selected);
                Log.i("brad", i+":"+i1+":"+selected+" tv : " + selected);
                adapter_opp.notifyDataSetChanged();
                oppList.collapseGroup(i);
                return true;
            }
        });
    }
    private void loadChild(ArrayList<String> ParentElementsName) {
        childList = new ArrayList<>();
        for (String model : ParentElementsName)
            childList.add(model);
    }
}
