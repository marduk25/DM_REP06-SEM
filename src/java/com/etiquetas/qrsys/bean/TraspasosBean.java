package com.etiquetas.qrsys.bean;

import com.etiquetas.qrsys.e.model.Factura;
import com.etiquetas.qrsys.e.model.Serie;
import com.etiquetas.qrsys.e.model.Series;
import com.etiquetas.qrsys.s.model.Factf01;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "traspasosBean")
@ViewScoped
public class TraspasosBean implements Serializable {

    private Serie serie;
    private Factf01 fac;
    private List<Factura> listaFacturasV;
    private List<Series> listaSeriesV;
    private int departamento;
    private Date f1;
    private Date f2;

    public TraspasosBean() {
        serie = new Serie();
        fac = new Factf01();
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Factf01 getFac() {
        return fac;
    }

    public void setFac(Factf01 fac) {
        this.fac = fac;
    }

    public List<Series> getListaSeriesV() {
        return listaSeriesV;
    }

    public void setListaSeriesV(List<Series> listaSeriesV) {
        this.listaSeriesV = listaSeriesV;
    }

    public List<Factura> getListaFacturasV() {
        return listaFacturasV;
    }

    public void setListaFacturasV(List<Factura> listaFacturasV) {
        this.listaFacturasV = listaFacturasV;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public Date getF1() {
        return f1;
    }

    public void setF1(Date f1) {
        this.f1 = f1;
    }

    public Date getF2() {
        return f2;
    }

    public void setF2(Date f2) {
        this.f2 = f2;
    }

    public void buscarTraspasosEntrada() {
        ListasSae dao;
        try {
            dao = new ListasSae();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMMM-dd");//Formato para fecha en Español-España SQL Server 2012 Marubeni
            String fechaInicial = format.format(f1);
            String fechaFinal = format.format(f2);
            listaFacturasV = dao.listarFacturasTraspasosEntrada(fechaInicial, fechaFinal, departamento);
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarSeriesTraspasosEntrada(String factura) {
        ListasSae dao;
        try {
            dao = new ListasSae();
            listaSeriesV = dao.listarSeriesTraspasosEntrada(factura);
        } catch (Exception e) {
            throw e;
        }

    }

    public void buscarTraspasosSalida() {
        ListasSae dao;
        try {
            dao = new ListasSae();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMMM-dd");//Formato para fecha en Español-España SQL Server 2012 Marubeni
            String fechaInicial = format.format(f1);
            String fechaFinal = format.format(f2);
            listaFacturasV = dao.listarFacturasTraspasosSalida(fechaInicial, fechaFinal, departamento);
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarSeriesTraspasosSalida(String factura) {
        ListasSae dao;
        try {
            dao = new ListasSae();
            listaSeriesV = dao.listarSeriesTraspasosSalida(factura);
        } catch (Exception e) {
            throw e;
        }

    }
    

}
