package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.*;


public class RegisterNew extends Activity {

    RegisterUserClass pri, reg, ult, aux;
    EditText ednome, edendereco, edtelefone;
    Spinner edcidade;
    int numreg, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numreg = 0;
        pri = ult = null;
        CarregaTelaCadastro();
    }

    void CarregaTelaInicial() {
        setContentView(R.layout.activity_main);
      //  Button btnovo = (Button) findViewById(R.id.btnovo);
      //  Button btcadastrados = (Button) findViewById(R.id.btcadastrados);

         }

    void CarregaTelaCadastro() {

        setContentView(R.layout.activity_main);

        Button btconfirma = (Button) findViewById(R.id.btconfirma);
        Button btcancelar = (Button) findViewById(R.id.btcancelar);

        btconfirma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    reg = new RegisterUserClass();

                    ednome = (EditText) findViewById(R.id.ednome);
                    edendereco = (EditText) findViewById(R.id.edendereco);
                    edcidade = (Spinner) findViewById(R.id.SpinnerCidade);
                    edtelefone = (EditText) findViewById(R.id.edtelefone);

                    reg.nome = ednome.getText().toString();
                    reg.endereco = edendereco.getText().toString();
                    //reg.cidade = edcidade.getDropDownHorizontalOffset().toInt();
                    reg.telefone = edtelefone.getText().toString();

                    if (pri == null)
                        pri = reg;

                    reg.Ant = ult;
                    if (ult == null)
                        ult = reg;
                    else {
                        ult.Prox = reg;
                        ult = reg;
                    }

                    numreg++;
                    showMessage("Cadastrado com Exito", RegisterNew.this);
                    //Login();

                } catch (Exception e) {
                    showMessage("Erro Ao efetivar o cadastro", RegisterNew.this);

                }
            }
        });

        btcancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                CarregaTelaInicial();
            }
        });

    }



    public void showMessage(String Caption, Activity activity) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity);
        dialogo.setTitle("Atencao");
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

 //   public void onClickConfirmarClienteCadastro (View v)
   // {
//       startActivityForResult(new Intent(this,ListaServicoActivity.class),1);
        //teste
 //   }

}