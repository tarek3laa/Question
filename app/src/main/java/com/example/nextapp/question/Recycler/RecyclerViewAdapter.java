package com.example.nextapp.question.Recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.R;

import java.util.ArrayList;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList<User>arrayList;

    public RecyclerViewAdapter(ArrayList<User> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();

        View view= LayoutInflater.from(context).inflate(R.layout.row_view,parent,false);

        ViewHolder viewHolder =new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(String.valueOf(arrayList.get(position).getEmail()));
           arrayList.get(position).setRank(position+1);
        holder.rank.setText(String.valueOf(arrayList.get(position).getRank()));
        holder.score.setText(String.valueOf(arrayList.get(position).getScore()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView email,score,rank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            email=(TextView)itemView.findViewById(R.id.rv_tv_email);
            score=(TextView)itemView.findViewById(R.id.rv_tv_score);
            rank=(TextView)itemView.findViewById(R.id.rv_tv_rank);




        }


    }
}
