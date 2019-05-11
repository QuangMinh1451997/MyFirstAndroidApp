package com.example.mystore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import common.Helper;
import models.Constant;
import models.Product;

public class DetailActivity extends AppCompatActivity {
    private Intent intent;
    private Product product;
    int id;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.sharedpreferences = getSharedPreferences("cart", Context.MODE_PRIVATE);
        this.intent = getIntent();
        this.id = intent.getIntExtra("id", -1);
        this.product = Constant.PRODUCTS.get(id);

        this.initProductView();
        this.initEventOrder();
    }

    public void initProductView(){
        TextView lblContentName = findViewById(R.id.content_lbl_name);
        TextView lblContentPrice = findViewById(R.id.content_lbl_price);

        lblContentName.setText(product.getName());
        lblContentPrice.setText(product.getPrice() + "");
    }

    public void initEventOrder(){
        MaterialButton btnOrder = findViewById(R.id.btn_order);
        btnOrder.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                DetailActivity ctx = (DetailActivity)v.getContext();
                Set<String> set = sharedpreferences.getStringSet("productIds", new HashSet<String>() );
                set.add(id + "");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putStringSet("productIds", set);
                editor.apply();
                AlertDialog.Builder dialogBuilder = Helper.GetDialogBuilder(ctx, "Order success", "Do you want to go your cart?");
                dialogBuilder
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}
