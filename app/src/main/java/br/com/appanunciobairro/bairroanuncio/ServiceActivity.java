package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ServiceActivity  extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_form);
    }
    public void  OnclickCadastrarServico (View v){
        startActivityForResult(new Intent(this,ClienteActivity.class),1);
    }
}



