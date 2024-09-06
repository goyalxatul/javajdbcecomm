package dao;

import admin.AdminDashboard;
import connection.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import supplier.supplierDashboard;
import user.userdashboard;

public class Statistics {

    Connection con = Myconnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    private int total(String tableName) {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from " + tableName + "");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    private double totalSales() {
        double total = 0.0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select sum(total) as 'total' from purchase");
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    private double todaySales() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        String today = df.format(date);

        double total = 0.0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select sum(total) as 'total' from purchase where p_date =" + today + "'");
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    private double totalpurchase(int id) {
        double total = 0.0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select sum(total) as 'total' from purchase where uid =" + id + "");
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public void admin() {
        AdminDashboard.jLabel3.setText(String.valueOf(total("category")));
        AdminDashboard.jLabel28.setText(String.valueOf(total("product")));
        AdminDashboard.jLabel9.setText(String.valueOf(total("user")));
        AdminDashboard.jLabel27.setText(String.valueOf(total("supplier")));
        AdminDashboard.jLabel29.setText(String.valueOf(totalSales()));
        AdminDashboard.jLabel30.setText(String.valueOf(todaySales()));
    }

    public void user(int id) {
        userdashboard.jLabel16.setText(String.valueOf(total("category")));
        userdashboard.jLabel15.setText(String.valueOf(total("product")));
        userdashboard.jLabel3.setText(String.valueOf(totalpurchase(id)));

    }
    public void supplier(String name){
        supplierDashboard.jLabel3.setText(String.valueOf(totalDeliveries(name)));
    }
    private int totalDeliveries(String name) {
        int total = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select count(*) as 'total' from  purchase where supplier =" + name + "and status = 'Recieved'");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
