package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class ClienteActivity extends Activity {

    ConnectionClass connectionClass;
    EditText edtuserid,edtpass;
    Button btnlogin;
    ProgressBar pbbar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        connectionClass = new ConnectionClass();
        edtuserid = (EditText) findViewById(R.id.et_username);
        edtpass = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_Login);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

        //Intent it = new Intent(this, ListaServicoActivity.class);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");
                Intent i = new Intent(ClienteActivity.this, ListaServicoActivity.class);
                startActivity(i);
            }
        });

        }


    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();

        Context context;

        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {

            pbbar.setVisibility(View.GONE);
            Toast.makeText(ClienteActivity.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Toast.makeText(ClienteActivity.this,r,Toast.LENGTH_SHORT).show();
                setContentView(R.layout.list_service_provider);
                //context.startActivity(new Intent(context,ListaServicoActivity.class));
                //this.context = context.getApplicationContext();

            }

        }

        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select * from TB_USER where user_name='" + userid.toString() + "' and password='" + password.toString() + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        Log.d(z, "Login efetuado com sucesso");
                        if(rs.next())
                        {

                            z = "Login Successfull";
                            isSuccess=true;
                            Log.d(z, "Login sucesso");

                        }
                        else
                        {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }

                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;

        }

    }

   // Você precisa armazenar o status do usuário quando o aplicativo for fechado, para saber se ele estava logado ou não quando fechou
    // o aplicativo.
    // Isso pode ser feito de diversas formas, sendo a mais simples provavelmente o uso de SharePreferences:
    //Create a object SharedPreferences from getSharedPreferences("name_file",MODE_PRIVATE) of Context
   // private SharedPreferences pref;
   // pref = getSharedPreferences("info", MODE_PRIVATE);
    //Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
   // Editor editor = pref.edit();
//editor.putString("key5","value5");
//finally, when you are done saving the values, call the commit() method.
//editor.commit()

    //Lendo:

    //get SharedPreferences from getSharedPreferences("name_file", MODE_PRIVATE)
    //SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE)
    //Using getXXX- with XX is type date you wrote to file "name_file"
    //String string_temp = shared.getString("key5");

     public void onclickCadastrar (View v)
    {
        startActivityForResult(new Intent(this,CadastroNovo.class),1);
    }
}