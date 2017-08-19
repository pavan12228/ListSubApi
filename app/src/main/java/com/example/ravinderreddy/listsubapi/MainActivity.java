package com.example.ravinderreddy.listsubapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
      CustomAdapter customAdapter;
    List<Model> modelList = new LinkedList<>();
    private String catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        callApi();
    }

    private void callApi() {
       ApiServiceCall apiServiceCall= Utils.callApi("");
                     apiServiceCall.listMain(new Callback<JsonObject>() {
                         @Override
                         public void success(JsonObject jsonObject, Response response) {
                             JsonObject jsonObject1 = jsonObject.getAsJsonObject();
                             JsonArray jsonArray = jsonObject1.get("categories").getAsJsonArray();
                             for (int i = 0; i < jsonArray.size(); i++) {
                                 JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
                                  catId = jsonObject2.get("category_id").getAsString();
                                 String catName = jsonObject2.get("category").getAsString();
                                 Model model = new Model();
                                 model.setParent_id(catId);
                                 model.setCategory(catName);
                                 modelList.add(model);

                             }

                             customAdapter = new CustomAdapter(getApplicationContext(), modelList);
                             recyclerView.setAdapter(customAdapter);
                             customAdapter.notifyDataSetChanged();
                             recyclerView.addOnItemTouchListener(
                                     new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                                         @Override public void onItemClick(View view, int position) {

                                             Intent intent=new Intent(getApplicationContext(),SubActivity.class);
                                             intent.putExtra("categoryid",modelList.get(position).getParent_id());
                                             startActivity(intent);

                                               }
                                     })
                             );
                         }


                         @Override
                         public void failure(RetrofitError error) {
                             Utils.message(getApplicationContext(),"retrofit error"+error.toString());
                         }
                     });


    }
}
