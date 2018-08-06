package com.etiquetas.qrsys.bean;

import com.etiquetas.qrsys.dao.Almacenes01Dao;
import com.etiquetas.qrsys.dao.Almacenes01DaoImp;
import com.etiquetas.qrsys.dao.Compc01Dao;
import com.etiquetas.qrsys.dao.Compc01DaoImp;
import com.etiquetas.qrsys.dao.CompcClib01Dao;
import com.etiquetas.qrsys.dao.CompcClib01DaoImp;
import com.etiquetas.qrsys.dao.EnlaceLtpd01Dao;
import com.etiquetas.qrsys.dao.EnlaceLtpd01DaoImp;
import com.etiquetas.qrsys.dao.FacturaDao;
import com.etiquetas.qrsys.dao.FacturaDaoImp;
import com.etiquetas.qrsys.dao.Foliosc01Dao;
import com.etiquetas.qrsys.dao.Foliosc01DaoImp;
import com.etiquetas.qrsys.dao.Hnumser01Dao;
import com.etiquetas.qrsys.dao.Hnumser01DaoImp;
import com.etiquetas.qrsys.dao.Impu01Dao;
import com.etiquetas.qrsys.dao.ImpuDaoImp;
import com.etiquetas.qrsys.dao.Ltpd01Dao;
import com.etiquetas.qrsys.dao.Ltpd01DaoImp;
import com.etiquetas.qrsys.dao.Minve01Dao;
import com.etiquetas.qrsys.dao.Minve01DaoImp;
import com.etiquetas.qrsys.dao.Moned01Dao;
import com.etiquetas.qrsys.dao.MonedaDaoImp;
import com.etiquetas.qrsys.dao.Obsdocc01Dao;
import com.etiquetas.qrsys.dao.Obsdocc01DaoImp;
import com.etiquetas.qrsys.dao.ParCompc01Dao;
import com.etiquetas.qrsys.dao.ParCompc01Imp;
import com.etiquetas.qrsys.dao.ParCompcClib01Dao;
import com.etiquetas.qrsys.dao.ParCompcClib01DaoImp;
import com.etiquetas.qrsys.dao.Prov01Dao;
import com.etiquetas.qrsys.dao.Prov01DaoImp;
import com.etiquetas.qrsys.dao.SerieDao;
import com.etiquetas.qrsys.dao.SerieDaoImp;
import com.etiquetas.qrsys.dao.Tblcontrol01Dao;
import com.etiquetas.qrsys.dao.Tblcontrol01DaoImpl;
import com.etiquetas.qrsys.s.model.Almacenes01;
import com.etiquetas.qrsys.e.model.Factura;
import com.etiquetas.qrsys.s.model.Impu01;
import com.etiquetas.qrsys.s.model.Moned01;
import com.etiquetas.qrsys.s.model.Prov01;
import com.etiquetas.qrsys.e.model.Serie;
import com.etiquetas.qrsys.e.model.Usuario;
import com.etiquetas.qrsys.s.model.Compc01;
import com.etiquetas.qrsys.s.model.CompcClib01;
import com.etiquetas.qrsys.s.model.EnlaceLtpd01;
import com.etiquetas.qrsys.s.model.Ltpd01;
import com.etiquetas.qrsys.s.model.ObsDocc01;
import com.etiquetas.qrsys.s.model.ParCompc01;
import com.etiquetas.qrsys.s.model.ParCompcClib01;
import java.io.Serializable;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named(value = "facturaBean")
@ViewScoped
public class FacturaBean implements Serializable {

