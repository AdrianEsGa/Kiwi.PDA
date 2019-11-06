package com.xander.kiwipda;

import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static class SQLServer {

        private static final String LOG = "DEBUG";
        private static String server = GlobalApp.Business.DbConfiguration.ServerName;
        private static String instance = GlobalApp.Business.DbConfiguration.Instance;
        private static String classs = "net.sourceforge.jtds.jdbc.Driver";
        private static String db = GlobalApp.Business.DbConfiguration.Database;
        private static String user = GlobalApp.Business.DbConfiguration.User;
        private static String password = GlobalApp.Business.DbConfiguration.Password;
        private static String message = "";

        public static Connection Connect() {
            Connection connection = null;
            String ConnURL = null;

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                Class.forName(classs);

               /* ConnURL = "jdbc:jtds:sqlserver://" + server + ";"
                        + "databaseName=" + db + ";user=" + un + ";password="
                        + password + ";";*/

              /*  ConnURL = "jdbc:jtds:sqlserver://KiwiTPVServer/KiwiTPv;instance=SQLExpress;user=sa;password=licuo2019"; */

                ConnURL = "jdbc:jtds:sqlserver://" + server + ";instance=" + instance + ";databaseName=" + db + ";user=" + user + ";password="+password;

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
