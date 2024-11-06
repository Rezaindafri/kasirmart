/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projectkasir;

import Koneksi.Config;
import com.barcodelib.barcode.Linear;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Izzul Islam Ramadhan
 */
public final class pn_databarang2 extends javax.swing.JPanel {
    /**
     * Creates new form pn_databarang
     */
    
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;
    
    public pn_databarang2() {
        initComponents();
        load_table();
        kosong();
        Bersih();
        autonumber();
        setBackground(new Color(255,255,255));
        lblbarcode.setBackground(new Color(0,0,0));
        
        tbldatabarang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbldatabarang.getTableHeader().setOpaque(false);
        tbldatabarang.setBackground(new Color(122,186,120));
        tbldatabarang.setForeground(new Color(0,0,0));
        tbldatabarang.setRowHeight(40);
        txtstok.setText("0");
        txtstok.disable();
        setColumnWidth();
    }
    
    private void load_table(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Stok");
        model.addColumn("Supplier");
        
        String cari = txtpencarian.getText();
        try {
            int no=1;
            String sql = ("SELECT * FROM daftar_barang WHERE id_barang LIKE '%" + cari + "%' OR nama_barang LIKE '%" + cari + "%'"
                    + "OR harga_beli LIKE '%" + cari + "%' OR harga_jual LIKE '%" + cari + "%' OR stok LIKE '%" + cari + "%' OR nama_supplier LIKE '%" + cari + "%'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()) {
                model.addRow(new Object[]{no++,res.getString(1),res.getString(2),res.getString(3)
                        ,res.getString(4),res.getString(5),res.getString(6)});
            }
            tbldatabarang.setModel(model);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private void kosong () {
        txtkdbarang.enable();
        txtstok.enable();
        txtnamabarang.setText(null);
        txthargabeli.setText(null);
        txthargajual.setText(null);
        txtstok.setText(null);
        txtsupplier.setText(null);
    }
    
    private void Bersih(){
        txtnamabarang.setText("");
        txthargabeli.setText("");
        txthargajual.setText("");
        txtsupplier.setText("");
        
        btnsimpan.setText("Simpan");
        txtkdbarang.setEditable(true);
        txtstok.setText("0");
        txtkdbarang.disable();
        txtnamabarang.requestFocus();
        autonumber();
    }
    
    private void refresh(){
        txtpencarian.setText("");
        load_table();
        setColumnWidth();
    }
    
    private void setColumnWidth() {
        TableColumnModel columnModel = tbldatabarang.getColumnModel();
        columnModel.getColumn(noColumnIndex).setPreferredWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMaxWidth(noColumnWidth);
        columnModel.getColumn(noColumnIndex).setMinWidth(noColumnWidth);
        tbldatabarang.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbldatabarang.getColumnModel().getColumn(1).setMaxWidth(150);
        tbldatabarang.getColumnModel().getColumn(1).setMinWidth(150);
        tbldatabarang.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbldatabarang.getColumnModel().getColumn(2).setMaxWidth(300);
        tbldatabarang.getColumnModel().getColumn(2).setMinWidth(300);
    }
    
    /* public void valueChanged(ListSelectionEvent e) {
        if (tbldatabarang.getSelectedRow() != -1) {
            String kodeBarang = tbldatabarang.getValueAt(tbldatabarang.getSelectedRow(), 1).toString();
            
            String filePath = "\\C:\\Users\\Izzul Islam Ramadhan\\OneDrive\\Dokumen"
                    + "\\NetBeansProjects\\ProjectKasir\\src\\Image\\" + kodeBarang + ".png";
            
            File file = new File(filePath);
            if (file.exists()) {
                ImageIcon imgThisImg = new ImageIcon(filePath);
                lblbarcode.setIcon(imgThisImg);
            } else {
                lblbarcode.setText("Barcode tidak ditemukan");
            }
        }
    } */

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
        txtkdbarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txthargabeli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txthargajual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtstok = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtsupplier = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblbarcode = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldatabarang = new Custom.JTable_Custom();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btncari = new javax.swing.JButton();
        txtpencarian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Kode Barang");

        jLabel3.setText("Nama Barang");

        jLabel5.setText("Harga Beli");

        jLabel6.setText("Harga Jual");

        jLabel7.setText("Stok");

        jLabel10.setText("Supplier");

        txtsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupplierActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(10, 104, 71));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Data Barang");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Master Data > Barang");

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        lblbarcode.setBackground(new java.awt.Color(0, 0, 0));
        lblbarcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Barcode");

        tbldatabarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Kode Barang", "Nama Barang", "Harga Jual", "Harga Beli", "Stok", "Supplier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldatabarang.setFont(new java.awt.Font("Myanmar Text", 0, 12)); // NOI18N
        tbldatabarang.setSelectionBackground(new java.awt.Color(10, 104, 71));
        tbldatabarang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tbldatabarang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbldatabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbldatabarangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbldatabarang);

        btnhapus.setBackground(new java.awt.Color(122, 186, 120));
        btnhapus.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.setBorder(null);
        btnhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhapus.setPreferredSize(new java.awt.Dimension(80, 40));
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
        btnbatal.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnbatal.setText("Batal");
        btnbatal.setBorder(null);
        btnbatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbatal.setPreferredSize(new java.awt.Dimension(80, 40));
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
        btnedit.setText("Ubah");
        btnedit.setBorder(null);
        btnedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnedit.setPreferredSize(new java.awt.Dimension(80, 40));
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

        btnsimpan.setBackground(new java.awt.Color(122, 186, 120));
        btnsimpan.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.setBorder(null);
        btnsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnsimpan.setPreferredSize(new java.awt.Dimension(80, 40));
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnrefreshMousePressed(evt);
            }
        });
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

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

        txtpencarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpencarianMouseClicked(evt);
            }
        });
        txtpencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpencarianActionPerformed(evt);
            }
        });
        txtpencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpencarianKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpencarianKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Cari Data :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtpencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnrefresh)
                        .addGap(88, 88, 88)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txthargabeli, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtkdbarang)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txthargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jLabel12))
                            .addComponent(txtsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtkdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txthargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txthargabeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblbarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpencarian)
                    .addComponent(btncari)
                    .addComponent(btnrefresh)
                    .addComponent(jLabel4)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        if (txtnamabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan isi data terlebih dahulu !");
            return;
        }
        try {
        String namaBarang = txtnamabarang.getText();
        String checkIfExistsSQL = "SELECT COUNT(*) FROM daftar_barang WHERE nama_barang = ?";
        Connection connCheck = Config.configDB();
        PreparedStatement pstCheck = connCheck.prepareStatement(checkIfExistsSQL);
        pstCheck.setString(1, namaBarang);
        ResultSet rsCheck = pstCheck.executeQuery();
        generate(String.valueOf(txtkdbarang.getText()));
        rsCheck.next();

        int count = rsCheck.getInt(1);

        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Nama barang '" + namaBarang + "' sudah ada. Gunakan nama barang yang berbeda.");
        } else {
            String insertSQL = "INSERT INTO daftar_barang VALUES (?, ?, ?, ?, ?, ?)";
            Connection conn = Config.configDB();
            PreparedStatement pstInsert = conn.prepareStatement(insertSQL);
            pstInsert.setString(1, txtkdbarang.getText());
            pstInsert.setString(2, namaBarang);
            pstInsert.setString(3, txthargabeli.getText());
            pstInsert.setString(4, txthargajual.getText());
            pstInsert.setString(5, txtstok.getText());
            pstInsert.setString(6, txtsupplier.getText());

            pstInsert.executeUpdate();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
        load_table();
        Bersih();
        autonumber();
        setColumnWidth();
        String kb = String.valueOf(txtkdbarang.getText());
        try {
            generate(kb);
        } catch (Exception ex) {
            Logger.getLogger(pn_databarang2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

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
                    String sql = "DELETE FROM daftar_barang WHERE id_barang = '" + txtkdbarang.getText() + "'";
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

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        Bersih();
        tbldatabarang.clearSelection();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        if (txtnamabarang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan diubah !");
            return;
        }
        try {
            String sql = "UPDATE daftar_barang "+
            "SET nama_barang ='"+txtnamabarang.getText()
            +"', harga_beli = '"+txthargabeli.getText()+"', harga_jual = '"+txthargajual.getText()
            +"', stok = '"+txtstok.getText()+"', nama_supplier = '"+txtsupplier.getText()+"'WHERE id_barang = '"+txtkdbarang.getText()+"'";
            Connection conn= Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        load_table();
        kosong();
        autonumber();
        setColumnWidth();
    }//GEN-LAST:event_btneditActionPerformed

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

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        refresh();
        Bersih();
        lblbarcode.setIcon(null);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void txtpencarianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpencarianKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpencarianKeyTyped

    private void txtpencarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpencarianMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpencarianMouseClicked

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

    private void btnrefreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrefreshMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnrefreshMousePressed

    private void btnrefreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnrefreshMouseExited
        // TODO add your handling code here:
        btnrefresh.setBackground(new Color(10,104,71));
    }//GEN-LAST:event_btnrefreshMouseExited

    private void txtsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupplierActionPerformed

    private void tbldatabarangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldatabarangMousePressed
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        String s= source.getModel().getValueAt(row, 1) + "";
        try {
            String sql = ("SELECT * FROM daftar_barang " + "where id_barang='" + s + "'");
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()) {
                txtkdbarang.setText(res.getString(1));
                txtkdbarang.enable(false);
                txtnamabarang.setText(res.getString("nama_barang"));
                txthargabeli.setText(res.getString(3));
                txthargajual.setText(res.getString(4));
                txtstok.setText(res.getString(5));
                txtsupplier.setText(res.getString(6));
                ImageIcon imgThisImg = new ImageIcon("\\C:\\Users\\Izzul Islam Ramadhan\\OneDrive"
                    + "\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\BarcodeBarang\\ " + txtkdbarang.getText() + ".png");
                lblbarcode.setIcon(imgThisImg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR \n Data Tidak Ada / Tidak Ditemukan" + e.getMessage());
        }
    }//GEN-LAST:event_tbldatabarangMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblbarcode;
    private Custom.JTable_Custom tbldatabarang;
    private javax.swing.JTextField txthargabeli;
    private javax.swing.JTextField txthargajual;
    private javax.swing.JTextField txtkdbarang;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtpencarian;
    private javax.swing.JTextField txtstok;
    private javax.swing.JTextField txtsupplier;
    // End of variables declaration//GEN-END:variables
    private void autonumber() {
        try {
            String sql = "SELECT MAX(RIGHT(id_barang,4)) AS no_auto FROM daftar_barang";
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
        txtkdbarang.disable();
    }

    private void pencarian() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void generate(String kode) throws Exception {
        try {
            Linear barcode = new Linear();
            
            barcode.setData(kode);
            
            barcode.setType(Linear.CODE128A);
            
            barcode.setX(3f);
            barcode.setY(75f);
            
            barcode.setLeftMargin(0f);
            barcode.setRightMargin(0f);
            barcode.setTopMargin(0f);
            barcode.setBottomMargin(0f);
            
            barcode.setResolution(72);
            
            String fileName = txtkdbarang.getText();
            barcode.renderBarcode(("\\C:\\Users\\Izzul Islam Ramadhan\\OneDrive"
                    + "\\Dokumen\\NetBeansProjects\\ProjectKasir\\src\\BarcodeBarang\\ " + fileName + ".png"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak barcode " + ex.toString());
        }
    }
}
