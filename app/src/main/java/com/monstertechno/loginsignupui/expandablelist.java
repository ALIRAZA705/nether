package com.monstertechno.loginsignupui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class expandablelist extends BaseExpandableListAdapter {
    private Context c;
  //  private list<String> list;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    public expandablelist(Context c, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.c = c;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headertitle = (String) getGroup(groupPosition);
        if(convertView==null)
        {
            LayoutInflater Inflater=  (LayoutInflater)this.c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            convertView =Inflater.inflate(R.layout.list_group,null);

        }
        TextView tx= convertView.findViewById(R.id.ltg);
        tx.setTypeface(null, Typeface.BOLD);
        tx.setText(headertitle);



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    final     String childtitle = (String) getChild(groupPosition,childPosition);
        if(convertView==null)
        {
            LayoutInflater Inflater=  (LayoutInflater)this.c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
             convertView =Inflater.inflate(R.layout.list_item,null);

        }
        TextView tx= convertView.findViewById(R.id.ltt);
        tx.setTypeface(null, Typeface.BOLD);
        tx.setText(childtitle);



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
