package br.com.appanunciobairro.bairroanuncio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.appanunciobairro.bairroanuncio.Adapter.UserAdapter;


//public class ServiceAreaRestrict extends Fragment {
    public class ServiceAreaRestrict extends AppCompatActivity {
    private UserAdapter adapter;

   // @Override
   // public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_user);

      // setHasOptionsMenu(true);

     //   View rootView = inflater.inflate(R.layout.area_user,container,false);
       // ListView lista = (ListView) rootView.findViewById(R.id.listView);

       // PreencheLista(lista);

      //  lista.setOnItemClickListener(new AdapterView.OnItemClickListener()

     //   {
     //       @Override
     //       public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                //pega o valor do item
    //           long nCdCliente = adapter.getItemId(position);
                //chama a tela de cadastro
               // Intent cadastro = new Intent(getActivity(),UserRegisterNew.class);
               // cadastro.putExtra("nCdCliente",nCdCliente);
               // startActivityForResult(cadastro,1);
      //      }
       // });
      //  return rootView;
        }

  //  private void PreencheLista(ListView lista) {
  //      UserDataSource ds = new UserDataSource(getActivity());

  //      List<UserModel> listaClientes = ds.getAll();
    //   // adapter = new UserAdapter(getActivity(),listaClientes);
    //    lista.setAdapter(adapter);
   //     registerForContextMenu(lista);
  //  }

  //  @Override
  //  public void onActivityResult(int requestCode, int resultCode, Intent data) {
  //      super.onActivityResult(requestCode, resultCode, data);
  //      switch (requestCode) {
 //           case 1:
 //               ListView lista = (ListView)getActivity().findViewById(R.id.listView);
  //              PreencheLista(lista);
  //              break;
   //     }
  //  }

  //  @Override
  //  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
  //      if (v.getId() == R.id.listView) {
      //      menu.add(R.string.excluir);
 //       }
 //   }

 //   @Override
 //   public boolean onContextItemSelected(MenuItem item) {
  //      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
 //       switch (item.getItemId()) {
  //          case 0:
  //              long id = adapter.getItemId(info.position);
  //              UserDataSource ds = new UserDataSource(getActivity());
  //              ds.delete(String.valueOf(id));
  //              ListView lista = (ListView)getActivity().findViewById(R.id.listView);
  //              PreencheLista(lista);
  //      }
   //     return true;
   // }



}