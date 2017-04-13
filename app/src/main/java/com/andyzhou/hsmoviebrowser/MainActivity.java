package com.andyzhou.hsmoviebrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andyzhou.hsmoviebrowser.api.HttpClient;
import com.andyzhou.hsmoviebrowser.models.ApiConfiguration;
import com.andyzhou.hsmoviebrowser.models.MovieDetail;
import com.andyzhou.hsmoviebrowser.models.MovieRequest;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button mSearchButton;
    EditText mSearchEditText;
    ImageRecyclerAdapter imageRecyclerAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSearchButton = (Button) findViewById(R.id.btnSearch);
        mSearchEditText = (EditText) findViewById(R.id.edtSearch);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie();
            }
        });

        imageRecyclerAdapter = new ImageRecyclerAdapter(mRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(imageRecyclerAdapter);

        loadConfiguration();
    }

    private void loadConfiguration() {
        Observable<ApiConfiguration> apiConfigurationObservable = HttpClient.getInstance()
                .movieApiService
                .getConfig(HttpClient.apiKey);
        apiConfigurationObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ApiConfiguration>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ApiConfiguration apiConfiguration) {
                        String imageBaseUrl = apiConfiguration.getImages().getBase_url();
                        HttpClient.getInstance().setImageBaseUrl(imageBaseUrl);
                    }
                });
    }

    private void searchMovie() {
        String keyword = mSearchEditText.getText().toString();
        //Don't search blank string
        if (!TextUtils.isEmpty(keyword)) {
            Observable<MovieRequest> requestObservable = HttpClient.getInstance()
                    .movieApiService
                    .getMovies(keyword, HttpClient.apiKey, 1);

            requestObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MovieRequest>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(MovieRequest movieRequest) {
                            List<MovieDetail> movieDetailList = movieRequest.getResults();
                            imageRecyclerAdapter.setData(movieDetailList);
                        }
                    });

        }
    }
}
