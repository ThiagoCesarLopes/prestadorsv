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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;
import java.io.PrintWriter;
import java.sql.Statement;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.lang.Object;

import br.com.appanunciobairro.bairroanuncio.Model.ItensServiceModel;

public class ListaServicoActivity extends AppCompatActivity {


    private ArrayList<ItensServiceModel> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private ListView listView; // Listview
    private boolean success = false; // boolean
    private ConnectionClass connectionClass; //Connection Class Variable

    private RatingBar rb;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_service_provider);

        rb=(RatingBar) findViewById(R.id.ratingBar); //RatingBar Declaration
        score=(TextView) findViewById(R.id.score);//Valor do RatingBar -score
        listView = (ListView) findViewById(R.id.listView); //Listview Declaration
        connectionClass = new ConnectionClass(); // Connection Class Initialization
        itemArrayList = new ArrayList<ItensServiceModel>(); // Arraylist Initialization

        // Calling Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");
    }



    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String>
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(ListaServicoActivity.this, "Carregando",
                    "Lista de Prestadores de Serviço!...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try
            {
                Connection conn = connectionClass.CONN(); //Connection Object
                if (conn == null)
                {
                    success = false;
                }
                else {
                    // Change below query according to your own database.
                    //String query = "SELECT name,last_name,url_picture FROM TB_USER";
                    String query = "SELECT us.name ,us.last_name,us.url_picture ,us.score, ne.desc_bairro  from TB_USER us INNER JOIN NEIGHBORHOOD NE on ne.bairro_id = us.bairro_id where us.bairro_id = 2";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (rs.next())
                        {
                            try {
                                itemArrayList.add(new ItensServiceModel(rs.getString("name"), rs.getString("last_name"),rs.getString("url_picture"),rs.getInt("score"),rs.getString("desc_bairro")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my listview
        {
            progress.dismiss();
            Toast.makeText(ListaServicoActivity.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false)
            {
            }
            else {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, ListaServicoActivity.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex)
                {

                }

            }
        }
    }

    public class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
    {
        public class ViewHolder
        {
            TextView textName;
            TextView textLastName;
            TextView textBairro;
            RatingBar ratingBar;
            TextView score;
            ImageView imageView;
        }

        public List<ItensServiceModel> parkingList;

        public Context context;
        ArrayList<ItensServiceModel> arraylist;

        private MyAppAdapter(List<ItensServiceModel> apps,Context context)
        {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ItensServiceModel>();
            arraylist.addAll(parkingList);
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;
            ViewHolder viewHolder= null;
            if (rowView == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.custom_list, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textName = (TextView) rowView.findViewById(R.id.textName);
                viewHolder.textLastName = (TextView) rowView.findViewById(R.id.textLastName);
                viewHolder.textBairro = (TextView) rowView.findViewById(R.id.textBairro);
                viewHolder.ratingBar = (RatingBar) rowView.findViewById(R.id.ratingBar);
                viewHolder.score = (TextView) rowView.findViewById(R.id.score);
                viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
                rowView.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // here setting up names and images

            viewHolder.textName.setText(parkingList.get(position).getName()+"");
            viewHolder.textLastName.setText(parkingList.get(position).getLastName()+"");
            viewHolder.textBairro.setText(parkingList.get(position).getDescBairro()+"");
            int value = parkingList.get(position).getScore();
            viewHolder.ratingBar.setEnabled(false);
            viewHolder.ratingBar.setRating(value);
            viewHolder.score.setText(parkingList.get(position).getScore()+"");
            Picasso.with(context).load("http://"+parkingList.get(position).getImg()).into(viewHolder.imageView);

            return rowView;
        }
    }

}

