/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectkasir;

import Koneksi.Config;
import java.awt.Color;
import java.awt.PopupMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Izzul Islam Ramadhan
 */
public class Login2 extends javax.swing.JFrame {
    /**
     * Creates new form NewJFrame
     */
    public Login2() {
        initComponents();
        btn_hide1.setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnlogin = new javax.swing.JButton();
        btn_show1 = new javax.swing.JLabel();
        btn_hide1 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        btnlogout = new javax.swing.JLabel();
        btnregister2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpanelgradient_11 = new jpanelgradient.jpanelgradient_1();
        jLabel3 = new javax.swing.JLabel();
        pn_btnclose3 = new javax.swing.JPanel();
        btnclose3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 3, 14)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(769, 527));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Silahkan masukkan akun anda");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        txtusername.setBackground(new java.awt.Color(230, 227, 227));
        txtusername.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        txtusername.setForeground(new java.awt.Color(102, 102, 102));
        txtusername.setText("*Masukkan username");
        txtusername.setAutoscrolls(false);
        txtusername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtusername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtusername.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtusername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtusernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtusernameFocusLost(evt);
            }
        });
        txtusername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtusernameMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtusernameMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtusernameMouseReleased(evt);
            }
        });
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });
        txtusername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtusernameKeyTyped(evt);
            }
        });
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 330, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Kembali!");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        btnlogin.setBackground(new java.awt.Color(0, 153, 0));
        btnlogin.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        btnlogin.setForeground(new java.awt.Color(255, 255, 255));
        btnlogin.setText("LOGIN");
        btnlogin.setBorder(null);
        btnlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnlogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnloginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnloginMouseExited(evt);
            }
        });
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        jPanel1.add(btnlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 330, 60));

        btn_show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hidden.png"))); // NOI18N
        btn_show1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_show1MouseClicked(evt);
            }
        });
        jPanel1.add(btn_show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 40, 40));

        btn_hide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/show.png"))); // NOI18N
        btn_hide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hide1MouseClicked(evt);
            }
        });
        jPanel1.add(btn_hide1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 40, 40));

        txtpassword.setBackground(new java.awt.Color(230, 230, 230));
        txtpassword.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(102, 102, 102));
        txtpassword.setText("Masukkanpassword");
        txtpassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtpassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpasswordFocusLost(evt);
            }
        });
        txtpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpasswordKeyTyped(evt);
            }
        });
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 330, 40));

        btnlogout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnlogout.setForeground(new java.awt.Color(102, 102, 102));
        btnlogout.setText("Lupa Password?");
        btnlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnlogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnlogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnlogoutMouseExited(evt);
            }
        });
        jPanel1.add(btnlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, -1));

        btnregister2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnregister2.setForeground(new java.awt.Color(32, 136, 203));
        btnregister2.setText("Register");
        btnregister2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregister2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnregister2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnregister2MouseExited(evt);
            }
        });
        jPanel1.add(btnregister2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 600, 80, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Belum memiliki akun?");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Selamat Datang ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Username");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jpanelgradient_11.setColorEnd(new java.awt.Color(24, 252, 0));
        jpanelgradient_11.setColorStart(new java.awt.Color(0, 100, 0));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/okok-removebg-preview.png"))); // NOI18N

        pn_btnclose3.setBackground(new java.awt.Color(0, 153, 0));
        pn_btnclose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_btnclose3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pn_btnclose3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pn_btnclose3MouseExited(evt);
            }
        });

        btnclose3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnclose3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnclose3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnclose3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pn_btnclose3Layout = new javax.swing.GroupLayout(pn_btnclose3);
        pn_btnclose3.setLayout(pn_btnclose3Layout);
        pn_btnclose3Layout.setHorizontalGroup(
            pn_btnclose3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btnclose3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnclose3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_btnclose3Layout.setVerticalGroup(
            pn_btnclose3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btnclose3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnclose3)
                .addGap(10, 10, 10))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Kasir1-removebg-preview.png"))); // NOI18N

        javax.swing.GroupLayout jpanelgradient_11Layout = new javax.swing.GroupLayout(jpanelgradient_11);
        jpanelgradient_11.setLayout(jpanelgradient_11Layout);
        jpanelgradient_11Layout.setHorizontalGroup(
            jpanelgradient_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(34, 34, 34)
                .addComponent(pn_btnclose3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jpanelgradient_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        jpanelgradient_11Layout.setVerticalGroup(
            jpanelgradient_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                .addGroup(jpanelgradient_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pn_btnclose3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpanelgradient_11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelgradient_11Layout.createSequentialGroup()
                    .addGap(131, 131, 131)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelgradient_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
            .addComponent(jpanelgradient_11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameActionPerformed
        // TODO add your handling code here:
        String kodeBarang = txtusername.getText();
        try {
            Connection conn= Config.configDB();
            String query = "SELECT username, password FROM akun WHERE username = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kodeBarang);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            txtusername.setText(rs.getString("username"));
            txtpassword.setText(rs.getString("password"));
            this.setVisible(false);
            new MainMenu2().setVisible(true);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtusernameActionPerformed

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM akun WHERE username='"+txtusername.getText()
                    +"'AND password='"+txtpassword.getText()+"'";
            Connection conn= Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if(rs.next()){
                if(txtusername.getText().equals(rs.getString("username"))
                        && txtpassword.getText().equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "BERHASIL LOGIN");
                    String sql1 = "INSERT INTO log_akun (username,password) VALUES ('"+txtusername.getText()+"','"+txtpassword.getText()+"')";
                    java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
                    pst1.executeUpdate();
                    this.setVisible(false);
                    new MainMenu2().setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(null, "AKUN TIDAK DITEMUKAN");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnloginActionPerformed

    private void btnloginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseEntered
        // TODO add your handling code here:
        btnlogin.setBackground(new Color(0,102,0));
    }//GEN-LAST:event_btnloginMouseEntered

    private void btnloginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseExited
        // TODO add your handling code here:
        btnlogin.setBackground(new Color(0,153,0));
    }//GEN-LAST:event_btnloginMouseExited

    private void btnlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new LupaPassword().setVisible(true);
    }//GEN-LAST:event_btnlogoutMouseClicked

    private void btnlogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseEntered
        // TODO add your handling code here:
        btnlogout.setForeground(new Color(0,51,153));
    }//GEN-LAST:event_btnlogoutMouseEntered

    private void btnlogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnlogoutMouseExited
        // TODO add your handling code here:
        btnlogout.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_btnlogoutMouseExited

    private void txtusernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameKeyPressed

    private void txtusernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernameKeyTyped
        // TODO add your handling code here:
        txtusername.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_txtusernameKeyTyped

    private void txtusernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusernameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameMouseClicked

    private void txtusernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusernameMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameMousePressed

    private void txtusernameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusernameMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameMouseReleased

    private void btnregister2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregister2MouseEntered
        // TODO add your handling code here:
        btnregister2.setForeground(new Color(0,51,153));
    }//GEN-LAST:event_btnregister2MouseEntered

    private void btnregister2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregister2MouseExited
        // TODO add your handling code here:
        btnregister2.setForeground(new Color(32,136,203));
    }//GEN-LAST:event_btnregister2MouseExited

    private void btnregister2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregister2MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new PasswordToko().setVisible(true);
    }//GEN-LAST:event_btnregister2MouseClicked

    private void btnclose3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclose3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnclose3MouseClicked

    private void btnclose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclose3MouseEntered
        // TODO add your handling code here:
        pn_btnclose3.setBackground(new Color(255,0,0));
        btnclose3.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnclose3MouseEntered

    private void btnclose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnclose3MouseExited
        // TODO add your handling code here:
        pn_btnclose3.setBackground(new Color(0,153,0));
        btnclose3.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnclose3MouseExited

    private void pn_btnclose3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_btnclose3MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_pn_btnclose3MouseClicked

    private void pn_btnclose3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_btnclose3MouseEntered
        // TODO add your handling code here:
        pn_btnclose3.setBackground(new Color(255,0,0));
        btnclose3.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_pn_btnclose3MouseEntered

    private void pn_btnclose3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_btnclose3MouseExited
        // TODO add your handling code here:
        pn_btnclose3.setBackground(new Color(0,153,0));
        btnclose3.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_pn_btnclose3MouseExited

    private void txtusernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusGained
        // TODO add your handling code here:
        String user = txtusername.getText();
        if (user.equals("*Masukkan username")) {
            txtusername.setText("");
        }
    }//GEN-LAST:event_txtusernameFocusGained

    private void txtusernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusLost
        // TODO add your handling code here:
        String user = txtusername.getText();
        if (user.equals("") || user.equals("*Masukkan username")) {
            txtusername.setText("*Masukkan username");
            txtusername.setForeground(new Color(102,102,102));
        }
    }//GEN-LAST:event_txtusernameFocusLost

    private void txtpasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusGained
        // TODO add your handling code here:
        String user = txtpassword.getText();
        if (user.equals("Masukkanpassword")) {
            txtpassword.setText("");
        }
    }//GEN-LAST:event_txtpasswordFocusGained

    private void txtpasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpasswordFocusLost
        // TODO add your handling code here:
        String user = txtpassword.getText();
        if (user.equals("") || user.equals("Masukkanpassword")) {
            txtpassword.setText("Masukkanpassword");
            txtpassword.setForeground(new Color(102,102,102));
        }
    }//GEN-LAST:event_txtpasswordFocusLost

    private void txtpasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordKeyTyped
        // TODO add your handling code here:
        txtpassword.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_txtpasswordKeyTyped

    private void btn_show1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_show1MouseClicked
        // TODO add your handling code here:
        btn_show1.setVisible(false);
        btn_hide1.setVisible(true);
        txtpassword.setEchoChar((char)0);
    }//GEN-LAST:event_btn_show1MouseClicked

    private void btn_hide1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hide1MouseClicked
        // TODO add your handling code here:
        btn_show1.setVisible(true);
        btn_hide1.setVisible(false);
        txtpassword.setEchoChar('*');
    }//GEN-LAST:event_btn_hide1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_hide1;
    private javax.swing.JLabel btn_show1;
    private javax.swing.JLabel btnclose3;
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel btnlogout;
    private javax.swing.JLabel btnregister2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private jpanelgradient.jpanelgradient_1 jpanelgradient_11;
    private javax.swing.JPanel pn_btnclose3;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables

    private static class Kasirbaju {

        public Kasirbaju() {
        }

        private void setVisible(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}