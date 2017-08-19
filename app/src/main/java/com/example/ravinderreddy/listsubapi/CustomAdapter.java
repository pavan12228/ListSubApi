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

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.Recycler>
{

    List<Model> modelList = new LinkedList<>();
    Context mContext;

    public CustomAdapter(Context mContext, List<Model> modelList) {
        this.mContext = mContext;
        this.modelList = modelList;

    }

    public  class Recycler extends RecyclerView.ViewHolder {
        TextView productid, productName, productPrice;
        public Recycler(View itemView) {
            super(itemView);
            productid = (TextView) itemView.findViewById(R.id.product_id);
            productName = (TextView) itemView.findViewById(R.id.product_name);


        }
    }

    @Override
    public Recycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout,parent,false);
        return new Recycler(view);
    }

    @Override
    public void onBindViewHolder(Recycler holder, int position) {
        Model model = modelList.get(position);
        holder.productid.setText(model.getParent_id());
        holder.productName.setText(model.getCategory());

    }

    @Override
    public int getItemCount() {
        if(modelList.size()> 0)
        return modelList.size();
        else return 1;
    }

    public void clear(){
        modelList.clear();
        notifyDataSetChanged();
    }
}