    private Factura factura;
    private List<Factura> listarFactura;
    private Prov01 proveedor;
    private List<SelectItem> listaProveedores;
    private Impu01 impuesto;
    private List<SelectItem> listaImpuestos;
    private Moned01 moneda;
    private List<SelectItem> listaMonedas;
    private List<SelectItem> listaTipoCambio;
    private Almacenes01 almacen;
    private List<SelectItem> listaAlmacenes;
    private Serie series;
    private List<Serie> listarSeries;
    private Serie serieEditar;
    private Factura fact;
    private Factura invoice;
    private List<Serie> listaSeriesIngresadas;
    private Serie serie;
    private List<Serie> listaSeriesImpresion;
    private int contador;
    private EtiquetaBean etiqueta;
    private String noFacturaImpresion;
    private Factura facturaObservacion;
    private Factura facturaRegistro;
    private ObsDocc01 obsdoc;
    private Compc01 compc;
    private int valorMoneda = 0;
    private CompcClib01 compcclib01;
    private Ltpd01 lp;
    private List<String> listaSeriesSaeReg;
    private EnlaceLtpd01 enlace;
    private ParCompc01 parcompc;
    private ParCompcClib01 clib01;
    Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    public FacturaBean() {
        factura = new Factura();
        proveedor = new Prov01();
        impuesto = new Impu01();
        moneda = new Moned01();
        almacen = new Almacenes01();
        series = new Serie();
        serieEditar = new Serie();
        fact = new Factura();
        invoice = new Factura();
        serie = new Serie();
        etiqueta = new EtiquetaBean();
        facturaObservacion = new Factura();
        facturaRegistro = new Factura();
        obsdoc = new ObsDocc01();
        compc = new Compc01();
        compcclib01 = new CompcClib01();
        lp = new Ltpd01();
        enlace = new EnlaceLtpd01();
        parcompc = new ParCompc01();
        clib01 = new ParCompcClib01();
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Factura> getListarFactura() {
        FacturaDao fDao = new FacturaDaoImp();
        listarFactura = fDao.listaFactura();
        return listarFactura;
    }

    public Prov01 getProveedor() {
        return proveedor;
    }

    public void setProveedor(Prov01 proveedor) {
        this.proveedor = proveedor;
    }

    public Impu01 getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impu01 impuesto) {
        this.impuesto = impuesto;
    }

    public Moned01 getMoneda() {
        return moneda;
    }

    public void setMoneda(Moned01 moneda) {
        this.moneda = moneda;
    }

