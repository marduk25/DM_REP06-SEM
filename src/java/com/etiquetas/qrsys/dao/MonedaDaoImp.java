package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.Moned01;
import com.etiquetas.qrsys.util.HibernateUtilSae;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MonedaDaoImp implements Moned01Dao {

    @Override
    public List<Moned01> listarMonedas() {
        List<Moned01> lista = null;
        Session session = HibernateUtilSae.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Moned01 ORDER BY tcambio DESC";
        try {
            lista = session.createQuery(hql).list();
            t.commit();
            session.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

}
