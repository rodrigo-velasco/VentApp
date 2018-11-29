package com.example.rodrigo.ventapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class fragment_sales extends Fragment
{

    static int user;
    static String json;
    String[]n, d;

    public static fragment_sales newInstance(int id)
    {
        user = id;
        fragment_sales listProducts = new fragment_sales();
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
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        new WebPOST().execute("http://rodrigo-vr12.000webhostapp.com/listVentas.php?usuario=" + user);
    }

    public class adapter_product extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return n.length;
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

            productName.setText(n[position]);
            productDesc.setText(d[position]);

            return view;
        }
    }

    public class WebGET extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                String result = "";
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();

                if(code==200)
                {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    if (in != null)
                    {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line;

                        while ((line = bufferedReader.readLine()) != null)
                            result += line;
                    }

                    in.close();

                    return result;
                }
            }

            catch (Exception e)
            {
                return  e.getMessage();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result)
        {
            json = result;
        }
    }

    public class WebPOST extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                URL url = new URL(params[0]);
                URLConnection uc = url.openConnection();

                uc.setDoInput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null)
                    response.append(inputLine);

                in.close();

                return response.toString();
            }

            catch (Exception e)
            {
                return e.getMessage();
            }
        }

        protected void onPostExecute(String response)
        {
            try
            {
                json = response;

                JSONArray jsonArray = new JSONArray(json);

                List<String> nombres = new ArrayList<>();
                List<String> descripcion = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject json = jsonArray.getJSONObject(i);
                    nombres.add(json.getString("fecha"));
                    descripcion.add(json.getString("total"));
                }

                n = nombres.toArray(new String[nombres.size()]);
                d = descripcion.toArray(new String[descripcion.size()]);

                ListView listProducts = getActivity().findViewById(R.id.list_sales_lst_products);
                adapter_product adapterProduct = new adapter_product();
                listProducts.setAdapter(adapterProduct);
            }

            catch (Exception e)
            {

            }
        }
    }
}
