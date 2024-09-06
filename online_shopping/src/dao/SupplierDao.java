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

public class SupplierDao {

    Connection con = Myconnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRow() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select max(sid) from supplier");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from supplier where semail =? ");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from supplier where sphone =? ");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isUsernameExist(String name) {
        try {
            ps = con.prepareStatement("select * from supplier where sname =? ");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(int id, String username, String email, String pass, String Phone, String address, String address1) {
        String sql = "insert into supplier values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, Phone);
            ps.setString(6, address);
            ps.setString(7, address1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "supplier added successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public void getSupplierValue(JTable table, String search) {
        String sql = "select * from supplier where concat(sid,sname,semail) like ? order by sid desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);

                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int id, String username, String email, String pass, String Phone, String address, String address1) {
        String sql = "update supplier set sname =? ,semail =?, spassword =?, sphone =?, saddress1 =?, saddress2 =? where sid =?";
        try {

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, Phone);
            ps.setString(5, address);
            ps.setString(6, address1);
            ps.setInt(7, id);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Supplier data successfully updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account", "Delete Acount", JOptionPane.OK_CANCEL_OPTION, 0);
        if (x == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from supplier where sid = ?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Account deleted");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getSupplierid(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("select sid from supplier where semail =? ");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String getSupplierName(String email) {
        String supplierName = "";

        try {
            ps = con.prepareStatement("select sname from supplier where semail =? ");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplierName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplierName;
    }

    public String[] getSupplierValue(int id) {
        String[] value = new String[7];
        try {
            ps = con.prepareStatement("select * from supplier where sid =? ");
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

            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public int CountSuppliers() {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from supplier");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public String[] getSuppliers() {
        String[] suppliers = new String[CountSuppliers()];
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from supplier");
            int i = 0;
            while (rs.next()) {
                suppliers[i] = rs.getString(2);
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }

}