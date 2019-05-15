package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mystore.DetailActivity;
import com.example.mystore.R;

import java.util.List;

import models.Product;

public class ProductsAdapter extends BaseAdapter {


    private Context mContext;
    private List<Product> listData;
    private LayoutInflater layoutInflater;

    public ProductsAdapter(Context aContext,  List<Product> listData) {
        this.mContext = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }

        final Product product = listData.get(position);

        TextView contentLblName = (TextView) convertView.findViewById(R.id.content_lbl_name);
        TextView contentLblPrice = (TextView) convertView.findViewById(R.id.content_lbl_price);

        contentLblName.setText(product.getName());
        contentLblPrice.setText(product.getPrice() + "");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("id", position);
                view.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

}