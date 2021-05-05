package com.example.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class InterestListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    HashMap<String,String> hashMap;

    public InterestListViewAdapter(Context context, HashMap<String, String> arrayList) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.hashMap = arrayList;
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView mNameTv, mCodeTv;
        Button mBtn;
    }

    @Override
    public int getCount() {
        return hashMap.size();
    }

    @Override
    public Object getItem(int position) {
        return hashMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.interest_row, null);

            holder.mCodeTv = view.findViewById(R.id.intListCode);
            holder.mNameTv = view.findViewById(R.id.intListName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Iterator<String> keys = hashMap.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
            String value = (String) hashMap.get(key);
            holder.mNameTv.setText(key);
            holder.mCodeTv.setText(value);
            System.out.println(key);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mBtn = view.findViewById(R.id.listDelete);
        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //제대로 들어올시 삭제코드
            }
        });
        return view;
    }
}