package com.example.stock;

import android.content.Context;
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
import java.util.Iterator;
import java.util.List;

public class InterestListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<StockList> stockLists;
    ArrayList<StockList> arrayList;

    public InterestListViewAdapter(Context context, List<StockList> stockLists) {
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
            view = inflater.inflate(R.layout.interest_row, null);

            holder.mCodeTv = view.findViewById(R.id.intListCode);
            holder.mNameTv = view.findViewById(R.id.intListName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(stockLists.get(position).getName());
        holder.mCodeTv.setText(stockLists.get(position).getCode());

        holder.mBtn = view.findViewById(R.id.listDelete);

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
                        notifyDataSetChanged();
                    }
                };
                InterestListDelete interestListDelete = new InterestListDelete(name, code, responseListener);
                RequestQueue queue = Volley.newRequestQueue(mContext);
                queue.add(interestListDelete);
            }
        });
        return view;
    }
}