package com.etiquetas.qrsys.bean;

import com.etiquetas.qrsys.dao.Conexion;
import com.etiquetas.qrsys.e.model.Factura;
import com.etiquetas.qrsys.e.model.Serie;
import com.etiquetas.qrsys.e.model.Series;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListasSae extends Conexion {

    public List<Factura> listarVentas() {
        List<Factura> lista = null;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT F.CVE_DOC, F.CVE_CLPV, F.FECHA_DOC, P.NOMBRE FROM FACTF01 AS F, PROV01 AS P WHERE F.CVE_CLPV=P.CLAVE AND F.FECHA_DOC>='2018-01-01' AND F.FECHA_DOC<='2018-08-14' AND F.NUM_ALMA='3'");
            rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Factura fVentas = new Factura();
                fVentas.setNofactura(rs.getString("CVE_DOC"));
                fVentas.setIdproveedor(rs.getString("CVE_CLPV"));
                fVentas.setFecha(rs.getDate("FECHA_DOC"));
                fVentas.setProveedor(rs.getString("NOMBRE"));
                lista.add(fVentas);
            }
            this.Cerrar();
        } catch (SQLException e) {
        }
        return lista;
    }

    public List<Series> listarSeriesVentas(String factura) {
        List<Series> listar = null;
        ResultSet rs;
        try {
            this.Conectar();
            listar = new ArrayList<>();
            PreparedStatement st = this.getCn().prepareCall("SELECT dbo.PAR_FACTF01.CVE_DOC AS CVE_DOC,\n"
                    + "dbo.PAR_FACTF01.CVE_ART AS CVE_ART,\n"
                    + "dbo.INVE01.DESCR AS DESCR,\n"
                    + "dbo.HNUMSER01.NUM_SER AS SERIES,\n"
                    + "dbo.LTPD01.PEDIMENTO AS PEDIMENTO,\n"
                    + "dbo.LTPD01.NOM_ADUAN AS ADUANA,\n"
                    + "dbo.LTPD01.FCHADUANA AS FECHA_ADUANA,\n"
                    + "dbo.LTPD01.STATUS\n"
                    + "FROM dbo.PAR_FACTF01, dbo.ENLACE_LTPD01, dbo.LTPD01, dbo.HNUMSER01, dbo.INVE01\n"
                    + "WHERE   \n"
                    + "PAR_FACTF01.REG_SERIE=HNUMSER01.REG_SERIE AND\n"
                    + "dbo.PAR_FACTF01.CVE_ART = dbo.INVE01.CVE_ART AND \n"
                    + "dbo.PAR_FACTF01.E_LTPD = dbo.ENLACE_LTPD01.E_LTPD AND \n"
                    + "dbo.ENLACE_LTPD01.REG_LTPD = dbo.LTPD01.REG_LTPD AND \n"
                    + "dbo.HNUMSER01.STATUS='V'  AND\n"
                    + "PAR_FACTF01.CVE_DOC ='" + factura + "'");
            rs = st.executeQuery();
            while (rs.next()) {
                Series sVentas = new Series();
                sVentas.setFactura(rs.getString("CVE_DOC"));
                sVentas.setArticulo(rs.getString("CVE_ART"));
                sVentas.setDescripcion(rs.getString("DESCR"));
                sVentas.setSerie(rs.getString("SERIES"));
                sVentas.setPedimento(rs.getString("PEDIMENTO"));
                sVentas.setAduana(rs.getString("ADUANA"));
                sVentas.setFechapedimento(rs.getDate("FECHA_ADUANA"));
                sVentas.setSeleccionar(Boolean.FALSE);
                listar.add(sVentas);
            }
            this.Cerrar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listar;
    }

}
