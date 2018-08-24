package com.etiquetas.qrsys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SAE70Empre01";
    private final String USERNAME = "sa";
    private final String PASS = "8dev*sys#1";
    private final String URL2 = "jdbc:sqlserver://localhost:1433;databaseName=dbqrsys";

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
            cnqr = DriverManager.getConnection(URL2, USERNAME, PASS);
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
