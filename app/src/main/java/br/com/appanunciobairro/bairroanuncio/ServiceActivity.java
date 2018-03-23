package br.com.appanunciobairro.bairroanuncio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
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
    String z = "";

    ConnectionClass connectionClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_form);

        //add aqui bill
        connectionClass = new ConnectionClass();
        SpinnerUF = findViewById(R.id.SpinnerUF);


        String query = "select cidade_id, flg_estado from City";
        try {
            //alterei de lugar aqui bill
            Connection con = connectionClass.CONN();


                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
                ArrayList<String> data = new ArrayList<String>();

                //tem probelma no while para aceitar 2 parametros (bill)
                while (rs.next()) {
                    String id = rs.getString("flg_estado");
                    //String name = rs.getString("cidade_id");
                    data.add(id);
                    //   data.add(id, name);
                }

                ArrayAdapter NoCoreAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
                SpinnerUF.setAdapter(NoCoreAdapter);

            }
        catch(SQLException e)
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
    }






