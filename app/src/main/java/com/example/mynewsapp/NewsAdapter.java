package com.example.mynewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    NewsResponse recyclerDataList;
    Context context;

    public NewsAdapter(NewsResponse recyclerDataList, Context context)
    {
        this.recyclerDataList = recyclerDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.card, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from our modal class.
        holder.time.setText(recyclerDataList.getArticles().get( position ).getPublishedAt());
        holder.source_news.setText(recyclerDataList.getArticles().get( position ).getSource().getName());
        holder.titleTv.setText(recyclerDataList.getArticles().get( position ).getTitle());
        holder.desriptionTv.setText(recyclerDataList.getArticles().get( position ).getDescription());
        Picasso.get().load(recyclerDataList.getArticles().get( position ).getUrlToImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataList.getArticles().size();
    }


    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView time, source_news,date,titleTv,desriptionTv;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iV);
            time = itemView.findViewById(R.id.time);
            source_news = itemView.findViewById(R.id.source_news);
            titleTv = itemView.findViewById(R.id.titleTv);
            desriptionTv = itemView.findViewById(R.id.desriptionTv);

        }
    }
}