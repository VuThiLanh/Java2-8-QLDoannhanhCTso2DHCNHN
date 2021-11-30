/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import Connect.Connect_Data_QLSP;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GiaoDienQLSanPham extends javax.swing.JFrame {

    /**
     * Creates new form GiaoDienQLSanPham
     */
    Connect_Data_QLSP cn = new Connect_Data_QLSP();
    public static ResultSet rs =null;
    public Statement st;
    int selectedRow;
    private void LoadData(){
        cn.getConnect();
        try {
            rs = cn.GetData("SAN_PHAM");
            jTableSanPham.removeAll();
            String []tieuDe={"MASP","TENSP","SLCO"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MASP"));
                vt.add(rs.getString("TENSP"));
                vt.add(rs.getString("SLCO"));
                
                model.addRow(vt);
            }
            jTableSanPham.setModel(model);
        
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void xoaTrang(){
        jTextMaSP.setText("");
        jTextTenSP.setText("");
        jTextSLCo.setText("");
        jTextMaSP.requestFocus();
    }
    public GiaoDienQLSanPham() {
        initComponents();
        LoadData();
        xoaTrang();
    }
    public void them(){
       Connect_Data_QLSP cn = new Connect_Data_QLSP();
       cn.getConnect();
       String maSP=jTextMaSP.getText();
       String tenSP = jTextTenSP.getText();
       
       if(maSP.equals("") || tenSP.equals("") || jTextSLCo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
       else{
           
        try {
            
            int slCo = Integer.parseInt(jTextSLCo.getText());
            if(slCo<0){
                JOptionPane.showMessageDialog(null,"Số lượng phải lớn hơn 0. Nhập lại");
            }
            else if(!cn.check(jTextMaSP.getText())){
                JOptionPane.showMessageDialog(null,"Thêm thành công !");
                cn.add(maSP, tenSP, slCo);
            }
            else{
                 JOptionPane.showMessageDialog(null,"Mã sản phẩm đã tồn tại !");
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
       LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
       }
    }
    public void capNhat(){
       Connect_Data_QLSP cn = new Connect_Data_QLSP();
       cn.getConnect();
       String maSP=jTextMaSP.getText();
       String tenSP = jTextTenSP.getText();
       
       if(maSP.equals("") || tenSP.equals("") || jTextSLCo.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
       else{
        try {
            int slCo = Integer.parseInt(jTextSLCo.getText());
            if(slCo<0){
                JOptionPane.showMessageDialog(null,"Số lượng phải lớn hơn 0. Nhập lại");
            }
            if(!cn.check(jTextMaSP.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm cần cập nhật !");
            }
            else{ 
                JOptionPane.showMessageDialog(null,"Cập nhật thành công !");
                cn.update(maSP, tenSP, slCo);
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
       }
    }
    public void xoa(){
        cn.getConnect();
        if(jTextMaSP.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập mã sản phẩm trước khi xóa !");
       }
        else{
        try {
            if(!cn.check(jTextMaSP.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm cần xóa !");
            }
            else {
                JOptionPane.showMessageDialog(null,"Xóa thành công !");
                cn.remove(jTextMaSP.getText());
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);       
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,e);
        }   
        }
    }
   public void timKiem() throws ClassNotFoundException{
       cn.getConnect();
        try {
            rs = cn.search(jTextMaSP.getText());
            if(!cn.check(jTextMaSP.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy sản phẩm cần tìm kiếm !");
            }
            jTableSanPham.removeAll();
            String []tieuDe={"MASP","TENSP","SLCO"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MASP"));
                vt.add(rs.getString("TENSP"));
                vt.add(rs.getString("SLCO"));
                
                model.addRow(vt);
            }
            jTableSanPham.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE,null,ex);
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
        jTextMaSP = new javax.swing.JTextField();
        jTextTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextSLCo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jThem = new javax.swing.JButton();
        jSua = new javax.swing.JButton();
        jTimkiem = new javax.swing.JButton();
        jXoa = new javax.swing.JButton();
        jThoat = new javax.swing.JButton();
        jExit = new javax.swing.JButton();
        jDanhSach = new javax.swing.JButton();
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
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Mã sản phẩm");

        jLabel3.setText("Tên sản phẩm ");

        jLabel4.setText("Số lượng có");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextSLCo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextSLCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSanPham);

        jScrollPane1.setViewportView(jScrollPane2);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý"));

        jThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add.png"))); // NOI18N
        jThem.setText("Thêm");
        jThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThemActionPerformed(evt);
            }
        });

        jSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Edit.png"))); // NOI18N
        jSua.setText("     Cập nhật");
        jSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSuaActionPerformed(evt);
            }
        });

        jTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Search.png"))); // NOI18N
        jTimkiem.setText("Tìm kiếm");
        jTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTimkiemActionPerformed(evt);
            }
        });

        jXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Add to basket.png"))); // NOI18N
        jXoa.setText("Xóa");
        jXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXoaActionPerformed(evt);
            }
        });

        jThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Delete.png"))); // NOI18N
        jThoat.setText("Thoát");
        jThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jThoatActionPerformed(evt);
            }
        });

        jExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/Back.png"))); // NOI18N
        jExit.setText("Trở về");
        jExit.setPreferredSize(new java.awt.Dimension(83, 33));
        jExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitActionPerformed(evt);
            }
        });

        jDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AnhGD/List.png"))); // NOI18N
        jDanhSach.setText("Hiển thị");
        jDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jExit, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jThem)
                    .addComponent(jDanhSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTimkiem)
                    .addComponent(jSua))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXoa)
                    .addComponent(jExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jThoat)
                .addContainerGap(18, Short.MAX_VALUE))
        );

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
                        .addGap(280, 280, 280)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSanPhamMouseClicked
        // TODO add your handling code here:
        selectedRow = evt.getClickCount();
        if(selectedRow>=0)
        {
            jTextMaSP.setText(jTableSanPham.getValueAt(jTableSanPham.getSelectedRow(), 0).toString());
            jTextTenSP.setText(jTableSanPham.getValueAt(jTableSanPham.getSelectedRow(), 1).toString());
            jTextSLCo.setText(jTableSanPham.getValueAt(jTableSanPham.getSelectedRow(), 2).toString());
            
        }
    }//GEN-LAST:event_jTableSanPhamMouseClicked

    private void jThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThemActionPerformed
        // TODO add your handling code here:
                them();
                xoaTrang();
    }//GEN-LAST:event_jThemActionPerformed

    private void jSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSuaActionPerformed
        // TODO add your handling code here:
        capNhat();
        xoaTrang();
    }//GEN-LAST:event_jSuaActionPerformed

    private void jTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTimkiemActionPerformed
        // TODO add your handling code here:
        try {
            timKiem();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTimkiemActionPerformed

    private void jXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXoaActionPerformed
        // TODO add your handling code here:
        xoa();
        xoaTrang();
    }//GEN-LAST:event_jXoaActionPerformed

    private void jThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jThoatActionPerformed
        // TODO add your handling code here:
        int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn thoát","Thoát",JOptionPane.YES_NO_OPTION);
        if(ret==JOptionPane.YES_NO_OPTION) System.exit(0);
    }//GEN-LAST:event_jThoatActionPerformed

    private void jExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExitActionPerformed
        // TODO add your handling code here:
        new GiaoDienMenuChinh().setVisible(true);
        dispose();
    }//GEN-LAST:event_jExitActionPerformed

    private void jDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDanhSachActionPerformed
        // TODO add your handling code here:
        LoadData();
    }//GEN-LAST:event_jDanhSachActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienQLSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDanhSach;
    private javax.swing.JButton jExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JButton jSua;
    private javax.swing.JTable jTableSanPham;
    private javax.swing.JTextField jTextMaSP;
    private javax.swing.JTextField jTextSLCo;
    private javax.swing.JTextField jTextTenSP;
    private javax.swing.JButton jThem;
    private javax.swing.JButton jThoat;
    private javax.swing.JButton jTimkiem;
    private javax.swing.JButton jXoa;
    // End of variables declaration//GEN-END:variables
}
