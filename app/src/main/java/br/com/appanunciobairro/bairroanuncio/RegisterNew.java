package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.appanunciobairro.bairroanuncio.DataBase.ClienteDataSource;
import br.com.appanunciobairro.bairroanuncio.Model.ClienteModel;

public class RegisterNew extends Activity {

    ConnectionClass connectionClass;
    EditText edtuserid,edtpass;
    Button btn_CadastroUser;
    ProgressBar pbbar;
    private int nCdCliente= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        connectionClass = new ConnectionClass();

        btn_CadastroUser = (Button) findViewById(R.id.btn_CadastroNovo);

        Spinner tipo = (Spinner) findViewById(R.id.tipo);
        String[] tipos = new String[3];
        tipos[0] = "Usuario";
        tipos[1] = "Prestador";
        tipos[2] = "Usuario/Prestador";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,tipos);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);


        Bundle nCdClientes = getIntent().getExtras();
        if (nCdClientes != null) {
            nCdCliente = Integer.valueOf(getIntent().getExtras().get("nCdCliente").toString());
            LoadData();
        }else {
            nCdCliente = 0;
        }

    }
    private void Save() {

        EditText nome = (EditText)findViewById(R.id.editnome);
        EditText Sobrenome = (EditText)findViewById(R.id.editsobrenome);
        Spinner tipo = (Spinner)findViewById(R.id.tipo);
        btn_CadastroUser = (Button) findViewById(R.id.btn_CadastroUser);


        ClienteDataSource ds = new ClienteDataSource(this);
        ClienteModel cliente = new ClienteModel();

        cliente.setNome(nome.getText().toString());
        cliente.setSobrenome(Sobrenome.getText().toString());
        cliente.setnIdTipo(tipo.getSelectedItemPosition() + 1);


        if (nCdCliente > 0)
            cliente.setId(nCdCliente);
        ds.Save(cliente);
        Toast.makeText(this, "Registro salvo com sucesso!",
                Toast.LENGTH_LONG).show();
        finish();
    }

    private void LoadData(){
        ClienteDataSource ds = new ClienteDataSource(this);
        ClienteModel cliente = new ClienteModel();
        cliente = ds.getByID(nCdCliente);

        if (cliente != null && cliente.getId() > 0){
            EditText nome = (EditText)findViewById(R.id.editnome);
            EditText sobrenome = (EditText)findViewById(R.id.editsobrenome);
            Spinner tipo = (Spinner)findViewById(R.id.tipo);

            nome.setText(cliente.getNome());
            sobrenome.setText(cliente.getSobrenome());
            tipo.setSelection(cliente.getnIdTipo() - 1);
        }
    }

    public void onclickCadastrar (View v)
    {
      //  startActivityForResult(new Intent(this,RegisterNew.class),1);
        // startActivityForResult(new Intent(this,RegisterNew.class),1);
        Save();
        startActivityForResult(new Intent(this,ListaServicoActivity.class),1);

    }
}