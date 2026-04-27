package com.lapzone.lapzoneweb.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lapzone_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            System.out.println("Không kết nối được database!!!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection testconnection = getConnection();
        if (testconnection != null) {
            System.out.println("Kết nối được database lapzone_db thành công");
        } else {
            System.out.println("Kết nối thất bại!! Kiểm tra lại XAMPP/MySQL hoặc DB_PASS");
        }
    }
}