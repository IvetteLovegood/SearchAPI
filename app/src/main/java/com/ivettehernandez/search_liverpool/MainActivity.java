package com.ivettehernandez.search_liverpool;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;
import com.ivettehernandez.search_liverpool.Data.RequestService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements RequestService.OnRequestSuccessListener, View.OnClickListener{

    RequestService requestService;
    TextInputLayout ti_main_producto;
    EditText et_main_producto;
    Button bt_main_search;
    RecyclerView rv_main_producto;
    ProgressBar spinner;

    private List<Product> products;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (ProgressBar)findViewById(R.id.progressBarloading);
        spinner.setVisibility(View.GONE);

        ti_main_producto = (TextInputLayout) findViewById(R.id.ti_main_producto);
        et_main_producto = (EditText) findViewById(R.id.et_main_producto);
        bt_main_search   = (Button) findViewById(R.id.but_main_search);
        rv_main_producto = (RecyclerView) findViewById(R.id.rv_main_producto);

        bt_main_search.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    @Override
    public void onSuccess(JsonObject response) {
        products = new ArrayList<>();
        JsonObject contents = response.getAsJsonArray("contents").get(0).getAsJsonObject().getAsJsonArray("mainContent").get(3).getAsJsonObject().getAsJsonArray("contents").get(0).getAsJsonObject();
        for(int i = 0; i<contents.getAsJsonArray("records").size();i++){
            JsonObject records = contents.getAsJsonArray("records").get(i).getAsJsonObject();

            products.add(new Product(records.getAsJsonArray("productDisplayName").get(0).getAsString(),
                    records.getAsJsonArray("sku.salePrice").get(0).getAsString(),
                    records.getAsJsonObject().getAsJsonArray("sku.thumbnailImage").get(0).getAsString()));
        }


        ProductItemAdapter itemAdapterGoals = new ProductItemAdapter(products,this);
        rv_main_producto.setLayoutManager(linearLayoutManager);
        rv_main_producto.setAdapter(itemAdapterGoals);
        itemAdapterGoals.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

        if(et_main_producto.getText().length() == 0 || et_main_producto.getText().toString().matches(" ")){
            ti_main_producto.setError("Search Product");



        }else{
            ti_main_producto.setError(null);
            requestService = new RequestService(this);
            requestService.setSuccessListener(this);
            requestService.sendRequest(et_main_producto.getText().toString(),"json");



        }


    }
}
