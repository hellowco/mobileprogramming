package com.example.jusik;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<JusikList> jusikLists;
    ArrayList<JusikList> arrayList;

    public ListViewAdapter(Context context, List<JusikList> jusikLists) {
        this.mContext = context;
        this.jusikLists = jusikLists;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<JusikList>();
        this.arrayList.addAll(jusikLists);
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        TextView mNameTv, mCodeTv;
        Button mBtn;
    }

    @Override
    public int getCount() {
        return jusikLists.size();
    }

    @Override
    public Object getItem(int i) {
        return jusikLists.get(i);
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
            view = inflater.inflate(R.layout.row, null);

            holder.mCodeTv = view.findViewById(R.id.listCode);
            holder.mNameTv = view.findViewById(R.id.listName);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(jusikLists.get(position).getName());
        holder.mCodeTv.setText(jusikLists.get(position).getCode());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mBtn = view.findViewById(R.id.listAdd);
        InterestList interestList = new InterestList();
        HashMap map = new HashMap<String, String>();

        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jname = holder.mNameTv.getText().toString();
                String jcode = holder.mCodeTv.getText().toString();

                System.out.println("map put:" + jname +"," + jcode);
                map.put(jname, jcode);
                interestList.setInterestList(map);
                Intent intent = new Intent(mContext, InterestActivity.class);
                intent.putExtra("interestList", interestList);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        jusikLists.clear();
        if (charText.length() ==0){
            jusikLists.addAll(arrayList);
        }
        else{
            for (JusikList jusikList : arrayList) {
                if (jusikList.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    jusikLists.add(jusikList);
                }
            }
        }
        notifyDataSetChanged();
    }


}
