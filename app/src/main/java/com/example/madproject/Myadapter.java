package com.example.madproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {

    Context context;
    ArrayList<User> list;

    public Myadapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new Myviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        User user=list.get(position);
        holder.name.setText(user.name);
        holder.phoneno.setText(user.phoneno);
        holder.availablity.setText(user.volunteer);

    }

    @Override
    public int getItemCount() {

        return list.size();

    }

    public static class Myviewholder extends RecyclerView.ViewHolder{

        TextView name , phoneno , availablity;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phoneno=itemView.findViewById(R.id.phone);
            availablity=itemView.findViewById(R.id.available);


        }
    }


}
