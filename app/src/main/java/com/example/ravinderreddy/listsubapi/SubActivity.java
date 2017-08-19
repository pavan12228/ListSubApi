package com.example.ravinderreddy.listsubapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ravinder Reddy on 19-08-2017.
 */

public class SubActivity  extends AppCompatActivity
{
    private RecyclerView recyclerView;
    CustomAdapter1 customAdapter;
    List<Model> modelList = new LinkedList<>();
    private String categoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list);
         Bundle b= this.getIntent().getExtras();
        if(b!=null){
             categoryId=b.getString("categoryid");
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(SubActivity.this,SubActivity.class).putExtra("categoryid",modelList.get(position).getParent_id()));
            }
        }));
        callApi();


    }

    private void callApi() {
        ApiServiceCall apiServiceCall= Utils.callApi("");
              apiServiceCall.listSub(categoryId,"","A","10","1", new Callback<JsonObject>() {
                  @Override
                  public void success(JsonObject jsonObject, Response response) {
                     JsonArray jsonArray =jsonObject.get("products").getAsJsonArray();
                      if (jsonArray.size() >0 ) {
                          for (int i = 0; i < jsonArray.size(); i++) {
                              JsonObject  jsonObject1 =jsonArray.get(i).getAsJsonObject();
                              String name  = jsonObject1.get("product").getAsString();
                              Model model=new Model();
                              model.setCategory(name);
                              modelList.add(model);
                          }


                          customAdapter = new CustomAdapter1(SubActivity.this, modelList);
                          recyclerView.setAdapter(customAdapter);
                          customAdapter.notifyDataSetChanged();
                      } else {
                      }

                  }

                  @Override
                  public void failure(RetrofitError error) {
                      Utils.message(getApplicationContext(),"retrofit error"+error.toString());

                  }
              });


    }
}
