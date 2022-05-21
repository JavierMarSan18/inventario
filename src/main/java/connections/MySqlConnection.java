package connections;

import java.sql.*;

public class MySqlConnection {

    private Connection connection;
    public MySqlConnection() {
        try{
            String username = "root";
            String password = "patito20";
            String url = "jdbc:mysql://localhost:3306/polar_city";
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(int cantidad, Long id){
        try {
            String query = "UPDATE cantidad_producto SET cantidad=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cantidad);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet select(String query){
        try{
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
