package managers;

import connections.MySqlConnection;
import models.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserManager {

    public List<User> getUsers(){
        try{
            MySqlConnection connection = new MySqlConnection();

            String query= "SELECT * FROM users";
            ResultSet rs = connection.select(query);

            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            connection.close();
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