    public Almacenes01 getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacenes01 almacen) {
        this.almacen = almacen;
    }

    public Serie getSeries() {
        return series;
    }

    public void setSeries(Serie series) {
        this.series = series;
    }

    public Serie getSerieEditar() {
        return serieEditar;
    }

    public void setSerieEditar(Serie serieEditar) {
        this.serieEditar = serieEditar;
    }

    public Factura getFact() {
        return fact;
    }

    public void setFact(Factura fact) {
        this.fact = fact;
    }

    public Factura getInvoice() {
        return invoice;
    }

    public void setInvoice(Factura invoice) {
        this.invoice = invoice;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public EtiquetaBean getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(EtiquetaBean etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getNoFacturaImpresion() {
        return noFacturaImpresion;
    }

    public void setNoFacturaImpresion(String noFacturaImpresion) {
        this.noFacturaImpresion = noFacturaImpresion;
    }

    public Factura getFacturaObservacion() {
        return facturaObservacion;
    }

    public void setFacturaObservacion(Factura facturaObservacion) {
        this.facturaObservacion = facturaObservacion;
    }

    public Factura getFacturaRegistro() {
        return facturaRegistro;
    }

    public void setFacturaRegistro(Factura facturaRegistro) {
        this.facturaRegistro = facturaRegistro;
    }

    public ObsDocc01 getObsdoc() {
        return obsdoc;
    }

    public void setObsdoc(ObsDocc01 obsdoc) {
        this.obsdoc = obsdoc;
    }

    public Compc01 getCompc() {
        return compc;
    }

    public void setCompc(Compc01 compc) {
        this.compc = compc;
    }

    public int getValorMoneda() {
        return valorMoneda;
    }

    public void setValorMoneda(int valorMoneda) {
        this.valorMoneda = valorMoneda;
    }

    public CompcClib01 getCompcclib01() {
        return compcclib01;
    }

    public void setCompcclib01(CompcClib01 compcclib01) {
        this.compcclib01 = compcclib01;
    }

    public Ltpd01 getLp() {
        return lp;
    }

    public void setLp(Ltpd01 lp) {
        this.lp = lp;
    }

    public EnlaceLtpd01 getEnlace() {
        return enlace;
    }

    public void setEnlace(EnlaceLtpd01 enlace) {
        this.enlace = enlace;
    }

    public ParCompc01 getParcompc() {
        return parcompc;
    }

    public void setParcompc(ParCompc01 parcompc) {
        this.parcompc = parcompc;
    }

    public ParCompcClib01 getClib01() {
        return clib01;
    }

    public void setClib01(ParCompcClib01 clib01) {
        this.clib01 = clib01;
    }

    public List<String> getListaSeriesSaeReg() {
        return listaSeriesSaeReg;
    }

    public List<SelectItem> getListaProveedores() {
        listaProveedores = new ArrayList<>();
        Prov01Dao pDao = new Prov01DaoImp();
        List<Prov01> prov = pDao.listaProveedores();
        listaProveedores.clear();

        for (Prov01 nombrePro : prov) {
            SelectItem nombreProv = new SelectItem(nombrePro.getNombre() + "-" + nombrePro.getClave());
            listaProveedores.add(nombreProv);
        }
        return listaProveedores;
    }

    public List<SelectItem> getListaImpuestos() {
        listaImpuestos = new ArrayList<>();
        Impu01Dao iDao = new ImpuDaoImp();
        List<Impu01> impu = iDao.listarImpuestos();
        listaImpuestos.clear();
        for (Impu01 impu01 : impu) {
            SelectItem imp = new SelectItem(impu01.getDescripesq());
            listaImpuestos.add(imp);
        }
        return listaImpuestos;
    }

    public List<SelectItem> getListaMonedas() {
        listaMonedas = new ArrayList<>();
        Moned01Dao mDao = new MonedaDaoImp();
        List<Moned01> mon = mDao.listarMonedas();
        listaMonedas.clear();
        for (Moned01 moned01 : mon) {
            SelectItem tmon = new SelectItem(moned01.getDescr());
            listaMonedas.add(tmon);
        }
        return listaMonedas;
    }

    public List<SelectItem> getListaTipoCambio() {
        listaTipoCambio = new ArrayList<>();
        Moned01Dao mDao = new MonedaDaoImp();
        List<Moned01> tcam = mDao.listarMonedas();
        listaTipoCambio.clear();
        for (Moned01 moned01 : tcam) {
            SelectItem tcambio = new SelectItem(new BigDecimal(moned01.getTcambio()));
            listaTipoCambio.add(tcambio);
        }
        return listaTipoCambio;
    }

    public List<SelectItem> getListaAlmacenes() {
        listaAlmacenes = new ArrayList<>();
        Almacenes01Dao aDao = new Almacenes01DaoImp();
        List<Almacenes01> alm = aDao.listaAlmacenes();
        listaAlmacenes.clear();
        for (Almacenes01 almacenes01 : alm) {
            SelectItem almac = new SelectItem(almacenes01.getDescr() + "-" + almacenes01.getCveAlm());
            listaAlmacenes.add(almac);
        }
        return listaAlmacenes;
    }

    public void actualizarFactura() {
        //**OBTENEMOS EL MÁXIMO VALOR DE LA TABLA OBS_DOCC01**//
        Obsdocc01Dao obsDao = new Obsdocc01DaoImp();

        //**GUARDAMOS LA INFORMACION DEL CAMPO OBSERVACION EN LA TABLA OBS_DOCC01**//
        Obsdocc01Dao oDao = new Obsdocc01DaoImp();
        String dato = obsDao.obtenerMaximoValor().toString().replace("[", "");
        dato = dato.replace("]", "");
        obsdoc.setCveObs(new Integer(dato));
        //obsdoc.setStrObs(this.facturaObservacion.getObservacion());
        obsdoc.setStrObs(factura.getObservacion());
        oDao.saveObservacion(obsdoc);

        //**ACTUALIZAMOS LA TABLA TBLCONTROL01 CAMPO ULT_CVE ID_TABLE=57 CON EL DATO DEL MAXIMO VALOR DE LA TABLA OBS_DOCC01**//
        Tblcontrol01Dao tDao = new Tblcontrol01DaoImpl();
        tDao.updateTblControl(Integer.parseInt(dato));

        FacturaDao fDao = new FacturaDaoImp();
        String valorCodProv = factura.getProveedor();
        String[] codprov = valorCodProv.split("-");
        String valorCodAlm = factura.getAlmacen();
        String[] codAlm = valorCodAlm.split("-");
        factura.setIdusuario(user.getIdusuario());
        factura.setTipo("Compra");
        factura.setSubtipo("Entrada");
        factura.setAlmacen(codAlm[0]);
        factura.setIdalmacen(Integer.parseInt(codAlm[1]));
        factura.setProveedor(codprov[0]);
        factura.setIdproveedor(codprov[1]);
        factura.setCveobs(Integer.parseInt(dato));

        //**OBTENEMOS LA INFORMACION DEL CAMPO OBSERVACION DE LA TABLA FACTURA**//
        facturaRegistro = new Factura();
        FacturaDao facRegDao = new FacturaDaoImp();
        this.facturaRegistro = facRegDao.returnIdFactura(factura);

        //**GUARDAMOS EN LA TABLA COMPC01**//
        Compc01Dao cDao = new Compc01DaoImp();
        compc.setTipDoc("c");
        compc.setCveClpv(this.facturaRegistro.getIdproveedor());
        compc.setStatus("O");
        compc.setSuRefer(this.facturaRegistro.getNofactura());
        Compc01Dao comDao = new Compc01DaoImp();
        String folio = comDao.obtenerMaximoValor().toString().replace("[", "");
        folio = folio.replace("]", "");
        String x = "";
        for (int i = 0; i < (20 - folio.length()); i++) {
            x = x + " ";
        }
        factura.setCvedoc(folio);
        fDao.updateFactura(factura);
        compc.setCveDoc(x.concat(folio));
        compc.setFechaDoc(facturaRegistro.getFecha());
        compc.setFechaRec(facturaRegistro.getFecha());
        compc.setFechaPag(facturaRegistro.getFecha());
        compc.setCanTot(0.0);
        compc.setImpTot1(0.0);
        compc.setImpTot2(0.0);
        compc.setImpTot3(0.0);
        compc.setImpTot4(0.0);
        compc.setDesTot(0.0);
        compc.setDesFin(0.0);
        compc.setTotInd(0.0);
        compc.setObsCond(null);
        compc.setCveObs(this.facturaRegistro.getCveobs());
        compc.setNumAlma(3);
        compc.setActCxp("S");
        compc.setActCoi("N");
        compc.setEnlazado("O");
        compc.setTipDocE("O");
        switch (this.facturaRegistro.getMoneda()) {
            case "PESOS":
                valorMoneda = 1;
                break;
            case "USD":
                valorMoneda = 2;
                break;
            case "LIBRAS":
                valorMoneda = 3;
                break;
            case "EUROS":
                valorMoneda = 4;
                break;
            default:
                valorMoneda = 5;
                break;
        }
        compc.setNumMoned(valorMoneda);
        compc.setTipcamb(this.facturaRegistro.getTipocambio());
        compc.setNumPagos(1);
        compc.setFechaelab(this.facturaRegistro.getFecha());
        compc.setSerie("");
        compc.setFolio(Integer.parseInt(folio));
        compc.setCtlpol(0);
        compc.setEscfd("N");
        compc.setContado("N");
        compc.setBloq("N");
        compc.setDesFinPorc(0.0);
        compc.setDesTotPorc(0.0);
        compc.setDesTotPorc(0.0);
        compc.setImporte(0.0);
        cDao.saveComc01(compc);

        //**ACTUALIZAMOS LA TABLA FOLIOSC01 CON ULT_DOC Y FECH_ULT_DOC**//
        Foliosc01Dao folDao = new Foliosc01DaoImp();
        folDao.updateFolio(Integer.parseInt(folio), this.facturaObservacion.getFecha());

        //**GUARDAMOS EL ÚLTIMO DOCUMENTO EN LA TABLA COMPC_CLIB01**//
        compcclib01.setClaveDoc(x.concat(folio));
        CompcClib01Dao compcClibDao = new CompcClib01DaoImp();
        compcClibDao.saveCompcClib(compcclib01);

        factura = new Factura();
        compc = new Compc01();
    }

    public List<Serie> getListarSeries() {
        return listarSeries;
    }

    public List<Serie> getListaSeriesIngresadas() {
        return listaSeriesIngresadas;
    }

    public List<Serie> getListaSeriesImpresion() {
        return listaSeriesImpresion;
    }

    public void listaSeriesCargadas(String nofactura) {
        if (!nofactura.isEmpty()) {
            SerieDao sDao = new SerieDaoImp();
            listaSeriesIngresadas = sDao.listaDeSeries(nofactura);
        }
    }

    public void listaSerie(String nofactura) {
        if (!nofactura.isEmpty()) {
            SerieDao sDao = new SerieDaoImp();
            //listarSeries = sDao.listaSeriesPorFactura(nofactura);
            listarSeries = sDao.listaSeriesConInformacion(nofactura);
        }
    }

    public void procesar() {
        if (serieEditar.getAduana().isEmpty() && serieEditar.getPedimento().isEmpty() && serieEditar.getLote().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "Campos requeridos"));
        }
        SerieDao sDao = new SerieDaoImp();
        for (int i = 0; i < listarSeries.size(); i++) {
            if (listarSeries.get(i).getSeleccionar().equals(TRUE)) {
                FacturaDao facDao = new FacturaDaoImp();
                invoice.setNofactura(listarSeries.get(i).getFactura().getNofactura());
                this.fact = facDao.returnIdFactura(invoice);
                series.setFactura(fact);
                series.setUsuario(user);
                series.setIdserie(listarSeries.get(i).getIdserie());
                series.setSerie(listarSeries.get(i).getSerie());
                series.setArticulo(listarSeries.get(i).getArticulo());
                series.setDescripcion(listarSeries.get(i).getDescripcion());
                series.setAduana(serieEditar.getAduana());
                series.setPedimento(serieEditar.getPedimento());
                series.setFechapedimento(serieEditar.getFechapedimento());
                series.setLote(serieEditar.getLote());
                series.setImpreso(0);
                series.setSeleccionar(FALSE);
                series.setSae(FALSE);
                //**ACTUALIZAMOS LOS DATOS ADUANA, PEDIMENTO, FECHA Y LOTE EN LA TABLA SERIE**//
                sDao.updateSerie(series);

            }
        }

        //**OBTENEMOS LA INFORMACION DEL CAMPO OBSERVACION DE LA TABLA FACTURA**//
        facturaObservacion = new Factura();
        FacturaDao facDao = new FacturaDaoImp();
        this.facturaObservacion = facDao.returnIdFactura(invoice);

        //**OBTENEMOS LA LISTA DE LAS SERIES ACTUALIZADAS**//
        SerieDao serRegDao = new SerieDaoImp();
        listaSeriesSaeReg = serRegDao.listarSeriesSaeRegistro(user.getIdusuario(), serieEditar.getPedimento());
        for (int i = 0; i < listaSeriesSaeReg.size(); i++) {

            //**OBTENEMOS EL MAXIMO VALOR DE LA TABLA LTPD01 DEL CAMPO REG_LTPD**//
            Ltpd01Dao lDao = new Ltpd01DaoImp();
            String maxVal = lDao.obtenerMaximoValor().toString().replace("[", "").replace("]", "");

            //**OBTENEMOS EL TOTAL DE LAS SERIES**//
            String miArt = listaSeriesSaeReg.toString().replace("[", "").replace("]", "");
            String[] art = miArt.split(",");

            //**GUARDAMOS EL TOTAL DE LAS SERIES EN LA TABLA LTPD01**//
            SerieDao serieDao = new SerieDaoImp();
            String conteo = serieDao.listarSeriesSae(user.getIdusuario(), serieEditar.getPedimento(), art[i].trim()).toString().replace("[", "").replace("]", "");
            lp.setCveArt(art[i].trim());
            lp.setLote(serieEditar.getLote());
            lp.setPedimento(serieEditar.getPedimento());
            lp.setCveAlm(this.facturaObservacion.getIdalmacen());
            lp.setFchaduana(serieEditar.getFechapedimento());
            lp.setFchultmov(serieEditar.getFechapedimento());
            lp.setNomAduan(serieEditar.getAduana());
            lp.setCantidad(new Double(conteo));
            lp.setRegLtpd(Integer.parseInt(maxVal));
            lp.setCveObs(this.facturaObservacion.getCveobs());
            lp.setCiudad("");
            lp.setFrontera("");
            lp.setStatus("A");
            lp.setPedimentosat(serieEditar.getPedimento());
            Ltpd01Dao lpDao = new Ltpd01DaoImp();
            lpDao.saveLtp01(lp);

            //**OBTENEMOS EL MAXIMO VALOR DE LA TABLA LTPD01 DEL CAMPO REG_LTPD**//
            Ltpd01Dao regDao = new Ltpd01DaoImp();
            String maxReg = regDao.obtenerMaximoValor().toString().replace("[", "").replace("]", "");

            //**ACTUALIZAMOS LA TABLA TBLCONTROL01 CAMPO ULT_CVE='maxVal'-ID_TABLE=48**//
            Tblcontrol01Dao tablaDao = new Tblcontrol01DaoImpl();
            tablaDao.updateTablaControl(Integer.parseInt(maxReg));

            //**OBTENEMOS EL VALOR MAXIMO DE LA TABLA ENLACE_LTPD01**//
            EnlaceLtpd01Dao eDao = new EnlaceLtpd01DaoImp();
            String maxRegEnlace = eDao.obtenerMaximoValor().toString().replace("[", "").replace("]", "");

            //**GUARDAMOS EN LA TABLA ENLACE_LTPD01 LOS VALORES EN LOS CAMPOS E_LTPD,REG_LTPD,CANTIDAD,PXRS**//
            EnlaceLtpd01Dao enDao = new EnlaceLtpd01DaoImp();
            enlace.seteLtpd(Integer.parseInt(maxRegEnlace));
            enlace.setRegLtpd(Integer.parseInt(maxReg));
            enlace.setCantidad(Double.parseDouble(conteo));
            enlace.setPxrs(Double.parseDouble(conteo));
            enDao.saveEnlaceLtpd01(enlace);

            //**ACTUALIZAMOS LA TABLA TBLCONTROL01 SET ULT_CVE = 'MAX(E_LTPD)' WHERE ID_TABLA=67**//
            Tblcontrol01Dao taDao = new Tblcontrol01DaoImpl();
            taDao.updateTbl67Control(Integer.parseInt(maxRegEnlace));
            //**OBTENEMOS EL MAXIMO VALOR DE LA TABLA HNUMSER01**//
            Hnumser01Dao hDao = new Hnumser01DaoImp();
            String maxRegHnumser = hDao.obtenerMaximoValor().toString().replace("[", "").replace("]", "");
            //**GUARDAMOS EN LA TABLA PAR_COMPC01**//
            ParCompc01Dao parDao = new ParCompc01Imp();
            parcompc.setCveDoc(facturaObservacion.getCvedoc());
            parcompc.setNumPar(i);//buscar el número de partida en esta tabla PAR_COMPC01
            parcompc.setCveArt(art[i].trim());
            parcompc.setCant(Double.parseDouble(conteo));
            parcompc.setPxr(Double.parseDouble(conteo));
            parcompc.setPrec(0.0);
            parcompc.setCost(0.0);
            parcompc.setImpu1(0.0);
            parcompc.setImpu2(0.0);
            parcompc.setImpu3(0.0);
            parcompc.setImpu4(0.0);
            parcompc.setImp1apla(Short.parseShort("0"));
            parcompc.setImp2apla(Short.parseShort("0"));
            parcompc.setImp3apla(Short.parseShort("0"));
            parcompc.setImp4apla(Short.parseShort("0"));
            parcompc.setTotimp1(0.0);
            parcompc.setTotimp2(0.0);
            parcompc.setTotimp3(0.0);
            parcompc.setTotimp4(0.0);
            parcompc.setDescu(0.0);
            parcompc.setActInv("S");
            parcompc.setTipCam(facturaObservacion.getTipocambio());
            parcompc.setUniVenta("PZ");
            parcompc.setTipoElem("N");
            parcompc.setTipoProd("P");
            parcompc.setCveObs(0);
            parcompc.setRegSerie(Integer.parseInt(maxRegHnumser));
            parcompc.setELtpd(Integer.parseInt(maxRegEnlace));
            parcompc.setFactconv(1.0);
            parcompc.setCostDev(0.0);
            parcompc.setNumAlm(facturaObservacion.getIdalmacen());
            parcompc.setMindirecto(0.0);
            //**OBTENEMOS EL MAXIMO VALOR DE LA TABLA MINVE01 DEL CAMPO NUM_MOV**//
            Minve01Dao mDao = new Minve01DaoImp();
            String maxValMinve = mDao.obtenerMaximoValor().toString().replace("[", "").replace("]", "");
            parcompc.setNumMov(Integer.parseInt(maxValMinve));///Colocar el NUM_MOV
            parcompc.setTotPartida(0.0);
            parDao.saveParCompc01(parcompc);

            //**GUARDAMOS EN LA TABLA PAR_COMPC_CLIB01**//
            ParCompcClib01Dao clibDao = new ParCompcClib01DaoImp();
            clib01.setClaveDoc(facturaObservacion.getCvedoc());
            clib01.setNumPart(i);//colocarl el número de partida
            clibDao.saveParCompcClib(clib01);
            
            conteo = "";
            maxVal = "";
            maxReg = "";
            maxRegEnlace = "";
            maxValMinve = "";
        }
        //**ACTUALIZAMOS LA TABLA SERIES CAMPO SAE A 1**//
        SerieDao serDao = new SerieDaoImp();
        List<String> serieSae = null;
        serieSae = serDao.listarSeriesSaeEstado1(user.getIdusuario(), serieEditar.getPedimento());
        for (int j = 0; j < serieSae.size(); j++) {
            serDao.updateSerieSae1(serieSae.get(j).replace("[", "").replace("]", ""));
        }
        //**LIMPIAMOS LOS OBJETOS**//
        compcclib01 = new CompcClib01();
        obsdoc = new ObsDocc01();
        lp = new Ltpd01();
        parcompc = new ParCompc01();
        clib01 = new ParCompcClib01();
        //**LIMPIAMOS LOS OBJETOS**//

        //**LISTAMOS LA INFORMACION DE LAS SERIES SIN INFORMACIÓN CORRESPONDIENTES A LA FACTURA SELECCIONADA**//
        listaSerie(invoice.getNofactura());
        serieEditar = new Serie();
        this.valorMoneda = 0;
        RequestContext.getCurrentInstance().update("frmVerSeries:tblSeries");
        RequestContext.getCurrentInstance().update("frmVerSeries:mensajes");

    }

    public void eliminarFactura() {
        FacturaDao fDao = new FacturaDaoImp();
        fDao.deleteFactura(factura);
        factura = new Factura();
    }

    public void eliminarSerie() {
        SerieDao sDao = new SerieDaoImp();
        sDao.deleteSerie(serie);
        listaSeriesCargadas(serie.getFactura().getNofactura());
        serie = new Serie();
    }

    public void listarSeriesImpresion(String nofactura) {
        if (!nofactura.isEmpty()) {
            SerieDao sDao = new SerieDaoImp();
            listaSeriesImpresion = sDao.listaSeriesImprimir(nofactura);
        }
    }

    public void imprimirEtiquetas() {
        this.contador = 0;
        this.noFacturaImpresion = null;
        for (int i = 0; i < listaSeriesImpresion.size(); i++) {
            if (listaSeriesImpresion.get(i).getSeleccionar().equals(TRUE)) {
                this.etiqueta.generarQR(listaSeriesImpresion.get(i).getSerie(), listaSeriesImpresion.get(i).getFactura().getNofactura(),
                        listaSeriesImpresion.get(i).getArticulo(), listaSeriesImpresion.get(i).getDescripcion(), listaSeriesImpresion.get(i).getSerie(),
                        listaSeriesImpresion.get(i).getFactura().getProveedor(), listaSeriesImpresion.get(i).getPedimento(), listaSeriesImpresion.get(i).getAduana(),
                        listaSeriesImpresion.get(i).getFechapedimento().toString());
                SerieDao sDao = new SerieDaoImp();
                sDao.updateNoImpresion(listaSeriesImpresion.get(i).getSerie());
                this.noFacturaImpresion = listaSeriesImpresion.get(i).getFactura().getNofactura();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SISTEMA DE ETIQUETAS", "Etiqueta procesada con serie no: ".concat(listaSeriesImpresion.get(i).getSerie())));
            } else {
                this.contador++;
            }
        }
        listarSeriesImpresion(noFacturaImpresion);

        if (this.contador == listaSeriesImpresion.size()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "Selecciona etiquetas"));
        }
    }

}
