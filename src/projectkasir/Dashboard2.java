/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package projectkasir;

import Koneksi.Config; //digunakan untuk mengimpor kelas Config dari paket Koneksi
import java.awt.Color; //untuk mengatur warna untuk elemen-elemen GUI seperti teks, background, border, dll
import java.awt.Font; //untuk mengatur gaya dan ukuran font untuk elemen-elemen GUI
import java.sql.Connection; //digunakan untuk mewakili koneksi ke database
import java.sql.SQLException; //digunakan untuk mewakili pengecualian yang terjadi selama operasi database SQL
import javax.swing.table.DefaultTableModel; //digunakan untuk membuat dan mengelola model data untuk tabel Swing.
import java.awt.Label; //digunakan untuk menampilkan teks pada form atau panel Swing.
import java.sql.ResultSet; //digunakan saat kita menggunakan statement select ke database
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Izzul Islam Ramadhan
 */
public class Dashboard2 extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    private final Connection conn; 
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;
    
    public Dashboard2() throws SQLException {
        initComponents();
        conn= Config.configDB();
        load_table1();
        load_table2();
        load_table3();
        lblexpired.setText(tampilJumlahBarangExpired());
        lbldatabarang.setText(jml_barang());
        txttanggal.hide();
        txttanggal2.hide();
        setColumnWidth();
        
        tbllappenjualan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllappenjualan.getTableHeader().setOpaque(false);
        tbllappenjualan.setBackground(new Color(0,82,51));
        tbllappenjualan.setForeground(new Color(255,255,255));
        tbllappenjualan.setRowHeight(35);
        tbllappembelian.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllappembelian.getTableHeader().setOpaque(false);
        tbllappembelian.setBackground(new Color(0,82,51));
        tbllappembelian.setForeground(new Color(255,255,255));
        tbllappembelian.setRowHeight(35);
        tblexpired.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tblexpired.getTableHeader().setOpaque(false);
        tblexpired.setBackground(new Color(0,82,51));
        tblexpired.setForeground(new Color(255,255,255));
        tblexpired.setRowHeight(35);
        String totalmasuk = jml_pemasukan(txttanggal.getText());
        lbljmlpenjualan.setText(totalmasuk);
        
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        txttanggal.setText(s.format(date));
        txttanggal.disable();
        txttanggal2.setText(s.format(date));
        txttanggal2.disable();
        String totalkeluar = jml_pengeluaran(txttanggal2.getText());
        lbljmlpengeluaran.setText(totalkeluar);
    }
    
    private void load_table1(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No.Transaksi");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Subtotal");
        
        String tanggal = txttanggal.getText();
        try {
            String query = "SELECT transaksi.id_transaksi, detail_transaksi.nama_barang, detail_transaksi.harga_barang, detail_transaksi.jumlah_barang, detail_transaksi.subtotal " +
                "FROM transaksi INNER JOIN detail_transaksi ON transaksi.id_transaksi = detail_transaksi.id_transaksi " + "WHERE transaksi.tgl_transaksi LIKE '%" + tanggal + "%'";
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            ResultSet res=stm.executeQuery(query);
            while(res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)});
            }
            tbllappenjualan.setModel(model);
            tbllappenjualan.disable();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private void load_table2() throws SQLException{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No. Pembelian");
        model.addColumn("Supplier");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Subtotal");
        
        String tanggal2 = txttanggal.getText();
        try {
            String query = "SELECT pembelian.id_pembelian, detail_pembelian.nama_supplier, detail_pembelian.nama_barang, detail_pembelian.harga_barang, detail_pembelian.jumlah_barang, detail_pembelian.subtotal " +
                "FROM pembelian INNER JOIN detail_pembelian ON pembelian.id_pembelian = detail_pembelian.id_pembelian " + "WHERE pembelian.tgl_pembelian LIKE '%" + tanggal2 + "%'";
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            while(res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
            }
            tbllappembelian.setModel(model);
            tbllappembelian.disable();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private void load_table3() throws SQLException{
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Tanggal Expired");
        
        try {
            String query = "SELECT id_barang, nama_barang, expired_awal FROM kartu_stok WHERE expired_awal >= CURRENT_DATE() AND expired_awal <= CURRENT_DATE() + INTERVAL 14 DAY";
            Connection conn= Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(query);
            while(res.next()) {
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3)});
            }
            tblexpired.setModel(model);
            tblexpired.disable();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    private void setColumnWidth() {
        tbllappenjualan.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbllappenjualan.getColumnModel().getColumn(1).setMaxWidth(200);
        tbllappenjualan.getColumnModel().getColumn(1).setMinWidth(200);
        tbllappembelian.getColumnModel().getColumn(0).setPreferredWidth(140);
        tbllappembelian.getColumnModel().getColumn(0).setMaxWidth(140);
        tbllappembelian.getColumnModel().getColumn(0).setMinWidth(140);
        tbllappembelian.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbllappembelian.getColumnModel().getColumn(1).setMaxWidth(150);
        tbllappembelian.getColumnModel().getColumn(1).setMinWidth(150);
        tbllappembelian.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbllappembelian.getColumnModel().getColumn(2).setMaxWidth(200);
        tbllappembelian.getColumnModel().getColumn(2).setMinWidth(200);
        tblexpired.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblexpired.getColumnModel().getColumn(0).setMaxWidth(150);
        tblexpired.getColumnModel().getColumn(0).setMinWidth(150);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cari_tanggal = new com.raven.datechooser.DateChooser();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbllappenjualan = new javax.swing.JTable();
        panelCustom3 = new Custom.PanelCustom();
        lbljmlpenjualan = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txttanggal2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllappembelian = new javax.swing.JTable();
        panelCustom4 = new Custom.PanelCustom();
        lbljmlpengeluaran = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblexpired = new javax.swing.JTable();
        panelCustom1 = new Custom.PanelCustom();
        lblstokkeluar6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbldatabarang = new javax.swing.JLabel();
        panelCustom2 = new Custom.PanelCustom();
        lblexpired = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblstokkeluar5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        cari_tanggal.setForeground(new java.awt.Color(29, 0, 174));
        cari_tanggal.setDateFormat("yyyy-MM-dd");
        cari_tanggal.setFocusable(false);
        cari_tanggal.setTextRefernce(txttanggal);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Master Data > Dashboard");

        jPanel1.setBackground(new java.awt.Color(122, 186, 120));

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pemasukan hari ini :");

        txttanggal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        tbllappenjualan.setBackground(new java.awt.Color(180, 230, 253));
        tbllappenjualan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbllappenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. Transaksi", "Nama Barang", "Harga Barang", "Jumlah", "Subtotal"
            }
        ));
        tbllappenjualan.setFocusable(false);
        tbllappenjualan.setGridColor(new java.awt.Color(255, 255, 255));
        tbllappenjualan.setRowHeight(25);
        tbllappenjualan.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tbllappenjualan.setShowHorizontalLines(true);
        tbllappenjualan.getTableHeader().setReorderingAllowed(false);
        tbllappenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllappenjualanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbllappenjualan);
        if (tbllappenjualan.getColumnModel().getColumnCount() > 0) {
            tbllappenjualan.getColumnModel().getColumn(0).setHeaderValue("No.");
        }

        panelCustom3.setBackground(new java.awt.Color(0, 82, 51));
        panelCustom3.setRoundBottomLeft(20);
        panelCustom3.setRoundBottomRight(20);
        panelCustom3.setRoundTopLeft(20);
        panelCustom3.setRoundTopRight(20);

        lbljmlpenjualan.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lbljmlpenjualan.setForeground(new java.awt.Color(255, 255, 255));
        lbljmlpenjualan.setText("Jumlah Pemasukan");

        javax.swing.GroupLayout panelCustom3Layout = new javax.swing.GroupLayout(panelCustom3);
        panelCustom3.setLayout(panelCustom3Layout);
        panelCustom3Layout.setHorizontalGroup(
            panelCustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbljmlpenjualan)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        panelCustom3Layout.setVerticalGroup(
            panelCustom3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbljmlpenjualan)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(panelCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(122, 186, 120));

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pengeluaran hari ini:");

        txttanggal2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttanggal2.setText("Tanggal");
        txttanggal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggal2ActionPerformed(evt);
            }
        });

        tbllappembelian.setBackground(new java.awt.Color(180, 230, 253));
        tbllappembelian.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbllappembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Pembelian", "Supplier", "Nama Barang", "Harga Barang", "Jumlah", "Subtotal"
            }
        ));
        tbllappembelian.setFocusable(false);
        tbllappembelian.setGridColor(new java.awt.Color(255, 255, 255));
        tbllappembelian.setRowHeight(25);
        tbllappembelian.setSelectionBackground(new java.awt.Color(255, 0, 51));
        tbllappembelian.setShowHorizontalLines(true);
        tbllappembelian.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbllappembelian);

        panelCustom4.setBackground(new java.awt.Color(0, 82, 51));
        panelCustom4.setRoundBottomLeft(20);
        panelCustom4.setRoundBottomRight(20);
        panelCustom4.setRoundTopLeft(20);
        panelCustom4.setRoundTopRight(20);

        lbljmlpengeluaran.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lbljmlpengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        lbljmlpengeluaran.setText("Jumlah Pengeluaran");

        javax.swing.GroupLayout panelCustom4Layout = new javax.swing.GroupLayout(panelCustom4);
        panelCustom4.setLayout(panelCustom4Layout);
        panelCustom4Layout.setHorizontalGroup(
            panelCustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbljmlpengeluaran)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        panelCustom4Layout.setVerticalGroup(
            panelCustom4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbljmlpengeluaran)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelCustom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCustom4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(122, 186, 120));

        tblexpired.setBackground(new java.awt.Color(180, 230, 253));
        tblexpired.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblexpired.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Tanggal Expired"
            }
        ));
        tblexpired.setFocusable(false);
        tblexpired.setGridColor(new java.awt.Color(255, 255, 255));
        tblexpired.setRowHeight(25);
        tblexpired.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tblexpired.setShowHorizontalLines(true);
        tblexpired.getTableHeader().setReorderingAllowed(false);
        tblexpired.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblexpiredMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblexpired);

        panelCustom1.setBackground(new java.awt.Color(0, 82, 51));
        panelCustom1.setRoundBottomLeft(20);
        panelCustom1.setRoundBottomRight(20);
        panelCustom1.setRoundTopLeft(20);
        panelCustom1.setRoundTopRight(20);

        lblstokkeluar6.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        lblstokkeluar6.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar6.setText("Jumlah Data Barang");

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\package.png")); // NOI18N

        lbldatabarang.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lbldatabarang.setForeground(new java.awt.Color(255, 255, 255));
        lbldatabarang.setText("0");

        javax.swing.GroupLayout panelCustom1Layout = new javax.swing.GroupLayout(panelCustom1);
        panelCustom1.setLayout(panelCustom1Layout);
        panelCustom1Layout.setHorizontalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstokkeluar6)
                    .addComponent(lbldatabarang))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        panelCustom1Layout.setVerticalGroup(
            panelCustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(26, 26, 26))
            .addGroup(panelCustom1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblstokkeluar6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbldatabarang)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panelCustom2.setBackground(new java.awt.Color(0, 82, 51));
        panelCustom2.setRoundBottomLeft(20);
        panelCustom2.setRoundBottomRight(20);
        panelCustom2.setRoundTopLeft(20);
        panelCustom2.setRoundTopRight(20);

        lblexpired.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblexpired.setForeground(new java.awt.Color(255, 255, 255));
        lblexpired.setText("0");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/expired.png"))); // NOI18N

        lblstokkeluar5.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        lblstokkeluar5.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar5.setText("Barang yang akan expired");

        javax.swing.GroupLayout panelCustom2Layout = new javax.swing.GroupLayout(panelCustom2);
        panelCustom2.setLayout(panelCustom2Layout);
        panelCustom2Layout.setHorizontalGroup(
            panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustom2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(25, 25, 25)
                .addGroup(panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstokkeluar5)
                    .addComponent(lblexpired))
                .addGap(30, 30, 30))
        );
        panelCustom2Layout.setVerticalGroup(
            panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustom2Layout.createSequentialGroup()
                .addGroup(panelCustom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustom2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6))
                    .addGroup(panelCustom2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblstokkeluar5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblexpired)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(panelCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("RINGKASAN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbllappenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllappenjualanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllappenjualanMouseClicked

    private void txttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttanggalActionPerformed

    private void txttanggal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggal2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttanggal2ActionPerformed

    private void tblexpiredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblexpiredMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblexpiredMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser cari_tanggal;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbldatabarang;
    private javax.swing.JLabel lblexpired;
    private javax.swing.JLabel lbljmlpengeluaran;
    private javax.swing.JLabel lbljmlpenjualan;
    private javax.swing.JLabel lblstokkeluar5;
    private javax.swing.JLabel lblstokkeluar6;
    private Custom.PanelCustom panelCustom1;
    private Custom.PanelCustom panelCustom2;
    private Custom.PanelCustom panelCustom3;
    private Custom.PanelCustom panelCustom4;
    private javax.swing.JTable tblexpired;
    private javax.swing.JTable tbllappembelian;
    private javax.swing.JTable tbllappenjualan;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttanggal2;
    // End of variables declaration//GEN-END:variables
    private String tampilJumlahBarangExpired() throws SQLException {
        String sql = "SELECT COUNT(*) AS jumlah_barang FROM kartu_stok WHERE expired_awal >= CURRENT_DATE() AND expired_awal <= CURRENT_DATE() + INTERVAL 14 DAY";
        java.sql.PreparedStatement pernyataan = conn.prepareStatement(sql);
        ResultSet hasil = pernyataan.executeQuery();
        hasil.next(); 
        int expired = hasil.getInt("jumlah_barang");
        
        Label lblexpired = new Label("Jumlah data: ");
        String data_jumlah = String.valueOf(expired);
        
        return data_jumlah;
    }
    private String jml_barang()throws SQLException {
        String sql = "SELECT COUNT(*) AS jumlah FROM daftar_barang";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int jumlah = res.getInt("jumlah");
            
            Label lblbarang = new Label("Jumlah data: ");
            String data_jumlah = String.valueOf(jumlah);

            return data_jumlah;
        }
    private String jml_pemasukan(String text)throws SQLException {
            String tanggal = txttanggal.getText(); //Mengambil teks kriteria tanggal transaksi dari komponen txtpencarian
            String sql = "SELECT SUM(transaksi.total) AS total FROM transaksi  WHERE tgl_transaksi LIKE '%" + tanggal + "%'"; //Membuat kueri SQL untuk menghitung total jumlah pembelian berdasarkan kriteria tanggal transaksi.
            java.sql.Statement stm = conn.createStatement(); //Melakukan eksekusi kueri SQL menggunakan objek Statement.
            ResultSet res = stm.executeQuery(sql); //Membaca hasil kueri SQL menggunakan objek ResultSet.
            
            res.next(); 
            int jumlah = res.getInt("total"); //Mengambil nilai total jumlah pembelian dari hasil kueri SQL
            
            Label lbljmlpenjualan = new Label("total pemasukan: ");
            String data_jumlah = formatRupiah(jumlah); //Mengkonversi nilai total jumlah pembelian ke dalam bentuk String.

            return data_jumlah; //Mengembalikan nilai total jumlah pembelian dalam bentuk String.
        }
    private String jml_pengeluaran(String text)throws SQLException {
            String tanggal2 = txttanggal2.getText();
            String sql = "SELECT SUM(pembelian.total) AS total FROM pembelian  WHERE tgl_pembelian LIKE '%" + tanggal2 + "%'";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int hasil = res.getInt("total");
            
            Label lbljmlpengeluaran = new Label("total pengeluaran: ");
            String data_hasil = formatRupiah(hasil);

            return data_hasil;
        }
        private String formatRupiah(int amount) {
        NumberFormat rupiahFormat = new DecimalFormat("#,###,###,###");
        return "Rp " + rupiahFormat.format(amount);
        }
}