package br.com.appanunciobairro.bairroanuncio;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.Toast;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    private static final String LOG_TAG = "EmailLauncherActivity";

  //  ConnectionClass connectionClass;
    EditText edtEmail,edtpass;
    Button btnlogin,btnSave;
    ProgressBar pbbar;

    //Declare Connection Server
    Connection con;
    String un,pass,db,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connectionClass = new ConnectionClass();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtpass = (EditText) findViewById(R.id.et_password);
       // btnSave = (Button) findViewById(R.id.btn_CadastroNovoPrestador);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        //pbbar.setVisibility(View.GONE);

             // aqui inicia os tab pagina home
        TabHost host;
        host = findViewById(R.id.tabHost);
        host.setup();

        //Tab Principal
        TabHost.TabSpec spec = host.newTabSpec("Home");
        spec.setContent(R.id.HOME);
        spec.setIndicator("Home");
        host.addTab(spec);

        //Tab Hospital
        spec = host.newTabSpec("Prestador");
        spec.setContent(R.id.Prestador);
        spec.setIndicator("Prestador");
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

    public void onclickPintor(View v){
            startActivityForResult(new Intent(this,ServiceActivity.class),1);
        }
    public void onclickManicure(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickCabeleleiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickEncanador(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickEletricista(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void  onclickBaba(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickCostureira(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickChaveiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickDiarista(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickPedreiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickMarcineiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickVidraceiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickCozinheiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickCelulare(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickFisioterapeuta(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickEsteticista(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickBarman(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }
    public void onclickBorracheiro(View v){
        startActivityForResult(new Intent(this,ServiceActivity.class),1);
    }



    public void btn_Login(View v){
       startActivityForResult(new Intent(this,ServiceAreaRestrict.class),1);
    }

    public void onclickCadastrarPrestador(View v){
        startActivityForResult(new Intent(this,RegisterNew.class),1);

    }
    public void onClickesqueceusenhaPrestador(View v){
        startActivityForResult(new Intent(this,LoginActivity.class),1);
      //  setContentView(R.layout.new_user);
    }



  }



