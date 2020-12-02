package com.example.ejercicioclaseasincrona;

import android.util.Log;
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

import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView datalist;
    private List<Comment> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Comment>> listCall = retrofit.create(APIService.class).getCommentsFromPost(getIntent().getExtras().getInt("Position") + 1);

        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                elements = response.body();
                assert elements != null;
                for (Comment comment: elements
                     ) {
                    System.out.println("Commentario: " + comment.getId());
                }
                CommentAdapter adapter = new CommentAdapter(getApplicationContext(), elements);
                datalist = findViewById(R.id.commentsList);
                datalist.setLayoutManager(new LinearLayoutManager(CommentsActivity.this));
                datalist.setHasFixedSize(true);
                datalist.setItemAnimator(new DefaultItemAnimator());
                datalist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }
}