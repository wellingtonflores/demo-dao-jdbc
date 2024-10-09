package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== TEST 6: department delete ====");
        System.out.println("Enter id for delete test: ");
        int id = scanner.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");

        scanner.close();
    }
}
