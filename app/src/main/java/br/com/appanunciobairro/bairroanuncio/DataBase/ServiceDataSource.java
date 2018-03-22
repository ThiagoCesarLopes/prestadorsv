package br.com.appanunciobairro.bairroanuncio.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import br.com.appanunciobairro.bairroanuncio.Model.ServiceModel;

public class ServiceDataSource {

    private SQLiteDatabase database;
    private ServiceDataBaseHelper dbHelper;
    private Context contexto;

    public ServiceDataSource(Context context) {
        dbHelper = new ServiceDataBaseHelper(context);
        contexto = context;
    }

    private void open(){
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public boolean Save(ServiceModel service) {
        try {
            open();
            if (service.getId_Service() == 0)
                Create(service);
            else
                Update(service);
            close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Inserting new lable into lables table
     * */
    private long Create(ServiceModel service) {
        try {
            database.execSQL("INSERT INTO SERVICE(nIdUF,nIdCidade,nIdBairro,nIdUrgencia,TituloServico,DescricaoServico) VALUES ('" +
                    service.getnIdUF() + "','" +
                    service.getnIdCidade() + "','" +
                    service.getnIdBairro() + "','" +
                    service.getnIdUrgencia() + "','" +
                    service.getTituloServico() + "','" +
                    service.getDescricaoServico() + "')");

        } catch (Exception ex) {
        }
        return 0;
    }

    /**
     * Update new lable into lables table
     * */
    private long Update(ServiceModel service) {
        try {
            database.execSQL("UPDATE service SET nIdUF ='" + service.getnIdUF() + "','" +
                            "'nIdCidade ='" +  service.getnIdCidade() + "','" +
                            "'nIdBairro ='" +     service.getnIdBairro() + "','" +
                            "'nIdUrgencia ='" +     service.getnIdUrgencia() + "','" +
                            "'TituloServico ='" +    service.getTituloServico() + "','" +
                            "'DescricaoServico ='" +    service.getDescricaoServico() +" where Id = '" + service.getId_Service() + "'");
        } catch (Exception ex) {
        }
        return 0;
    }

    /**
     * Delete new lable into lables table
     * */
    public int delete(String nCdService) {
        int id = 0;
        try {
            open();
            id = database.delete("service", "id_service = ? ",
                    new String[] { nCdService });
            close();
        }
        catch (Exception ex) {
        }
        return id;
    }


    private ServiceModel cursorToService(Cursor cursor) {
        ServiceModel service = new ServiceModel();
        service.setId_Service(cursor.getInt(0));
        service.setnIdUF(cursor.getInt(3));
        service.setnIdCidade(cursor.getInt(3));
        service.setnIdBairro(cursor.getInt(3));
        service.setnIdUrgencia(cursor.getInt(3));
        service.setTituloServico(cursor.getString(1));
        service.setDescricaoServico(cursor.getString(2));

        return service;
    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllState() {

        List<String> services = new ArrayList<String>();
        // Select All Query

        Cursor cursor = database.rawQuery("select * from City", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                services.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        database.close();

        // returning services
        return services;
    }
    /**
     * Getting all getAllCITY
     * returns list of labels
     * */
    public List<String> getAllCITY() {
        open();
        List<String> services = new ArrayList<String>();

        Cursor cursor = database.rawQuery("select * from City", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                services.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
       close();

        // returning services
        return services;
    }
    /**
     * Getting all getAllNEIGHBORHOOD
     * returns list of labels
     * */
    public List<String> getAllNEIGHBORHOOD() {
        open();
        List<String> services = new ArrayList<String>();

        Cursor cursor = database.rawQuery("select * from NEIGHBORHOOD", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                services.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        close();

        // returning services
        return services;
    }
    /**
     * Getting all getAllEMERGENCY
     * returns list of labels
     * */
    public List<String> getAllEMERGENCY() {
        open();
        List<String> services = new ArrayList<String>();

        Cursor cursor = database.rawQuery("select * from StateEmergency", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                services.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        close();

        // returning services
        return services;
    }

    public ServiceModel getByID (int nCdService){
           open();

        ServiceModel cliente = new ServiceModel ();
        Cursor cursor = database.rawQuery("select * from cliente where id=?",
                new String[] { String.valueOf(nCdService) });
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cliente = cursorToService(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return cliente;
    }
}