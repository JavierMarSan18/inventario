package managers;

import connections.MySqlConnection;
import models.Product;

import java.sql.ResultSet;

public class ProductManager {

    public Product getProduct(Long id) {
        try{
            MySqlConnection connection = new MySqlConnection();
            String query= "SELECT * FROM product WHERE id="+id;
            ResultSet rs = connection.select(query);

            Product product = new Product();
            while (rs.next()){
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
            }
            connection.close();
            return product;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
