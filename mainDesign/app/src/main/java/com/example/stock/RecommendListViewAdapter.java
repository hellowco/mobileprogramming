package com.example.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RecommendListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<StockList> stockLists;
    ArrayList<StockList> arrayList;

    public RecommendListViewAdapter(Context context, List<StockList> stockLists) {
        this.mContext = context;
        this.stockLists = stockLists;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<StockList>();
        this.arrayList.addAll(stockLists);
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView mNameTv, mCodeTv;
        Button mBtn;
    }

    @Override
    public int getCount() {
        return stockLists.size();
    }

    @Override
    public Object getItem(int i) {
        return stockLists.get(i);
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

        holder.mNameTv.setText(stockLists.get(position).getName());
        holder.mCodeTv.setText(stockLists.get(position).getCode());

        return view;
    }
}