package br.com.appanunciobairro.bairroanuncio;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.appanunciobairro.bairroanuncio.Adapter.ClienteAdapter;
import br.com.appanunciobairro.bairroanuncio.DataBase.ClienteDataSource;
import br.com.appanunciobairro.bairroanuncio.Model.ClienteModel;


public class ItemDetailFragment  extends Fragment {
    private ClienteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

       setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.list_user_adm,container,false);
        ListView lista = (ListView) rootView.findViewById(R.id.listView);

       // PreencheLista(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                //pega o valor do item
                long nCdCliente = adapter.getItemId(position);
                //chama a tela de cadastro
                Intent cadastro = new Intent(getActivity(),RegisterNew.class);
                cadastro.putExtra("nCdCliente",nCdCliente);
                startActivityForResult(cadastro,1);
            }
        });
        return rootView;
    }

    private void PreencheLista(ListView lista) {
        ClienteDataSource ds = new ClienteDataSource(getActivity());

        List<ClienteModel> listaClientes = ds.getAll();
       // adapter = new ClienteAdapter(getActivity(),listaClientes);
        lista.setAdapter(adapter);
        registerForContextMenu(lista);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                ListView lista = (ListView)getActivity().findViewById(R.id.listView);
                PreencheLista(lista);
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listView) {
      //      menu.add(R.string.excluir);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                long id = adapter.getItemId(info.position);
                ClienteDataSource ds = new ClienteDataSource(getActivity());
                ds.delete(String.valueOf(id));
                ListView lista = (ListView)getActivity().findViewById(R.id.listView);
                PreencheLista(lista);
        }
        return true;
    }



}