package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CadastroNovo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);
    }
    public void onClickConfirmarClienteCadastro (View v)
    {
        startActivityForResult(new Intent(this,ListaServicoActivity.class),1);
        //teste



    }
}