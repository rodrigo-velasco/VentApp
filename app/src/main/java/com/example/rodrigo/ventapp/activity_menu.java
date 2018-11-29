package com.example.rodrigo.ventapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class activity_menu extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Pone la pantalla de productos primero
        setContentView(R.layout.layout_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        Fragment selectedFragment = fragment_products.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        //Cambia la pantalla según el click en la barra inferior
        bottomNavigationView.setOnNavigationItemSelectedListener
        (
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    Fragment selectedFragment = null;

                    switch (item.getItemId())
                    {
                        case R.id.navigation_products:
                            selectedFragment = fragment_products.newInstance(); break;
                        case R.id.navigation_clients:
                            selectedFragment = fragment_clients.newInstance(); break;
                        case R.id.navigation_sales:
                            selectedFragment = fragment_sales.newInstance(); break;
                    }

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.relative_layout, selectedFragment);
                    transaction.commit();

                    return true;
                }
            }
        );
    }


    //Botón Menu principal/Registrar producto
    public void registerProductOnClick(View view)
    {
        setContentView(R.layout.layout_addproduct);
    }

    //Botón Menu principal/Registrar cliente
    public void registerClientOnClick(View view)
    {
        setContentView(R.layout.layout_addclient);
    }

    //Botón Menu principal/Registrar venta
    public void registerSaleOnClick(View view)
    {
        setContentView(R.layout.layout_addsale);
    }

    //Botón Registrar producto/Atrás
    public void registerProductBackOnClick(View view)
    {
        //Pone la pantalla de productos primero
        setContentView(R.layout.layout_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        Fragment selectedFragment = fragment_products.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        //Cambia la pantalla según el click en la barra inferior
        bottomNavigationView.setOnNavigationItemSelectedListener
                (
                        new BottomNavigationView.OnNavigationItemSelectedListener()
                        {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item)
                            {
                                Fragment selectedFragment = null;

                                switch (item.getItemId())
                                {
                                    case R.id.navigation_products:
                                        selectedFragment = fragment_products.newInstance(); break;
                                    case R.id.navigation_clients:
                                        selectedFragment = fragment_clients.newInstance(); break;
                                    case R.id.navigation_sales:
                                        selectedFragment = fragment_sales.newInstance(); break;
                                }

                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.relative_layout, selectedFragment);
                                transaction.commit();

                                return true;
                            }
                        }
                );
    }

    //Botón Registrar producto/Registrar
    public void ProductUploadOnClick(View view) throws Exception
    {
        TextView name = findViewById(R.id.register_product_txt_name);
        TextView buyingPrice = findViewById(R.id.register_product_txt_buyingprice);
        TextView sellingPrice = findViewById(R.id.register_product_txt_sellingprice);
        TextView qty = findViewById(R.id.register_product_txt_quantity);
        TextView desc = findViewById(R.id.register_product_txt_description);

        String tmp = "http://rodrigo-vr12.000webhostapp.com/insertProducto.php?" +
                "nombre=" + name.getText().toString() + "&" +
                "precioventa=" + sellingPrice.getText().toString() + "&" +
                "cantidad=" + qty.getText().toString() + "&" +
                "preciocosto=" + buyingPrice.getText().toString() + "&" +
                "descripcion=" + desc.getText().toString();

        new WebPOST().execute(tmp);
    }

    //Botón Registrar producto/Tomar foto
    public void ProductTakePhotoOnClick(View view)
    {

    }

    //Botón Registrar cliente/Atrás
    public void registerClientBackOnClick(View view)
    {
        setContentView(R.layout.layout_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        Fragment selectedFragment = fragment_clients.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.navigation_clients);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_products:
                        selectedFragment = fragment_products.newInstance();
                        break;
                    case R.id.navigation_clients:
                        selectedFragment = fragment_clients.newInstance();
                        break;
                    case R.id.navigation_sales:
                        selectedFragment = fragment_sales.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.relative_layout, selectedFragment);
                transaction.commit();

                return true;
            }
        });
    }

    //Botón Registrar cliente/Registrar
    public void ClientUploadOnClick(View view)
    {
        TextView name = findViewById(R.id.register_client_txt_name);
        TextView address = findViewById(R.id.register_client_txt_address);
        TextView mail = findViewById(R.id.register_client_txt_mail);
        TextView phone = findViewById(R.id.register_client_txt_phone);

        String tmp = "http://rodrigo-vr12.000webhostapp.com/insertCliente.php?" +
                "direccion=" + address.getText().toString() + "&" +
                "mail=" + mail.getText().toString() + "&" +
                "nombre=" + name.getText().toString() + "&" +
                "telefono=" + phone.getText().toString();

        new WebPOST().execute(tmp);
    }

    //Botón Registrar venta/Atrás
    public void registerSaleBackOnClick(View view)
    {
        setContentView(R.layout.layout_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        Fragment selectedFragment = fragment_sales.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.navigation_sales);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_products:
                        selectedFragment = fragment_products.newInstance();
                        break;
                    case R.id.navigation_clients:
                        selectedFragment = fragment_clients.newInstance();
                        break;
                    case R.id.navigation_sales:
                        selectedFragment = fragment_sales.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.relative_layout, selectedFragment);
                transaction.commit();

                return true;
            }
        });
    }

    //Botón Registar venta/Registrar
    public void SaleUploadOnClick(View view)
    {
        TextView client = findViewById(R.id.register_sale_txt_client);
        TextView product = findViewById(R.id.register_sale_txt_product);
        TextView qty = findViewById(R.id.register_sale_txt_quantity);
        RadioButton cash = findViewById(R.id.register_sale_rbtn_cash);
        RadioButton credit = findViewById(R.id.register_sale_rbtn_credit);

        String cashSelected;
        String creditSelected;

        if(cash.isChecked())
            cashSelected = "1";
        else
            cashSelected = "0";

        if(credit.isChecked())
            creditSelected = "1";
        else
            creditSelected = "0";


        String tmp = "http://rodrigo-vr12.000webhostapp.com/insertVenta.php?" +
                "cantidad=" + qty.getText().toString() + "&" +
                "contado=" + cashSelected + "&" +
                "credito=" + creditSelected + "&" +
                "nombreCliente=" + client.getText().toString() + "&" +
                "nombreProducto=" + product.getText().toString();

        new WebPOST().execute(tmp);
    }

    public class WebGET extends AsyncTask<String, Void, String>
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
            Toast.makeText(activity_menu.this, "Simón", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(activity_menu.this, "Simón", Toast.LENGTH_SHORT).show();
        }
    }
}
