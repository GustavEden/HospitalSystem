package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static final String connect = "jdbc:sqlite:HospitalDB.db";

    public static Connection getConnection()throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(connect);

        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        return null;
    }

}
