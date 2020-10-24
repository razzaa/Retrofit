package com.midmax.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Item> items;

    public PostAdapter(Context context, List<Item> item) {
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Item item=items.get(position);
        holder.title.setText(item.getTitle());
        holder.descrition.setText(item.getContent());
        Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m=p.matcher(item.getContent());
        List<String> tokens=new ArrayList<>();
        while (m.find()){
            String token=m.group(1);
            tokens.add(token);
        }
        Glide.with(context).load(tokens.get(0)).into(holder.postimage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postimage;
        TextView title,descrition;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postimage=itemView.findViewById(R.id.postimage);
            title=itemView.findViewById(R.id.postTitle);
            descrition=itemView.findViewById(R.id.postDescription);
        }
    }

}
