package com.example.ejercicioclaseasincrona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements Serializable {

    private final Context context;
    private ClickOnRowListener clickOnRowListener;
    private List<Post> elements;

    PostAdapter(Context context, List<Post> elements, ClickOnRowListener clickOnRowListener)
    {
        this.context = context;
        this.elements = elements;
        this.clickOnRowListener = clickOnRowListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;
        ClickOnRowListener clickOnRowListener;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView, ClickOnRowListener clickOnRowListener) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
            this.clickOnRowListener = clickOnRowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickOnRowListener.ClickOnRow(getAdapterPosition());
        }

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, clickOnRowListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PostAdapter.MyViewHolder holder, int position) {
        Post element = elements.get(position);
        holder.txtId.setText(String.valueOf(element.getId()));
        holder.txtTitle.setText(element.getTitle());
        holder.txtBody.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    interface ClickOnRowListener {
        void ClickOnRow(int position);
    }
}
