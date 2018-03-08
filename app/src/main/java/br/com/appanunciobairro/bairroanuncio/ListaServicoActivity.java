package br.com.appanunciobairro.bairroanuncio;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.io.PrintWriter;
import java.sql.Statement;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;

public class ListaServicoActivity extends AppCompatActivity {

    private ArrayList<ListModel> itemArrayList; //List items Array
    private MyAppAdapter myAppAdapter;//ARRAY Adapter
    private ListView lstpro;
    private boolean success = false;
    private ConnectionClass connectionClass;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_service_provider);

        lstpro = (ListView) findViewById(R.id.lstpro);//Listview declaration
        connectionClass= new ConnectionClass();//conexao inicializada

        ArrayList<ListModel> itemArrayList;//Array inicializado

        //chamada Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");


    }

        public class SyncData extends AsyncTask<String, String, String> {
        String z = "Internet/DB_Credentials/Windows_Firewall_TurnOn Error, See Android Monitor in the Bottom For Details";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
           progress = ProgressDialog.show(ListaServicoActivity.this,"Sincronizando","Lista Carregando! Por Favor Aguarde...",true);
        }
        @Override
        protected String doInBackground(String... strings) {
            try{
                Connection con = connectionClass.CONN();//Conection Object
                if (con==null){
                    success = false;

                }else{

                    String query = "Select name,last_nanme,url from TB_USER ";
                    Statement ps =con.createStatement();
                    ResultSet rs = ps.executeQuery(query);
                    if (rs!=null)// caso nao seja nulo add
                    {
                        while (rs.next()) {
                            try {

                                itemArrayList.add(new ListModel(rs.getString("name"), rs.getString("last_name"),rs.getString("url")));

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
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ListaServicoActivity.this, s, Toast.LENGTH_SHORT).show();
            if (success=false){}
            else{
                try{
                    myAppAdapter = new MyAppAdapter(itemArrayList,ListaServicoActivity.this);
                    lstpro.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lstpro.setAdapter(myAppAdapter);
                }catch (Exception ex){

                }
            }
        }
    }

        public class MyAppAdapter extends BaseAdapter {
        public class ViewHolder {
            TextView txtFirstName;
            TextView txtLastName;
            ImageView imageView;
        }

        List<String> parkingList;
        public Context context;
        ArrayList<String> arraylist;

        private MyAppAdapter(List<String> apps,Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<String>();
            arraylist.addAll(parkingList);

        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }


        @Override
        public View getView(final int position,View convertView,ViewGroup parent) // inflating the layou and initialinz widgets
        {
            View view = convertView;
            ViewHolder viewHolder = null;

            if (convertView == null) {
                LayoutInflater Inflater = getLayoutInflater();
                convertView = Inflater.inflate(R.layout.custom_list,null);

                viewHolder = new ViewHolder();
                viewHolder.txtFirstName = view.findViewById(R.id.textView1);
                viewHolder.txtLastName = view.findViewById(R.id.textView2);
                viewHolder.imageView = view.findViewById(R.id.imageView);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtFirstName.setText(parkingList.getClass().getName()+"");
            viewHolder.txtLastName.setText(parkingList.getClass().getLastName()+"");
            Picasso.with(context).load("http//"+parkingList.getClass().getImg()).into(viewHolder.imageView);

            return convertView;
        }

    }
}


