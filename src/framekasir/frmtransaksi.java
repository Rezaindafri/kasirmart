/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package framekasir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.Config;
import java.sql.SQLException;
/**
 *
 * @author Izzul Islam Ramadhan
 */
public class frmtransaksi extends javax.swing.JInternalFrame {
    
    String Tanggal;
    DefaultTableModel model;
    
    public void totalBiaya(){
        int jumlahBaris = tbltransaksi.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
        jumlahBarang = Integer.parseInt(tbltransaksi.getValueAt(i, 4).toString());
        hargaBarang = Integer.parseInt(tbltransaksi.getValueAt(i, 3).toString());
        totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
    }
        txttotaltransaksi.setText(String.valueOf(totalBiaya));
        lblrupiah.setText("Rp "+ totalBiaya + ",00");
    }
    
    private void autonumber(){
        try {
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            String sql ="SELECT MAX(RIGHT(id_transaksi,4)) AS noTransaksi FROM transaksi";
            ResultSet res=stm.executeQuery(sql);
            if (res.next()) {
                String noTransaksi, nol;
                int p;
                noTransaksi = Integer.toString(res.getInt(1)+1);
                txtnotransaksi.setText("1212"+noTransaksi);
                p = noTransaksi.length();
                nol = "";
                for (int i = 1; i <= 4-p; i++) {
                    nol = nol +"0";
                }
                txtnotransaksi.setText("1212"+nol+noTransaksi);
            } else {
                txtnotransaksi.setText("12120001");
            }
        } catch (Exception e) {
            txtnotransaksi.setText("12120001");
        }
    }
    
    public void load_table(){
        DefaultTableModel model = (DefaultTableModel) tbltransaksi.getModel();
        model.addRow(new Object[]{
            txtnotransaksi.getText(),
            txtkdbarang.getText(),
            txtnamabarang.getText(),
            txtharga.getText(),
            txtjumlah.getText(),
            txttotaltransaksi.getText(),
        });
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) tbltransaksi.getModel();     
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txtnotransaksi.setText("");
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtharga.setText("");
        txtjumlah.setText("");
        txttotaltransaksi.setText("");
        autonumber();
    }
    
    public void clear(){
        txtusername.setText("");
        txttotaltransaksi.setText("");
        txtpembayaran.setText("");
        txtkembalian.setText("");
        lblrupiah.setText("");
    }
    
    public void clear2(){
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
        
        txttotaltransaksi.setText(String.valueOf(total));
        
        load_table();
        totalBiaya();
        clear2();
        txtkdbarang.requestFocus();
    }
    /**
     * Creates new form frmtransaksi
     */
    public frmtransaksi() {
        initComponents();
        
        model = new DefaultTableModel();
        
        tbltransaksi.setModel(model);
        
        model.addColumn("No.Transaksi");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Qty");
        model.addColumn("Subtotal");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        txttanggal.setText(s.format(date));
        txttotaltransaksi.setText("0");
        txtpembayaran.setText("0");
        txtkembalian.setText("0");
        txtusername.requestFocus();
    }
    
    public void KodeBarang() throws SQLException {
    Connection conn= Config.configDB();
    java.sql.Statement stm=conn.createStatement();
    String kodeBarang = txtkdbarang.getText();
    String sql = "SELECT nama_barang, harga_jual FROM daftar_barang WHERE id_barang = '" + kodeBarang + "'";
    ResultSet res=stm.executeQuery(sql);
    
        if (res.next()) {
            txtnamabarang.setText(res.getString("nama_barang"));
            txtharga.setText(res.getString("harga_jual"));
        } else {
            txtnamabarang.setText("Kode Barang tidak ditemukan");
            txtharga.setText("");
        }
        conn.close();
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
        btnkembali = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtnotransaksi = new javax.swing.JTextField();
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
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        lblrupiah = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttotaltransaksi = new javax.swing.JTextField();
        pembayaran = new javax.swing.JLabel();
        txtpembayaran = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtkembalian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksi = new javax.swing.JTable();
        btnsimpan = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel2.setText("Transaksi");

        btnkembali.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btnkembali.setText("Kembali");
        btnkembali.setBorder(null);
        btnkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(403, 403, 403)
                .addComponent(btnkembali)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnkembali)
                    .addComponent(jLabel2)))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("No. Transaksi");

        txtnotransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnotransaksiActionPerformed(evt);
            }
        });

        jLabel4.setText("Username");

        jLabel6.setText("Tanggal");

        txtkdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdbarangActionPerformed(evt);
            }
        });

        jLabel7.setText("Kode Barang");

        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        jLabel8.setText("Nama Barang");

        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel9.setText("Harga");

        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });

        jLabel10.setText("Jumlah");

        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        btntambah.setBackground(new java.awt.Color(102, 255, 102));
        btntambah.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/tambah.jpg.png"))); // NOI18N
        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(255, 51, 51));
        btnhapus.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hapus.jpg.png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        lblrupiah.setBackground(new java.awt.Color(255, 102, 102));
        lblrupiah.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lblrupiah.setText("RP.");
        lblrupiah.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(txtnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblrupiah, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(btntambah)
                        .addGap(18, 18, 18)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblrupiah, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel12.setText("Total Transaksi");

        pembayaran.setText("Pembayaran");

        txtpembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpembayaranActionPerformed(evt);
            }
        });

        jLabel13.setText("Kembalian");

        tbltransaksi.setBackground(new java.awt.Color(102, 204, 255));
        tbltransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No.Transaksi", "Kode Barang", "Nama Barang", "Harga", "Qty", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tbltransaksi);

        btnsimpan.setBackground(new java.awt.Color(153, 153, 153));
        btnsimpan.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/simpan.jpg.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsimpan)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pembayaran)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txttotaltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txttotaltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pembayaran))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnotransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnotransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnotransaksiActionPerformed

    private void txtkdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdbarangActionPerformed
        // TODO add your handling code here:
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

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbltransaksi.getModel();
        int row = tbltransaksi.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txtpembayaran.setText("0");
        txtkembalian.setText("0");
    }//GEN-LAST:event_btnhapusActionPerformed

    private void txtpembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpembayaranActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;

        total = Integer.parseInt(txttotaltransaksi.getText());
        bayar = Integer.parseInt(txtpembayaran.getText());

        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txtkembalian.setText(String.valueOf(kembalian));
            lblrupiah.setText("Rp "+ kembalian + ",00");
        }
    }//GEN-LAST:event_txtpembayaranActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbltransaksi.getModel();

        String noTransaksi = txtnotransaksi.getText();
        String tanggal = txttanggal.getText();
        String username = txtusername.getText();
        String total = txttotaltransaksi.getText();

        try {
            Connection conn= Config.configDB();
            String sql = "INSERT INTO transaksi VALUES (?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1, noTransaksi);
            pst.setString(2, username);
            pst.setString(3, tanggal);
            pst.setString(4, total);
            pst.executeUpdate();
            System.out.println(sql);
            pst.close();
        } catch (Exception e) {
            System.out.println("simpan transaksi error");
        }

        try {
            Connection conn=Config.configDB();
            int baris = tbltransaksi.getRowCount();

            for(int i = 0; i < baris; i++) {
                String sql = "INSERT INTO detail_transaksi(id_transaksi,id_barang, harga_barang, jumlah_barang, total) VALUES('"
                + tbltransaksi.getValueAt(i, 0) +"','"+ tbltransaksi.getValueAt(i, 1) +"','"+ tbltransaksi.getValueAt(i, 3) +"','"+ tbltransaksi.getValueAt(i, 4) +"','"+ tbltransaksi.getValueAt(i, 5) +"')";
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
        kosong();
        lblrupiah.setText("Rp 0");
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkembaliActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnkembaliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkembali;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblrupiah;
    private javax.swing.JLabel pembayaran;
    private javax.swing.JTable tbltransaksi;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkdbarang;
    private javax.swing.JTextField txtkembalian;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtnotransaksi;
    private javax.swing.JTextField txtpembayaran;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttotaltransaksi;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
