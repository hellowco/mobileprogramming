package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class StockListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<StockList> stockLists;
    ArrayList<StockList> arrayList;

    public StockListViewAdapter(Context context, List<StockList> stockLists) {
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
            view = inflater.inflate(R.layout.stock_row, null);

            holder.mCodeTv = view.findViewById(R.id.listCode);
            holder.mNameTv = view.findViewById(R.id.listName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(stockLists.get(position).getName());
        holder.mCodeTv.setText(stockLists.get(position).getCode());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mBtn = view.findViewById(R.id.listAdd);

        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = holder.mNameTv.getText().toString();
                String code = holder.mCodeTv.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                InterestListAdd interestListAdd = new InterestListAdd(name, code, responseListener);
                RequestQueue queue = Volley.newRequestQueue(mContext);
                queue.add(interestListAdd);
            }
        });
        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        stockLists.clear();
        if (charText.length() ==0){
            stockLists.addAll(arrayList);
        }
        else{
            for (StockList stockList : arrayList) {
                if (stockList.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    stockLists.add(stockList);
                }
            }
        }
        notifyDataSetChanged();
    }
}
