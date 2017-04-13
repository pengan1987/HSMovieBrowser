package com.andyzhou.hsmoviebrowser;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andyzhou.hsmoviebrowser.api.HttpClient;
import com.andyzhou.hsmoviebrowser.models.MovieDetail;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by andyzhou on 2017-04-13.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    Uri imageUri;
    SimpleDraweeView draweeView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        draweeView = (SimpleDraweeView) itemView.findViewById(R.id.draweeImage);
    }

    public void bindData(MovieDetail movieDetail) {
        String backdropPath = movieDetail.getBackdrop_path();
        String baseUrl = HttpClient.getInstance().getImageBaseUrl();


        Uri uri = Uri.parse(baseUrl + backdropPath);
        draweeView.setImageURI(uri);

    }
}
