/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import Connect.Connect_Data_QLNCC;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VuThiLanh
 */

public class GiaoDienQLNCC extends javax.swing.JFrame {

    /**
     * Creates new form GiaoDienQLNCC
     */
    public static ResultSet rs = null;
    public Statement st;
    Connect_Data_QLNCC cn = new Connect_Data_QLNCC();
    
    private void LoadData(){
        cn.getConnect();
        try {
            rs = cn.GetData("NHACC");
            jTableNhaCC.removeAll();
            String []tieuDe={"MANCC","TENNCC","SDT","DIACHI"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MANCC"));
                vt.add(rs.getString("TENNCC"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("DIACHI"));
                model.addRow(vt);
            }
            jTableNhaCC.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public GiaoDienQLNCC() {
        initComponents();
        xoaTrang();
        LoadData();
    }
    public void xoaTrang(){
        jTextMANCC.setText("");
        jTextTenNCC.setText("");
        jTextSDT.setText("");
        jTextDiaChi.setText("");
        jTextMANCC.requestFocus();   
    }
    public void them(){
       Connect_Data_QLNCC cn = new Connect_Data_QLNCC();
       cn.getConnect();
       String maNCC=jTextMANCC.getText();
       String tenNCC = jTextTenNCC.getText();
       String sdt = jTextSDT.getText();
       String diaChi = jTextDiaChi.getText();
       if(maNCC.equals("") || tenNCC.equals("") || sdt.equals("") || diaChi.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
       else{
               try {
            if(!cn.check(jTextMANCC.getText())){
                JOptionPane.showMessageDialog(null,"Thêm thành công !");
                cn.add(maNCC, tenNCC, sdt, diaChi); 
            }
            else{
                 JOptionPane.showMessageDialog(null,"Mã nhà cung cấp đã tồn tại !");
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
       LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
       }
    }
    public void timKiem() throws ClassNotFoundException {
        cn.getConnect();
        try {
            rs = cn.search(jTextMANCC.getText());
            if(!cn.check(jTextMANCC.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhà cung cấp cần tìm kiếm !");
            }
            jTableNhaCC.removeAll();
            String []tieuDe={"MANCC","TENNCC","SDT","DIACHI"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MANCC"));
                vt.add(rs.getString("TENNCC"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("DIACHI"));
                model.addRow(vt);
            }
            jTableNhaCC.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,ex);
        }
      
    }
    public void xoa(){
        cn.getConnect();
     if(jTextMANCC.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập mã nhân nhà cung cấp trước khi xóa !");
       }
        else{
        try {
            if(!cn.check(jTextMANCC.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhà cung cấp cần xóa !");
            }
            else {
                JOptionPane.showMessageDialog(null,"Xóa thành công !");
                cn.remove(jTextMANCC.getText());
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);       
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }   
        }

    }
    public void capnhat(){
       Connect_Data_QLNCC cn = new Connect_Data_QLNCC();
       cn.getConnect();
       String maNCC=jTextMANCC.getText();
       String tenNCC = jTextTenNCC.getText();
       String sdt = jTextSDT.getText();
       String diaChi = jTextDiaChi.getText();
        if(maNCC.equals("") || tenNCC.equals("") || sdt.equals("") || diaChi.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
        else{
           try {
               if(!cn.check(jTextMANCC.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhà cung cấp cần cập nhật !");
            }
               else{
                    JOptionPane.showMessageDialog(null,"Cập nhật thành công !");
                    cn.update(maNCC, tenNCC, sdt, diaChi);
               }
           
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE,null,e);
        }     
        }
 

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextMANCC = new javax.swing.JTextField();
        jTextDiaChi = new javax.swing.JTextField();
        jTextSDT = new javax.swing.JTextField();
        jTextTenNCC = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButtonThem = new javax.swing.JButton();
        jButtonCapNhat = new javax.swing.JButton();
        jButtonTimkiem = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jButtonHienThi = new javax.swing.JButton();
        jButtonTroVe = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableNhaCC = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuThoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuQLSP = new javax.swing.JMenuItem();
        jMenuQLPX = new javax.swing.JMenuItem();
        jMenuQLCTPX = new javax.swing.JMenuItem();
        jMenuQLNCC = new javax.swing.JMenuItem();
        jMenuQLDT = new javax.swing.JMenuItem();
        jMenuQLPN = new javax.swing.JMenuItem();
        jMenuQLTK = new javax.swing.JMenuItem();
        jMenuQLNV = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý đồ ăn nhanh căng tin số 2 - ĐH Công nghiệp Hà Nội");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÝ NHÀ CUNG CẤP ");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Mã nhà cung cấp ");

        jLabel3.setText("Tên nhà cung cấp ");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Địa chỉ ");

        jTextMANCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMANCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)
                        .addComponent(jTextMANCC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextMANCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý"));

        jButtonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add.png"))); // NOI18N
        jButtonThem.setText("    Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Edit.png"))); // NOI18N
        jButtonCapNhat.setText("     Cập nhật");
        jButtonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCapNhatActionPerformed(evt);
            }
        });

        jButtonTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Search.png"))); // NOI18N
        jButtonTimkiem.setText("Tìm kiếm");
        jButtonTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimkiemActionPerformed(evt);
            }
        });

        jButtonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add to basket.png"))); // NOI18N
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        jButtonThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Delete.png"))); // NOI18N
        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        jButtonHienThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/List.png"))); // NOI18N
        jButtonHienThi.setText("Hiển Thị");
        jButtonHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHienThiActionPerformed(evt);
            }
        });

        jButtonTroVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Back.png"))); // NOI18N
        jButtonTroVe.setText("Trở về");
        jButtonTroVe.setPreferredSize(new java.awt.Dimension(83, 33));
        jButtonTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTroVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHienThi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jButtonCapNhat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCapNhat)
                    .addComponent(jButtonHienThi))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXoa)
                    .addComponent(jButtonThem))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimkiem)
                    .addComponent(jButtonTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableNhaCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableNhaCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableNhaCCMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableNhaCC);

        jScrollPane1.setViewportView(jScrollPane2);

        jMenu1.setText("File");

        jMenuThoat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuThoat.setText("Thoát");
        jMenuThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuThoatActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuThoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Windows");

        jMenuQLSP.setText("Quản lý sản phẩm");
        jMenuQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLSPActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLSP);

        jMenuQLPX.setText("Quản lý phiếu xuất");
        jMenuQLPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLPXActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLPX);

        jMenuQLCTPX.setText("Quản lý chi tiết phiếu xuất");
        jMenuQLCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLCTPXActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLCTPX);

        jMenuQLNCC.setText("Quản lý nhà cung cấp");
        jMenuQLNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLNCCActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLNCC);

        jMenuQLDT.setText("Quản lý doanh thu");
        jMenuQLDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLDTActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLDT);

        jMenuQLPN.setText("Quản lý phiếu nhập");
        jMenuQLPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLPNActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLPN);

        jMenuQLTK.setText("Quản lý tài khoản");
        jMenuQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLTKActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLTK);

        jMenuQLNV.setText("Quản lý nhân viên");
        jMenuQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQLNVActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuQLNV);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(289, 289, 289))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextMANCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMANCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextMANCCActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        them();
        xoaTrang();
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatActionPerformed
        // TODO add your handling code here:
        capnhat();
        xoaTrang();
       
    }//GEN-LAST:event_jButtonCapNhatActionPerformed

    private void jButtonTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimkiemActionPerformed
        // TODO add your handling code here:
        try {
            timKiem();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        xoaTrang();
    }//GEN-LAST:event_jButtonTimkiemActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        xoa();
        xoaTrang();
       
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn thoát","Thoát",JOptionPane.YES_NO_OPTION);
        if(ret==JOptionPane.YES_NO_OPTION) System.exit(0);
    }//GEN-LAST:event_jButtonThoatActionPerformed
    int selectedRow;
    private void jTableNhaCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNhaCCMouseClicked
        // TODO add your handling code here:
        selectedRow  = evt.getClickCount();
        if(selectedRow>=0)
        {
            jTextMANCC.setText(jTableNhaCC.getValueAt(jTableNhaCC.getSelectedRow(), 0).toString());
            jTextTenNCC.setText(jTableNhaCC.getValueAt(jTableNhaCC.getSelectedRow(), 1).toString());
            jTextSDT.setText(jTableNhaCC.getValueAt(jTableNhaCC.getSelectedRow(), 2).toString());
            jTextDiaChi.setText(jTableNhaCC.getValueAt(jTableNhaCC.getSelectedRow(), 3).toString());
        }
    }//GEN-LAST:event_jTableNhaCCMouseClicked

    private void jButtonHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHienThiActionPerformed
        // TODO add your handling code here:
        LoadData();
    }//GEN-LAST:event_jButtonHienThiActionPerformed

    private void jButtonTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTroVeActionPerformed
        // TODO add your handling code here:
        GiaoDienMenuChinh x = new GiaoDienMenuChinh();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jButtonTroVeActionPerformed

    private void jMenuThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuThoatActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn thoát","Thoát",JOptionPane.YES_NO_OPTION);
        if(ret==JOptionPane.YES_NO_OPTION) System.exit(0);
    }//GEN-LAST:event_jMenuThoatActionPerformed

    private void jMenuQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLSPActionPerformed
        // TODO add your handling code here:
        new GiaoDienQLSanPham().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLSPActionPerformed

    private void jMenuQLPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLPXActionPerformed
        // TODO add your handling code here:
        GiaoDienQLPhieuXuat x = new GiaoDienQLPhieuXuat();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jMenuQLPXActionPerformed

    private void jMenuQLCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLCTPXActionPerformed
        // TODO add your handling code here:
        new GiaoDienQLCTPhieuXuat().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLCTPXActionPerformed

    private void jMenuQLNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLNCCActionPerformed
        // TODO add your handling code here:
        new GiaoDienQLNCC().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLNCCActionPerformed

    private void jMenuQLDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLDTActionPerformed
        // TODO add your handling code here:
        new GiaoDienDoanhThu().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLDTActionPerformed

    private void jMenuQLPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLPNActionPerformed
        // TODO add your handling code here:
        GiaoDienQLPhieuNhap x = new GiaoDienQLPhieuNhap();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jMenuQLPNActionPerformed

    private void jMenuQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLTKActionPerformed
        // TODO add your handling code here:
        new GiaoDienQLTaiKhoan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLTKActionPerformed

    private void jMenuQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLNVActionPerformed
        // TODO add your handling code here:
        new GiaoDienQLNhanVien().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLNVActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoDienQLNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GiaoDienQLNCC n = new GiaoDienQLNCC();
                n.setVisible(true);
                n.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCapNhat;
    private javax.swing.JButton jButtonHienThi;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonTimkiem;
    private javax.swing.JButton jButtonTroVe;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuQLCTPX;
    private javax.swing.JMenuItem jMenuQLDT;
    private javax.swing.JMenuItem jMenuQLNCC;
    private javax.swing.JMenuItem jMenuQLNV;
    private javax.swing.JMenuItem jMenuQLPN;
    private javax.swing.JMenuItem jMenuQLPX;
    private javax.swing.JMenuItem jMenuQLSP;
    private javax.swing.JMenuItem jMenuQLTK;
    private javax.swing.JMenuItem jMenuThoat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableNhaCC;
    private javax.swing.JTextField jTextDiaChi;
    private javax.swing.JTextField jTextMANCC;
    private javax.swing.JTextField jTextSDT;
    private javax.swing.JTextField jTextTenNCC;
    // End of variables declaration//GEN-END:variables
}
