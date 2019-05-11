package com.example.mystore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

import adapters.ProductsAdapter;
import models.Product;

public class HomeSearchResultFragment extends Fragment {
    private GridView listView;
    private ProductsAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home_introduce, container, false);

        listView = (GridView) view.findViewById(R.id.productGridView);
        ArrayList<Product> list = new ArrayList<>();
        updateList(list);

        mAdapter = new ProductsAdapter(getContext(),list);
        listView.setAdapter(mAdapter);

        return view;
    }


    private void updateList(ArrayList<Product> list){
        Random rd = new Random();
        for (int i = 0; i < 10; i++){
            list.add(
                    new Product(
                            "Tivi " + (i + 1),
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            "Siêu phẩm đó mấy ba. mua lẹ đi còn kịp" + rd.nextInt(1)*200
                    ));
        }
    }
}
