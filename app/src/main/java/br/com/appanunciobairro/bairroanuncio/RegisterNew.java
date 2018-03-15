package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class RegisterNew extends AppCompatActivity {

    private List<RegisterUserClass> lstPessoa = null;
    private Integer posicao = -1;
    private Integer posicaoFinal = -1;

    private Button btnConfirmar;

    private EditText edtNome;
    private EditText edtSobrenome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        // Atravez do findViewById podemos pegar os campos que estão na tela
        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        edtNome = (EditText) findViewById(R.id.editnome);
        edtSobrenome = (EditText) findViewById(R.id.editsobrenome);

        // Quando o botao for clicado, executará esta acão
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lstPessoa == null) {
                    lstPessoa = new ArrayList<RegisterUserClass>();
                }

                RegisterUserClass pessoa = new RegisterUserClass();
                pessoa.setNome(edtNome.getText().toString());
                pessoa.setSobreNome(edtSobrenome.getText().toString());

                lstPessoa.add(pessoa);
                posicaoFinal++;

                apresentaMensagem("Pessoa cadastrada com sucesso.", "Informação");
                limpaCampos();
            }
        });
    }

    private void apresentaMensagem(String msg, String titulo) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(msg);
        dlgAlert.setTitle(titulo);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void limpaCampos(){
        edtNome.setText("");
        edtSobrenome.setText("");
    }
}