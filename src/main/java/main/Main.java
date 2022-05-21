package main;

import managers.InventoryManager;
import managers.UserManager;
import models.Inventory;
import models.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner read =new Scanner(System.in);
    public static void main(String[] args) {
        Main.login();
    }

    public static void login(){
        User user = new User();

        System.out.println("Ingrese username");
        user.setUsername(read.nextLine());

        System.out.println("Ingrese password");
        user.setPassword(read.nextLine());

        UserManager manager = new UserManager();
        List<User> users = manager.getUsers();

        if(users.contains(user)){
            module();
        }else {
            System.out.println("No autorizado");
        }
    }

    private static void module() {
        InventoryManager inventoryManager = new InventoryManager();
        List<Inventory> inventories = inventoryManager.getInventory();

        System.out.println("Ingrese el id del producto");
        int id = read.nextInt();

        Inventory inventory = inventories.get(id - 1);
        System.out.println("ID: " + inventory.getId() + " Nombre: " + inventory.getProduct().getName() + " Cantidad: " + inventory.getQuantity());

        System.out.println("Ingrese la cantidad a comprar");
        int quantity = read.nextInt();

        if (quantity <= inventory.getQuantity()) {
            System.out.println("Compra exitosa!!!");
            inventory.setQuantity(inventory.getQuantity() - quantity);
            System.out.println("Nombre: " + inventory.getProduct().getName() + " Cantidad: " + inventory.getQuantity());
            inventories.set(id - 1, inventory);
            inventoryManager.setInventory(inventories);
        } else {
            System.out.println("La cantidad supera a la disponible");
        }
    }
}
