package managers;

import connections.MySqlConnection;
import models.Inventory;
import models.Product;
import models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryManager {
    public List<Inventory> getInventory(){
        try{
            MySqlConnection connection = new MySqlConnection();
            String query= "SELECT * FROM cantidad_producto";
            ResultSet rs = connection.select(query);

            List<Inventory> inventories = new ArrayList<>();
            ProductManager productManager = new ProductManager();
            while (rs.next()){
                Inventory inventory = new Inventory();
                inventory.setId(rs.getLong("id"));
                inventory.setQuantity(rs.getInt("cantidad"));
                inventory.setProduct(productManager.getProduct(rs.getLong("id_producto")));
                inventories.add(inventory);
            }
            connection.close();
            return inventories;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void setInventory(List<Inventory> inventories){
        try{
            MySqlConnection connection = new MySqlConnection();
            for(Inventory in : inventories){
                int cantidad = in.getQuantity();
                Long id = in.getId();
                connection.update(cantidad,id);
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
