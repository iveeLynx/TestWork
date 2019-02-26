package com.yaskovskiy.testwork.Adapter;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yaskovskiy.testwork.Model.Item;
import com.yaskovskiy.testwork.R;

import java.util.List;

import Interface.ILoadMore;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public static ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView name, link;
    public ImageView img, favImg;

    public ImageView getImage(){
        return this.img;
    }

    public void bind(){
        name = (TextView) itemView.findViewById(R.id.txtName);
        link = (TextView) itemView.findViewById(R.id.txtLink);
        img = (ImageView) itemView.findViewById(R.id.imageName);
        favImg = (ImageView) itemView.findViewById(R.id.favorite);
    }

    public ItemViewHolder(View itemView) {
        super(itemView);
        bind();

    }

}

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    List<Item> items;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;
    private Context context;

    public Adapter(Context context,RecyclerView recyclerView, Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
        this.context = context;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if(loadMore != null)
                        loadMore.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_layout,parent,false);
            return new ItemViewHolder(view);
        }
        else if(viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity)
                    .inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }


    public String linkUrl;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){

        if(holder instanceof ItemViewHolder){
//            Item item = items.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.name.setText(items.get(position).getName());
            viewHolder.link.setHint(items.get(position).getLink());
            Picasso.with(context).load(items.get(position).getImg()).error(R.drawable.ic_launcher_background).into(((ItemViewHolder) holder).img);
        } else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            LoadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

}
