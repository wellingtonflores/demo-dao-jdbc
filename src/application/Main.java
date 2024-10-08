package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department department = new Department(1, "Books");
        System.out.println(department);
        Seller seller = new Seller(21, department, 3000.0, new Date(), "wellingtonflores@gmail.com", "Wellington Flores");
        System.out.println(seller);
    }
}