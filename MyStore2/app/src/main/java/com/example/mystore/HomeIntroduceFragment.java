package com.example.mystore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import adapters.ProductsAdapter;
import database.MyDatabaseHelper;
import models.Product;
import models.ProductSearchModel;

public class HomeIntroduceFragment extends Fragment {
    private GridView listView;
    private ProductsAdapter mAdapter;
    private ProductSearchModel searchModel = new ProductSearchModel();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home_introduce, container, false);

        listView = (GridView) view.findViewById(R.id.productGridView);
//        ArrayList<Product> list = Constant.PRODUCTS;
        ArrayList<Product> list = new ArrayList<Product>();
        updateList(list);

        mAdapter = new ProductsAdapter(getContext(),list);
        listView.setAdapter(mAdapter);

        return view;
    }

    private void updateList(ArrayList<Product> list){

        MyDatabaseHelper db = new MyDatabaseHelper(this.getContext());
        db.createDefaultNotesIfNeed();

        list.addAll(db.getAllProduct());
    }

}
