package com.ram.githublogin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ram.githublogin.R;
import com.ram.githublogin.modals.Post;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<Post> postArrayList ;

    public RecyclerAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout ,parent);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postArrayList.get(position);

        holder.id.setText(post.getId());
        holder.name.setText(post.getName());
        holder.issues.setText(post.getOpen_issues());
        holder.stars.setText(post.getStargazers_count());
        holder.date.setText(post.getPushed_at());

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id , name , issues ,stars ,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            issues = itemView.findViewById(R.id.issues);
            stars = itemView.findViewById(R.id.stars);
            date = itemView.findViewById(R.id.date);
        }
    }
}
