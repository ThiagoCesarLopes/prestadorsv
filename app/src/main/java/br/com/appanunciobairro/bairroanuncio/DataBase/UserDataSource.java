package br.com.appanunciobairro.bairroanuncio.DataBase;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.appanunciobairro.bairroanuncio.ConnectionClass;
import br.com.appanunciobairro.bairroanuncio.Model.UserModel;

public class UserDataSource {

    String z = "";
    Boolean isSuccess = false;
    ConnectionClass connectionClass;

  //  private Statement database;
   // private ResultSet dbHelper;
    private Context contexto;

    public UserDataSource(Context context) {
        connectionClass = new ConnectionClass();
        contexto = context;
    }
//
//    private void open(){
//        database = rs.getWritableDatabase();
//    }
//
//    private void close() {
//        rs.close();
//    }

    public boolean Save(UserModel cliente) {
        try {

            if (cliente.getId() == 0)
                Create(cliente);
            else
                Update(cliente);

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private long Create(UserModel cliente) {
        try {
            Connection con = connectionClass.CONN();
                if (con == null) {
                    z = "Error in connection with SQL server";
                } else {
                String query = ("INSERT INTO TB_USER(Nome,Sobrenome) VALUES ('" +
                        cliente.getNome() + "','" +
                        cliente.getSobrenome() + "', '" + "')");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                    Log.d(z, "Cadastro efetuado com sucesso");
                    if(rs.next())
                    {
                         z = "Cadastro Successfull";
                        isSuccess=true;
                        Log.d(z, "Cadastro sucesso");

                    }
                    else
                    {
                        z = "Invalid Credentials";
                        isSuccess = false;
                    }

                }
            }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions";
        }
            return 0;
    }

    private long Update(UserModel cliente) {
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
                String query =("UPDATE TB_USER SET Nome = '" +
                    cliente.getNome() + "', Sobrenome = '" +
                    cliente.getSobrenome() + " where Id = '" + cliente.getId() + "'");

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                Log.d(z, "Atualização efetuado com sucesso");
                if(rs.next())
                {
                    z = "Atualização Successfull";
                    isSuccess=true;
                    Log.d(z, "Atualização sucesso");

                }
                else
                {
                    z = "Invalid Credentials";
                    isSuccess = false;
                }

            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions";
        }
        return 0;
    }

    public int Delete(String nCdCliente) {
        int id = 0;
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
            String query =("Delete TB_USER where id="+ nCdCliente);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Log.d(z, "Exclusão efetuado com sucesso");
            if(rs.next())
            {
                z = "Exclusão Successfull";
                isSuccess=true;
                Log.d(z, "Exclusão sucesso");

            }
            else
            {
                z = "Invalid Credentials";
                isSuccess = false;
            }

        }
    }
        catch (Exception ex)
    {
        isSuccess = false;
        z = "Exceptions";
    }
      return id;
    }

    private UserModel cursorToCliente(Cursor cursor) {
        UserModel cliente = new UserModel();
        cliente.setId(cursor.getInt(0));
        cliente.setNome(cursor.getString(1));
        cliente.setSobrenome(cursor.getString(2));
        return cliente;
    }

    public List<UserModel> getAll() {

        List<UserModel> clientes = new ArrayList<UserModel>();
        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {
               String  query = ("select * from cliente");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                    Cursor cursor =  rs.getArray();
                    cursor.moveToFirst();

                    while (!cursor.isAfterLast()) {
                        UserModel cliente = cursorToCliente(cursor);
                        clientes.add(cliente);
                        cursor.moveToNext();
                    }
                    cursor.close();

                Log.d(z, "Cadastro efetuado com sucesso");
                if(rs.next())
                {
                    z = "Cadastro Successfull";
                    isSuccess=true;
                    Log.d(z, "Cadastro sucesso");
                }
                else
                {
                    z = "Invalid Credentials";
                    isSuccess = false;
                }
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions";
        }
        return cliente;
    }

    public UserModel getByID (int nCdCliente){

        UserModel cliente = new UserModel();

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Error in connection with SQL server";
            } else {

                Cursor cursor =  query = ("select * from TB_USER where id=" + nCdCliente);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    cliente = cursorToCliente(cursor);
                    cursor.moveToNext();
                }
                cursor.close();

                Log.d(z, "Cadastro efetuado com sucesso");
                if(rs.next())
                {
                    z = "Cadastro Successfull";
                    isSuccess=true;
                    Log.d(z, "Cadastro sucesso");
                }
                else
                {
                    z = "Invalid Credentials";
                    isSuccess = false;
                }

            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            z = "Exceptions";
        }
        return cliente;
    }

}