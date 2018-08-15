package com.etiquetas.qrsys.bean;

import com.etiquetas.qrsys.dao.*;
import com.etiquetas.qrsys.e.model.*;
import com.etiquetas.qrsys.s.model.*;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "ventasBean")
@ViewScoped
public class VentasBean implements Serializable {

    private Serie serie;
    private Factf01 fac;
    private List<Factura> listaFacturasV;
    private List<Series> listaSeriesV;

    public VentasBean() {
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

    public void buscarFacturasVentas() {
        ListasSae dao;
        try {
            dao = new ListasSae();
            listaFacturasV = dao.listarVentas();
        } catch (Exception e) {
            throw e;
        }
    }

    public void buscarSeriesVentas(String factura) {
        ListasSae dao;
        try {
            dao = new ListasSae();
            listaSeriesV = dao.listarSeriesVentas(factura);
        } catch (Exception e) {
            throw e;
        }

    }

}
