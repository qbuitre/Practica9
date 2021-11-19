/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diu.ulpgc.es.practica9;
        
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

/**
 *
 * @author Jorge Santana
 */
public class DB {
    private Connection connect;
    private DatabaseMetaData mdata;
    
    public DB() {
        
    }
    

    public boolean connectDB(String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://i7-lab5.dis.ulpgc.es/DIU_BD?useSSL=true", user, password);
            mdata = connect.getMetaData();
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
        return true;
        
    }
    
    public List readTables() throws SQLException {
        String[] types = {"TABLE"};
        ResultSet rs = mdata.getTables(null, null, "%", types);
        List<String> tables = new ArrayList<>();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
       /*     String nombreTabla = rs.getString("TABLE_NAME");
            System.out.println("Tabla: " + nombreTabla);*/
        }
        return tables;
    }
    
    public List readColumns(String table) throws SQLException{
        ResultSet rs2 = mdata.getColumns(null, null, table, null);
        List<String> columns = new ArrayList<>();
        while (rs2.next()) {
            columns.add(rs2.getString("COLUMN_NAME"));
        /*    String nombreCampo = rs2.getString("COLUMN_NAME");
            System.out.println(" Campo: " + nombreCampo);*/
        }
        return columns;
    }

    public void closeConnection(){
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
