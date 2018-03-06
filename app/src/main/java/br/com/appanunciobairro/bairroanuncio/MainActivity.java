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

        //connectionClass = new ConnectionClass();
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



