package com.example.jusik.Memo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jusik.R;
import com.example.jusik.Room.AppDatabase;
import com.example.jusik.Room.User;

import java.util.ArrayList;


public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.MyViewHolder> {

    private ArrayList<User> userData = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(userData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public void addItem(User user) {
        userData.add(user);
        notifyDataSetChanged();
    }

    public void addItems(ArrayList<User> users) {
        userData = users;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView title;
        private TextView content;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.memoDate);
            title = itemView.findViewById(R.id.memoTitle);
            content = itemView.findViewById(R.id.memoContent);
        }

        public void onBind(User user, int position) {
            title.setText(user.getTitle());
            content.setText(user.getContext());
            date.setText(user.getDate());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    userData.remove(user);
                    AppDatabase.getInstance(itemView.getContext()).userDao().delete(user);

                    notifyDataSetChanged();
                    return false;
                }
            });
            itemView.setOnClickListener(v -> {

                Intent intent = new Intent(itemView.getContext(), ModifyActivity.class);
                intent.putExtra("data", user);
                itemView.getContext().startActivity(intent);

            });
        }
    }
}
