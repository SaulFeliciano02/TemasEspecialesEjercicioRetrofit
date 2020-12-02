package com.example.ejercicioclaseasincrona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> implements Serializable {

    private final Context context;
    private List<Comment> elements;

    CommentAdapter(Context context, List<Comment> elements)
    {
        this.context = context;
        this.elements = elements;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommentAdapter.MyViewHolder holder, int position) {
        Comment element = elements.get(position);
        holder.txtId.setText(String.valueOf(element.getId()));
        holder.txtTitle.setText(element.getName());
        holder.txtBody.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

}
