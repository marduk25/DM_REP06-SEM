package com.etiquetas.qrsys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  
    
    private final String URL = "jdbc:sqlserver://10.130.22.211\\SQLEXPRESS;databaseName=SAE70Empre01";
    private final String USERNAME = "sa";
    private final String USERNAME2 = "gastos_usrdb";
    private final String PASS = "aspel**2012";
    private final String PASS2 = "k1CruJ@raDix";
    private final String URL2 = "jdbc:sqlserver://10.220.221.158\\SQLEXPRESS;databaseName=dbqrsys";
    
    
//    private final String URL = "jdbc:sqlserver://DESKTOP-DOKG39Q\\SQLEXPRESS2017;databaseName=SAE70Empre01";
//    private final String USERNAME = "sa";
//    private final String USERNAME2 = "sa";
//    private final String PASS = "desarrollo";
//    private final String PASS2 = "desarrollo";
//    private final String URL2 = "jdbc:sqlserver://DESKTOP-DOKG39Q\\SQLEXPRESS2017;databaseName=dbqrsys";

    private Connection cn;
    private Connection cnqr;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public Connection getCnqr() {
        return cnqr;
    }

    public void setCnqr(Connection cnqr) {
        this.cnqr = cnqr;
    }

    public void Conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
        }

    }

    public void Cerrar() throws SQLException {
        try {
            if (cn != null) {
                if (cn.isClosed() == false) {
                    cn.close();
                }
            }
        } catch (SQLException e) {
            throw e;
        }

    }

    public void ConectarQr() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnqr = DriverManager.getConnection(URL2, USERNAME2, PASS2);
        } catch (ClassNotFoundException | SQLException e) {
        }

    }

    public void CerrarQr() throws SQLException {
        try {
            if (cnqr != null) {
                if (cnqr.isClosed() == false) {
                    cnqr.close();
                }
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
