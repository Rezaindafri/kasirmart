/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projectkasir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.Config;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Izzul Islam Ramadhan
 */
public class pn_pembelian extends javax.swing.JPanel {
    
    String Tanggal;
    DefaultTableModel model;
    
    public void totalBiaya(){
        int jumlahBaris = tblpembelian.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
        jumlahBarang = Integer.parseInt(tblpembelian.getValueAt(i, 6).toString());
        hargaBarang = Integer.parseInt(tblpembelian.getValueAt(i, 5).toString());
        totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
    }
        txttotalpembelian.setText(String.valueOf(totalBiaya));
        lblrupiah.setText(formatRupiah(totalBiaya) + ",00");
    }
    
    private void autonumber(){
        try {
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            String sql ="SELECT MAX(RIGHT(id_pembelian,4)) AS noPembelian FROM pembelian";
            ResultSet res=stm.executeQuery(sql);
            if (res.next()) {
                String noPembelian, nol;
                int p;
                noPembelian = Integer.toString(res.getInt(1)+1);
                txtnopembelian.setText("1212"+noPembelian);
                p = noPembelian.length();
                nol = "";
                for (int i = 1; i <= 4-p; i++) {
                    nol = nol +"0";
                }
                txtnopembelian.setText("2323"+nol+noPembelian);
            } else {
                txtnopembelian.setText("23230001");
            }
        } catch (Exception e) {
            txtnopembelian.setText("23230001");
        }
        txtnopembelian.disable();
    }
    
    public void load_table(){
        DefaultTableModel model = (DefaultTableModel) tblpembelian.getModel();
         // Cek apakah kode barang sudah ada di dalam daftar
        int index = -1;
        for (int i = 0; i < model.getRowCount(); i++) {
        if (txtkdbarang.getText().equals(model.getValueAt(i, 3))) {
            index = i;
            break;
        }
    }
        // Jika ada, tambahkan jumlah barangnya
        if (index != -1) {
        int qty = Integer.parseInt(model.getValueAt(index, 6).toString());
        qty += Integer.parseInt(txtjumlah.getText());
        model.setValueAt(qty, index, 6);

        // Hitung subtotal
        int subtotal = Integer.parseInt(model.getValueAt(index, 5).toString()) * qty;
        model.setValueAt(subtotal, index, 7);
        
        } else {
        // Jika tidak ada, tambahkan barang baru ke dalam daftar
        model.addRow(new Object[]{
            txtnopembelian.getText(),
            txtkdsupplier.getText(),
            txtnamasupplier.getText(),
            txtkdbarang.getText(),
            txtnamabarang.getText(),
            txtharga.getText(),
            txtjumlah.getText(),
            txttotalpembelian.getText(),
        });
    }
}  
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) tblpembelian.getModel();     
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txtnopembelian.setText("");
        txtkdsupplier.setText("");
        txtnamasupplier.setText("");
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        txttotalpembelian.setText("");
        autonumber();
    }
    
    public void clear(){
        txttotalpembelian.setText("");
        txtpembayaran.setText("");
        txtkembalian.setText("");
        lblrupiah.setText("");
    }
    
    public void clear2(){
        txtkdsupplier.setText("");
        txtnamasupplier.setText("");
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
    }
    
    public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.parseInt(txtjumlah.getText());
        harga = Integer.parseInt(txtharga.getText());
        total = jumlah * harga;
        
        txttotalpembelian.setText(String.valueOf(total));
        
        load_table();
        totalBiaya();
        clear2();
        txtkdsupplier.requestFocus();
    }
    /**
     * Creates new form pn_pembelian
     */
    public pn_pembelian() {
        initComponents();
        try {
            autofield();
        } catch (SQLException ex) {
            Logger.getLogger(pn_pembelian.class.getName()).log(Level.SEVERE, null, ex);
        }
        autonumber2();
        txtcetak.setText("");
        txtcetak.hide();
        
        model = new DefaultTableModel();
        
        tblpembelian.setModel(model);
        
        model.addColumn("No.Pembelian");
        model.addColumn("Kode Supplier");
        model.addColumn("Supplier");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        txttanggal.setText(s.format(date));
        txttanggal.disable();
        txttotalpembelian.setText("0");
        txttotalpembelian.disable();
        txtpembayaran.setText("0");
        txtkembalian.setText("0");
        txtkembalian.disable();
        txtusername.requestFocus();
        
        tblpembelian.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblpembelian.getTableHeader().setOpaque(false);
        tblpembelian.setBackground(new Color(122,186,120));
        tblpembelian.setForeground(new Color(0,0,0));
        tblpembelian.setRowHeight(35);
    }
    
    private void autofield() throws SQLException {
        Connection conn = Config.configDB();
        String query = "SELECT username FROM log_akun WHERE id_log_akun = (SELECT MAX(id_log_akun) FROM log_akun)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                txtusername.setText(username);
                txtusername.disable();
            } else {
                System.out.println("");
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void autonumber2() {
        try {
            Connection conn = Config.configDB();
            String query = "SELECT id_pembelian FROM pembelian WHERE id_pembelian = (SELECT MAX(id_pembelian) FROM pembelian)";
            java.sql.Statement stm=conn.createStatement(); 
            ResultSet res=stm.executeQuery(query);
            if (res.next()) {
                String cetak = res.getString("id_pembelian");
                txtcetak.setText(cetak);
                txtcetak.disable();
            } else {
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void Bersih(){
        txtkdsupplier.setText("");
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        txtnamasupplier.setText("");
        txtkdsupplier.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtnopembelian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtkdbarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtjumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        txtkdsupplier = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtnamasupplier = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btncarisupplier = new javax.swing.JButton();
        btncaribarang = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblrupiah = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpembelian = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtkembalian = new javax.swing.JTextField();
        txttotalpembelian = new javax.swing.JTextField();
        pembayaran = new javax.swing.JLabel();
        txtpembayaran = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnsimpan = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btncetaktransaksi = new javax.swing.JButton();
        txtcetak = new javax.swing.JTextField();
        btnbayar = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(10, 104, 71));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pembelian");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Transaksi > Pembelian");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(122, 186, 120));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("No. Pembelian");

        txtnopembelian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtnopembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnopembelianActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Username");

        txtusername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Tanggal");

        txtkdbarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtkdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdbarangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Kode Barang");

        txtnamabarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Nama Barang");

        txtharga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Harga");

        txtjumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Jumlah");

        txttanggal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        txtkdsupplier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtkdsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdsupplierActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Kode Supplier");

        txtnamasupplier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtnamasupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamasupplierActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Nama Supplier");

        btncarisupplier.setBackground(new java.awt.Color(10, 104, 71));
        btncarisupplier.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncarisupplier.setForeground(new java.awt.Color(255, 255, 255));
        btncarisupplier.setText("Cari");
        btncarisupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncarisupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncarisupplierMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncarisupplierMouseExited(evt);
            }
        });
        btncarisupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarisupplierActionPerformed(evt);
            }
        });

        btncaribarang.setBackground(new java.awt.Color(10, 104, 71));
        btncaribarang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncaribarang.setForeground(new java.awt.Color(255, 255, 255));
        btncaribarang.setText("Cari");
        btncaribarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncaribarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncaribarangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncaribarangMouseExited(evt);
            }
        });
        btncaribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaribarangActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(10, 104, 71));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        lblrupiah.setBackground(new java.awt.Color(32, 136, 203));
        lblrupiah.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        lblrupiah.setForeground(new java.awt.Color(255, 255, 255));
        lblrupiah.setText("Rp 0,00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblrupiah, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblrupiah, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("(Scan disini)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("(Scan disini)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                            .addComponent(txtnopembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncaribarang))
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtkdsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btncarisupplier)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8)
                                        .addComponent(txtnamabarang, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 333, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnopembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addComponent(btncaribarang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtnamabarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(41, 41, 41)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtkdsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(4, 4, 4)
                                    .addComponent(btncarisupplier))
                                .addComponent(txtnamasupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))))
        );

        tblpembelian.setBackground(new java.awt.Color(180, 230, 253));
        tblpembelian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblpembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.Pembelian", "Kode Supplier", "Supplier", "Kode Barang", "Nama Barang", "Harga", "Qty", "Subtotal"
            }
        ));
        tblpembelian.setGridColor(new java.awt.Color(0, 0, 0));
        tblpembelian.setSelectionBackground(new java.awt.Color(10, 104, 71));
        tblpembelian.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblpembelian.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblpembelian.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblpembelian.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tblpembelian);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Total Pembelian");

        txtkembalian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txttotalpembelian.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        pembayaran.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pembayaran.setText("Pembayaran");

        txtpembayaran.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtpembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpembayaranActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Kembalian");

        btnsimpan.setBackground(new java.awt.Color(122, 186, 120));
        btnsimpan.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\save.png")); // NOI18N
        btnsimpan.setText("Simpan Transaksi");
        btnsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsimpanMouseExited(evt);
            }
        });
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(122, 186, 120));
        btnhapus.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hapus.jpg.png"))); // NOI18N
        btnhapus.setText("Hapus Data");
        btnhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnhapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnhapusMouseExited(evt);
            }
        });
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btnbatal.setBackground(new java.awt.Color(122, 186, 120));
        btnbatal.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbatalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbatalMouseExited(evt);
            }
        });
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btncetaktransaksi.setBackground(new java.awt.Color(122, 186, 120));
        btncetaktransaksi.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btncetaktransaksi.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\printer.png")); // NOI18N
        btncetaktransaksi.setText("Cetak Transaksi");
        btncetaktransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncetaktransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncetaktransaksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncetaktransaksiMouseExited(evt);
            }
        });
        btncetaktransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetaktransaksiActionPerformed(evt);
            }
        });

        txtcetak.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcetakActionPerformed(evt);
            }
        });
        txtcetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcetakKeyPressed(evt);
            }
        });

        btnbayar.setBackground(new java.awt.Color(122, 186, 120));
        btnbayar.setFont(new java.awt.Font("Segoe UI Black", 0, 22)); // NOI18N
        btnbayar.setText("Bayar");
        btnbayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbayarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbayarMouseExited(evt);
            }
        });
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });

        btntambah.setBackground(new java.awt.Color(122, 186, 120));
        btntambah.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        btntambah.setForeground(new java.awt.Color(0, 0, 0));
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tambah.jpg.png"))); // NOI18N
        btntambah.setText("Tambah Data");
        btntambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btntambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntambahMouseExited(evt);
            }
        });
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnbayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(txtkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtpembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(txttotalpembelian)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btntambah)
                        .addGap(18, 18, 18)
                        .addComponent(btnhapus)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btncetaktransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txttotalpembelian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pembayaran))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnhapus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addComponent(btnbatal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btncetaktransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtnopembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnopembelianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnopembelianActionPerformed

    private void txtkdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdbarangActionPerformed
        // TODO add your handling code here:
        String kodeBarang = txtkdbarang.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT nama_barang, harga_beli FROM daftar_barang WHERE id_barang = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kodeBarang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtnamabarang.setText(rs.getString("nama_barang"));
            txtharga.setText(rs.getString("harga_beli"));
            txtjumlah.requestFocus();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtkdbarangActionPerformed

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void txttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttanggalActionPerformed

    private void txtkdsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdsupplierActionPerformed
        // TODO add your handling code here:
        String kodeSupplier = txtkdsupplier.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT nama_supplier FROM supplier WHERE id_supplier = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kodeSupplier);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtnamasupplier.setText(rs.getString("nama_supplier"));
            txtkdbarang.requestFocus();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtkdsupplierActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if (tblpembelian.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus !");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblpembelian.getModel();
        int row = tblpembelian.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txtpembayaran.setText("0");
        txtkembalian.setText("0");
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        if (txtkdbarang.getText().equals("") || txtnamabarang.getText().equals("") 
                || txtharga.getText().equals("") || txtjumlah.getText().equals("") || txtjumlah.getText().equals("0") 
                || txtkdsupplier.getText().equals("") || txtnamasupplier.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi data terlebih dahulu!");
            return;
        }
        tambahTransaksi();
        Bersih();
    }//GEN-LAST:event_btntambahActionPerformed

    private void txtpembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpembayaranActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;

        total = Integer.parseInt(txttotalpembelian.getText());
        bayar = Integer.parseInt(txtpembayaran.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txtkembalian.setText(String.valueOf(kembalian));
            lblrupiah.setText(formatRupiah(kembalian) + ",00");
        }
    }//GEN-LAST:event_txtpembayaranActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblpembelian.getModel();

        String noPembelian = txtnopembelian.getText();
        String tanggal = txttanggal.getText();
        String username = txtusername.getText();
        String total = txttotalpembelian.getText();
        String pembayaran = txtpembayaran.getText();
        String kembalian = txtkembalian.getText();
        
        if (model.getRowCount() == 0) {
        // Display message
        JOptionPane.showMessageDialog(this, "Silahkan lakukan transaksi terlebih dahulu!");
        return;
        }
        if (txtkembalian.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Silahkan lakukan pembayaran terlebih dahulu!");
            return;
        }
        try {
            Connection conn= Config.configDB();
            String sql = "INSERT INTO pembelian VALUES (?,?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, noPembelian);
            pst.setString(2, username);
            pst.setString(3, tanggal);
            pst.setString(4, total);
            pst.setString(5, pembayaran);
            pst.setString(6, kembalian);
            pst.executeUpdate();
            System.out.println(sql);
            pst.close();
        } catch (Exception e) {
            System.out.println("simpan pembelian error");
        }

        try {
            Connection conn=Config.configDB();
            int baris = tblpembelian.getRowCount();

            for(int i = 0; i < baris; i++) {
                String sql = "INSERT INTO detail_pembelian(id_pembelian, id_supplier, nama_supplier, id_barang, nama_barang, harga_barang, jumlah_barang, subtotal) VALUES('"
                + tblpembelian.getValueAt(i, 0) +"','"+ tblpembelian.getValueAt(i, 1) +"','"+ tblpembelian.getValueAt(i, 2) +"','"+ tblpembelian.getValueAt(i, 3) +"','"+ tblpembelian.getValueAt(i, 4) +"','"+ tblpembelian.getValueAt(i, 5) +"','"+ tblpembelian.getValueAt(i, 6) +"','"+ tblpembelian.getValueAt(i, 7)+"')";
                System.out.println(sql);
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        clear();
        utama();
        autonumber();
        autonumber2();
        kosong();
        lblrupiah.setText("Rp 0");
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        Bersih();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btntambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseEntered
        // TODO add your handling code here:
        btntambah.setBackground(new Color(10,104,71));
        btntambah.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btntambahMouseEntered

    private void btntambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseExited
        // TODO add your handling code here:
        btntambah.setBackground(new Color(122,186,120));
        btntambah.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btntambahMouseExited

    private void btnhapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapusMouseEntered
        // TODO add your handling code here:
        btnhapus.setBackground(new Color(10,104,71));
        btnhapus.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnhapusMouseEntered

    private void btnhapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapusMouseExited
        // TODO add your handling code here:
        btnhapus.setBackground(new Color(122,186,120));
        btnhapus.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnhapusMouseExited

    private void btnbatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseEntered
        // TODO add your handling code here:
        btnbatal.setBackground(new Color(10,104,71));
        btnbatal.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnbatalMouseEntered

    private void btnbatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbatalMouseExited
        // TODO add your handling code here:
        btnbatal.setBackground(new Color(122,186,120));
        btnbatal.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnbatalMouseExited

    private void txtnamasupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamasupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamasupplierActionPerformed

    private void btnsimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpanMouseEntered
        // TODO add your handling code here:
        btnsimpan.setBackground(new Color(10,104,71));
        btnsimpan.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnsimpanMouseEntered

    private void btnsimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpanMouseExited
        // TODO add your handling code here:
        btnsimpan.setBackground(new Color(122,186,120));
        btnsimpan.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnsimpanMouseExited

    private void btncarisupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarisupplierActionPerformed
        this.setVisible(true);
        new ListSupplier().setVisible(true);
    }//GEN-LAST:event_btncarisupplierActionPerformed

    private void btncaribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaribarangActionPerformed
        // TODO add your handling code here:
        this.setVisible(true);
        new ListBarang2().setVisible(true);
    }//GEN-LAST:event_btncaribarangActionPerformed

    private void btncetaktransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetaktransaksiActionPerformed
        if (txtcetak.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan lakukan transaksi terlebih dahulu !");
            return;
        }
        try {
            String report = "C:\\Users\\Izzul Islam Ramadhan\\OneDrive\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\report\\cetak_pembelian.jrxml";
            Connection conn=Config.configDB();
            HashMap<String, Object> parameter = new HashMap<>();
            //Mengambil parameter dari irreport
            parameter.put("buy", txtcetak.getText());
            JasperReport JRpt = JasperCompileManager.compileReport(report);
            JasperPrint JPrint = JasperFillManager.fillReport(JRpt, parameter, conn);
            JasperViewer.viewReport(JPrint, false);
            txtcetak.setText("");
        } catch (Exception e) {
            System.out.println("Cetak tidak berhasil karena : " + e);
        }
    }//GEN-LAST:event_btncetaktransaksiActionPerformed

    private void txtcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcetakActionPerformed

    private void txtcetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcetakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcetakKeyPressed

    private void btncetaktransaksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncetaktransaksiMouseEntered
        // TODO add your handling code here:
        btncetaktransaksi.setBackground(new Color(10,104,71));
        btncetaktransaksi.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btncetaktransaksiMouseEntered

    private void btncetaktransaksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncetaktransaksiMouseExited
        // TODO add your handling code here:
        btncetaktransaksi.setBackground(new Color(122,186,120));
        btncetaktransaksi.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btncetaktransaksiMouseExited

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;

        total = Integer.parseInt(txttotalpembelian.getText());
        bayar = Integer.parseInt(txtpembayaran.getText());
        
        if (txtpembayaran.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi jumlah pembayaran !");
            return;
        }
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txtkembalian.setText(String.valueOf(kembalian));
            lblrupiah.setText(formatRupiah(kembalian) + ",00");
        }
    }//GEN-LAST:event_btnbayarActionPerformed

    private void btnbayarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbayarMouseEntered
        // TODO add your handling code here:
        btnbayar.setBackground(new Color(10,104,71));
        btnbayar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnbayarMouseEntered

    private void btnbayarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbayarMouseExited
        // TODO add your handling code here:
        btnbayar.setBackground(new Color(122,186,120));
        btnbayar.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnbayarMouseExited

    private void btncaribarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncaribarangMouseEntered
        // TODO add your handling code here:
        btncaribarang.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btncaribarangMouseEntered

    private void btncarisupplierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncarisupplierMouseEntered
        // TODO add your handling code here:
        btncarisupplier.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btncarisupplierMouseEntered

    private void btncarisupplierMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncarisupplierMouseExited
        // TODO add your handling code here:
        btncarisupplier.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btncarisupplierMouseExited

    private void btncaribarangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncaribarangMouseExited
        // TODO add your handling code here:
        btncaribarang.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btncaribarangMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnbayar;
    private javax.swing.JButton btncaribarang;
    private javax.swing.JButton btncarisupplier;
    private javax.swing.JButton btncetaktransaksi;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblrupiah;
    private javax.swing.JLabel pembayaran;
    private javax.swing.JTable tblpembelian;
    private javax.swing.JTextField txtcetak;
    public static javax.swing.JTextField txtharga;
    public static javax.swing.JTextField txtjumlah;
    public static javax.swing.JTextField txtkdbarang;
    public static javax.swing.JTextField txtkdsupplier;
    private javax.swing.JTextField txtkembalian;
    public static javax.swing.JTextField txtnamabarang;
    public static javax.swing.JTextField txtnamasupplier;
    private javax.swing.JTextField txtnopembelian;
    private javax.swing.JTextField txtpembayaran;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttotalpembelian;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
    private String formatRupiah(int amount) {
        NumberFormat rupiahFormat = new DecimalFormat("#,###,###,###");
        return "Rp " + rupiahFormat.format(amount);
    }
}
