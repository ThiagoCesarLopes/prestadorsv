package br.com.appanunciobairro.bairroanuncio;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    private static final String LOG_TAG = "EmailLauncherActivity";

  //  ConnectionClass connectionClass;
    EditText edtuserid,edtpass;
    Button btnlogin;
    ProgressBar pbbar;

    //Declare Connection Server
    Connection con;
    String un,pass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connectionClass = new ConnectionClass();
        edtuserid = (EditText) findViewById(R.id.et_username);
        edtpass = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_Login);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        //pbbar.setVisibility(View.GONE);

        //Setting information Server
        ip = "192.168.43.109:1433";
        db = "DB_PrestadorServico";
        un = "sa";
        pass = "tlopes13";

        //btnlogin.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //    public void onClick(View v) {
        //       DoLogin  doLogin = new DoLogin();
        //        doLogin.execute("");

        //     }
        //  });

        // aqui inicia os tab pagina home
        TabHost host;
        host = findViewById(R.id.tabHost);
        host.setup();

        //Tab Principal
        TabHost.TabSpec spec = host.newTabSpec("Principal");
        spec.setContent(R.id.Principal);
        spec.setIndicator("Principal");
        host.addTab(spec);

        //Tab Hospital
        spec = host.newTabSpec("Cadastrar");
        spec.setContent(R.id.Cadastrar);
        spec.setIndicator("Cadastrar");
        host.addTab(spec);

        //Tab Contato
        spec = host.newTabSpec("Contato");
        spec.setContent(R.id.Contato);
        spec.setIndicator("Contato");
        host.addTab(spec);
    }
        // Send a simple plain text email (no contents supplied)
    public void sendSimpleEmail(View button) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            startActivity(emailIntent);
        } catch (Exception e) {
            Log.e(LOG_TAG, "sendSimpleEmail() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void chooseEmail(View button) {

        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            startActivity(Intent.createChooser(emailIntent,
                    "Send your email in:"));
        } catch (Exception e) {
            Log.e(LOG_TAG, "chooseEmail() failed to start activity.", e);
            Toast.makeText(this, "No handler", Toast.LENGTH_LONG).show();
        }
    }

    public void onclicklaunchForm(View v) {

     //   Intent launchFormActivity = new Intent(this, FormActivity.class);
      //  startActivity(launchFormActivity);
         startActivityForResult(new Intent(this,FormActivity.class),1);
    }

    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {

            String user_name = edtuserid.getText().toString();
            String password = edtpass.getText().toString();

            if(user_name.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    //Connection con = connectionClass.CONN();
                    con =connectionClass(un,pass,db,ip); //connect to database
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {

                        String query = "select * from TB_USER where user_name='" + user_name.toString() + "' and password='" + password.toString() + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        Log.d(z, "Login sucesso");
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
        @Override
        protected void onPostExecute(String r) {
     //       pbbar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            }

        }


    }

    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String Server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnURL = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://" + ip + database+ ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnURL);

        } catch (SQLException se) {
            Log.e("ERRO 1", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO 2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO 3", e.getMessage());
        }
        return connection;

    }

    public void onclickIncluirPintor(View v){
            startActivityForResult(new Intent(this,ServiceActivity.class),1);
        }
    public void onclickIncluirManicure(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }

    public void onclickCadastroNovo(View v){
        startActivityForResult(new Intent(this,ClienteActivity.class),1);
    }

  }



