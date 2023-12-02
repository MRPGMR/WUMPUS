package wumpus;

import java.sql.DriverManager;
import java.sql.*;

public class Adatbazis {
    static final String url = "jdbc:mysql://localhost:3306/adatbazis";
    static final String nev = "root";
    static final String jelszo = "";
    private Connection connection;

    public Adatbazis() {
        this.connection = createConnection();
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, nev, jelszo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void frissites(String nev) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO jatekosok (gyozelmi_allapot, id, lepesek_szama, nev) " + "VALUES (?, 1, 0, ?) " + "ON DUPLICATE KEY UPDATE gyozelmek_szama = gyozelmek_szama + 1, lepesek_szama = lepesek_szama + 1")) {
            st.setInt(1, 1);
            st.setString(2, nev);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
