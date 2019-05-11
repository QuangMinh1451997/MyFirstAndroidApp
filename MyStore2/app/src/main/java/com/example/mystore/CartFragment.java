package com.example.mystore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import adapters.CartAdapter;
import models.Constant;
import models.Product;

public class CartFragment extends Fragment {

    ListView listView;
    CartAdapter mAdapter;
    SharedPreferences sharedpreferences;
    View view;
    double totalPrice = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Đọc file xml tạo ra đối tượng View.

        // inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)

        view= inflater.inflate(R.layout.fragment_cart, container, false);
        this.sharedpreferences = view.getContext().getSharedPreferences("cart", Context.MODE_PRIVATE);
        Set<String> set = sharedpreferences.getStringSet("productIds", new HashSet<String>() );
        listView = (ListView) view.findViewById(R.id.cart_detail);
        ArrayList<Product> products = new ArrayList<Product>();
        updateList(set, products);

        mAdapter = new CartAdapter(getContext(), products);
        listView.setAdapter(mAdapter);

        return view;
    }

    public void updateList(Set<String> ids, ArrayList<Product> list){
        ArrayList<Product> products = Constant.PRODUCTS;
        for(int i=0; i< products.size(); i++){
            if(ids.contains(i + "") == true){
                list.add(products.get(i));
                totalPrice += products.get(i).getPrice();
            }
        }

        EditText inputAddress = (EditText) view.findViewById(R.id.input_address);
        inputAddress.setText(Constant.user.getAddress());
        TextView contentLblTotal = view.findViewById(R.id.content_lbl_total_price);
        contentLblTotal.setText(totalPrice + "");
    }

    private void initEventPayment(){
        MaterialButton btnPay = view.findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                DetailActivity ctx = (DetailActivity)v.getContext();

            }
        });
    }
}
