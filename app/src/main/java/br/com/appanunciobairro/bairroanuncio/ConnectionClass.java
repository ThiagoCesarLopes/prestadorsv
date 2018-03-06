package br.com.appanunciobairro.bairroanuncio;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionClass {
    String ip = "192.168.0.33:1433";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "DB_PrestadorServico";
    String un = "sa";
    String password = "tlopes13";


    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un+ ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;

    }
}
