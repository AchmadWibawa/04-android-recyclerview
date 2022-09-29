package com.example.a04androidrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.example.a04androidrecyclerview.MountainData;
import com.example.a04androidrecyclerview.R;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.MountainViewHolder>{
    private ArrayList<MountainData> mountainList;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnItemClickListener;

    public MountainAdapter(Context context, ArrayList<MountainData> mountainList){
        mInflater = LayoutInflater.from(context);
        this.mountainList = mountainList;
    }

    @NonNull
    @Override
    public MountainAdapter.MountainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.mountain_list, viewGroup, false);
        return new MountainViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MountainAdapter.MountainViewHolder mountainViewHolder, int position) {
        mountainViewHolder.name.setText((mountainList.get(position)).getName());
        mountainViewHolder.description.setText((mountainList.get(position)).getDescription());
        Glide.with(mountainViewHolder.itemView)
                .load(mountainList.get(position).getImage())
                .override(100, 150)
                .into(mountainViewHolder.image);
    }


    @Override
    public int getItemCount() {
        return mountainList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class MountainViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;


        public MountainViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.mountain_name);
            description = itemView.findViewById(R.id.mountain_description);
            image = itemView.findViewById(R.id.mountain_image);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
