package wumpus;

import java.sql.DriverManager;
import java.sql.*;

public class Adatbazis {
    static final String url="jdbc:mysql://localhost:3306/adatbazis";
    static final String nev="root";
    static final String jelszo="";
    private Connection connection;
    public Adatbazis(){
        this.connection=createConnection();
    }
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, nev, jelszo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
