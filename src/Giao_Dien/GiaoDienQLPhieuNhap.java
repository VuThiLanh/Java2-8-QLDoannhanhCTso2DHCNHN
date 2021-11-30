/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import Connect.Connect_Data_QLPN;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;


public class GiaoDienQLPhieuNhap extends javax.swing.JFrame {

    /**
     * Creates new form GiaoDienQLSanPham
     */
    int selectedRow;
    public static ResultSet rs = null;
    public Statement st;
    Connect_Data_QLPN cn = new Connect_Data_QLPN();
    private void LoadData(){
        cn.getConnect();
        try {
            rs = cn.GetData("PHIEU_NHAP");
            jTableCtPhieuNhap.removeAll();
            String []tieuDe={"MASP","MANCC","GIANHAP","SLNHAP","NGAYNHAP","THANHTIEN"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MASP"));
                vt.add(rs.getString("MANCC"));
                vt.add(rs.getString("GIANHAP"));
                vt.add(rs.getString("SLNHAP"));
                vt.add(rs.getString("NGAYNHAP"));
                vt.add(rs.getString("THANHTIEN"));
                model.addRow(vt);
            }
            jTableCtPhieuNhap.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public GiaoDienQLPhieuNhap() {
        initComponents();
        xoaTrang();
        LoadData();
    }
    public void xoaTrang(){
        jTextMaSP.setText("");
        jTextMaNCC.setText("");
        jTextGiaNhap.setText("");
        jTextSLNhap.setText("");
        jTextNgayNhap.setText("");
        jTextMaSP.requestFocus();   
    }
    public void them() throws SQLException, ClassNotFoundException{
       Connect_Data_QLPN cn = new Connect_Data_QLPN();
       cn.getConnect();
       String maSP=jTextMaSP.getText();
       String maNCC = jTextMaNCC.getText();
       if(maSP.equals("")|| maNCC.equals("")|| jTextGiaNhap.getText().equals("")|| jTextSLNhap.getText().equals("")||jTextNgayNhap.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin !");
       }
       else if(!cn.checkMaSP(maSP)){
           JOptionPane.showMessageDialog(null, "Mã sản phẩm không hợp lệ !");
       }
       else if(!cn.checkMaNCC(maNCC)){
           JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không hợp lệ !");
       }
       else if(cn.check(maSP, maNCC)){
           JOptionPane.showMessageDialog(null, "Phiếu nhập đã tồn tại !");
       }
       else{
          try {
            double giaNhap = Double.parseDouble(jTextGiaNhap.getText());
            int slNhap = Integer.parseInt(jTextSLNhap.getText());
            Date ngayNhap = Date.valueOf(jTextNgayNhap.getText());
            JOptionPane.showMessageDialog(null, "Thêm thành công !");
            cn.add(maSP, maNCC, giaNhap, slNhap, ngayNhap);
            cn.update(maSP, slNhap);
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,e);
        }
       LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,e);
        } 
       }
        
    }
    public void timKiem() throws ClassNotFoundException {
        cn.getConnect();
        if(jTextMaSP.getText().equals("")||jTextMaNCC.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đủ mã sản phẩm và mã nhà cung cấp !");
        }
        else{
            try {
            rs = cn.search(jTextMaSP.getText(),jTextMaNCC.getText());
            if(!cn.check(jTextMaSP.getText(),jTextMaNCC.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy phiếu nhập cần tìm kiếm !");
            }
            jTableCtPhieuNhap.removeAll();
            String []tieuDe={"MASP","MANCC","GIANHAP","SLNHAP","NGAYNHAP","THANHTIEN"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MASP"));
                vt.add(rs.getString("MANCC"));
                vt.add(rs.getString("GIANHAP"));
                vt.add(rs.getString("SLNHAP"));
                vt.add(rs.getString("NGAYNHAP"));
               vt.add(rs.getString("THANHTIEN"));
                model.addRow(vt);
            }
            jTableCtPhieuNhap.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,ex);
        } 
        }
        
    }
    public void xoa() throws SQLException, ClassNotFoundException{
        cn.getConnect();
        if(!cn.check(jTextMaSP.getText(),jTextMaNCC.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy phiếu nhập cần xóa !");
        }
        else{
           try {
            cn.remove(jTextMaSP.getText(),jTextMaNCC.getText());
            JOptionPane.showMessageDialog(null,"Xóa thành công!");
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,e);       
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE,null,e);
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
        jLabel6 = new javax.swing.JLabel();
        jTextGiaNhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextNgayNhap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextSLNhap = new javax.swing.JTextField();
        jTextMaNCC = new javax.swing.JTextField();
        jTextMaSP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButtonThem = new javax.swing.JButton();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();
        jButtonTroVe = new javax.swing.JButton();
        jButtonHienThi = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCtPhieuNhap = new javax.swing.JTable();
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
        jLabel1.setText("QUẢN LÝ PHIẾU NHẬP");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Mã sản phẩm");

        jLabel3.setText("Mã nhà cung cấp");

        jLabel6.setText("Giá nhập");

        jLabel7.setText("Số lượng nhập");

        jLabel8.setText("Ngày nhập");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextSLNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)
                        .addComponent(jTextMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextSLNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý"));

        jButtonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add.png"))); // NOI18N
        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Search.png"))); // NOI18N
        jButtonTimKiem.setText("Tìm kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Delete.png"))); // NOI18N
        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
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

        jButtonHienThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/List.png"))); // NOI18N
        jButtonHienThi.setText("Hiển thị");
        jButtonHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHienThiActionPerformed(evt);
            }
        });

        jButtonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add to basket.png"))); // NOI18N
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonThoat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHienThi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButtonTimKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonThem, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addComponent(jButtonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThem)
                    .addComponent(jButtonHienThi))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimKiem)
                    .addComponent(jButtonXoa))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonThoat)
                    .addComponent(jButtonTroVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTableCtPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableCtPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCtPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCtPhieuNhap);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        try {
            // TODO add your handling code here:

            them();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        xoaTrang();
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        // TODO add your handling code here:
        try {
            timKiem();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonTimKiemActionPerformed
    
    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn thoát","Thoát",JOptionPane.YES_NO_OPTION);
        if(ret==JOptionPane.YES_NO_OPTION) System.exit(0);
    }//GEN-LAST:event_jButtonThoatActionPerformed

    private void jButtonTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTroVeActionPerformed
        // TODO add your handling code here:
       GiaoDienMenuChinh x = new GiaoDienMenuChinh();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_jButtonTroVeActionPerformed

    private void jButtonHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHienThiActionPerformed
        // TODO add your handling code here:
        LoadData();
    }//GEN-LAST:event_jButtonHienThiActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        try {
            // TODO add your handling code here:
            xoa();
        } catch (SQLException ex) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        xoaTrang();
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jTableCtPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCtPhieuNhapMouseClicked
        // TODO add your handling code here:
        selectedRow = evt.getClickCount();
        if(selectedRow>=0){
            jTextMaSP.setText(jTableCtPhieuNhap.getValueAt(jTableCtPhieuNhap.getSelectedRow(), 0).toString());
            jTextMaNCC.setText(jTableCtPhieuNhap.getValueAt(jTableCtPhieuNhap.getSelectedRow(), 1).toString());
            jTextGiaNhap.setText(jTableCtPhieuNhap.getValueAt(jTableCtPhieuNhap.getSelectedRow(), 2).toString());
            jTextSLNhap.setText(jTableCtPhieuNhap.getValueAt(jTableCtPhieuNhap.getSelectedRow(), 3).toString());
            jTextNgayNhap.setText(jTableCtPhieuNhap.getValueAt(jTableCtPhieuNhap.getSelectedRow(), 4).toString());
        }
    }//GEN-LAST:event_jTableCtPhieuNhapMouseClicked

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
        new GiaoDienQLPhieuXuat().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuQLPXActionPerformed

    private void jMenuQLCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQLCTPXActionPerformed
        // TODO add your handling code here:
        GiaoDienQLCTPhieuXuat x= new GiaoDienQLCTPhieuXuat();
        x.setVisible(true);
        x.setLocationRelativeTo(null);
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
        new GiaoDienQLPhieuNhap().setVisible(true);
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
            java.util.logging.Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienQLPhieuNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHienThi;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JButton jButtonTroVe;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTable jTableCtPhieuNhap;
    private javax.swing.JTextField jTextGiaNhap;
    private javax.swing.JTextField jTextMaNCC;
    private javax.swing.JTextField jTextMaSP;
    private javax.swing.JTextField jTextNgayNhap;
    private javax.swing.JTextField jTextSLNhap;
    // End of variables declaration//GEN-END:variables
}
