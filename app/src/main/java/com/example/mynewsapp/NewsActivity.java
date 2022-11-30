package com.example.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsResponse recyclerDataArrayList;
    NewsAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = findViewById( R.id.additional__recycler );
        recyclerDataArrayList = new NewsResponse();


        getAllData();

    }

    private void getAllData() {


        ApiInterface apiInterface = ApiClient.getClientCI().create( ApiInterface.class );
        apiInterface.getBasic("in","5eb50f4ae343421bb2e31f490d331821").enqueue( new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                Log.i("status"," msg:" + response.body().getStatus() );
                Log.i("total"," msg:" + response.body().getTotalResults() );
                Log.i("article"," msg:" + response.body().getArticles().get( 1 ).getAuthor() );
                Toast.makeText( NewsActivity.this, ""+ response.body().getArticles().get( 1 ).getAuthor(), Toast.LENGTH_SHORT ).show();

                recyclerDataArrayList = response.body();
                // below line is to add our data from api to our array list.
                recyclerViewAdapter = new NewsAdapter(recyclerDataArrayList, NewsActivity.this);
                // below line is to set layout manager for our recycler view.
              // LinearLayoutManager manager = new LinearLayoutManager(NewsActivity.this);

                // below line is to set layout manager for our recycler view.
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

                // setting layout manager for our recycler view.
                recyclerView.setLayoutManager(linearLayoutManager);

                // below line is to set adapter to our recycler view.
                recyclerView.setAdapter(recyclerViewAdapter);
                final int time = 3000; // it's the delay time for sliding between items in recyclerview

                //The LinearSnapHelper will snap the center of the target child view to the center of the attached RecyclerView , it's optional if you want , you can use it
                final LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
                linearSnapHelper.attachToRecyclerView(recyclerView);

                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {

                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (recyclerViewAdapter.getItemCount() - 1)) {

                            linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                        }

                        else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (recyclerViewAdapter.getItemCount() - 1)) {

                            linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                        }
                    }
                }, 0, time);

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        } );
    }
}


