package until;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUntil {
    private  static ComboPooledDataSource dataSource= new ComboPooledDataSource();

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
