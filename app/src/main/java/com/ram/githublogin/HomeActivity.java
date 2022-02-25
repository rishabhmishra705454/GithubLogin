package com.ram.githublogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ram.githublogin.adapters.RecyclerAdapter;
import com.ram.githublogin.modals.ApiInterface;
import com.ram.githublogin.modals.ApiResponse;
import com.ram.githublogin.modals.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/";

    RecyclerView recyclerView;
    ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ApiResponse> call = apiInterface.getPost();


        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (!response.isSuccessful()) {

                    Toast.makeText(HomeActivity.this, "Data is not getting from server", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApiResponse apiResponse = response.body();
                Toast.makeText(HomeActivity.this, "we get data", Toast.LENGTH_SHORT).show();
                postArrayList = new ArrayList<>(Arrays.asList(apiResponse.getPosts()));
                RecyclerAdapter adapter = new RecyclerAdapter(postArrayList );
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    /*
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(HomeActivity.this, "Data is not getting from server", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Post> postList = response.body();


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

     */


    }
}