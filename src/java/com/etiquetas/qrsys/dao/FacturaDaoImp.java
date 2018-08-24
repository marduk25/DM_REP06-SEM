package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.e.model.Factura;
import com.etiquetas.qrsys.util.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FacturaDaoImp implements FacturaDao {

    @Override
    public List<Factura> listaFactura() {
        List<Factura> lista = null;
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Factura WHERE tipo='Compra' AND subtipo='Entrada'";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }
    
  

    @Override
    public List<Factura> listaBuscarFactura(String nofactura) {
        List<Factura> lista = null;
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "SELECT idfactura FROM Factura WHERE nofactura='" + nofactura + "' ";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public Factura obtenerIdFactura(Factura factura) {
        Session session = HibernateUtil.getSessionfactory().openSession();
        String hql = "FROM Factura WHERE nofactura=:nofactura";
        Query<?> q = session.createQuery(hql);
        q.setParameter("nofactura", factura.getNofactura());
        return (Factura) q.uniqueResult();
    }

    @Override
    public Factura returnIdFactura(Factura factura) {
        Factura factId = this.obtenerIdFactura(factura);
        return factId;
    }

    @Override
    @SuppressWarnings({"null", "ConvertToTryWithResources"})
    public void saveFactura(Factura factura) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionfactory().openSession();
            session.beginTransaction();
            session.save(factura);
            session.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "SISTEMA DE ETIQUETAS", "Registro guardado"));
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "ERROR"));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    @SuppressWarnings({"null", "ConvertToTryWithResources"})
    public void updateFactura(Factura factura) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionfactory().openSession();
            session.beginTransaction();
            session.merge(factura);
            session.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "SISTEMA DE ETIQUETAS", "Registro actualizado"));
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "ERROR" + e.getMessage()));
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteFactura(Factura factura) {
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "DELETE  Factura  WHERE nofactura=:nofactura";
        Query<?> q = session.createQuery(hql);
        q.setParameter("nofactura", factura.getNofactura());
        try {
            q.executeUpdate();
            t.commit();
            session.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "SISTEMA DE ETIQUETAS", "Registro borrado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "SISTEMA DE ETIQUETAS", "ERROR" + e.getMessage()));
            t.rollback();
        }

    }

    @Override
    public List<Factura> listaFacturaVenta() {
        List<Factura> lista = null;
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Factura WHERE tipo='Venta' AND subtipo='Salida'";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public List<Factura> listaTraspasoEntrada() {
        List<Factura> lista = null;
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Factura WHERE tipo='Traspaso' AND subtipo='Entrada'";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public List<Factura> listaTraspasoSalida() {
        List<Factura> lista = null;
        Session session = HibernateUtil.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Factura WHERE tipo='Traspaso' AND subtipo='Salida'";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

   

}
