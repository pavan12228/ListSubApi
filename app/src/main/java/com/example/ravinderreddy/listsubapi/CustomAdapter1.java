package com.example.ravinderreddy.listsubapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ravinder Reddy on 19-08-2017.
 */

public  class CustomAdapter1  extends RecyclerView.Adapter<CustomAdapter1.Recycler>
{

    List<Model> modelList = new LinkedList<>();
    Context mContext;

    public CustomAdapter1(Context mContext, List<Model> modelList) {
        this.mContext = mContext;
        this.modelList = modelList;

    }

    public static class Recycler extends RecyclerView.ViewHolder {
        TextView  productName;
        public Recycler(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.product_name);


        }
    }

    @Override
    public Recycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout, parent,false);
        return new Recycler(view);
    }

    @Override
    public void onBindViewHolder(Recycler holder, int position) {
        Model model = modelList.get(position);
        holder.productName.setText(model.getCategory());

    }



    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void clear(){
        modelList.clear();
        notifyDataSetChanged();
    }


}
