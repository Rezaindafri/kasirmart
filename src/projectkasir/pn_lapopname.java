/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projectkasir;

import Koneksi.Config;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Izzul Islam Ramadhan
 */
public class pn_lapopname extends javax.swing.JPanel {    
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;
    /**
     * Creates new form pn_lappenjualan
     * @throws java.sql.SQLException
     */
    
    public void tambahOpname(){
        int baris = tbllapopname.getSelectedRow();
    // Ambil nilai stok sistem dan fisik dari tabel
    int stokSistem = Integer.parseInt(tbllapopname.getValueAt(baris, 4).toString());
    int stokFisik = Integer.parseInt(txtstokfisik.getText());

    // Hitung selisih stok
    int selisihStok = stokSistem - stokFisik;

    // Set nilai selisih stok di tabel
    tbllapopname.setValueAt(selisihStok, baris, 6);
    }
    
    
    
    public pn_lapopname() {
        initComponents();
        try {
            autofield();
        } catch (SQLException ex) {
            Logger.getLogger(pn_lapopname.class.getName()).log(Level.SEVERE, null, ex);
        }
        Bersih();
        Bersih2();
        load_table();
        autonumber();
        setBackground(new Color(255,255,255));
        
        tbllapopname.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllapopname.getTableHeader().setOpaque(false);
        tbllapopname.setBackground(new Color(122,186,120));
        tbllapopname.setForeground(new Color(0,0,0));
        tbllapopname.setRowHeight(35);
        
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        txttanggal.setText(s.format(date));
        txttanggal.disable();
        setColumnWidth();
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
    
    private void Bersih(){
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtstoksistem.setText("");
        txtstokfisik.setText("");
        
        btnsimpan.setText("Simpan");
        txtkdbarang.setEditable(true);
        txtkdbarang.enable();
        txtnamabarang.enable();
        txtstoksistem.enable();
        txtkdbarang.requestFocus();
        autonumber();
    }
    
    private void Bersih2(){
        txtkdbarang.setText("");
        txtnamabarang.setText("");
        txtstoksistem.setText("");
        txtstokfisik.setText("");
        
        btnsimpan.setText("Simpan");
        txtkdbarang.setEditable(true);
        txtkdbarang.enable();
        txtnamabarang.enable();
        txtstoksistem.enable();
        txtpencarian.setText("");
        txtpencarian.requestFocus();
        autonumber();
    }
    
    private void load_table() {
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("No.");
        model.addColumn("No. Opname");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Sistem");
        model.addColumn("Stok Fisik");
        model.addColumn("Selisih Stok");
        model.addColumn("Tanggal");
     
        String cari = txtpencarian.getText();
        try {
            int no=1;
            String sql = ("SELECT opname.id_opname, detail_opname.id_barang, detail_opname.nama_barang, detail_opname.stok_sistem, detail_opname.stok_fisik, detail_opname.selisih_stok, opname.tgl_opname " + 
                    "FROM opname INNER JOIN detail_opname ON opname.id_opname = detail_opname.id_opname " + "WHERE detail_opname.id_opname LIKE '%" + cari + "%' OR detail_opname.id_barang LIKE '%" + cari + "%' "
                    + "OR detail_opname.nama_barang LIKE '%" + cari + "%' OR detail_opname.stok_sistem LIKE '%" + cari + "%' OR detail_opname.stok_fisik LIKE '%" + cari + "%' OR detail_opname.selisih_stok LIKE '%" + cari + "%'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()) {
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)
                        ,res.getString(5),res.getString(6),res.getString(7)});
            }
            tbllapopname.setModel(model);
            tambahOpname();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private void kosong () {
        txtkdbarang.enable();
        txtnamabarang.enable();
        txtstoksistem.enable();
        txtkdbarang.setText(null);
        txtnamabarang.setText(null);
        txtstoksistem.setText(null);
        txtstokfisik.setText(null);
    }
    
    private void refresh(){
        txtpencarian.setText("");
        load_table();
        setColumnWidth();
    }
    
    private void setColumnWidth() {
        TableColumnModel columnModel = tbllapopname.getColumnModel();
        columnModel.getColumn(noColumnIndex).setPreferredWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMaxWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMinWidth(noColumnWidth);
        tbllapopname.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbllapopname.getColumnModel().getColumn(1).setMaxWidth(150);
        tbllapopname.getColumnModel().getColumn(1).setMinWidth(150);
        tbllapopname.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbllapopname.getColumnModel().getColumn(2).setMaxWidth(150);
        tbllapopname.getColumnModel().getColumn(2).setMinWidth(150);
        tbllapopname.getColumnModel().getColumn(3).setPreferredWidth(350);
        tbllapopname.getColumnModel().getColumn(3).setMaxWidth(350);
        tbllapopname.getColumnModel().getColumn(3).setMinWidth(350);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtkdbarang = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtstoksistem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtstokfisik = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtnoopname = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        btncari1 = new javax.swing.JButton();
        lblbarcode = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btncari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtpencarian = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllapopname = new Custom.JTable_Custom();

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("(Scan disini)");

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tanggal Opname");

        txttanggal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Kode Barang");

        txtkdbarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtkdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdbarangActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(122, 186, 120));
        btnsimpan.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\save.png")); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setBorder(null);
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

