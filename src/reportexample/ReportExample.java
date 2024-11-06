package reportexample;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReportExample {

    public static Connection connection;
    public static Statement statement;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String csvFilePath = "C:\\Users\\Izzul Islam Ramadhan\\OneDrive\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\documents\\Data_barang.csv";

        try {
            String url = "jdbc:mysql://localhost:3306/kasirtobaku";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();

            String sql = "SELECT * FROM daftar_barang";
            ResultSet resultSet = statement.executeQuery(sql);

            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath))) {
                fileWriter.write("id_barang, nama_barang, harga_beli, harga_jual, stok, nama_supplier");

                while (resultSet.next()) {
                    String id_barang = resultSet.getString("id_barang");
                    String nama_barang = resultSet.getString("nama_barang");
                    String harga_beli = resultSet.getString("harga_beli");
                    String harga_jual = resultSet.getString("harga_jual");
                    String stok = resultSet.getString("stok");
                    String nama_supplier = resultSet.getString("nama_supplier");

                    String line = String.format("%s,%s,%s,%s,%s,%s",
                            id_barang, nama_barang, harga_beli, harga_jual, stok, nama_supplier);

                    fileWriter.newLine();
                    fileWriter.write(line);
                }

                statement.close();
            }

        } catch (Exception e) {
            System.err.println("koneksi gagal " + e.getMessage());
        }

        String excelFilePath = "C:\\Users\\Izzul Islam Ramadhan\\OneDrive\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\documents\\data_supplier.xlsx";

        try {
            String url = "jdbc:mysql://localhost:3306/kasirtobaku";
            String user = "root";
            String pass = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();

            String sql = "SELECT * FROM supplier";
            ResultSet resultSet = statement.executeQuery(sql);

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet("Report");

                writeHeaderLine(sheet);

                writeDataLines(resultSet, workbook, sheet);

                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);

                workbook.close();

                statement.close();

            }

        } catch (Exception e) {
            System.err.println("koneksi gagal " + e.getMessage());
        }

    }

    private static void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("id supplier");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("nama supplier");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("alamat supplier");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("nomor telepon");
    }

    private static void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {

        int rowCount = 1;

        while (result.next()) {
            String id_supplier = result.getString("id_supplier");
            String nama_supplier = result.getString("nama_supplier");
            String alamat_supplier = result.getString("alamat_supplier");
            String telp_supplier = result.getString("telp_supplier");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;

            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id_supplier);

            cell = row.createCell(columnCount++);
            cell.setCellValue(nama_supplier);

            cell = row.createCell(columnCount++);
            cell.setCellValue(alamat_supplier);

            cell = row.createCell(columnCount++);
            cell.setCellValue(telp_supplier);
        }

    }

}
