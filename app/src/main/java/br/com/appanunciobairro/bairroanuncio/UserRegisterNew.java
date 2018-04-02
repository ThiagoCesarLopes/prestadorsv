package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ProgressBar;

import br.com.appanunciobairro.bairroanuncio.DataBase.UserDataSource;
import br.com.appanunciobairro.bairroanuncio.Model.UserModel;

public class UserRegisterNew extends Activity {

    ConnectionClass connectionClass;
    EditText edtuserid,edtpass;
    Button btn_CadastroUser;
    ProgressBar pbbar;
    private int nCdCliente= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer userid = extras.getInt("userid");

            connectionClass = new ConnectionClass();

             EditText tipo =(EditText) findViewById(R.id.tipouser);
            btn_CadastroUser = (Button) findViewById(R.id.btn_CadastroUser);

            if(userid==1) {
               tipo.setText("Prestador de Serviço !");
            }
            if(userid==2) {
                tipo.setText("Seja bem Vindo!");// usuario novo
            }

            Bundle nCdClientes = getIntent().getExtras();
            if (nCdClientes != null) {
            //    nCdCliente = Integer.valueOf(getIntent().getExtras().get("nCdCliente").toString());
                LoadData();
            } else {
                nCdCliente = 0;
            }

        }
    }
    private void Save() {

        EditText nome = (EditText)findViewById(R.id.editnome);
        EditText Sobrenome = (EditText)findViewById(R.id.editsobrenome);

        btn_CadastroUser = (Button) findViewById(R.id.btn_CadastroUser);


        UserDataSource ds = new UserDataSource(this);
        UserModel cliente = new UserModel();

        cliente.setNome(nome.getText().toString());
        cliente.setSobrenome(Sobrenome.getText().toString());



        if (nCdCliente > 0)
            cliente.setId(nCdCliente);
        ds.Save(cliente);
        Toast.makeText(this, "Registro salvo com sucesso!",
                Toast.LENGTH_LONG).show();
        finish();
    }

    private void LoadData(){
        UserDataSource ds = new UserDataSource(this);
        UserModel cliente = new UserModel();
        cliente = ds.getByID(nCdCliente);

        if (cliente != null && cliente.getId() > 0){
            EditText nome = (EditText)findViewById(R.id.editnome);
            EditText sobrenome = (EditText)findViewById(R.id.editsobrenome);


            nome.setText(cliente.getNome());
            sobrenome.setText(cliente.getSobrenome());

        }
    }

    public void onclickdCadastrarUser (View v)
    {
        Save();
        startActivityForResult(new Intent(this,ListaServicoActivity.class),1);

    }
}