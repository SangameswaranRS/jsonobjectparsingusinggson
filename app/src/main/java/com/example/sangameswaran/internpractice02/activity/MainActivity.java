package com.example.sangameswaran.internpractice02.activity;

import  android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sangameswaran.internpractice02.R;
import com.example.sangameswaran.internpractice02.adapter.RecyclerViewAdapter;
import com.example.sangameswaran.internpractice02.entity.ContactEntity;
import com.example.sangameswaran.internpractice02.entity.Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    Button b1;
    ArrayList<Model> arrayList;
    Gson gson = new Gson();
    ArrayList<Model> modelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.rv);
        b1=(Button) findViewById(R.id.downloadbtn);
        manager=new LinearLayoutManager(this);

        ArrayList<Model> modelList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        adapter=new RecyclerViewAdapter(modelList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadContacts();
            }
        });
    }

    public void loadContacts(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "http://api.androidhive.info/contacts/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray array;
                try {
                    array=response.getJSONArray("contacts");
                    Log.d("asvdfjkhfg","ad"+array.toString());
                    Gson gson=new Gson();
                    Type listType = new TypeToken<List<Model>>() {}.getType();

                    modelList=gson.fromJson(array.toString(),listType);
                    adapter=new RecyclerViewAdapter(modelList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(manager);

                    adapter.notifyDataSetChanged();


                    int count=0;
                    /*while(count<array.length()) {
                        JSONObject jsonObject = array.getJSONObject(count);
                        Model m=new Model();
                        Gson gson=new Gson();
                        m=gson.fromJson(jsonObject.toString(),Model.class);
                        modelList.add(m);
                        Log.d("TAAGGG","+"+m.getName());
                        adapter=new RecyclerViewAdapter(modelList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(manager);

                        adapter.notifyDataSetChanged();
                        count++;
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
