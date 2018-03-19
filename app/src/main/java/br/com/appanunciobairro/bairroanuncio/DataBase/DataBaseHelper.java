package br.com.appanunciobairro.bairroanuncio.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "DB_PrestadorServico.db";
    private static int DATABASE_VERSION = 1;
    public static String TABLE_CLIENTE = "cliente";
    private static String CREATE_CLIENTE = "create table cliente"
            + "(id integer primary key, "
            + "sNmCliente text not null, "
            + "tDtNascimento date not null, "
            + "nidTipo int not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLIENTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
        onCreate(db);
    }
}