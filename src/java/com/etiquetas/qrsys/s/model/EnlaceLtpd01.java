package com.etiquetas.qrsys.s.model;

public class EnlaceLtpd01 implements java.io.Serializable {

    private int id;
    private Integer regLtpd;
    private Double cantidad;
    private Double pxrs;

    public EnlaceLtpd01() {
    }

    public EnlaceLtpd01(int id) {
        this.id = id;
    }

    public EnlaceLtpd01(int id, Integer regLtpd, Double cantidad, Double pxrs) {
        this.id = id;
        this.regLtpd = regLtpd;
        this.cantidad = cantidad;
        this.pxrs = pxrs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRegLtpd() {
        return regLtpd;
    }

    public void setRegLtpd(Integer regLtpd) {
        this.regLtpd = regLtpd;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPxrs() {
        return pxrs;
    }

    public void setPxrs(Double pxrs) {
        this.pxrs = pxrs;
    }

}
