package org.iii.fsit03.hitobaseball;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by edlo on 2017/9/2.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private LayoutInflater context;
    private Map<String, List<String>> dataCollection;
    private Map<String, List<String>> childItems, parentTitle;

    public MyExpandableListAdapter(LayoutInflater context,
                                   Map<String, List<String>> parentTitle,
                                 Map<String, List<String>> dataCollection) {
        this.context = context;
        this.dataCollection = dataCollection;
        this.parentTitle = parentTitle;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return dataCollection.get(parentTitle.get("battingOrder").get(groupPosition)).get(
                childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition,
                             final int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        TextView textView = new TextView(context.getContext());
        textView.setText(getChild(groupPosition, childPosition).toString());

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textView.setPadding(50, 7, 7, 7);

        return textView;
    }

    public int getChildrenCount(int groupPosition) {
        Log.i("brad", parentTitle.get("battingOrder").get(groupPosition));
        return dataCollection.get(parentTitle.get("battingOrder").get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return parentTitle.get("battingOrder").get(groupPosition);
    }

    public Object getGroupContext(int groupPosition) {
        return parentTitle.get("batter").get(groupPosition);
    }

    public int getGroupCount() {
        return parentTitle.get("batter").size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String childName = (String) getGroup(groupPosition);
        String childContext = (String) getGroupContext(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = context;
            convertView = infalInflater.inflate(R.layout.layout_parent_roster, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.textView1);
        TextView item2 = (TextView) convertView.findViewById(R.id.textViewSub);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(childName);
        item2.setTypeface(null, Typeface.BOLD);
        item2.setText(childContext);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


