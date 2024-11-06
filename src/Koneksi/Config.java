package Koneksi;

import java.sql.Connection; // membangun koneksi ke basis data
import java.sql.SQLException; // menangani kemungkinan kesalahan yang terkait dengan database
import java.sql.DriverManager; // mengelola koneksi ke database

public class Config {
    private static Connection mysqlconfig;
    public static Connection configDB()throws SQLException{
        try {
            String url="jdbc:mysql://localhost:3306/kasirtobaku"; // Mendefinisikan URL untuk koneksi ke database MySQL
            String user="root"; // Mendefinisikan nama pengguna untuk koneksi ke basis data MySQL
            String pass=""; // Mendefinisikan kata sandi untuk koneksi ke basis data MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); // Mendaftarkan driver MySQL
            mysqlconfig=DriverManager.getConnection(url, user, pass); // Melakukan koneksi ke basis data MySQL
        } catch (Exception e) {
            System.err.println("koneksi gagal "+e.getMessage()); // Mencetak pesan kesalahan ke konsol jika koneksi gagal
        }
        return mysqlconfig; // Mengembalikan objek koneksi mysqlconfig yang telah dibuat
    }

    public static Connection getConfig() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
