package Connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author VuThiLanh
 */

public class Connect_Data_QLCTPX {
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
        kq=st.executeQuery("select * from CT_PHIEU_XUAT order by MaPX");
        return kq;
    }
    public int add(String maSP,String maPX, double giaBan, int slBan, double phiShip) throws Exception 
    {
        int t=1;
        try {
            PreparedStatement pr = con.prepareStatement("insert into CT_PHIEU_XUAT values(?,?,?,?,?,?)" );
            pr.setString(1, maPX);
            pr.setString(2, maSP);
            pr.setString(3, giaBan+"");
            pr.setString(4, slBan+"");
            pr.setString(5, phiShip+"");
            pr.setString(6, (giaBan*slBan+phiShip)+"");
            if(pr.execute()) t=0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    public int update(String maSP,int slBan){
        int t=1;
        try {
            PreparedStatement pr=con.prepareStatement("update SAN_PHAM set SLCO = SLCO - "+slBan+""
                    + " WHERE MASP = ?"); 
            pr.setString(1,maSP);     
            t=pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return   t;
    }
    public ResultSet search(String maSP, String maPX) throws SQLException{
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from CT_PHIEU_XUAT where MASP='"+maSP+"' AND MAPX = '"+maPX+"'");
        return kq;
    }
    public ResultSet searchbyMaPX( String maPX) throws SQLException{
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from CT_PHIEU_XUAT where MAPX = '"+maPX+"'");
        return kq;
    }
    public boolean checkbyMaPX(String maPX) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from CT_PHIEU_XUAT where MAPX = '"+maPX+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public boolean check(String maSP,String maPX) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from CT_PHIEU_XUAT where MASP='"+maSP+"' AND MAPX = '"+maPX+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public boolean checkMaSP(String maSP) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from SAN_PHAM where MASP='"+maSP+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public boolean checkMaNCC(String maPX) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from PHIEU_XUAT where MAPX='"+maPX+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public int remove(String maSP, String maPX)throws Exception
    {
        int t;
        t= getStatement().executeUpdate("delete from CT_PHIEU_XUAT where "
                + "MASP='"+maSP+"' AND MAPX = '"+maPX+"'");
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

}
