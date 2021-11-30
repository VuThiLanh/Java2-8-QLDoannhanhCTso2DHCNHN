/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

public class Connect_Data_QLSP {
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
        kq=st.executeQuery("select * from SAN_PHAM order by MASP");
        return kq;
    }
    public int add(String maSP, String tenSP,int slCo) throws Exception
    {
        int t=1;
        try {
            PreparedStatement pr = con.prepareStatement("insert into SAN_PHAM values (?,?,?)");
            pr.setString(1, maSP);
            pr.setString(2, tenSP);
            pr.setString(3, slCo+"");
            if(pr.execute()) t=0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    public int update(String maSP, String tenSP, int slCo) throws Exception
    {
        int t=1;
        try {
            PreparedStatement pr=con.prepareStatement("update SAN_PHAM set TENSP = ?,  SLCO = ? WHERE MASP = ?");
            pr.setString(1, tenSP);
            pr.setString(2, slCo+"");
            pr.setString(3, maSP);
            t=pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return   t;
    }
    public ResultSet search(String maSP) throws SQLException{
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from SAN_PHAM where MASP='"+maSP+"'");
        return kq;
    }
    public boolean check(String maSP) throws SQLException,ClassNotFoundException{
        ResultSet kq;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from SAN_PHAM where MASP='"+maSP+"'");
        if(!kq.next())    return false;
        else return true;
    }
    public int remove(String maSP)throws Exception
    {
        int t;
        t= getStatement().executeUpdate("delete from SAN_PHAM where MASP='"+maSP+"'");
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
    public ResultSet list(Date d) throws SQLException
    {
        ResultSet kq=null;
        Statement st=con.createStatement();
        kq=st.executeQuery("select * from SAN_PHAM where HSD <= '"+d+"'");
        return kq;
    }
}
