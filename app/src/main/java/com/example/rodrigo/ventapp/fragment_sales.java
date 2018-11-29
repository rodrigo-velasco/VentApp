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

public class fragment_sales extends Fragment
{

    public static fragment_sales newInstance() {
        fragment_sales listSales = new fragment_sales();
        return listSales;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        ListView listSales = getActivity().findViewById(R.id.list_sales_lst_sales);
        adapter_sales adapterSales = new adapter_sales();
        listSales.setAdapter(adapterSales);

    }

    public class adapter_sales extends BaseAdapter

    {
        String[] clientes = {"men1", "men2", "men3", "men4", "men5", };
        String[] total = {"999", "8888", "777", "666", "555", };

        @Override
        public int getCount()
        {
            return clientes.length;
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

            productName.setText(clientes[position]);
            productDesc.setText(total[position]);

            return view;
        }
    }
}
