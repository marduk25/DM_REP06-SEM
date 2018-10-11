package com.etiquetas.qrsys.bean;

import com.etiquetas.qrsys.dao.FacturaDao;
import com.etiquetas.qrsys.dao.FacturaDaoImp;
import com.etiquetas.qrsys.dao.SerieDao;
import com.etiquetas.qrsys.dao.SerieDaoImp;
import com.etiquetas.qrsys.e.model.*;
import com.etiquetas.qrsys.s.model.*;
import java.io.Serializable;
import static java.lang.Boolean.TRUE;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named(value = "ventasBean")
@ViewScoped
public class VentasBean implements Serializable {
    
    private Serie serie;
    private Factf01 fac;
    private List<Factura> listaFacturasV;
    private List<Series> listaSeriesV;
    private int departamento;
    private Date f1;
    private Date f2;
    private List<Factura> listaFacturasVentas;
    private List<Serie> listaSeriesImpresion;
    private int contador;
    private String noFacturaImpresion;
    private List<String> listaSeriesEstado1;
    
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
    
    public List<Factura> getListaFacturasVentas() {
        return listaFacturasVentas;
    }
    
    public List<Serie> getListaSeriesImpresion() {
        return listaSeriesImpresion;
    }
    
    public int getContador() {
        return contador;
    }
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public String getNoFacturaImpresion() {
        return noFacturaImpresion;
    }
    
    public void setNoFacturaImpresion(String noFacturaImpresion) {
        this.noFacturaImpresion = noFacturaImpresion;
    }
    
    public List<String> getListaSeriesEstado1() {
        return listaSeriesEstado1;
    }
    
    public void setListaSeriesEstado1(List<String> listaSeriesEstado1) {
        this.listaSeriesEstado1 = listaSeriesEstado1;
    }
    
    public void buscarFacturasVentas() {
        ListasSaeSave dao;
        try {
            dao = new ListasSaeSave();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MMMM-dd");//Formato para fecha en Español-España SQL Server 2012 Marubeni
            String fechaInicial = format.format(f1);
            String fechaFinal = format.format(f2);
            dao.listarVentas(fechaInicial, fechaFinal, departamento);
            listaFacturasVentas = new ArrayList<>();
            FacturaDao fDao = new FacturaDaoImp();
            listaFacturasVentas = fDao.listaFacturaVenta();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listarSeriesimpresion(String nofactura) {
        if (!nofactura.isEmpty()) {
            SerieDao sDao = new SerieDaoImp();
            listaSeriesImpresion = sDao.listarSeriesImprVentas(nofactura);
        }
    }
    
    public void imprimirEtiquetas() {
        this.contador = 0;
        this.noFacturaImpresion = null;
        this.listaSeriesEstado1 = new ArrayList<>();
        for (int i = 0; i < listaSeriesImpresion.size(); i++) {
            if (listaSeriesImpresion.get(i).getSeleccionar().equals(TRUE)) {
                listaSeriesEstado1.add(listaSeriesImpresion.get(i).getSerie());
                SerieDao sDao = new SerieDaoImp();
                sDao.updateNoImpresion(listaSeriesImpresion.get(i).getSerie());
                this.noFacturaImpresion = listaSeriesImpresion.get(i).getFactura().getNofactura();
            } else {
                this.contador++;
            }
        }
        listarSeriesimpresion(noFacturaImpresion);
        //**ABRIMOS EL DIALOGO PARA IMPRIMIR**//
        RequestContext.getCurrentInstance().execute("PF('dlgImpresion').show()");
        if (this.contador == listaSeriesImpresion.size()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "Selecciona etiquetas"));
        }
    }
    
    public void regresarEstadoFalse() {
        String dato = listaSeriesEstado1.toString().replace("[", "").replace("]", "");
        String[] info = dato.split(",");
        for (int i = 0; i < listaSeriesEstado1.size(); i++) {
            SerieDao sDao = new SerieDaoImp();
            sDao.updateSerieEstadoCero(info[i].trim());
        }
        listarSeriesimpresion(noFacturaImpresion);
    }
    
}
