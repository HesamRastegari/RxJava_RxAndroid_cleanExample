package com.example.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


import android.os.Bundle;

import com.example.rxdemo1.Adapter.PostAdapter;
import com.example.rxdemo1.Model.Post;
import com.example.rxdemo1.Retrofit.IMyAPI;
import com.example.rxdemo1.Retrofit.RetrofitClient;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MyApp";

    IMyAPI myAPI;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

    }

    private void fetchData() {
        compositeDisposable.add(myAPI.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) {
                displayPost(posts);
            }
        }));
    }

    private void displayPost(List<Post> posts) {
        PostAdapter postAdapter = new PostAdapter(this,posts);
        recyclerView.setAdapter(postAdapter);

    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
