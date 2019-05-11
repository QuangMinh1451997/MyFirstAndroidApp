package com.example.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Random;

import models.Constant;
import models.Product;
import models.User;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private User user;
    private String fragment = "home";
    private FragmentTransaction transaction;
    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // fake data
        updateList(Constant.PRODUCTS);

        this.intent = getIntent();
        this.bottomNav = (BottomNavigationView) this.findViewById(R.id.navigation_bottom);
        user = (User) this.intent.getSerializableExtra("user");
        this.fragment = this.intent.getStringExtra("fragment");

        this.initBottomNavigationEvent();
        this.bottomNav.setSelectedItemId(R.id.bottom_nav_home);
    }

    private void initBottomNavigationEvent(){
        this.bottomNav.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getItemId()) {
                            case R.id.bottom_nav_home:
                                transaction.replace(R.id.frame_main, new HomeIntroduceFragment());
                                break;
                            case R.id.bottom_nav_orders:
                                transaction.replace(R.id.frame_main, new HomeIntroduceFragment());
                                break;
                            case R.id.bottom_nav_profile:
                                transaction.replace(R.id.frame_main, new HomeIntroduceFragment());
                                break;
                            case R.id.bottom_nav_setting:
                                transaction.replace(R.id.frame_main, new CartFragment());
                                break;
                        }
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    }
                });
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