        btnbatal.setBackground(new java.awt.Color(122, 186, 120));
        btnbatal.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setBorder(null);
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

        btnedit.setBackground(new java.awt.Color(122, 186, 120));
        btnedit.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\exchange.png")); // NOI18N
        btnedit.setText("Ubah");
        btnedit.setBorder(null);
        btnedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btneditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btneditMouseExited(evt);
            }
        });
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnhapus.setBackground(new java.awt.Color(122, 186, 120));
        btnhapus.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\bin.png")); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setBorder(null);
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

        jPanel2.setBackground(new java.awt.Color(10, 104, 71));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Laporan Opname");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Laporan > Opname");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtnamabarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Nama Barang");

        txtstoksistem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Stok Sistem");

        txtstokfisik.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Fisik");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("No. Opname");

        txtnoopname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtnoopname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoopnameActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Username");

        txtusername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        btncari1.setBackground(new java.awt.Color(10, 104, 71));
        btncari1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncari1.setForeground(new java.awt.Color(255, 255, 255));
        btncari1.setText("Cari");
        btncari1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncari1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncari1MouseExited(evt);
            }
        });
        btncari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncari1ActionPerformed(evt);
            }
        });

        lblbarcode.setBackground(new java.awt.Color(0, 0, 0));
        lblbarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Barcode");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("(Scan disini)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnoopname, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncari1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstoksistem, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstokfisik, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtnoopname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(btncari1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtstokfisik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtstoksistem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel12))
                            .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        btncari.setBackground(new java.awt.Color(10, 104, 71));
        btncari.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncari.setForeground(new java.awt.Color(255, 255, 255));
        btncari.setText("Cari");
        btncari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncariMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncariMouseExited(evt);
            }
        });
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Cari Data  :");

        txtpencarian.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtpencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpencarianActionPerformed(evt);
            }
        });
        txtpencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpencarianKeyPressed(evt);
            }
        });

        btnrefresh.setBackground(new java.awt.Color(10, 104, 71));
        btnrefresh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnrefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnrefresh.setText("Refresh");
        btnrefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnrefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnrefreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnrefreshMouseExited(evt);
            }
        });
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        tbllapopname.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "No. Opname", "Kode Barang", "Nama Barang", "Stok Sistem", "Stok Fisik", "Selisih Stok", "Tanggal"
            }
        ));
        tbllapopname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbllapopnameMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbllapopname);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1370, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtpencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnrefresh)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttanggalActionPerformed

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

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        if (txtnamabarang.getText().equals("") || txtkdbarang.equals("") || txtstoksistem.equals("") || txtstokfisik.equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi data terlebih dahulu !");
            return;
        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("No. Opname");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Sistem");
        model.addColumn("Stok Fisik");
        model.addColumn("Selisih Stok");
        
        int baris = tbllapopname.getSelectedRow();
        String noOpname = txtnoopname.getText();
        String kdBarang = txtkdbarang.getText();
        String namaBarang = txtnamabarang.getText();
        int stokSistem = Integer.parseInt(txtstoksistem.getText());
        int stokFisik = Integer.parseInt(txtstokfisik.getText());
        int selisihStok = stokSistem - stokFisik;
        
        try {
            String insertSQL = "INSERT INTO opname VALUES ('"+txtnoopname.getText()+"','"+txtusername.getText()+"','"+txttanggal.getText()+"')";
            Connection conn= Config.configDB();
            java.sql.PreparedStatement pstinsert=conn.prepareStatement(insertSQL);
            pstinsert.executeUpdate();
            
            String insertSQL2 = "INSERT INTO detail_opname (id_opname, id_barang, nama_barang, stok_sistem, stok_fisik, selisih_stok) VALUES (?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement pstinsert2=conn.prepareStatement(insertSQL2);
            pstinsert2.setString(1, noOpname);
            pstinsert2.setString(2, kdBarang);
            pstinsert2.setString(3, namaBarang);
            pstinsert2.setInt(4, stokSistem);
            pstinsert2.setInt(5, stokFisik);
            pstinsert2.setInt(6, selisihStok);
            pstinsert2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            txtusername.requestFocus();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
        load_table();
        Bersih();
        setColumnWidth();
    }//GEN-LAST:event_btnsimpanActionPerformed

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

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        Bersih();
        tbllapopname.clearSelection();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditMouseEntered
        // TODO add your handling code here:
        btnedit.setBackground(new Color(10,104,71));
        btnedit.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btneditMouseEntered

    private void btneditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditMouseExited
        // TODO add your handling code here:
        btnedit.setBackground(new Color(122,186,120));
        btnedit.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btneditMouseExited

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        if (txtkdbarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan diubah !");
            return;
        }
        int baris = tbllapopname.getSelectedRow();
        String noOpname = txtnoopname.getText();
        int stokSistem = Integer.parseInt(txtstoksistem.getText());
        int stokFisik = Integer.parseInt(txtstokfisik.getText());
        int selisihStok = stokSistem - stokFisik;
        
        try {
            Connection conn= Config.configDB();
            String sql = "UPDATE detail_opname "+
            "SET stok_fisik ='"+stokFisik+"', selisih_stok = '"+selisihStok+"'WHERE id_opname = '"+noOpname+"'";
            java.sql.PreparedStatement pstm=conn.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan data gagal"+e.getMessage());
        }
        load_table();
        kosong();
        setColumnWidth();
    }//GEN-LAST:event_btneditActionPerformed

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

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if (txtkdbarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus !");
        } else{
            int jawab = JOptionPane.showConfirmDialog(null, "Data ini akan dihapus, lanjutkan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == 0) {
                try {
                    Connection conn= Config.configDB();
                    java.sql.Statement stm=conn.createStatement();
                    String sql = "DELETE FROM opname WHERE id_opname = '" + txtnoopname.getText() + "'";
                    stm.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    Bersih();
                    load_table();
                    lblbarcode.setIcon(null);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        setColumnWidth();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void txtnoopnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoopnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoopnameActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        if (txtpencarian.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi data terlebih dahulu !");
            return;
        }
        load_table();
        setColumnWidth();
    }//GEN-LAST:event_btncariActionPerformed

    private void txtpencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpencarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpencarianActionPerformed

    private void txtpencarianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpencarianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpencarianKeyPressed

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
        txtkdbarang.requestFocus();
    }//GEN-LAST:event_txtusernameActionPerformed

    private void txtkdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdbarangActionPerformed
        // TODO add your handling code here:
        String kodeBarang = txtkdbarang.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT nama_barang, stok FROM daftar_barang WHERE id_barang = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kodeBarang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtnamabarang.setText(rs.getString("nama_barang"));
            txtstoksistem.setText(rs.getString("stok"));
            txtstokfisik.requestFocus();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtkdbarangActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        refresh();
        Bersih2();
        lblbarcode.setIcon(null);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btncari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncari1ActionPerformed
        this.setVisible(true);
        new ListBarang3().setVisible(true);
    }//GEN-LAST:event_btncari1ActionPerformed

    private void btncari1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncari1MouseEntered
        // TODO add your handling code here:
        btncari1.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btncari1MouseEntered

    private void btncari1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncari1MouseExited
        // TODO add your handling code here:
        btncari1.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btncari1MouseExited

    private void btncariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncariMouseEntered
        // TODO add your handling code here:
        btncari.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btncariMouseEntered

    private void btncariMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncariMouseExited
        // TODO add your handling code here:
        btncari.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btncariMouseExited

    private void btnrefreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrefreshMouseEntered
        // TODO add your handling code here:
        btnrefresh.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btnrefreshMouseEntered

    private void btnrefreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrefreshMouseExited
        // TODO add your handling code here:
        btnrefresh.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btnrefreshMouseExited

    private void tbllapopnameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllapopnameMousePressed
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        String s= source.getModel().getValueAt(row, 1) + "";
        String noOpname = tbllapopname.getValueAt(row,1).toString();
        txtnoopname.setText(noOpname);
        txtnoopname.disable();
        txtkdbarang.disable();
        txtnamabarang.disable();
        txtstoksistem.disable();
        try {
            String sql = ("SELECT * FROM detail_opname " + "where id_opname='" + s + "'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()) {
                txtkdbarang.setText(res.getString(2));
                txtnamabarang.setText(res.getString(3));
                txtstoksistem.setText(res.getString(4));
                txtstokfisik.setText(res.getString(5));
                ImageIcon imgThisImg = new ImageIcon("\\C:\\Users\\Izzul Islam Ramadhan\\OneDrive"
                    + "\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\BarcodeBarang\\ " + txtkdbarang.getText() + ".png");
                lblbarcode.setIcon(imgThisImg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR \n Data Tidak Ada / Tidak Ditemukan" + e.getMessage());
        }
    }//GEN-LAST:event_tbllapopnameMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncari1;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblbarcode;
    private Custom.JTable_Custom tbllapopname;
    public static javax.swing.JTextField txtkdbarang;
    public static javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtnoopname;
    private javax.swing.JTextField txtpencarian;
    public static javax.swing.JTextField txtstokfisik;
    public static javax.swing.JTextField txtstoksistem;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
    private void autonumber() {
        try {
            String sql = "SELECT MAX(RIGHT(id_opname,4)) AS no_auto FROM opname";
            Connection conn= Config.configDB();
            java.sql.Statement stm= conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                String no_auto, nol_plus;
                int p;
                no_auto = Integer.toString(res.getInt(1)+1);
                p = no_auto.length();
                nol_plus = "";
                for(int i=1;i<=4-p;i++){
                    nol_plus = nol_plus +"0";
                }
                txtnoopname.setText("OPN"+nol_plus+no_auto);
            }
        } catch (Exception e) {
            txtnoopname.setText("OPN0001");
        }
        txtnoopname.disable();
    }
}
