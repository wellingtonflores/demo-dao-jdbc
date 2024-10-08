package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Department department = new Department(1, "Books");

        Seller seller = new Seller(21, department, 3000.0, new Date(), "wellingtonflores@gmail.com", "Wellington Flores");

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(seller);
    }
}