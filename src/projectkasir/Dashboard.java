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
import java.util.HashMap; //Bisa digunakan untuk menyimpan data konfigurasi, temporary data, dll.
import java.util.logging.Level; //digunakan untuk merepresentasikan tingkat keparahan pesan log.
import java.util.logging.Logger; //digunakan untuk mencatat pesan log.
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane; //digunakan untuk menampilkan dialog message boxes kepada user.
import javax.swing.table.TableColumnModel; //untuk mengatur tampilan dan behavior kolom-kolom dalam tabel GUI.
import net.sf.jasperreports.engine.JasperCompileManager; //digunakan untuk membuat laporan dari data.
import net.sf.jasperreports.engine.JasperFillManager; //digunakan untuk mengisi data ke dalam file report yang sudah dikompilasi (.jasper).
import net.sf.jasperreports.engine.JasperPrint; //mewakili hasil akhir dari pengisian data ke dalam report template
import net.sf.jasperreports.engine.JasperReport; //Bisa diisi dengan data menggunakan JasperFillManager untuk menghasilkan report akhir.
import net.sf.jasperreports.view.JasperViewer; //Ini mengimpor class JasperView dari library Jasper.

/**
 *
 * @author Izzul Islam Ramadhan
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    private final Connection conn; 
    private final int noColumnIndex = 0;
    private final int noColumnWidth = 50;
    
    public Dashboard() throws SQLException {
        initComponents();
        conn= Config.configDB();
        load_table1();
        load_table2();
        lblakun.setText(jml_akun());
        lblbarang.setText(jml_barang());
        lblsupplier.setText(jml_supplier());
        lblpenjualan.setText(jml_penjualan());
        lblpembelian.setText(jml_pembelian());
        txttanggal.hide();
        txttanggal2.hide();
        setColumnWidth();
        
        tbllappenjualan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllappenjualan.getTableHeader().setOpaque(false);
        tbllappenjualan.setBackground(new Color(32,136,203));
        tbllappenjualan.setForeground(new Color(255,255,255));
        tbllappenjualan.setRowHeight(35);
        tbllappembelian.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbllappembelian.getTableHeader().setOpaque(false);
        tbllappembelian.setBackground(new Color(32,136,203));
        tbllappembelian.setForeground(new Color(255,255,255));
        tbllappembelian.setRowHeight(35);
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
        jPanel2 = new javax.swing.JPanel();
        lbljudul = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblakun = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblstokkeluar = new javax.swing.JLabel();
        lblsupplier = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblstokkeluar2 = new javax.swing.JLabel();
        lblbarang = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblstokkeluar4 = new javax.swing.JLabel();
        lblpenjualan = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblstokkeluar5 = new javax.swing.JLabel();
        lblpembelian = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbljmlpenjualan = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbllappenjualan = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txttanggal2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lbljmlpengeluaran = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllappembelian = new javax.swing.JTable();

        cari_tanggal.setForeground(new java.awt.Color(29, 0, 174));
        cari_tanggal.setDateFormat("yyyy-MM-dd");
        cari_tanggal.setFocusable(false);
        cari_tanggal.setTextRefernce(txttanggal);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(32, 136, 203));
        jPanel2.setPreferredSize(new java.awt.Dimension(230, 135));

        lbljudul.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        lbljudul.setForeground(new java.awt.Color(255, 255, 255));
        lbljudul.setText("AKUN");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N

        lblakun.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblakun.setForeground(new java.awt.Color(255, 255, 255));
        lblakun.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbljudul)
                    .addComponent(lblakun, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbljudul, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblakun, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(32, 136, 203));
        jPanel3.setPreferredSize(new java.awt.Dimension(230, 135));

        lblstokkeluar.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        lblstokkeluar.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar.setText("SUPPLIER");

        lblsupplier.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblsupplier.setForeground(new java.awt.Color(255, 255, 255));
        lblsupplier.setText("0");

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\global.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblstokkeluar))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblstokkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Master Data > Dashboard");

        jPanel6.setBackground(new java.awt.Color(32, 136, 203));
        jPanel6.setPreferredSize(new java.awt.Dimension(230, 135));

        lblstokkeluar2.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        lblstokkeluar2.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar2.setText("BARANG");

        lblbarang.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblbarang.setForeground(new java.awt.Color(255, 255, 255));
        lblbarang.setText("0");

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\package.png")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstokkeluar2)
                    .addComponent(lblbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblstokkeluar2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(32, 136, 203));
        jPanel8.setPreferredSize(new java.awt.Dimension(230, 135));

        lblstokkeluar4.setFont(new java.awt.Font("Rockwell", 1, 22)); // NOI18N
        lblstokkeluar4.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar4.setText("PENJUALAN");

        lblpenjualan.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblpenjualan.setForeground(new java.awt.Color(255, 255, 255));
        lblpenjualan.setText("0");

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\trend.png")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblstokkeluar4))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblstokkeluar4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(32, 136, 203));
        jPanel9.setPreferredSize(new java.awt.Dimension(230, 135));

        lblstokkeluar5.setFont(new java.awt.Font("Rockwell", 1, 22)); // NOI18N
        lblstokkeluar5.setForeground(new java.awt.Color(255, 255, 255));
        lblstokkeluar5.setText("PEMBELIAN");

        lblpembelian.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lblpembelian.setForeground(new java.awt.Color(255, 255, 255));
        lblpembelian.setText("0");

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Izzul Islam Ramadhan\\Downloads\\order.png")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstokkeluar5)
                    .addComponent(lblpembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblstokkeluar5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblpembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9)))
                .addGap(14, 14, 14))
        );

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ringkasan");

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jPanel4.setBackground(new java.awt.Color(32, 136, 203));
        jPanel4.setPreferredSize(new java.awt.Dimension(230, 135));

        lbljmlpenjualan.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lbljmlpenjualan.setForeground(new java.awt.Color(255, 255, 255));
        lbljmlpenjualan.setText("Jumlah Pemasukan");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbljmlpenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbljmlpenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 244, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));

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

        jPanel5.setBackground(new java.awt.Color(32, 136, 203));
        jPanel5.setPreferredSize(new java.awt.Dimension(230, 135));

        lbljmlpengeluaran.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 40)); // NOI18N
        lbljmlpengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        lbljmlpengeluaran.setText("Jumlah Pengeluaran");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbljmlpengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbljmlpengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

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
        tbllappembelian.setRowHeight(25);
        tbllappembelian.setSelectionBackground(new java.awt.Color(255, 0, 51));
        tbllappembelian.setShowHorizontalLines(true);
        tbllappembelian.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbllappembelian);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 256, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 784, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11))
                    .addComponent(jLabel12))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(30, 30, 30)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.datechooser.DateChooser cari_tanggal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblakun;
    private javax.swing.JLabel lblbarang;
    private javax.swing.JLabel lbljmlpengeluaran;
    private javax.swing.JLabel lbljmlpenjualan;
    private javax.swing.JLabel lbljudul;
    private javax.swing.JLabel lblpembelian;
    private javax.swing.JLabel lblpenjualan;
    private javax.swing.JLabel lblstokkeluar;
    private javax.swing.JLabel lblstokkeluar2;
    private javax.swing.JLabel lblstokkeluar4;
    private javax.swing.JLabel lblstokkeluar5;
    private javax.swing.JLabel lblsupplier;
    private javax.swing.JTable tbllappembelian;
    private javax.swing.JTable tbllappenjualan;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttanggal2;
    // End of variables declaration//GEN-END:variables

    private String jml_akun()throws SQLException {
            String sql = "SELECT COUNT(*) AS jumlah FROM akun";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int jumlah = res.getInt("jumlah");
            
            Label lblakun = new Label("Jumlah data: ");
            String data_jumlah = String.valueOf(jumlah);

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

    private String jml_supplier()throws SQLException {
        String sql = "SELECT COUNT(*) AS jumlah FROM supplier";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int jumlah = res.getInt("jumlah");
            
            Label lblsupplier = new Label("Jumlah data: ");
            String data_jumlah = String.valueOf(jumlah);

            return data_jumlah;
        }

    private String jml_penjualan()throws SQLException {
        String sql = "SELECT COUNT(*) AS jumlah FROM transaksi";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int jumlah = res.getInt("jumlah");
            
            Label lblpenjualan = new Label("Jumlah data: ");
            String data_jumlah = String.valueOf(jumlah);

            return data_jumlah;
        }

    private String jml_pembelian()throws SQLException {
        String sql = "SELECT COUNT(*) AS jumlah FROM pembelian";
            java.sql.Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            res.next();
            int jumlah = res.getInt("jumlah");
            
            Label lblpembelian = new Label("Jumlah data: ");
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