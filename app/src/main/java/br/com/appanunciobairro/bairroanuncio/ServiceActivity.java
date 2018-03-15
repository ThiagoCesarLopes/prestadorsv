package br.com.appanunciobairro.bairroanuncio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceActivity  extends Activity
{
 //   Button btnConfirmar;
 //   Spinner SpinnerUF;
 //   ArrayAdapter<String> array;
//    ArrayList<modelState> CadastrolistUF;  //List items Array
  //  private ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_form);

      //  CadastrolistUF = new ArrayList<modelState>();
      //  connectionClass = new ConnectionClass();
       // getStates();


       // ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.CadastrolistUF,android.R.layout.activity_list_item);
       // SpinnerUF = (Spinner) findViewById(R.id.SpinnerUF);
       // SpinnerUF.setAdapter(adapter);


      //  btnConfirmar = (Button) findViewById(R.id.btnconfirmar);

        //array = new ArrayAdapter(this, android.R.layout.activity_list_item, CadastrolistUF.subList(0,1));
        //SpinnerUF.setAdapter(array);

      //  btnConfirmar.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {

        //    }
       // });

    }
    public void  OnclickCadastrarServico (View v){
        startActivityForResult(new Intent(this,LoginActivity.class),1);
    }



}



