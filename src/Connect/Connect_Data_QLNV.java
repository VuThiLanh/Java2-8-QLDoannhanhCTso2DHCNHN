package Connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Connect_Data_QLNV {
    Connection con=null;    
    Statement sta=null;     
    ResultSet res=null;    
     
    public void getConnect() 
    {
        try{
            String url="jdbc:derby://localhost:1527/QLDACTS2";
            String user="Nhom8";
            String pass="123456";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con=(Connection)DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException|SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Không thể kết nối với database \n"+e);
        }      
    }
    protected  Statement getStatement()throws Exception
    {
        if(this.sta==null || this.sta.isClosed())
        {
            this.sta=this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }
        return this.sta;
    }
    public ResultSet executeQuery(String qr) throws Exception
    {
        try{
            this.res=getStatement().executeQuery(qr);
        }
        catch(Exception e)
        {
            throw new Exception("Câu lệnh Query không được thực thi");
        }
        return this.res;
    }
    
    public int executeUpdate(String qr) throws Exception
    {
        int ketqua=0;
        try{
            ketqua=getStatement().executeUpdate(qr);
        }
        catch(Exception e)
        {
            throw new Exception("Lỗi queryUpdate không được thực hiện");
        }
        finally{
            this.con.close();
        }
        return ketqua;
    }
    public ResultSet GetData(String jtable) throws SQLException
    {
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from NHAN_VIEN order by MANV");
        return kq;
    }

    public int add(String maNV, String tenNV, String sdt, String diaChi) throws Exception 
    {
        int t=1;
        try {
            PreparedStatement pr = con.prepareStatement("insert into NHAN_VIEN values(?,?,?,?)");
            pr.setString(1, maNV);
            pr.setString(2, tenNV);
            pr.setString(3, sdt);
            pr.setString(4, diaChi);
            if(pr.execute()) t=0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    public int update(String maNV, String tenNV, String sdt, String diaChi) throws Exception
    {
        int t=1;
        try {
            PreparedStatement pr=con.prepareStatement("update NHAN_VIEN set "
                    + "TENNV = ?, SDT = ?, DIACHI = ? WHERE MANV = ?");
            pr.setString(1, tenNV);
            pr.setString(2, sdt);
            pr.setString(3, diaChi);
            pr.setString(4, maNV);
            t=pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return   t;
    }
    public ResultSet search(String maNV) throws SQLException{
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from NHAN_VIEN where MANV='"+maNV+"'");
        return kq;
    }
    public boolean check(String maNV) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from NHAN_VIEN where MANV='"+maNV+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public int remove(String maNV)throws Exception
    {
        int t;
        t= getStatement().executeUpdate("delete from NHAN_VIEN where MANV='"+maNV+"'");
        return t;
    }
    public void closeConnect() throws SQLException
    {
        if(this.res!=null&&!this.res.isClosed())
        try{
            this.res.close();
            this.res=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Lỗi dòng kết quả");
        }
        if(this.sta!=null&&!this.sta.isClosed())
        try{
            this.sta.close();
            this.sta=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Lỗi dòng lệnh thực thi");
        }  
        if(this.con!=null&&!this.con.isClosed())
        try{
            this.con.close();
            this.con=null;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Lỗi dòng kết nối");
        } 
    }

    public void soNV(JTable jTableNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
