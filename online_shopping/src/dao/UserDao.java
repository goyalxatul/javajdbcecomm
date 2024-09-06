package dao;

import connection.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDao {

    Connection con = Myconnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(uid) from user");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from user where uemail =? ");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from user where uphone =? ");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int id, String username, String email, String pass, String Phone, String seq, String ans, String address, String address1) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, Phone);
            ps.setString(6, seq);
            ps.setString(7, address);
            ps.setString(8, address1);
            ps.setString(9, ans);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "user added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public String[] getUserValue(int id) {
        String[] value = new String[9];
        try {
            ps = con.prepareStatement("select * from user where uid =? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);
                value[7] = rs.getString(8);
                value[8] = rs.getString(9);

              
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }
    
    public int getUserid(String email) {
        int id=0;
        try {
            ps = con.prepareStatement("select uid from user where uemail =? ");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public void update(int id, String username, String email, String pass, String Phone, String seq, String ans, String address, String address1){
        String sql="update user set uname =? ,uemail =?, upassword =?, uphone =?,usecqus =?,uans =?, uaddress1 =?, uaddress2 =? where uid =?";
        try {
            
            ps= con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, Phone);
            ps.setString(5, seq);
            ps.setString(6, ans);
            ps.setString(7, address);
            ps.setString(8, address1);
            ps.setInt(9, id);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"user data successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(int id){
     int x =  JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this account","Delete Acount",JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
         try {
             ps = con.prepareStatement("delete from user where uid = ?");
             ps.setInt(1,id);
             if(ps.executeUpdate()>0){
                 JOptionPane.showMessageDialog(null,"Account deleted");
             }
         } catch (SQLException ex) {
             Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }
    
    public void getUserValue(JTable table, String search){
        String sql = "select * from user where concat(uid,uname,uemail,uphone) like ? order by sid desc";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,"%"+search +"%");
            rs= ps.executeQuery();
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            Object [] row;
            while(rs.next()){
                row=new Object [7];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                row[6]=rs.getString(7);
                
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
