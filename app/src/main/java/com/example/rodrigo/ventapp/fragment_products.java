package com.example.rodrigo.ventapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class fragment_products extends Fragment
{

    public static fragment_products newInstance() {
        fragment_products listProducts = new fragment_products();
        return listProducts;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        ListView listProducts = getActivity().findViewById(R.id.list_sales_lst_products);
        adapter_product adapterProduct = new adapter_product();
        listProducts.setAdapter(adapterProduct);

    }

    public class adapter_product extends BaseAdapter

    {
        String[] nombres = {"PS4", "Xbox", "Nintendo", "PC", "PS4", "Xbox", "Nintendo"};
        String[] descripciones = {"consola pro", "consola qlera", "juguete", "master race", "consola pro", "consola qlera", "juguete"};

        @Override
        public int getCount()
        {
            return nombres.length;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            view = getLayoutInflater().inflate(R.layout.layout_list, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.product_img);
            TextView productName = (TextView)view.findViewById(R.id.product_name);
            TextView productDesc = (TextView)view.findViewById(R.id.product_desc);

            productName.setText(nombres[position]);
            productDesc.setText(descripciones[position]);

            return view;
        }
    }
}
