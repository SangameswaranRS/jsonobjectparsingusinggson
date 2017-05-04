package com.example.sangameswaran.internpractice02.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sangameswaran.internpractice02.R;
import com.example.sangameswaran.internpractice02.entity.Model;

import java.util.ArrayList;

/**
 * Created by Sangameswaran on 03-05-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    ArrayList<Model> arrayList=new ArrayList<>();


    public RecyclerViewAdapter(ArrayList<Model> models)
    {
        this.arrayList=models;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Model model=new Model();
        model=arrayList.get(position);
        holder.t1.setText(model.getId());
        holder.t2.setText(model.getName());
        holder.t3.setText(model.getEmail());
        holder.t4.setText(model.getAddress());
        holder.t5.setText(model.getGender());
        holder.t6.setText(model.getPhone().getMobile());
        holder.t7.setText(model.getPhone().getHome());
        holder.t8.setText(model.getPhone().getOffice());

        Log.d("ADAPTER-ON_BIND",""+model.getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5,t6,t7,t8;

        public ViewHolder(View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.tv1);
            t2=(TextView)itemView.findViewById(R.id.tv2);
            t3=(TextView)itemView.findViewById(R.id.tv3);
            t4=(TextView)itemView.findViewById(R.id.tv4);
            t5=(TextView)itemView.findViewById(R.id.tv5);
            t6=(TextView)itemView.findViewById(R.id.tv6);
            t7=(TextView)itemView.findViewById(R.id.tv7);
            t8=(TextView)itemView.findViewById(R.id.tv8);



        }
    }
}
