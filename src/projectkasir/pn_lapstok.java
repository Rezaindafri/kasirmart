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
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Izzul Islam Ramadhan
 */
public class pn_lapstok extends javax.swing.JPanel {
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;
    /**
     * Creates new form pn_lapstok
     */
    public pn_lapstok() {
        initComponents();
        load_table();
        kosong();
        Bersih();
        Bersih2();
        autonumber();
        setBackground(new Color(255,255,255));
        
        tbllapstok.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllapstok.getTableHeader().setOpaque(false);
        tbllapstok.setBackground(new Color(122,186,120));
        tbllapstok.setForeground(new Color(0,0,0));
        tbllapstok.setRowHeight(35);
        txtstokmasuk.setText("0");
        txtstokmasuk.disable();
        txtstokkeluar.setText("0");
        txtstokkeluar.disable();
        setColumnWidth();
    }
    
    private void load_table(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok Awal");
        model.addColumn("Expired");
        model.addColumn("Stok Masuk");
        model.addColumn("Expired");
        model.addColumn("Stok Keluar");
     
        String cari = txtpencarian.getText();
        try {
            int no=1;
            String sql = ("SELECT * FROM kartu_stok WHERE id_barang LIKE '%" + cari + "%' OR nama_barang LIKE '%" + cari + "%' OR stok_awal LIKE '%" + cari + "%'"
                    + "OR expired_awal LIKE '%" + cari + "%' OR stok_masuk LIKE '%" + cari + "%' OR expired_masuk LIKE '%" + cari + "%' OR stok_keluar LIKE '%" + cari + "%'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()) {
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3),res.getString(4)
                        ,res.getString(5),res.getString(6),res.getString(7)});
            }
            tbllapstok.setModel(model);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
     
    private void kosong () {
        txtkdbarang.enable();
        txtnamabarang.enable();
        txtstokawal.enable();
        txtstokawal.setText(null);
        txtexpiredawal.setText(null);
        txtstokmasuk.setText(null);
        txtexpiredmasuk.setText(null);
        txtstokkeluar.setText(null);
        txtnamabarang.setText(null);
    }
    
    private void Bersih(){
        txtkdbarang.setText("");
        txtstokawal.setText("");
        txtexpiredawal.setText("");
        txtstokmasuk.setText("");
        txtexpiredmasuk.setText("");
        txtstokkeluar.setText("");
        txtnamabarang.setText("");
        
        btnsimpan.setText("Simpan");
        txtkdbarang.setEditable(true);
        txtkdbarang.enable();
        txtstokawal.enable();
        txtnamabarang.enable();
        txtstokmasuk.setText("0");
        txtstokkeluar.setText("0");
        txtkdbarang.requestFocus();
        autonumber();
    }
    
    private void Bersih2(){
        txtkdbarang.setText("");
        txtstokawal.setText("");
        txtexpiredawal.setText("");
        txtstokmasuk.setText("");
        txtexpiredmasuk.setText("");
        txtstokkeluar.setText("");
        txtnamabarang.setText("");
        
        txtkdbarang.setEditable(true);
        txtkdbarang.enable();
        txtstokawal.enable();
        txtnamabarang.enable();
        txtstokmasuk.setText("0");
        txtstokkeluar.setText("0");
        
        txtpencarian.setText("");
        txtpencarian.requestFocus();
        autonumber();
    }
     
    private void refresh(){
        txtpencarian.setText("");
        load_table();
        setColumnWidth();
    }
    
    private void setColumnWidth() {
        TableColumnModel columnModel = tbllapstok.getColumnModel();
        columnModel.getColumn(noColumnIndex).setPreferredWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMaxWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMinWidth(noColumnWidth);
        tbllapstok.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbllapstok.getColumnModel().getColumn(1).setMaxWidth(150);
        tbllapstok.getColumnModel().getColumn(1).setMinWidth(150);
        tbllapstok.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbllapstok.getColumnModel().getColumn(2).setMaxWidth(300);
        tbllapstok.getColumnModel().getColumn(2).setMinWidth(300);
    } 
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date_awal = new com.raven.datechooser.DateChooser();
        date_masuk = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtkdbarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtstokawal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtexpiredawal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtstokmasuk = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtexpiredmasuk = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtstokkeluar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        btncari2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lblbarcode = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btncari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtpencarian = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbllapstok = new Custom.JTable_Custom();

        date_awal.setForeground(new java.awt.Color(29, 0, 174));
        date_awal.setDateFormat("yyyy-MM-dd");
        date_awal.setFocusable(false);
        date_awal.setTextRefernce(txtexpiredawal);

        date_masuk.setForeground(new java.awt.Color(29, 0, 174));
        date_masuk.setDateFormat("yyyy-MM-dd");
        date_masuk.setTextRefernce(txtexpiredmasuk);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Kode Barang");

        txtkdbarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtkdbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkdbarangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Stok Awal");

        txtstokawal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtstokawal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokawalActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Tanggal Expired");

        txtexpiredawal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Stok Masuk");

        txtstokmasuk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Tanggal Expired");

        txtexpiredmasuk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

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
        jLabel9.setText("Laporan Stok");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Laporan > Stok");

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Stok Keluar");

        txtstokkeluar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Nama Barang");

        txtnamabarang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        btncari2.setBackground(new java.awt.Color(10, 104, 71));
        btncari2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btncari2.setForeground(new java.awt.Color(255, 255, 255));
        btncari2.setText("Cari");
        btncari2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncari2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncari2MouseExited(evt);
            }
        });
        btncari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncari2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Barcode");

        lblbarcode.setBackground(new java.awt.Color(0, 0, 0));
        lblbarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("(Scan disini)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtstokkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtstokmasuk)
                                                .addComponent(txtstokawal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncari2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtexpiredmasuk)
                                        .addComponent(txtexpiredawal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtexpiredawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(txtexpiredmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17)
                                            .addComponent(btncari2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtstokawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(txtstokmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtstokkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel13))
                            .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
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

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel4.setText("Cari Data :");

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

        tbllapstok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Kode Barang", "Nama Barang", "Stok Awal", "Expired", "Stok Masuk", "Expired", "Stok Keluar"
            }
        ));
        tbllapstok.setFont(new java.awt.Font("Myanmar Text", 0, 18)); // NOI18N
        tbllapstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbllapstokMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbllapstok);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1345, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbllapstok.getModel();
        if (txtnamabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi data terlebih dahulu !");
            return;
        }
        if (txtstokawal.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Pastikan stok lebih dari 0 !");
            return;
        }
        try {
        String kodeBarang = txtkdbarang.getText();
        String checkIfExistsSQL = "SELECT COUNT(*) FROM kartu_stok WHERE id_barang = ?";
        Connection connCheck = Config.configDB();
        PreparedStatement pstCheck = connCheck.prepareStatement(checkIfExistsSQL);
        pstCheck.setString(1, kodeBarang);
        ResultSet rsCheck = pstCheck.executeQuery();
        rsCheck.next();

        int count = rsCheck.getInt(1);

        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Kode barang dengan nomor '" + kodeBarang + "' sudah ada. Gunakan kode barang yang berbeda.");
        } else {
            String sql1 = "INSERT INTO kartu_stok VALUES ('"+txtkdbarang.getText()+"','"+txtnamabarang.getText()+"','"+txtstokawal.getText()+"','"+txtexpiredawal.getText()+"','"+txtstokmasuk.getText()+"','"+txtexpiredmasuk.getText()+"','"+txtstokkeluar.getText()+"')";
            Connection conn= Config.configDB();
            java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
            pst1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    load_table();
    Bersih();
    setColumnWidth();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        Bersih();
        setColumnWidth();
        tbllapstok.clearSelection();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        if (txtnamabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan diubah !");
            return;
        }
        try {
            String sql = "UPDATE kartu_stok "+
            "SET stok_awal ='"+txtstokawal.getText()
            +"', expired_awal = '"+txtexpiredawal.getText()+"', stok_masuk = '"+txtstokmasuk.getText()
            +"', expired_masuk = '"+txtexpiredmasuk.getText()+"', stok_keluar = '"+txtstokkeluar.getText()
            +"'WHERE id_barang = '"+txtkdbarang.getText()+"'";
            Connection conn= Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan data gagal"+e.getMessage());
        }
        load_table();
        kosong();
        setColumnWidth();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if (txtnamabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus !");
        } else{
            int jawab = JOptionPane.showConfirmDialog(null, "Data ini akan dihapus, lanjutkan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (jawab == 0) {
                try {
                    Connection conn= Config.configDB();
                    java.sql.Statement stm=conn.createStatement();
                    String sql = "DELETE FROM kartu_stok WHERE id_barang = '" + txtkdbarang.getText() + "'";
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
        load_table();
        kosong();
        setColumnWidth();
    }//GEN-LAST:event_btnhapusActionPerformed

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

    private void txtkdbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkdbarangActionPerformed
        // TODO add your handling code here:
        String kodeBarang = txtkdbarang.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT nama_barang FROM daftar_barang WHERE id_barang = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kodeBarang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtnamabarang.setText(rs.getString("nama_barang"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtnamabarang.requestFocus();
    }//GEN-LAST:event_txtkdbarangActionPerformed

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
        String namaBarang = txtnamabarang.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT stok FROM daftar_barang WHERE nama_barang = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, namaBarang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtstokawal.setText(rs.getString("stok"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtstokawal.requestFocus();
        txtnamabarang.disable();
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void txtstokawalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokawalActionPerformed
        // TODO add your handling code here:
        txtexpiredawal.requestFocus();
        txtstokawal.disable();
    }//GEN-LAST:event_txtstokawalActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        refresh();
        Bersih2();
        lblbarcode.setIcon(null);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btncari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncari2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(true);
        new ListBarang4().setVisible(true);
    }//GEN-LAST:event_btncari2ActionPerformed

    private void btncari2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncari2MouseEntered
        // TODO add your handling code here:
        btncari2.setBackground(new Color(0,89,8));
    }//GEN-LAST:event_btncari2MouseEntered

    private void btncari2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncari2MouseExited
        // TODO add your handling code here:
        btncari2.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btncari2MouseExited

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

    private void tbllapstokMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllapstokMousePressed
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        String s= source.getModel().getValueAt(row, 1) + "";
        String kdbarang = tbllapstok.getValueAt(row,1).toString();
        txtkdbarang.setText(kdbarang);
        txtkdbarang.disable();
        txtstokawal.disable();
        txtnamabarang.disable();

        try {
            String sql = ("SELECT * FROM kartu_stok " + "where id_barang='" + s + "'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()) {
                txtkdbarang.setText(res.getString(1));
                txtnamabarang.setText(res.getString(2));
                txtstokawal.setText(res.getString(3));
                txtexpiredawal.setText(res.getString(4));
                txtstokmasuk.setText(res.getString(5));
                txtexpiredmasuk.setText(res.getString(6));
                txtstokkeluar.setText(res.getString(7));
                ImageIcon imgThisImg = new ImageIcon("\\C:\\Users\\Izzul Islam Ramadhan\\OneDrive"
                    + "\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\BarcodeBarang\\ " + txtkdbarang.getText() + ".png");
                lblbarcode.setIcon(imgThisImg);
            }
            txtexpiredawal.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR \n Data Tidak Ada / Tidak Ditemukan" + e.getMessage());
        }
    }//GEN-LAST:event_tbllapstokMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btncari2;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private com.raven.datechooser.DateChooser date_awal;
    private com.raven.datechooser.DateChooser date_masuk;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblbarcode;
    private Custom.JTable_Custom tbllapstok;
    public static javax.swing.JTextField txtexpiredawal;
    private javax.swing.JTextField txtexpiredmasuk;
    public static javax.swing.JTextField txtkdbarang;
    public static javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtpencarian;
    public static javax.swing.JTextField txtstokawal;
    private javax.swing.JTextField txtstokkeluar;
    private javax.swing.JTextField txtstokmasuk;
    // End of variables declaration//GEN-END:variables
    private void autonumber() {
        try {
            String sql = "SELECT MAX(RIGHT(id_barang,4)) AS no_auto FROM kartu_stok";
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
                txtkdbarang.setText("A"+nol_plus+no_auto);
            }
        } catch (Exception e) {
            txtkdbarang.setText("A0001");
        }
    }
}
