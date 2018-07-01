package com.example.admin.lesson9;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> mData;

    public CustomAdapter(List<String> mData) {
        this.mData = mData;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public Button mButton;

        public MyViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textview);
            mButton = (Button) view.findViewById(R.id.buttoncell);
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder1 = (MyViewHolder) holder;
        viewHolder1.mTextView.setText(mData.get(position));
        viewHolder1.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Activity3.class);
                    startActivity(intent);
                }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
