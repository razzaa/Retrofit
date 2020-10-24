package com.midmax.retrofit;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerAPI {
    public static final String key = "AIzaSyAZbSeSjNcQjgfrPiZDFON-vxkU08pQzlg";
    public static final String url = "https://www.googleapis.com/blogger/v3/blogs/6481551262941092209/posts/";
    public static PostService postService = null;

    public static PostService getService() {
        if (postService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
            return postService;
    }

    public interface PostService {
        @GET("?key=" + key)
        Call<PostList> getPostList();
    }

}
