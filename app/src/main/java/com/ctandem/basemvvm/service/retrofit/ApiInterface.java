package com.ctandem.basemvvm.service.retrofit;

import com.ctandem.basemvvm.service.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Zaeem Sattar on 7/6/2018.
 */
public interface ApiInterface {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPostById(@Path("id") String id);
}
