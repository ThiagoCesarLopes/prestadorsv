package br.com.appanunciobairro.bairroanuncio.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ServiceDataBaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "DB_PrestadorServico.db";
    private static int DATABASE_VERSION = 1;
    public static String TABLE = "service";
    private static String CREATE= "create table Service"
            + "(id_Service integer primary key , "
            + "(nIdUF int not null, "
            + "(nIdCidade int not null, "
            + "(nIdBairro int not null, "
            + "(nIdUrgencia int not null, "
            + "TituloServico text not null, "
            + "DescricaoServico text not null);";


    public ServiceDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}