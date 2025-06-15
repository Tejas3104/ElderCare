package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);

        holder.doc1.setText(user.getDocd().getDoc1());
        holder.med1.setText(user.getDocd().getMed1());
        holder.medicalprob.setText(user.getMedd().getMedical());
        holder.mobility.setText(user.getMedd().getMod());
        holder.patho.setText(user.getPathd().getPatho());
        holder.physio.setText(user.getPathd().getPhysio());
        holder.dob.setText(user.getUserd().getDob());
        holder.gender.setText(user.getUserd().getGender());
        holder.heightuser.setText(user.getUserd().getHeight());
        holder.imageurl.setText(user.getUserd().getImageURL());
        holder.nameuser.setText(user.getUserd().getName());
        holder.weight.setText(user.getUserd().getWeight());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView doc1, med1, medicalprob, mobility, patho, physio, dob, gender, heightuser, imageurl, nameuser, weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doc1 = itemView.findViewById(R.id.doc1);
            med1 = itemView.findViewById(R.id.med1);
            medicalprob = itemView.findViewById(R.id.medicalprob);
            mobility = itemView.findViewById(R.id.mobility);
            patho = itemView.findViewById(R.id.patho);
            physio = itemView.findViewById(R.id.physio);
            dob = itemView.findViewById(R.id.dob);
            gender = itemView.findViewById(R.id.gender);
            heightuser = itemView.findViewById(R.id.heightuser);
            imageurl = itemView.findViewById(R.id.imageurl);
            nameuser = itemView.findViewById(R.id.nameuser);
            weight = itemView.findViewById(R.id.weight);
        }
    }
}
