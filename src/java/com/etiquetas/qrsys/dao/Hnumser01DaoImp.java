package com.etiquetas.qrsys.dao;

import com.etiquetas.qrsys.s.model.Hnumser01;
import com.etiquetas.qrsys.util.HibernateUtilSae;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Hnumser01DaoImp implements Hnumser01Dao {

    @Override
    public List<Hnumser01> obtenerMaximoValor() {
        List<Hnumser01> lista = null;
        Session session = HibernateUtilSae.getSessionfactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            lista = session.createQuery("SELECT MAX(regSerie) +1 FROM Hnumser01").list();
            t.commit();
            session.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    @Override
    @SuppressWarnings("null")
    public void saveHmunser(Hnumser01 hnumser) {
        Session session = null;
        try {
            session = HibernateUtilSae.getSessionfactory().openSession();
            session.beginTransaction();
            session.save(hnumser);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

    }

}
