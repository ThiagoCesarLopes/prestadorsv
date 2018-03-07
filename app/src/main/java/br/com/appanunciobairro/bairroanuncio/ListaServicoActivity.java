package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class ListaServicoActivity extends Activity {

    ConnectionClass connectionClass;
    private CustomAdapter adapter;
    ListView lstpro;
    public ArrayList<ListModel> CustomListViewValueArr = new ArrayList<ListModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_service_provider);

        lstpro = (ListView) findViewById(R.id.lstpro);
        FilList fill =new FilList();
        fill.execute("");

    }

    public class FilList extends AsyncTask<String, String, String> {
        String z = "";
        List<Map<String, String>> proList = new ArrayList<Map<String, String>>();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {
            Toast t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
            t.show();
        }

        @Override
        protected String doInBackground(String... Strings)
        {
            try
            {
                java.sql.Connection con = connectionClass.CONN();
                if (con == null) {
                    z = "ERRO in Connection";
                } else {
                  // final String fromdate = getIntent().getStringExtra("fromdate");
                   // final String todate = getIntent().getStringExtra("todate");
                    String query = "select * from TB_USER"; //where fromdate between '" + fromdate + "' and '" + todate + "'";

                    PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        CustomListViewValueArr.add(new ListModel(rs.getString("FirstName"),rs.getString("LastName")));
                    }
                    z = "Success";
                }
            }
            catch(Exception ex)
            {
                z = ex.getMessage();
            }
            return z;
        }
    }
}

