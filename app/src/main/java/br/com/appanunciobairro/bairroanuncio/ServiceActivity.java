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

import br.com.appanunciobairro.bairroanuncio.Model.CidadeModel;

public class ServiceActivity extends Activity {
    Spinner SpinnerUF;
    String cidade;
    int cidade_id;
    String uf;
    String bairro;
    Spinner SpinnerCidade;
    Spinner SpinnerBairro;
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
        String query = "select distinct flg_estado from City";
        try {
            //alterei de lugar aqui bill
            Connection con = connectionClass.CONN();

                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();
                ArrayList<String> data = new ArrayList<String>();

                //tem probelma no while para aceitar 2 parametros (bill)
                while (rs.next()) {
                    String id = rs.getString("flg_estado");
                    data.add(id);

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
                uf = SpinnerUF.getSelectedItem().toString();
                Toast.makeText(ServiceActivity.this, uf, Toast.LENGTH_SHORT).show();
                carregaCidade(uf);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


    }

         private void carregaCidade(String uf)
         {
                SpinnerCidade = findViewById(R.id.SpinnerCidade);

                String querycidade = "select distinct desc_cidade ,cidade_id from City where flg_estado like " + "'" + uf + "'";
                try {

                    Connection con = connectionClass.CONN();

                    stmt = con.prepareStatement(querycidade);
                    rs = stmt.executeQuery();
                    ArrayList<CidadeModel> data = new ArrayList<CidadeModel>();

                    while (rs.next()) {
                       CidadeModel c = new CidadeModel();
                        //String name = rs.getString("desc_cidade");
                        //int id = rs.getInt("id_cidade");
                        //data.add(name);
                         c.setId(rs.getInt("cidade_id"));
                        c.setName(rs.getString("desc_cidade"));
                        data.add(c);
                    }

                    ArrayAdapter NoCoreAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
                    NoCoreAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    SpinnerCidade.setAdapter(NoCoreAdapter);
                    SpinnerCidade.setSelection(0);

                }
                catch(SQLException e)
                {
                    e.printStackTrace();

                }

             SpinnerCidade.setOnItemSelectedListener(new OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,int position, long id)
                    {
                        cidade = SpinnerCidade.getSelectedItem().toString();
                        cidade_id = SpinnerCidade.getId();
                        Toast.makeText(ServiceActivity.this, cidade, Toast.LENGTH_SHORT).show();
                        carregaBairro(cidade_id);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });


         }

         private void carregaBairro(int cidade_id)
    {
        SpinnerBairro = findViewById(R.id.SpinnerBairro);

        String querycidade = "select bairro_id,desc_bairro from NEIGHBORHOOD where cidade_id = " +  cidade_id ;
        try {

            Connection con = connectionClass.CONN();

            stmt = con.prepareStatement(querycidade);
            rs = stmt.executeQuery();
            ArrayList<String> data = new ArrayList<String>();

            //tem probelma no while para aceitar 2 parametros
            while (rs.next()) {
                String id = rs.getString("desc_cidade");
                data.add(id);
            }
            ArrayAdapter NoCoreAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
            SpinnerBairro.setAdapter(NoCoreAdapter);
        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }

        SpinnerBairro.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id)
            {
                bairro = SpinnerBairro.getSelectedItem().toString();
                Toast.makeText(ServiceActivity.this, bairro, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


    }
    }






