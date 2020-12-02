package com.example.ejercicioclaseasincrona;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostAdapter.ClickOnRowListener {

    private RecyclerView datalist;
    private List<Post> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Post>> listCall = retrofit.create(APIService.class).getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                elements = response.body();
                PostAdapter adapter = new PostAdapter(getApplicationContext(), elements, MainActivity.this);
                datalist = findViewById(R.id.datalist);
                datalist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                datalist.setHasFixedSize(true);
                datalist.setItemAnimator(new DefaultItemAnimator());
                datalist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }

    @Override
    public void ClickOnRow(int position) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
    }
}
