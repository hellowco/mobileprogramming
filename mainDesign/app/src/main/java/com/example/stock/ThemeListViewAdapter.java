package com.example.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThemeListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<ThemeList> themeLists;
    ArrayList<ThemeList> arrayList;

    public ThemeListViewAdapter(Context context, List<ThemeList> themeLists) {
        this.mContext = context;
        this.themeLists = themeLists;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<ThemeList>();
        this.arrayList.addAll(themeLists);
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView mNameTv, mCodeTv;
        Button mBtn;
    }

    @Override
    public int getCount() {
        return themeLists.size();
    }

    @Override
    public Object getItem(int i) {
        return themeLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.recommend_row, null);

            holder.mCodeTv = view.findViewById(R.id.intListCode);
            holder.mNameTv = view.findViewById(R.id.intListName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(themeLists.get(position).getName());
        holder.mCodeTv.setText(themeLists.get(position).getCode());

        return view;
    }
}