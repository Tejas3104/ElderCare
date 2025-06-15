package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserRequestAdapter extends RecyclerView.Adapter<UserRequestAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Request> list;
    private DatabaseReference mDatabase;
    private int requestType;

    public UserRequestAdapter(Context context, ArrayList<Request> list, int requestType) {
        this.context = context;
        this.list = list;
        this.requestType = requestType;

        switch (this.requestType) {
            case 1:
                // Doctor requests
                mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                        .child("Admin").child("Requests").child("Doctor Requests");
                break;
            case 2:
                // Counsellor requests
                mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                        .child("Admin").child("Requests").child("Counsellor Requests");
                break;
            case 3:
                // Physiotherapist requests
                mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                        .child("Admin").child("Requests").child("Physiotherapist Requests");
                break;
            case 4:
                // Medical device requests
                mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                        .child("Admin").child("Requests").child("Medical Device Requests");
                break;
            default:
                // Invalid request type
                throw new IllegalArgumentException("Invalid request type: " + requestType);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_request, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Request request = list.get(position);
        if (request.getStatus().equals("Approved") || request.getStatus().equals("Denied")) {
            // If the request has already been approved or denied, hide the view
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        } else {
            // If the request has not been approved or denied, show the view
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.messageTextView.setText(request.getDescription());
            holder.statusTextView.setText(request.getStatus());
            holder.approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request.setStatus("Approved");
                    Map<String, Object> userUpdates1 = new HashMap<>();
                    userUpdates1.put("status", "Approved");
                    mDatabase.child(request.getId()).updateChildren(userUpdates1);
                    holder.denyButton.setVisibility(View.GONE);
                    holder.approveButton.setVisibility(View.GONE);
                }
            });
            holder.denyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    request.setStatus("Denied");
                    Map<String, Object> userUpdates1 = new HashMap<>();
                    userUpdates1.put("status", request.getStatus());
                    mDatabase.child(request.getId()).updateChildren(userUpdates1);
                    holder.denyButton.setVisibility(View.GONE);
                    holder.approveButton.setVisibility(View.GONE);
                    // Remove the request from the list and notify the adapter of the removal
                    int index = holder.getAdapterPosition();
                    list.remove(index);
                    notifyItemRemoved(index);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView statusTextView;
        Button approveButton;
        Button denyButton;
        public MyViewHolder(@NonNull View view) {
            super(view);
            messageTextView = view.findViewById(R.id.message_textview);
            statusTextView = view.findViewById(R.id.status_textview);
            approveButton = view.findViewById(R.id.approve_button);
            denyButton = view.findViewById(R.id.deny_button);
        }
    }
}
