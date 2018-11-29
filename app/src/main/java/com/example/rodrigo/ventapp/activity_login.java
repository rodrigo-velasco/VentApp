package com.example.rodrigo.ventapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class activity_login extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        TextView user = findViewById(R.id.login_txt_username);
        TextView pass = findViewById(R.id.login_txt_password);
        CheckBox check = findViewById(R.id.login_chk_remember);

        user.setText(getSharedPreferences("mail", Context.MODE_PRIVATE).getString("mail", ""));
        pass.setText(getSharedPreferences("pass", Context.MODE_PRIVATE).getString("pass", ""));

        if(getSharedPreferences("checked", Context.MODE_PRIVATE).getString("checked", "false").equals("true"))
         check.setChecked(true);
    }

    //Botón Login/Entrar
    public void LoginOnClick(View view)
    {
        TextView user = findViewById(R.id.login_txt_username);
        TextView pass = findViewById(R.id.login_txt_password);
        Button loginBtn = findViewById(R.id.login_btn_login);

        loginBtn.setEnabled(false);

        String url = "http://rodrigo-vr12.000webhostapp.com/login.php?" +
                "mail=" + user.getText() + "&" +
                "pass=" + pass.getText();

        new ValidateLogin().execute(url);

    }

    //Botón Login/Registrarse
    public void registerBtnOnClick(View view)
    {
        setContentView(R.layout.layout_addseller);
    }

    //Botón Registro vendedor/Atrás
    public void registerBackBtnOnClick(View view)
    {
        setContentView(R.layout.layout_login);
    }

    //Botón Registro vendedor/Registrar
    public void SellerUploadOnClick(View view)
    {

        TextView password = findViewById(R.id.register_seller_txt_password);
        TextView password2 = findViewById(R.id.register_seller_txt_password2);
        Button register = findViewById(R.id.register_seller_btn_register);

        if(password.getText().toString().equals(password2.getText().toString()))
        {
            TextView name = findViewById(R.id.register_seller_txt_name);
            TextView phone = findViewById(R.id.register_seller_txt_phone);
            TextView mail = findViewById(R.id.register_seller_txt_mail);
            TextView address = findViewById(R.id.register_seller_txt_address);

            String url = "http://rodrigo-vr12.000webhostapp.com/insertVendedor.php?" +
                    "nombre=" + name.getText().toString() + "&" +
                    "telefono=" + phone.getText().toString() + "&" +
                    "mail=" + mail.getText().toString() + "&" +
                    "contrasena=" + password.getText().toString() + "&" +
                    "direccion=" + address.getText().toString();

            register.setEnabled(false);
            new RegisterSeller().execute(url);
        }

        else
        {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            register.setEnabled(true);
        }
    }

    //Valida que los detos de Layout_Login sean correctos y pasa a la siguiente pantalla en caso de serlos
    private class ValidateLogin extends AsyncTask<String, Void, String>
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
            if(response.equals("ok"))
            {
                CheckBox check = findViewById(R.id.login_chk_remember);
                TextView email = findViewById(R.id.login_txt_username);
                TextView password = findViewById(R.id.login_txt_password);

                SharedPreferences mail = getSharedPreferences("mail", Context.MODE_PRIVATE);
                SharedPreferences pass = getSharedPreferences("pass", Context.MODE_PRIVATE);
                SharedPreferences checked = getSharedPreferences("checked", Context.MODE_PRIVATE);

                if(check.isChecked())
                {
                    mail.edit().putString("mail", email.getText().toString()).apply();
                    pass.edit().putString("pass", password.getText().toString()).apply();
                    checked.edit().putString("checked", "true").apply();
                }

                else
                {
                    mail.edit().putString("mail", "").apply();
                    pass.edit().putString("pass", "").apply();
                    checked.edit().putString("checked", "false").apply();
                }

                Intent intent = new Intent(activity_login.this, activity_menu.class);
                activity_login.this.startActivity(intent);
                finish();
            }

            else
            {
                Toast.makeText(activity_login.this, "Datos no válidos", Toast.LENGTH_SHORT).show();
                Button loginBtn = findViewById(R.id.login_btn_login);

                loginBtn.setEnabled(true);
            }
        }
    }

    //Registra un vendedor
    private class RegisterSeller extends AsyncTask<String, Void, String>
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
            Button register = findViewById(R.id.register_seller_btn_register);
            register.setEnabled(true);
            setContentView(R.layout.layout_login);
            Toast.makeText(activity_login.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        }
    }
}
