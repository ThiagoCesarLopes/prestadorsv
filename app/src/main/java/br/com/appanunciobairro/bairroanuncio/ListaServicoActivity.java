package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class ListaServicoActivity extends Activity {

    ConnectionClass connectionClass;
    private CustomAdapter adapter;
    ListView lstpro;
    public ArrayList<ListModel> customListViewValueArr = new ArrayList<ListModel>();
    private boolean success =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_service_provider);
        connectionClass= new ConnectionClass();
        customListViewValueArr=new ArrayList<ListModel>();
        lstpro = (ListView) findViewById(R.id.lstpro);
        FilList fill =new FilList();
        fill.execute("");

    }

    public class FilList extends AsyncTask<String, String, String> {
        String z = "";
        List<Map<String,String>>prolist=new ArrayList<Map<String, String>>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ListaServicoActivity.this, s, Toast.LENGTH_SHORT).show();
            if (success=false){}
            else{
                try{
                    adapter = new CustomAdapter(ListaServicoActivity.this,customListViewValueArr);
                    lstpro.setAdapter(adapter);
                }catch (Exception ex){

                }
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Connection con = connectionClass.CONN();
                if (con==null){
                    success = false;

                }else{

                    String query = "Select * from TB_USER ";
                    Statement ps =con.createStatement();
                    ResultSet rs = ps.executeQuery(query);
                    if (rs!=null) {

                        while (rs.next()) {
                            try {

                                customListViewValueArr.add(new ListModel(rs.getString("name"), rs.getString("last_name")));

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        z = "Success";
                        success = true;


                    }else {
                        z="DATA NOT FOUND";
                        success=false;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                Writer writer=new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                z=writer.toString();
                success=false;

            }
            return z;
        }
    }
}

