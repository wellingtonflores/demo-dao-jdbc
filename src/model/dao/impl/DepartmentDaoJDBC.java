package model.dao.impl;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection;

    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO department (Name) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )){
            preparedStatement.setString(1, department.getName());
            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                    if(resultSet.next()){
                        int id = resultSet.getInt(1);
                        department.setId(id);
                    }
                }
            } else {
                throw  new DbException("Erro inesperado! Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department department) {
        // FALTA SÓ ESSE
    }

    @Override
    public void deleteById(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Department WHERE Id = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department findById(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE Id = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return instantiateDepartment(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));
        return department;
    }

    @Override
    public List<Department> findAll() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM department"
        )){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Department> list = new ArrayList<>();
            while (resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                list.add(department);
            }
            for(Department obj : list){
                System.out.println(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
