package com.andyzhou.hsmoviebrowser;

/**
 * Created by andyzhou on 2017-04-13.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andyzhou.hsmoviebrowser.models.MovieDetail;

import java.util.ArrayList;
import java.util.List;


public class ImageRecyclerAdapter extends RecyclerView.Adapter {

    List<MovieDetail> mData;

    protected LayoutInflater mInflater;
    protected RecyclerView mRecyclerView;
    protected Context mContext;

    public ImageRecyclerAdapter(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
        this.mContext = mRecyclerView.getContext();
        this.mInflater = LayoutInflater.from(mContext);
        mData = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            movieViewHolder.bindData(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<MovieDetail> movieDetailList) {
        mData = movieDetailList;
        this.notifyDataSetChanged();
    }
}
