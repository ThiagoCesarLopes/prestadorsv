package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginActivity extends Activity {

    ConnectionClass connectionClass;
    EditText edtuserid,edtpass;
    Button btnlogin;
    ProgressBar pbbar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        connectionClass = new ConnectionClass();
        edtuserid = (EditText) findViewById(R.id.edtEmail);
        edtpass = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");
                Intent intent = new Intent(LoginActivity.this, ListaServicoActivity.class);
                Bundle local =new Bundle();
                intent.putExtras(local);
                startActivity(intent);

             }
        });

        }


    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();

        Context context;

        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {

            pbbar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this,r,Toast.LENGTH_SHORT).show();

            if(isSuccess) {
                Toast.makeText(LoginActivity.this,r,Toast.LENGTH_SHORT).show();
               // setContentView(R.layout.list_service_provider);
                //context.startActivity(new Intent(context,ListaServicoActivity.class));
                //this.context = context.getApplicationContext();

            }

        }

        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "select * from TB_USER where user_name='" + userid.toString() + "' and password='" + password.toString() + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        Log.d(z, "Login efetuado com sucesso");
                        if(rs.next())
                        {

                            z = "Login Successfull";
                            isSuccess=true;
                            Log.d(z, "Login sucesso");

                        }
                        else
                        {
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }

                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;

        }

    }


     public void onclickCadastrar (View v)
    {
        startActivityForResult(new Intent(this,RegisterNew.class),1);
       // startActivityForResult(new Intent(this,RegisterNew.class),1);
      //  setContentView(R.layout.new_user);
    }

    public void onClickesqueceusenha (View v)
    {
        startActivityForResult(new Intent(this,RegisterNew.class),1);
    }
}