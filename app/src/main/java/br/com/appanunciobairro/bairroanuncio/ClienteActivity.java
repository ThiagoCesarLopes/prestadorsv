package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClienteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        }
    public void onclicklogar(View v)
        {
            startActivityForResult(new Intent(this,ListaServicoActivity.class),1);
        }
    public void onclickCadastrar (View v)
    {
        startActivityForResult(new Intent(this,CadastroNovo.class),1);
    }
}