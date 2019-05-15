package adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mystore.MainActivity;
import com.example.mystore.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import common.Helper;
import models.Product;

public class CartAdapter  extends BaseAdapter {
    private Context mContext;
    private ArrayList<Product> list;
    SharedPreferences sharedpreferences;

    public CartAdapter(Context aContext, ArrayList<Product> listData) {
        mContext = aContext;
        list = listData;
        this.sharedpreferences = aContext.getSharedPreferences("cart", Context.MODE_PRIVATE);

    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.cart_item,parent,false);
        Product item = list.get(position);

        TextView contentLblName = (TextView) listItem.findViewById(R.id.content_lbl_name);
        TextView contentLblPrice = (TextView) listItem.findViewById(R.id.content_lbl_price);

        contentLblName.setText(item.getName());
        contentLblPrice.setText(item.getPrice() + "");


        MaterialButton btnDelete = listItem.findViewById(R.id.btn_delete_product);
        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity ctx = (MainActivity)v.getContext();
                Set<String> set = sharedpreferences.getStringSet("productIds", new HashSet<String>() );
                set.remove(0);
                list.remove(0);
                notifyDataSetChanged();
                AlertDialog.Builder dialogBuilder = Helper.GetDialogBuilder(ctx, "Order success", "");
                dialogBuilder
                        .setPositiveButton("Yes", null)
                        .show();
            }
        });
        return listItem;
    }


}
