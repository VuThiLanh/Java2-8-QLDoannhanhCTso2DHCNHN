/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Giao_Dien;

import Connect.Connect_Data_QLNV;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class GiaoDienQLNhanVien extends javax.swing.JFrame {
    public static ResultSet rs = null;
    public Statement st;
    Connect_Data_QLNV cn = new Connect_Data_QLNV();
    
    private void LoadData(){
        cn.getConnect();
        try {
            rs = cn.GetData("NHAN_VIEN");
            jTableNhanVien.removeAll();
            String []tieuDe={"MANV","TENNV","SDT","DIACHI"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MANV"));
                vt.add(rs.getString("TENNV"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("DIACHI"));
                model.addRow(vt);
            }
            jTableNhanVien.setModel(model);
             int d = jTableNhanVien.getRowCount();
             jLabelTongNV.setText(d+"");
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    /**
     * Creates new form GiaoDienQLNhanVien
     */
    public GiaoDienQLNhanVien() {
        initComponents();
        xoaTrang();
        LoadData();
    }
    public void xoaTrang(){
        jTextMANV.setText("");
        jTextTenNV.setText("");
        jTextSDT.setText("");
        jTextDiaChi.setText("");
        jTextMANV.requestFocus();   
    }
    public void them(){
       Connect_Data_QLNV cn = new Connect_Data_QLNV();
       cn.getConnect();
       String maNV=jTextMANV.getText();
       String tenNV = jTextTenNV.getText();
       String sdt = jTextSDT.getText();
       String diaChi = jTextDiaChi.getText();
       if(maNV.equals("") || tenNV.equals("") || sdt.equals("") || diaChi.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
       else{
           
        try {
            if(!cn.check(jTextMANV.getText())){
                JOptionPane.showMessageDialog(null,"Thêm thành công !");
                cn.add(maNV, tenNV, sdt, diaChi); 
            }
            else{
                 JOptionPane.showMessageDialog(null,"Mã nhân viên đã tồn tại !");
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
       LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
       }

    }
    public void timKiem() throws ClassNotFoundException {
        cn.getConnect();
        try {
            rs = cn.search(jTextMANV.getText());
            if(!cn.check(jTextMANV.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên cần tìm kiếm !");
            }
            jTableNhanVien.removeAll();
            String []tieuDe={"MANV","TENNV","SDT","DIACHI"};
            DefaultTableModel model = new DefaultTableModel(tieuDe,0);
            while(rs.next()){
                Vector vt = new Vector();
                vt.add(rs.getString("MANV"));
                vt.add(rs.getString("TENNV"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("DIACHI"));
                model.addRow(vt);
            }
            jTableNhanVien.setModel(model);
        } catch (SQLException ex) {
           Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,ex);
        }
      
    }
    public void xoa(){
        cn.getConnect();
        if(jTextMANV.getText().equals("") ){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập mã nhân viên trước khi xóa !");
       }
        else{
        try {
            if(!cn.check(jTextMANV.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên cần xóa !");
            }
            else {
                JOptionPane.showMessageDialog(null,"Xóa thành công !");
                cn.remove(jTextMANV.getText());
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);       
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }   
        }

 
    }
  
    public void capnhat(){
       Connect_Data_QLNV cn = new Connect_Data_QLNV();
       cn.getConnect();
       String maNV=jTextMANV.getText();
       String tenNV = jTextTenNV.getText();
       String sdt = jTextSDT.getText();
       String diaChi = jTextDiaChi.getText();
       if(maNV.equals("") || tenNV.equals("") || sdt.equals("") || diaChi.equals("")){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin !");
       }
       else{
        try {
            if(!cn.check(jTextMANV.getText())){
                JOptionPane.showMessageDialog(null,"Không tìm thấy nhân viên cần cập nhật !");
            }
            else{ 
                JOptionPane.showMessageDialog(null,"Cập nhật thành công !");
                cn.update(maNV, tenNV, sdt, diaChi);
            }
        } catch (Exception e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
        LoadData();
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
        }
        try {
            cn.closeConnect();
        } catch (SQLException e) {
            Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(Level.SEVERE,null,e);
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
        jTextMANV = new javax.swing.JTextField();
        jTextDiaChi = new javax.swing.JTextField();
        jTextSDT = new javax.swing.JTextField();
        jTextTenNV = new javax.swing.JTextField();
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
        jTableNhanVien = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabelTongNV = new javax.swing.JLabel();
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
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Mã nhân viên");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Địa chỉ ");

        jTextMANV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMANVActionPerformed(evt);
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
                        .addComponent(jTextTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jTextMANV, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextMANV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jButtonHienThi.setText("Hiển thị");
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jButtonThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonHienThi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(jButtonCapNhat, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTroVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        jTableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableNhanVien);

        jScrollPane1.setViewportView(jScrollPane2);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tổng số nhân viên");

        jLabelTongNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTongNV.setText("0");
        jLabelTongNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabelTongNVPropertyChange(evt);
            }
        });

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
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(53, 53, 53)
                        .addComponent(jLabelTongNV)
                        .addGap(92, 92, 92))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelTongNV))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextMANVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMANVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextMANVActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        them();
        xoaTrang();
//        int d = jTableNhanVien.getRowCount();
//        jLabelTongNV.setText(d+"");
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
            int d = jTableNhanVien.getRowCount();
            jLabelTongNV.setText(d+"");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GiaoDienQLNCC.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void jButtonHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHienThiActionPerformed
        // TODO add your handling code here:
        LoadData();
   
        
    }//GEN-LAST:event_jButtonHienThiActionPerformed

    private void jButtonTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTroVeActionPerformed
        // TODO add your handling code here:
        new GiaoDienMenuChinh().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonTroVeActionPerformed
    int selectedRow;
    private void jTableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNhanVienMouseClicked
        // TODO add your handling code here:
        selectedRow  = evt.getClickCount();
        if(selectedRow>=0)
        {
            jTextMANV.setText(jTableNhanVien.getValueAt(jTableNhanVien.getSelectedRow(), 0).toString());
            jTextTenNV.setText(jTableNhanVien.getValueAt(jTableNhanVien.getSelectedRow(), 1).toString());
            jTextSDT.setText(jTableNhanVien.getValueAt(jTableNhanVien.getSelectedRow(), 2).toString());
            jTextDiaChi.setText(jTableNhanVien.getValueAt(jTableNhanVien.getSelectedRow(), 3).toString());
        }
    }//GEN-LAST:event_jTableNhanVienMouseClicked

    private void jLabelTongNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabelTongNVPropertyChange
        // TODO add your handling code here:
           
    }//GEN-LAST:event_jLabelTongNVPropertyChange

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

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
            java.util.logging.Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQLNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienQLNhanVien().setVisible(true);
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelTongNV;
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
    private javax.swing.JTable jTableNhanVien;
    private javax.swing.JTextField jTextDiaChi;
    private javax.swing.JTextField jTextMANV;
    private javax.swing.JTextField jTextSDT;
    private javax.swing.JTextField jTextTenNV;
    // End of variables declaration//GEN-END:variables
}
