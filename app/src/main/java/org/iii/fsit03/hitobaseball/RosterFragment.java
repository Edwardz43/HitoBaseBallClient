package org.iii.fsit03.hitobaseball;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class RosterFragment extends Fragment {
    private ListView list;
    private List<Map<String,String>> data;
    private String[] from = {"title"};
    private int[] to = {R.id.item_title};
    private SimpleAdapter adapter;
    private ManageGameActivity manageGameActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        list = (ListView)manageGameActivity.findViewById(R.id.list_roster);
        initList();
        return inflater.inflate(R.layout.fragment_roster, container, false);
    }

    private void initList() {

    }
}
