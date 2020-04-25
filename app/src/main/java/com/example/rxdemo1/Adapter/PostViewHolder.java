package com.example.rxdemo1.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.rxdemo1.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title,txt_content,txt_author;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_author=itemView.findViewById(R.id.txt_author);
        txt_content=itemView.findViewById(R.id.txt_content);
        txt_title=itemView.findViewById(R.id.txt_title);
    }
}
