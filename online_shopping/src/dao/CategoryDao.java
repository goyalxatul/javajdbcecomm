
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

public class CategoryDao {
    Connection con = Myconnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(cid) from category");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
     public boolean isIdExist(int id) {
        try {
            ps = con.prepareStatement("select * from category where cid =? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public boolean isCategoryExist(String cname) {
        try {
            ps = con.prepareStatement("select * from category where cname =? ");
            ps.setString(1, cname);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      public void insert(int id, String cname,String desc) {
        String sql = "insert into category values(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, cname);
            ps.setString(3, desc);
           
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Category added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }
     public void getCategoryValue(JTable table, String search){
        String sql = "select * from category where concat(cid,cname) like ? order by cid desc";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1,"%"+search +"%");
            rs= ps.executeQuery();
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            Object [] row;
            while(rs.next()){
                row=new Object [3];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                
                
                model.addRow(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void update(int id, String cname,String cdesc){
        String sql="update category set cname =?, cdesc =? where cid =?";
        try {
            
            ps= con.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setString(2, cdesc);
            ps.setInt(3, id);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Category successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public void delete(int id){
     int x =  JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this account","Delete Category",JOptionPane.OK_CANCEL_OPTION,0);
        if(x == JOptionPane.OK_OPTION){
         try {
             ps = con.prepareStatement("delete from category where cid = ?");
             ps.setInt(1,id);
             if(ps.executeUpdate()>0){
                 JOptionPane.showMessageDialog(null,"Account deleted");
             }
         } catch (SQLException ex) {
             Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }
    


}
