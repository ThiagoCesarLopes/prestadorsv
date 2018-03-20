package br.com.appanunciobairro.bairroanuncio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.appanunciobairro.bairroanuncio.Model.ClienteModel;
import br.com.appanunciobairro.bairroanuncio.R;


public class ClienteAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<ClienteModel> data;
    private static LayoutInflater inflater = null;

    public ClienteAdapter(Activity a, ArrayList<ClienteModel> d)
    {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        ClienteModel cliente = data.get(position);
        return cliente.getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.row_dados, null);
        TextView nome = (TextView) vi.findViewById(R.id.nome);
        TextView sobrenome = (TextView) vi.findViewById(R.id.sobrenome);
        TextView tipo = (TextView) vi.findViewById(R.id.tipo);
        ClienteModel cliente = data.get(position);
        String tipoCliente = (cliente.getnIdTipo() == 1 ? "Ótimo" : (cliente.getnIdTipo()== 2 ? "Bom" : "Regular"));
        nome.setText(cliente.getNome());
        sobrenome.setText(cliente.getSobrenome());
        tipo.setText(tipoCliente);
        return vi;
    }

}