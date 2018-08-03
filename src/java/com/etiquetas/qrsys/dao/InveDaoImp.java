package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.Inve01;
import com.etiquetas.qrsys.util.HibernateUtilSae;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class InveDaoImp implements Inve01Dao {

    @Override
    public List<String> listaInve01(String articulo) {
        List<String> lista = null;
        Session session = HibernateUtilSae.sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        String hql = "SELECT descr FROM Inve01 WHERE conSerie='S' AND conPedimento='S' AND cveArt='" + articulo + "'";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
            session.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    public Inve01 obtenerArticuloExistente(Inve01 inve01) {
        Session session = HibernateUtilSae.getSessionfactory().openSession();
        String hql = "FROM Inve01 WHERE conSerie='S' AND conPedimento='S' AND cveArt=:articulo";
        Query<?> q = session.createQuery(hql);
        q.setParameter("articulo", inve01.getCveArt());
        return (Inve01) q.uniqueResult();
    }

    @Override
    public Inve01 returnArticuloExistente(Inve01 inve01) {
        Inve01 inve = this.obtenerArticuloExistente(inve01);
        return inve;
    }

}
