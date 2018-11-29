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

public class fragment_clients extends Fragment
{

    public static fragment_clients newInstance() {
        fragment_clients listClients = new fragment_clients();
        return listClients;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        ListView listProducts = getActivity().findViewById(R.id.list_clients_lst_clients);
        adapter_client adapterClient = new adapter_client();
        listProducts.setAdapter(adapterClient);

    }

    public class adapter_client extends BaseAdapter

    {
        String[] nombres = {"ramiro", "juanito", "roberto"};
        String[] mails = {"aber", "al", "syne"};

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
            productDesc.setText(mails[position]);

            return view;
        }
    }
}
