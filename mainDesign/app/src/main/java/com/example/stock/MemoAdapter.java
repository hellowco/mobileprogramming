package com.example.stock;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<MemoList> mData;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView content;
        private final TextView date;

        public ViewHolder(View view){
            super(view);

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        String id = mData.get(pos).getObjID();

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                            }
                        };
                        MemoDeleteRequest memoDeleteRequest = new MemoDeleteRequest(id, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(view.getContext());
                        queue.add(memoDeleteRequest);
                        Toast.makeText(view.getContext(), "삭제되었습니다.\n 스와이프하여 새로고침 해주세요.",Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                    return false;
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(view.getContext(), ModifyMemo.class);
                        intent.putExtra("objID", mData.get(pos).getObjID());
                        intent.putExtra("title", mData.get(pos).getTitle());
                        intent.putExtra("content", mData.get(pos).getContext());
                        intent.putExtra("date", mData.get(pos).getDate());
                        view.getContext().startActivity(intent);
                        notifyItemChanged(pos);
                    }
                }
            });

            title = view.findViewById(R.id.memoTitle);
            content = view.findViewById(R.id.memoContent);
            date = view.findViewById(R.id.memoDate);
//            notifyDataSetChanged();
        }

        public TextView getTitle() {
            return title;
        }
        public TextView getContent() {
            return content;
        }
        public TextView getDate() {
            return date;
        }
    }

    public MemoAdapter(ArrayList<MemoList> dataSet){
        mData = dataSet;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.memo_list, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        System.out.println(mData.size());
        if (mData.size() < 1){
            viewHolder.getTitle().setText("메모가 없습니다.");
            viewHolder.getContent().setText("메모를 추가해주세요.");
            viewHolder.getDate().setVisibility(View.INVISIBLE);
        }else{
        viewHolder.getTitle().setText(mData.get(position).getTitle());
        viewHolder.getContent().setText(mData.get(position).getContext());
        viewHolder.getDate().setText(mData.get(position).getDate());
        }
    }

    public int getItemCount() {
        return mData.size();
    }

}
