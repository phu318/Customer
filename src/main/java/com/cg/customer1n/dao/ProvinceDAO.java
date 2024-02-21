package com.cg.customer1n.dao;

import com.cg.customer1n.model.Customer;
import com.cg.customer1n.model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO implements IProvinceDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/customers?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private String SELECT_PROVINCE_BY_ID = "select * from province where id = ?";
    private String SELECT_PROVINCE = "SELECT * FROM province";

    @Override
    public List<Province> selectProvinces() {
        List<Province> provinces = new ArrayList<>();
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int province_id = rs.getInt("id");
                String province_name = rs.getString("name");
                Province province = new Province(province_id,province_name);
                provinces.add(province);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return provinces;
    }

    @Override
    public Province findProvinceById(int id) {
        Province province = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID);) {
            int province_id = 0;
            preparedStatement.setInt(1, province_id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                province_id = rs.getInt("id");
                String province_name = rs.getString("name");
                province = new Province(province_id, province_name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return province;
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
