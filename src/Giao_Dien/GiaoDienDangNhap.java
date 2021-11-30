/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GiaoDienDangNhap extends javax.swing.JFrame {
    /**
     * Creates new form FormCT
     */
    
    public GiaoDienDangNhap() {
        initComponents();  
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTieuDe = new javax.swing.JLabel();
        jPanelDangNhap = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextTenDN = new javax.swing.JTextField();
        jButtonDangNhap = new javax.swing.JButton();
        jTextMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý đồ ăn nhanh căng tin số 2 - ĐH Công nghiệp Hà Nội");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);

        jLabelTieuDe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabelTieuDe.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTieuDe.setText("QUẢN LÝ ĐỒ ĂN NHANH CĂNG TIN  SỐ 2 - ĐH CÔNG NGHIỆP HÀ NỘI");

        jPanelDangNhap.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("ĐĂNG NHẬP");

        jLabel3.setText("Tên đăng nhập");

        jLabel4.setText("Mật khẩu");

        jButtonDangNhap.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jButtonDangNhap.setForeground(new java.awt.Color(0, 51, 204));
        jButtonDangNhap.setText("ĐĂNG NHẬP");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDangNhapLayout = new javax.swing.GroupLayout(jPanelDangNhap);
        jPanelDangNhap.setLayout(jPanelDangNhapLayout);
        jPanelDangNhapLayout.setHorizontalGroup(
            jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDangNhapLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextTenDN, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jTextMatKhau))
                .addGap(41, 41, 41))
            .addGroup(jPanelDangNhapLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addGroup(jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDangNhap))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanelDangNhapLayout.setVerticalGroup(
            jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDangNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(jPanelDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabelTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jPanelDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jPanelDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection con=null;   
    ResultSet res=null;   
    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        // TODO add your handling code here:
        if(jTextTenDN.getText().equals("") && jTextMatKhau.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Vui lòng điền tên đăng nhập và mật khẩu !");
        }
        else if(jTextMatKhau.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Vui lòng điền mật khẩu !");
        }
        else if(jTextTenDN.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Vui lòng điền tên đăng nhập !");
        }
        else{
            try {
                String url="jdbc:derby://localhost:1527/QLDACTS2";
                String user="Nhom8";
                String pass="123456";
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con=(Connection)DriverManager.getConnection(url, user, pass);
                String sql ="SELECT * FROM TAI_KHOAN WHERE TENDN=? AND MATKHAU= ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, jTextTenDN.getText());
                ps.setString(2, jTextMatKhau.getText());
                
                res=ps.executeQuery();
                if(res.next()){
                   JOptionPane.showMessageDialog(this,"Đăng nhập thành công !");
                   new GiaoDienMenuChinh().setVisible(true);  
                   dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Đăng nhập thất bại !");
                }
            }
             catch (ClassNotFoundException|SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Không thể kết nối với database \n"+e);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoDienDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTieuDe;
    private javax.swing.JPanel jPanelDangNhap;
    private javax.swing.JPasswordField jTextMatKhau;
    private javax.swing.JTextField jTextTenDN;
    // End of variables declaration//GEN-END:variables
}
