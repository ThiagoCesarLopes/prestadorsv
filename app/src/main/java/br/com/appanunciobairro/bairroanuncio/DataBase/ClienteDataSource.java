package br.com.appanunciobairro.bairroanuncio.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.com.appanunciobairro.bairroanuncio.Model.ClienteModel;

public class ClienteDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context contexto;

    public ClienteDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
        contexto = context;
    }

    private void open(){
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public boolean Save(ClienteModel cliente) {
        try {
            open();
            if (cliente.getId() == 0)
                Create(cliente);
            else
                Update(cliente);
            close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private long Create(ClienteModel cliente) {
        try {
            database.execSQL("INSERT INTO CLIENTE(sNmCliente,tDtNascimento,nIdTipo) VALUES ('" +
                    cliente.getNome() + "','" +
                    cliente.getSobrenome() + "', '" +
                    cliente.getnIdTipo() + "')");
        } catch (Exception ex) {
        }
        return 0;
    }

    private long Update(ClienteModel cliente) {
        try {
            database.execSQL("UPDATE cliente SET sNmCliente = '" +
                    cliente.getNome() + "', tDtNascimento = '" +
                    cliente.getSobrenome() + "', nIdTipo = '" +
                    cliente.getnIdTipo() + " where Id = '" + cliente.getId() + "'");
        } catch (Exception ex) {
        }
        return 0;
    }

    public int delete(String nCdCliente) {
        int id = 0;
        try {
            open();
            id = database.delete("cliente", "id = ? ",
                    new String[] { nCdCliente });
            close();
        }
        catch (Exception ex) {
        }
        return id;
    }

    private ClienteModel cursorToCliente(Cursor cursor) {
        ClienteModel cliente = new ClienteModel();
        cliente.setId(cursor.getInt(0));
        cliente.setNome(cursor.getString(1));
        cliente.setSobrenome(cursor.getString(2));
        cliente.setnIdTipo(cursor.getInt(3));
        return cliente;
    }

    public List<ClienteModel> getAll() {
        open();
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();
        Cursor cursor = database.rawQuery("select * from cliente", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ClienteModel cliente = cursorToCliente(cursor);
            clientes.add(cliente);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return clientes;
    }

    public ClienteModel getByID (int nCdCliente){
        open();

        ClienteModel cliente = new ClienteModel ();
        Cursor cursor = database.rawQuery("select * from cliente where id=?",
                new String[] { String.valueOf(nCdCliente) });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cliente = cursorToCliente(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cliente;
    }
}