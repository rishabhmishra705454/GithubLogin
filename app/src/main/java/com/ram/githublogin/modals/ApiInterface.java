package com.ram.githublogin.modals;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("search/repositories?q=stars:>1&sort=stars")
    Call<ApiResponse> getPost();
}
