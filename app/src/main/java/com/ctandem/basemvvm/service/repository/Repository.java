package com.ctandem.basemvvm.service.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.ctandem.basemvvm.service.callbacks.CustomRetrofitCallback;
import com.ctandem.basemvvm.service.callbacks.RetrofitResponse;
import com.ctandem.basemvvm.service.model.Post;
import com.ctandem.basemvvm.service.retrofit.ApiClient;
import com.ctandem.basemvvm.service.retrofit.ApiInterface;
import com.ctandem.basemvvm.service.room.AppDatabase;
import com.ctandem.basemvvm.service.room.PostDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zaeem Sattar on 7/6/2018.
 */
public class Repository {

    private AppDatabase db;
    private ApiInterface apiInterface;

    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();
    private MutableLiveData<RetrofitResponse> error = new MutableLiveData<>();



    public Repository(Application application) {
        /*
         * SINGLE RESPONSIBILITY PRINCIPLE
         * a single source of truth for network and Database (Data sources)
         * */
        db = AppDatabase.getDatabase(application);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

/*
    public LiveData<List<Post>> getAllPosts() {
        apiInterface.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                */
/*
                 * notify all observers
                 * *//*

                posts.postValue(response.body());
                */
/*
                 * save in db for offline use
                 * *//*

                insertAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Log.e("", t.getMessage());

            }
        });

        */
/*
         * return saved copy of data till we receive callback for network call
         * *//*

        return db.postDao().getAllPosts();
    }
*/

    public LiveData<List<Post>> getAllPosts() {
        apiInterface.getPosts().enqueue(new CustomRetrofitCallback<List<Post>>() {
            @Override
            protected void onSuccess(@NotNull RetrofitResponse retrofitResponse, @NotNull Response<List<Post>> response) {

                posts.postValue(response.body());
                insertAll(response.body());


            }

            @Override
            protected void onError(@NotNull RetrofitResponse retrofitResponse) {
                error.postValue(retrofitResponse);


            }
        });

        /*
         * return saved copy of data till we receive callback for network call
         * */
        return db.postDao().getAllPosts();
    }

    public LiveData<RetrofitResponse> getError()
    {
        return error;
    }

    private void insertAll(List<Post> post) {
        new InsertTask(db.postDao()).execute(post);
    }

    private static class InsertTask extends AsyncTask<List<Post>, Void, Void> {

        private PostDao mAsyncTaskDao;

        InsertTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<Post>... params) {
            /*
             * insert data list to Room
             * */
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

}
