package br.com.appanunciobairro.bairroanuncio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class ServiceActivity extends Activity {
    Spinner SpinnerUF;
    PreparedStatement stmt;
    ResultSet rs;
    ConnectionClass connectionClass;
    Connection con = connectionClass.CONN();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.servico_form);


        SpinnerUF = (Spinner) findViewById(R.id.SpinnerUF);

        String query = "select flg_estado from City";
        try {
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
                ArrayList<String> data = new ArrayList<String>();

            while (rs.next())
            {
            String id = rs.getString("CountryName");
            data.add(id);
            }
                String[] array = data.toArray(new String[0]);
                ArrayAdapter NoCoreAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, data);
                SpinnerUF.setAdapter(NoCoreAdapter);

            }
        catch (SQLException e)
             {
         e.printStackTrace();
            }

        SpinnerUF.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id)
            {
                String name = SpinnerUF.getSelectedItem().toString();
                Toast.makeText(ServiceActivity.this, name, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    public void  OnclickCadastrarServico (View v){
        //Save();
        startActivityForResult(new Intent(this,ListaServicoActivity.class),1);
    }

}





