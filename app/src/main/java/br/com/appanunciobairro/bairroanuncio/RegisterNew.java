package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.appanunciobairro.bairroanuncio.DataBase.ClienteDataSource;
import br.com.appanunciobairro.bairroanuncio.Model.ClienteModel;

public class RegisterNew extends Activity {

    ConnectionClass connectionClass;
    EditText edtuserid,edtpass,edcelular;
    Button btnSave;
    ProgressBar pbbar;
    private int nCdCliente= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        connectionClass = new ConnectionClass();

        Spinner tipo = (Spinner) findViewById(R.id.SpinnerListaBairro);
        String[] tipos = new String[3];
        tipos[0] = "Ótimo";
        tipos[1] = "Bom";
        tipos[2] = "Regular";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,tipos);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);

        if (getIntent().getExtras().containsKey("nCdCliente")) {
           nCdCliente = Integer.valueOf(getIntent().getExtras().get(
                   "nCdCliente").toString());
        }

        LoadData();
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Save();
            }
        });

    }
    private void Save() {

        EditText nome = (EditText)findViewById(R.id.edNome);
        EditText Sobrenome = (EditText)findViewById(R.id.edsobrenome);
        Spinner tipo = (Spinner)findViewById(R.id.SpinnerListaBairro);
        edcelular = (EditText) findViewById(R.id.edcelular);
        btnSave = (Button) findViewById(R.id.btnSave);


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
            EditText nome = (EditText)findViewById(R.id.edNome);
            EditText sobrenome = (EditText)findViewById(R.id.edsobrenome);
            Spinner tipo = (Spinner)findViewById(R.id.SpinnerListaBairro);

            nome.setText(cliente.getNome());
            sobrenome.setText(cliente.getSobrenome());
            tipo.setSelection(cliente.getnIdTipo() - 1);
        }
    }
}