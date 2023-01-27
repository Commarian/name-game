package com.commarian.namegamet3.HomeMade.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commarian.namegamet3.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<String> mUsernames = new ArrayList<>();
    private ArrayList<String> mMessages = new ArrayList<>();


    public RecyclerAdapter(Context mContext, ArrayList<String> mUsernames, ArrayList<String> mMessages ) {
        this.mUsernames = mUsernames;
        this.mMessages = mMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.username.setText(mUsernames.get(getItemCount() - 1 - position));


        holder.message.setText(mMessages.get(getItemCount() - 1 - position));

    }

    @Override
    public int getItemCount() {
        return mUsernames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView message;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.tvRecyclerUN);
            message = itemView.findViewById(R.id.tvRecyclerMess);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }
    }



}
