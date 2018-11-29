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
import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_menu extends AppCompatActivity
{

    String tmp;

    public int id;
    public String[] productName;
    public String[] productDesc;
    public String[] productPhoto;

    public String[] ClientName;
    public String[] ClientPhone;
    public String[] ClientPhoto;

    public String[] SaleClient;
    public String[] SaleTotal;
    public String[] SalePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();

        if(b != null)
            id = b.getInt("id");

        //Pone la pantalla de productos primero
        setContentView(R.layout.layout_main);
        Fragment selectedFragment = null;
        selectedFragment = fragment_products.newInstance(id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

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
                        case R.id.navigation_products: selectedFragment = fragment_products.newInstance(id); break;
                        case R.id.navigation_clients: selectedFragment = fragment_clients.newInstance(id); break;
                        case R.id.navigation_sales: selectedFragment = fragment_sales.newInstance(id); break;
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
        Fragment selectedFragment = fragment_products.newInstance(id);
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
                            case R.id.navigation_products: selectedFragment = fragment_products.newInstance(id); break;
                            case R.id.navigation_clients: selectedFragment = fragment_clients.newInstance(id); break;
                            case R.id.navigation_sales: selectedFragment = fragment_sales.newInstance(id); break;
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

        if(!name.getText().toString().isEmpty() && !buyingPrice.getText().toString().isEmpty() && !sellingPrice.getText().toString().isEmpty() &&
                !qty.getText().toString().isEmpty() && !desc.getText().toString().isEmpty())
        {
            String tmp = "http://rodrigo-vr12.000webhostapp.com/insertProducto.php?" +
                    "usuario=" + id + "&" +
                    "nombre=" + name.getText().toString() + "&" +
                    "descripcion=" + desc.getText().toString() + "&" +
                    "preciocompra=" + buyingPrice.getText().toString() + "&" +
                    "precioventa=" + sellingPrice.getText().toString() + "&" +
                    "cantidad=" + qty.getText().toString() + "&" +
                    "foto=";

            new WebPOST().execute(tmp);
            registerProductBackOnClick(view);
        }

        else
        {
            Toast.makeText(activity_menu.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
        }
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
        Fragment selectedFragment = fragment_clients.newInstance(id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.navigation_clients);

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
                        case R.id.navigation_products: selectedFragment = fragment_products.newInstance(id); break;
                        case R.id.navigation_clients: selectedFragment = fragment_clients.newInstance(id); break;
                        case R.id.navigation_sales: selectedFragment = fragment_sales.newInstance(id); break;
                    }

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.relative_layout, selectedFragment);
                    transaction.commit();

                    return true;
                }
            }
        );
    }

    //Botón Registrar cliente/Registrar
    public void ClientUploadOnClick(View view)
    {
        TextView name = findViewById(R.id.register_client_txt_name);
        TextView address = findViewById(R.id.register_client_txt_address);
        TextView mail = findViewById(R.id.register_client_txt_mail);
        TextView phone = findViewById(R.id.register_client_txt_phone);


        if(!name.getText().toString().isEmpty() && !address.getText().toString().isEmpty() &&
                !mail.getText().toString().isEmpty() && !phone.getText().toString().isEmpty())
        {
            String tmp = "http://rodrigo-vr12.000webhostapp.com/insertCliente.php?" +
                    "usuario=" + id + "&" +
                    "nombre=" + name.getText().toString() + "&" +
                    "direccion=" + address.getText().toString() + "&" +
                    "telefono=" + mail.getText().toString() + "&" +
                    "mail=" + phone.getText().toString() + "&" +
                    "foto=";

            new WebPOST().execute(tmp);
            registerClientBackOnClick(view);
        }

        else
            Toast.makeText(activity_menu.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
    }

    //Botón Registrar venta/Atrás
    public void registerSaleBackOnClick(View view)
    {
        setContentView(R.layout.layout_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        Fragment selectedFragment = fragment_sales.newInstance(id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_layout, selectedFragment);
        transaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.navigation_sales);

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
                        case R.id.navigation_products: selectedFragment = fragment_products.newInstance(id); break;
                        case R.id.navigation_clients: selectedFragment = fragment_clients.newInstance(id);break;
                        case R.id.navigation_sales: selectedFragment = fragment_sales.newInstance(id); break;
                    }

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.relative_layout, selectedFragment);
                    transaction.commit();

                    return true;
                }
            }
        );
    }

    //Botón Registar venta/Registrar
    public void SaleUploadOnClick(View view)
    {
        TextView client = findViewById(R.id.register_sale_txt_client);
        TextView product = findViewById(R.id.register_sale_txt_product);
        TextView qty = findViewById(R.id.register_sale_txt_quantity);
        TextView discount = findViewById(R.id.register_sale_txt_discount);
        RadioButton cash = findViewById(R.id.register_sale_rbtn_cash);
        RadioButton credit = findViewById(R.id.register_sale_rbtn_credit);

        String cashSelected;
        String creditSelected;
        int disc = 0;

        if(cash.isChecked())
            cashSelected = "1";
        else
            cashSelected = "0";

        if(credit.isChecked())
            creditSelected = "1";
        else
            creditSelected = "0";

        if(Integer.parseInt(discount.getText().toString()) >= 100)
            disc = 100;

        else if(Integer.parseInt(discount.getText().toString()) <= 0)
            disc = 0;
        else
            disc = Integer.parseInt(discount.getText().toString());



        if(!client.getText().toString().isEmpty() && !product.getText().toString().isEmpty() &&
                !qty.getText().toString().isEmpty() && !cash.getText().toString().isEmpty()
                && !credit.getText().toString().isEmpty())
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String currentDateandTime = sdf.format(new Date());

            String tmp = "http://rodrigo-vr12.000webhostapp.com/insertVenta.php?" +
                    "usuario=" + id + "&" +
                    "cliente=" + client.getText().toString() + "&" +
                    "producto=" + product.getText().toString() + "&" +
                    "cantidad=" + qty.getText().toString() + "&" +
                    "descuento=" + disc + "&" +
                    "abono=" + creditSelected + "&" +
                    "contado=" + cashSelected+ "&" +
                    "fecha=" + currentDateandTime;

            new WebPOST().execute(tmp);
            registerSaleBackOnClick(view);
        }

        else
        {
            Toast.makeText(activity_menu.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(activity_menu.this, "OK!", Toast.LENGTH_SHORT).show();
        }
    }
}
