package com.example.kirupa.jsonnew;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ResponsePogo.CountriesBean> responsePogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData(); //all a method in which we have implement our GET type web API
//        addData();
    }

//    private void addData() {
//        (API.getClient().addData(id,name,password)).enqueue(new Callback<addpojo>() {
//            @Override
//            public void onResponse(Call<addpojo> call, Response<addpojo> response) {
//                Log.d("responseGET raw", String.valueOf(response.raw()));
//
//            }
//
//            @Override
//            public void onFailure(Call<addpojo> call, Throwable t) {
//
//            }
//
//
//        });
//    }

    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

//      Call<UserListResponse_withImage> routeapiCall = API_SERVICE.ManagerLogin(strPhone);
        (API.getClient().getData()).enqueue(new Callback<ResponsePogo>() {
            @Override
            public void onResponse(Call<ResponsePogo> call, Response<ResponsePogo> response) {
                Log.d("responseGET message", response.message());
                Log.d("responseGET body", String.valueOf(response.body()));
                Log.d("responseGET code", String.valueOf(response.code()));
                Log.d("responseGET headers", String.valueOf(response.headers()));
                Log.d("responseGETisSuccessful", String.valueOf(response.isSuccessful()));
                Log.d("responseGET raw", String.valueOf(response.raw()));
                Log.d("responseGET", String.valueOf(response.body().getCountries()));
                progressDialog.dismiss(); //dismiss progress dialog
                responsePogo = response.body().getCountries();

                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<ResponsePogo> call, Throwable t) {

            }


        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
//        GridLayoutManager linearLayoutManager= new GridLayoutManager(MainActivity.this,2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        RecyclerViewAdapter recyclerViewAdapter  = new RecyclerViewAdapter(MainActivity.this, responsePogo);
        recyclerView.setAdapter(recyclerViewAdapter); // set the Adapter to RecyclerView
    }
}
