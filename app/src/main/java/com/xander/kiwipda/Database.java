package com.xander.kiwipda;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static class SQLServer {

        private static final String LOG = "DEBUG";
        private static String ip = "192.168.192.51";
        private static String port = "1433";
        private static String classs = "net.sourceforge.jtds.jdbc.Driver";
        private static String db = "SerautoPP";
        private static String un = "sa";
        private static String password = "y0tuel";
        private static String message = "";

        public static Connection Connect() {
            Connection connection = null;
            String ConnURL = null;

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                Class.forName(classs);
                ConnURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
                        + "databaseName=" + db + ";user=" + un + ";password="
                        + password + ";";
                connection = DriverManager.getConnection(ConnURL);

            } catch (SQLException e) {
                Log.d(LOG, e.getMessage());
            } catch (ClassNotFoundException e) {
                Log.d(LOG, e.getMessage());
            }
            return connection;
        }
    }
}
