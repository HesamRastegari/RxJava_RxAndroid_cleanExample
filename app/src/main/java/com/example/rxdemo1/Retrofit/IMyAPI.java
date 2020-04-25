package com.example.rxdemo1.Retrofit;


import com.example.rxdemo1.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();
}
